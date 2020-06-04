import joblib
import pandas as pd

from sklearn.preprocessing import MinMaxScaler as SklearnMinMaxScaler

class MinMaxScaler():

  def __init__(self, path: str, columns: list):
    super().__init__()
    self.scaler = SklearnMinMaxScaler()

    columns = ['id', 'cycle', 'setting1', 'setting2', 'setting3', 's1', 's2', 's3',
              's4', 's5', 's6', 's7', 's8', 's9', 's10', 's11', 's12', 's13', 's14',
              's15', 's16', 's17', 's18', 's19', 's20', 's21']

    train_df = pd.read_csv(path, sep=' ', header=None)
    train_df.drop(train_df[[26, 27]], axis=1, inplace=True)
    train_df.columns = columns
    train_df.drop('id', axis=1, inplace=True)

    # Fit
    self.scaler.fit_transform(train_df)

  def transform(self, data: pd.DataFrame):
    """
    Accepts a dataframe and transforms it 
    """
    normalized_data = pd.DataFrame(
      self.scaler.transform(data), 
      index=data.index
    )

    return normalized_data


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
