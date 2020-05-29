import joblib

class PickledPreprocessor():
  def __init__(self, path):
    super().__init__()
    self.scaler = joblib.load(path)


  def transform(self, data):
    return self.scaler.transform(data)
