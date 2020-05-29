import os
from dotenv import load_dotenv

class Config:

  def __init__(self):
    super().__init__()

    load_dotenv('./.env')

    self.APP_NAME = os.getenv('APP_NAME')    
    self.BROKER_LIST = os.getenv('BROKER_LIST')
    self.INPUT_TOPIC = os.getenv('INPUT_TOPIC')
    self.OUTPUT_TOPIC = os.getenv('OUTPUT_TOPIC')
    self.PREPROCESSOR_PATH = os.getenv('PREPROCESSOR_PATH')
    self.ESTIMATOR_PATH = os.getenv('ESTIMATOR_PATH')
