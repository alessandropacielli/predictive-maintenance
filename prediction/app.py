import faust
from prediction.config import Config
from prediction.models.turbofan import TurbofanMeasurement
from prediction.models.prediction import PredictionEvent 
from prediction.preprocessing import PickledPreprocessor
from prediction.estimator import RnnEstimator
from prediction.state import SortedCircularBuffer
import sys
import pandas as pd
import numpy as np
import bisect
import time

config = Config()

app = faust.App(
  config.APP_NAME,
  broker=config.BROKER_LIST,
  stream_wait_empty=False,
  partitions=1
)

preprocessor = PickledPreprocessor(config.PREPROCESSOR_PATH)
estimator = RnnEstimator(config.ESTIMATOR_PATH)
N = estimator.get_sequence_length()

input_topic = app.topic(config.INPUT_TOPIC, value_type=TurbofanMeasurement)
output_topic = app.topic(config.OUTPUT_TOPIC, value_type=PredictionEvent)
state = app.Table(config.APP_NAME + '_state', default=list)

circular_buffer = SortedCircularBuffer[TurbofanMeasurement](N, lambda x: x['timestamp'])

@app.agent(input_topic)
async def handle(stream):
    async for measurement in stream.group_by(TurbofanMeasurement.device):
      circular_buffer.set_buffer(state[measurement.device])
      circular_buffer.add(measurement.to_dict())

      if circular_buffer.get_len() == N:
        df = pd.DataFrame(map(lambda x: x['data'], circular_buffer.get_buffer()))
        df = df[TurbofanMeasurement.get_column_order()]
        preprocessed = preprocessor.transform(df)

        data = np.reshape(preprocessed.to_numpy(), (1, N, 25))
        
        prediction = estimator.predict(data)

        output = PredictionEvent(timestamp=time.time_ns(), device=measurement.device, prediction=float(prediction[0][0]))

        await output_topic.send(value=output)

      state[measurement.device] = circular_buffer.get_buffer() # Saves current state to RocksDB and to Kafka

