import os
from dotenv import load_dotenv

class Config:

  def __init__(self):
    super().__init__()

    load_dotenv('./.env')

    self.MQTT_HOST = os.getenv('MQTT_HOST')
    self.MQTT_PORT = int(os.getenv('MQTT_PORT'))
    self.MQTT_TOPIC = os.getenv('MQTT_TOPIC')
    self.DATASET_PATH = os.getenv('DATASET_PATH')
    self.DEVICE_ID = int(os.getenv('DEVICE_ID'))
    self.SLEEP_TIME = int(os.getenv('SLEEP_TIME'))
