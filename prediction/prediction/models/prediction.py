import faust

class PredictionEvent(faust.Record):
  timestamp: int
  device: str
  prediction: float   
