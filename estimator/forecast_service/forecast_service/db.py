from abc import ABC, abstractmethod

class DB(ABC):

  @abstractmethod
  def get_last(self, n, device):
    pass

class InfluxDB(DB):

  def __init__(self, host, port, database):
    from influxdb import DataFrameClient
    super().__init__()
    self.client = DataFrameClient(host=host, port=port, database=database)

  def get_last(self, n, device):
    query = 'SELECT * FROM normalized WHERE device=$device ORDER BY time DESC LIMIT ' + str(int(n)) 
    return self.client.query(query, bind_params={'device': device})['normalized'].drop('device', axis=1)
