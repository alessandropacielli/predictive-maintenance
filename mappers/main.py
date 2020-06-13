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

# Data columns
sensor_columns = ['s' + str(i) for i in range(1,22)]
data_columns = ['setting1', 'setting2', 'setting3', 'cycle']
data_columns.extend(sensor_columns) 

# Read test data
test_df = pd.read_csv(path) 
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
  # client.publish(topic, serialized_msg)
