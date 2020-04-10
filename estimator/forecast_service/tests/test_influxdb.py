from influxdb import InfluxDBClient
from datetime import datetime, timedelta
import unittest
import pandas as pd
from forecast_service.db import InfluxDBPersistence
import numpy as np

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
         'measurement': 'normalized',
         'time': today + timedelta(seconds=i),
         'tags': {
           'device': 'test'
         },
         'fields': {
           'value': i
         }
       })
    
    self.test_df = pd.DataFrame(map(lambda x: x['fields'], json_body))

    self.client.write_points(json_body, database='test')
  
  def tearDown(self):
    self.client.drop_database('test')
    pass

  def test_get_last_n_success(self):
    db = InfluxDBPersistence('127.0.0.1', 8086, 'test')
    n = 50
    last_n = db.get_last(n, 'test')
    np.testing.assert_equal(last_n.values, self.test_df[-n:].values)