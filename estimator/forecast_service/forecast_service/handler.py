import numpy as np

class Handler():
  """
  The Handler class provides a function for handling forecast requests, producing a RUL forecast on the appropriate topic 
  """
  def __init__(self, estimator, db, event_sink):
    super().__init__()
    self.estimator = estimator
    self.db = db
    self.event_sink = event_sink
  
  def handle(self):
    seq_len = self.estimator.get_sequence_length()
    last_measurements = self.db.get_last(seq_len)
    prediction = self.estimator.predict(np.reshape(last_measurements, (1, seq_len, last_measurements.shape[1])))
    self.event_sink.publish(prediction)
