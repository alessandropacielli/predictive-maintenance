from influxdb import DataFrameClient

class InfluxDB():

  def __init__(self, host, port, database):
    super().__init__()
    self.client = DataFrameClient(host=host, port=port, database=database)

  def get_last(self, n, device, measurement, order=None):
    query = 'SELECT * FROM ' + str(measurement) + ' WHERE device=$device ORDER BY time DESC LIMIT ' + str(int(n)) 
    result = self.client.query(query, bind_params={'device': device})[measurement].drop('device', axis=1)
    print(result)

    if order is not None:
      result = result[order]

    return result
