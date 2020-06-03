import joblib
import pandas as pd

class PickledPreprocessor():
  def __init__(self, path):
    super().__init__()
    self.scaler = joblib.load(path)


  def transform(self, data):
    normalized_data = pd.DataFrame(
      self.scaler.fit_transform(data), 
      index=data.index
    )

    return normalized_data
