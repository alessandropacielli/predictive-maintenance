import unittest
from forecast_service.estimator import RnnEstimator

class EstimatorTest(unittest.TestCase):

  def setUp(self):
    self.model_path_v1 = './resources/models/rul_regression_v1.h5'

  def test_get_sequence_length_known_model(self):
    estimator = RnnEstimator(self.model_path_v1)
    assert estimator.get_sequence_length() == 50

