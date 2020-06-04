import unittest
from prediction.state import SortedCircularBuffer
import random
from prediction.models.turbofan import TurbofanMeasurement
import time

class SortedCircularBufferTest(unittest.TestCase):
  
  def setUp(self):
    self.n = 100

  def test_add_sorted(self):
    buffer = SortedCircularBuffer[int](self.n, lambda x: x)
    for i in range(1000):
      buffer.add(i)
    
    assert buffer.get_buffer() == list(range(1000 - self.n, 1000))
  
  def test_add_sorted_inverse(self):
    buffer = SortedCircularBuffer[int](self.n, lambda x: x)
    for i in range(999, 0, -1):
      buffer.add(i)
    
    assert buffer.get_buffer() == list(range(1000 - self.n, 1000))

  def test_add_random_order(self):
    buffer = SortedCircularBuffer[int](self.n, lambda x: x)
    values = list(range(1000))
    sample = random.sample(values, 1000)

    for i in sample:
      buffer.add(i)
    
    assert buffer.get_buffer() == list(range(1000 - self.n, 1000))
  
  def test_sort_view(self):
    buffer = SortedCircularBuffer[TurbofanMeasurement](self.n, lambda x: x.timestamp)
    values = []

    for i in range(1000):
      data = dict()
      values.append(TurbofanMeasurement(device='1', data=data, timestamp=time.time_ns()))
      buffer.add(values[i])

    assert buffer.get_buffer() == values[1000 - self.n : 1000]
    

