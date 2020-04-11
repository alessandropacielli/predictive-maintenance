import unittest
from forecast_service.estimator import RnnEstimator

class RnnEstimatorTest(unittest.TestCase):

  def test_get_sequence_length_v1(self):
    model_path_v1 = './resources/models/rul_regression_v1.h5'
    estimator = RnnEstimator(model_path_v1)
    assert estimator.get_sequence_length() == 50

