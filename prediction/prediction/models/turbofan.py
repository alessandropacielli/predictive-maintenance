import faust

class TurbofanMeasurement(faust.Record, serializer='json'):
  device: str
  data: dict

def get_column_order():
  sensor_cols = ['s' + str(i) for i in range(1,22)]
  column_order = ['setting1', 'setting2', 'setting3', 'cycle']
  column_order.extend(sensor_cols)
  return column_order

TurbofanMeasurement.get_column_order = staticmethod(get_column_order)