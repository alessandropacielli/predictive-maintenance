import paho.mqtt.client as mqtt
import os
import csv
import sys
import time
from config import Config
import pandas as pd
import json

config = Config()

sleep_time = config.SLEEP_TIME

# MQTT config
host = config.MQTT_HOST
port = config.MQTT_PORT
topic = config.MQTT_TOPIC

# Connect to mqtt broker
client = mqtt.Client()
client.connect(host, port)
client.loop_start()

# Dataset config
path = config.DATASET_PATH
device_id = config.DEVICE_ID

columns = ['id', 'cycle', 'setting1', 'setting2', 'setting3', 's1', 's2', 's3', 
  's4', 's5', 's6', 's7', 's8', 's9', 's10', 's11','s12', 's13', 's14', 
  's15', 's16', 's17', 's18', 's19','s20', 's21']

sensor_columns = ['s' + str(i) for i in range(1,22)]
data_columns = ['setting1', 'setting2', 'setting3', 'cycle']
data_columns.extend(sensor_columns) 

test_df = pd.read_csv(path, sep=' ', header=None) 
test_df.drop(test_df[[26, 27]], axis=1, inplace=True) 
test_df.columns = columns 
test_df.sort_values(['id', 'cycle'], inplace=True)
test_df = test_df[test_df['id'] == device_id]

for k, row in test_df.iterrows(): 
  message = dict()
  message['device'] = str(row['id'])
  message['timestamp'] = int(time.time())
  data = dict(row[data_columns]) 
  message['data'] = data
  time.sleep(sleep_time)

  serialized_msg = json.dumps(message)
  print(serialized_msg)
  client.publish(topic, serialized_msg)