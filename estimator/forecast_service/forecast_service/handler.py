import numpy as np

class Handler():

  def __init__(self, estimator, db, event_sink):
    self.estimator = estimator
    self.db = db
    self.event_sink = event_sink
  
  def handle(self):
    seq_len = self.estimator.get_sequence_length()
    last_measurements = self.db.get_last(seq_len)
    prediction = self.estimator.predict(np.reshape(last_measurements, (1, seq_len, last_measurements.shape[1])))
    self.event_sink.publish(prediction)
