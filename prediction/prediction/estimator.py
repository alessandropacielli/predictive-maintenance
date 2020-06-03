from tensorflow.keras.models import load_model
import numpy as np

class RnnEstimator():
  """
  Loads a keras model serialized in hdf5 format
  """

  def __init__(self, model_path: str):
    super().__init__()
    self.model = load_model(model_path)

  def get_sequence_length(self):
    """
    Returns the number of instances the input needs to produce an output
    """
    return self.model.input_shape[1]

  def predict(self, input: np.ndarray):
    """
    Evaluates input and returns a prediction
    """
    return self.model.predict(input)
