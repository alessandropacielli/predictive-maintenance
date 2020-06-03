import faust
from prediction.config import Config
from prediction.models.turbofan import TurbofanMeasurement
from prediction.models.prediction import PredictionEvent 
from prediction.preprocessing import PickledPreprocessor
from prediction.estimator import RnnEstimator
import sys
import pandas as pd
import numpy as np
import bisect
import time

config = Config()

app = faust.App(
  config.APP_NAME,
  broker=config.BROKER_LIST,
  stream_wait_empty=False
)

preprocessor = PickledPreprocessor(config.PREPROCESSOR_PATH)
estimator = RnnEstimator(config.ESTIMATOR_PATH)
N = estimator.get_sequence_length()

input_topic = app.topic(config.INPUT_TOPIC, value_type=TurbofanMeasurement)
output_topic = app.topic(config.OUTPUT_TOPIC, value_type=PredictionEvent)
state = app.Table(config.APP_NAME + '_state', default=list)

@app.agent(input_topic)
async def handle(stream):
    async for measurement in stream.group_by(TurbofanMeasurement.device):
      device_buffer = state[measurement.device]

      insert_point = bisect.bisect(list(map(lambda x: x['timestamp'], device_buffer)), measurement.data['timestamp'])
      device_buffer.insert(insert_point, measurement.data)      

      if len(device_buffer) > N:
        device_buffer.pop(0)
        df = pd.DataFrame(device_buffer)
        df = df[TurbofanMeasurement.get_column_order()]
        preprocessed = preprocessor.transform(df)

        data = np.reshape(preprocessed.to_numpy(), (1, N, 25))
        
        prediction = estimator.predict(data)

        output = PredictionEvent(timestamp=time.time_ns(), device=measurement.device, prediction=float(prediction[0][0]))

        await output_topic.send(value=output)


      state[measurement.device] = device_buffer

