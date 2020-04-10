from influxdb import DataFrameClient

class InfluxDBPersistence():

  def __init__(self, host, port, database):
    super().__init__()
    self.client = DataFrameClient(host=host, port=port, database=database)

  def get_last(self, n, device):
    query = 'SELECT * FROM normalized WHERE device=$device ORDER BY time DESC LIMIT ' + str(int(n)) 
    return self.client.query(query, bind_params={'device': device})['normalized'].drop('device', axis=1)
