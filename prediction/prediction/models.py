import faust

class TurbofanMeasurement(faust.Record):
  device: str
  data: dict

  def get_column_order(self):
    sensor_cols = ['s' + str(i) for i in range(1,22)]
    column_order = ['setting1', 'setting2', 'setting3', 'cycle']
    column_order.extend(sensor_cols)
