import unittest
import random
import numpy as np
from unittest.mock import Mock
from forecast_service.handler import Handler


class HandlerTest(unittest.TestCase):

  """
  This is an integration test for the business logic of the prediction service
  """

  def setUp(self):
    self.seq_len = 50
    self.attributes = 25
    self.input_shape = (1, self.seq_len, self.attributes)
    self.test_device = "test"

  def test_handler_calls(self):
    estimator = Mock()
    db = Mock()
    event_sink = Mock()

    # Generate fake data
    db_data = np.random.rand(self.seq_len, self.attributes)
    prediction = random.random()

    # Create mocks
    db.get_last = Mock(return_value=db_data)
    estimator.get_sequence_length = Mock(return_value=self.seq_len)
    estimator.predict = Mock(return_value=prediction)
    event_sink.publish = Mock()

    # Initialize and run component under test
    handler = Handler(estimator, db, event_sink)
    handler.handle(self.test_device)    

    # Assertions
    # The handlert should:
    # 1 - Obtain the sequence lenght N required by the estimator
    # 2 - Obtain the last N data points from the database
    # 3 - Call the predict method of the estimator to obtain a prediction
    # 4 - publish the result back to a topic
    estimator.get_sequence_length.assert_called_once()
    db.get_last.assert_called_with(self.seq_len, self.test_device)
    np.testing.assert_array_equal(np.reshape(db_data, (1, self.seq_len, self.attributes)), estimator.predict.call_args[0][0])
    event_sink.publish.assert_called_with(prediction)

if __name__ == '__main__':
  unittest.main()  