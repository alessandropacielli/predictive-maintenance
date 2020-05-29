import faust

class TurbofanMeasurement(faust.Record):
  device: str
  data: dict

  def get_column_order(self):
    sensor_cols = ['s' + str(i) for i in range(1,22)]
    column_order = ['setting1', 'setting2', 'setting3', 'cycle']
    column_order.extend(sensor_cols)
    
#  cycle: int
#  setting1: float
#  setting2: float
#  setting3: float
#  s1: float
#  s2: float
#  s3: float
#  s4: float
#  s5: float
#  s6: float
#  s7: float
#  s8: float
#  s9: float
#  s10: float
#  s11: float
#  s12: float
#  s13: float
#  s14: float
#  s15: float
#  s16: float
#  s17: float
#  s18: float
#  s19: float
#  s20: float
#  s21: float
