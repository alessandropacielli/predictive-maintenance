from tensorflow.keras.models import load_model
import numpy as np

class RnnEstimator():

  def __init__(self, model_path: str):
    super().__init__()
    self.model = load_model(model_path)

  def get_sequence_length(self):
    return self.model.input_shape[1]

  def predict(self, input: np.ndarray):
    return self.model.predict(input)
