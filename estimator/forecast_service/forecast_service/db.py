from influxdb import DataFrameClient

class InfluxDB():

  def __init__(self, host, port, database, measurement):
    super().__init__()
    self.client = DataFrameClient(host=host, port=port, database=database)
    self.measurement = measurement

  def get_last(self, n, device, order=None):
    query = 'SELECT * FROM ' + str(self.measurement) + ' WHERE device=$device ORDER BY time DESC LIMIT ' + str(int(n)) 
    result = self.client.query(query, bind_params={'device': device})[self.measurement].drop('device', axis=1)

    if order is not None:
      result = result[order]

    return result
