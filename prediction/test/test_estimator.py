import unittest
from prediction.estimator import RnnEstimator
from prediction.preprocessing import PickledPreprocessor
from keras.models import load_model
import random
from util import sample_test_data
import pandas as pd
import numpy as np


class RnnEstimatorTest(unittest.TestCase):

  def setUp(self):
    self.model_path_v1 = './resources/prediction/rul_regression_v1.h5'
    self.sequence_length = 50
    self.estimator = RnnEstimator(self.model_path_v1)
    self.preprocessor = PickledPreprocessor('./resources/preprocessing/turbofan_scaler.pkl')

  def test_get_sequence_length(self):
    """
    Checks the returned sequence length is right
    """
    assert self.estimator.get_sequence_length() == self.sequence_length

  def test_predictions_match(self):
    """
    Checks the RnnEstimator behaves as it should
    """
    # Load model from path
    loaded_model = load_model(self.model_path_v1)
    sequence_length = self.estimator.get_sequence_length()

    # Obtain sample data
    random_data = sample_test_data()

    # Preprocess sample data
    random_data = pd.DataFrame(self.preprocessor.transform(random_data))
    len_random_data = len(random_data)

    # Obtain sequences from sample data
    random_data_sequences = np.array([random_data[start:stop].values for start, stop in zip(range(0, len_random_data - sequence_length), range(sequence_length, len_random_data))])

    # Assert prediction of keras model is the same as the estimator
    np.testing.assert_equal(self.estimator.predict(random_data_sequences), loaded_model.predict(random_data_sequences))

    