from influxdb import InfluxDBClient
from datetime import datetime, timedelta
import unittest
import pandas as pd

class InfluxDBTest(unittest.TestCase):

  def setUp(self):
    # Create test database
    self.client = InfluxDBClient(host='127.0.0.1', port=8086)
    self.client.drop_database('test')
    self.client.create_database('test')
    
    # Create test measurements
    json_body = []
    today = datetime.now()
    for i in range(0, 100):
       json_body.append({
         'measurement': 'test',
         'time': today + timedelta(seconds=i),
         'fields': {
           'value': i
         }
       })
    
    self.test_df = pd.DataFrame(map(lambda x: x['fields'], json_body))

    self.client.write_points(json_body, database='test')
  
  def tearDown(self):
    # self.client.drop_database('test')
    pass

  def test_get_last_n_success(self):
    pass