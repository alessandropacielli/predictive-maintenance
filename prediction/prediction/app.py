import faust
from config import Config
from models import TurbofanMeasurement
from preprocessing import PickledPreprocessor
from estimator import RnnEstimator
import pandas as pd
import bisect

config = Config()

app = faust.App(
  config.APP_NAME,
  broker=config.BROKER_LIST,
  stream_wait_empty=False
)

preprocessor = PickledPreprocessor(config.PREPROCESSOR_PATH)
estimator = RnnEstimator(config.ESTIMATOR_PATH)
N = estimator.get_sequence_length()

input_topic = app.topic(config.INPUT_TOPIC, key_type=int, value_type=TurbofanMeasurement)
state = app.Table(config.APP_NAME + '_state', default=list)

@app.agent(input_topic)
async def handle(stream):
    async for measurement in stream.group_by(TurbofanMeasurement.device):
      device_buffer = state[measurement.device]

      insert_point = bisect.bisect(list(map(lambda x: x['timestamp'], device_buffer)), measurement.data['timestamp'])
      device_buffer.insert(insert_point, measurement.data)      
      
      if len(device_buffer) > N:
        device_buffer.pop(0)
        df = pd.DataFrame(device_buffer)[TurbofanMeasurement.column_order]
        df = preprocessor.transform(df)

        prediction = estimator.predict(df)
      
      state[measurement.device] = device_buffer