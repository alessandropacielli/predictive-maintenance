import paho.mqtt.client as mqtt
import time
import pandas as pd
import json
import argparse
import logging
import threading
import random

# Parse CLI arguments
parser = argparse.ArgumentParser(description='Mapper. Sends dataset data to an MQTT topic at fixed intervals.')
parser.add_argument('--host', help='MQTT host', type=str, default='localhost')
parser.add_argument('--port', help='MQTT port', type=int, default=1883)
parser.add_argument('-t', '--topic', help='MQTT topic to send data to', type=str, default='turbofan_data')
parser.add_argument('-s', '--sleep', help='Sleep time', type=float, default=1.0)
parser.add_argument('--dataset', help='CSV file to use as dataset', type=str, default='./datasets/over_100.csv')
parser.add_argument('-n', '--number', help='Number of concurrent devices sending data', type=int, default=10)

args = parser.parse_args()

# Mapper config
sleep_time = args.sleep
number = args.number

# MQTT config
host = args.host
port = args.port
topic = args.topic

# Connect to mqtt broker
client = mqtt.Client()
client.connect(host, port)
client.loop_start()

# Dataset config
path = args.dataset

# Data columns
sensor_columns = ['s' + str(i) for i in range(1,22)]
data_columns = ['setting1', 'setting2', 'setting3', 'cycle']
data_columns.extend(sensor_columns) 

format = "%(asctime)s: %(message)s"
logging.basicConfig(format=format, level=logging.INFO, datefmt="%H:%M:%S")

# Read test data
test_df = pd.read_csv(path) 

# Extract ids at random
ids = list(test_df['id'].sample(n=number))

def run_mapper(df, device_id):
	logging.info("Device %s: starting", device_id)
	df = df[df['id'] == device_id]
	for k, row in df.iterrows(): 
	  message = dict()
	  message['device'] = str(row['id'])
	  message['timestamp'] = int(time.time())
	  data = dict(row[data_columns]) 
	  message['data'] = data
	  time.sleep(sleep_time)
	
	  serialized_msg = json.dumps(message)
          # logging.info(serialized_msg)
	  client.publish(topic, serialized_msg)

	logging.info("Device %s: stopping", device_id)

for i in range(number):
	x = threading.Thread(target=run_mapper, args=(test_df, ids[i]))
	x.start()
