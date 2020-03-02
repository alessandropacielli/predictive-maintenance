import paho.mqtt.client as mqtt
import os
import csv
import sys
import util.messages
import time

# TODO read from configuration / env
host = '192.168.1.138'
port = 1883
topic = '$hw/events/device/turbofan/twin/update'
path = os.path.join(os.path.dirname(__file__), '../datasets/PM_test_49.txt')
sensor_cols = ['s' + str(i) for i in range(1,22)]
sequence_cols = ['cycle', 'setting1', 'setting2', 'setting3']
sleep_time = 2
sequence_cols.extend(sensor_cols)
columns = sequence_cols

# Connect to mqtt broker
client = mqtt.Client()
client.connect(host, port)
client.loop_start()

# Initialize empty message body
msg = util.messages.UpdateMessage()

with open(path, 'r') as file:
  reader = csv.reader(file, delimiter=' ')
  for line in reader:
    time.sleep(sleep_time)
    values = list(filter(lambda x: x.strip(), line))
    for (i, col) in enumerate(columns):
      msg.add_attribute(col, values[i])
    client.publish(topic, msg.toJSON())