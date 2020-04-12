from abc import ABC, abstractmethod

class Estimator(ABC):

  @abstractmethod
  def predict(self, input):
    pass

class RnnEstimator():

  def __init__(self, model_path):
    from keras.models import load_model
    super().__init__()
    self.model = load_model(model_path)

  def get_sequence_length(self):
    return self.model.input_shape[1]

  def predict(self, input):
    return self.model.predict(input)
