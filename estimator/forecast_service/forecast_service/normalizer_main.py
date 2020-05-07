from kafka import KafkaConsumer, KafkaProducer
import json
from forecast_service.normalization import PickledScaler
import numpy as np

consumer = KafkaConsumer('preprocess', bootstrap_servers=['localhost:9092'], group_id='preprocessing', value_deserializer=lambda m: json.loads(m.decode('ascii')))
producer = KafkaProducer(bootstrap_servers=['localhost:9092'])
preprocessor = PickledScaler('./resources/preprocessors/turbofan_scaler.pkl')

for msg in consumer:
  preprocessed = preprocessor.transform(np.array(msg.value['data'])).tolist()
  result = { 
    'device_id': msg.value['device_id'],
    'data': preprocessed 
  }
  producer.send('predict', value=bytearray(json.dumps(result), 'UTF-8'))
  print('Sent!')