import pandas as pd
import time
import prediction.models.turbofan as models
import asyncio
import json
import kafka
import time
import keras
import joblib

producer = kafka.KafkaProducer(
  bootstrap_servers=['localhost:9092'],
  value_serializer=lambda m: json.dumps(m).encode('ascii')
)

model_path = '../estimator/training/models/regression_model_v1.h5'
model = keras.models.load_model(model_path)

preprocessing_path = './resources/preprocessing/turbofan_scaler.pkl'
preprocessing = joblib.load(preprocessing_path)

test_path = '../estimator/training/data/PM_train.txt'
columns = ['id', 'cycle', 'setting1', 'setting2', 'setting3', 's1', 's2', 's3', 
  's4', 's5', 's6', 's7', 's8', 's9', 's10', 's11','s12', 's13', 's14', 
  's15', 's16', 's17', 's18', 's19','s20', 's21']

test_df = pd.read_csv(test_path, sep=' ', header=None) 
test_df.drop(test_df[[26, 27]], axis=1, inplace=True) 
test_df.columns = columns 
test_df.sort_values(['id', 'cycle'], inplace=True)
test_49 = test_df[test_df['id'] == 49]

j = 0
for k, i in test_49.iterrows(): 
  data = dict(i) 
  data['timestamp'] = time.time_ns()

  message = dict()
  message['device'] = '1'
  message['data'] = data
  
  producer.send('turbofan_data', message)
  print('dio')
