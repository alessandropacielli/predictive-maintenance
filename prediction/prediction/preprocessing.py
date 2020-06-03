import joblib
import pandas as pd

class PickledPreprocessor():
  """
  Util class for loading a pickled scikit-learn scaler and transforming pandas dataframes with it
  """
  def __init__(self, path: str):
    super().__init__()
    self.scaler = joblib.load(path)


  def transform(self, data: pd.DataFrame):
    """
    Accepts a dataframe and transforms it 
    """
    normalized_data = pd.DataFrame(
      self.scaler.fit_transform(data), 
      index=data.index
    )

    return normalized_data
