from influxdb import InfluxDBClient
from datetime import datetime, timedelta
import unittest
import pandas as pd
from forecast_service.db import InfluxDBPersistence
import numpy as np
import unittest
import pytest


class TestDB(object):
  """
  "Abstract" test case, unittest for actual database implementations should inherit from TestDB and 
  define the "self.db" attribute in the setUp method
  """

  @pytest.mark.skip('Abstract')
  def test_get_last_n_success(self):
    n = 50
    last_n = self.db.get_last(n, 'test')
    np.testing.assert_equal(last_n.values, self.test_df[-n:].values)

class TestInfluxDB(unittest.TestCase, TestDB):

  def test_get_last_n_success(self):
    return super().test_get_last_n_success()

  def _create_instance(self):
    super()._create_instance()
    self.asbstract = False

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

    self.db = InfluxDBPersistence('127.0.0.1', 8086, 'test')
  
  def tearDown(self):
    self.client.drop_database('test')
