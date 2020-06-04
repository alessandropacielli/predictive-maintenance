import bisect
from typing import TypeVar, Generic, Callable

T = TypeVar('T')

class SortedCircularBuffer(Generic[T]):

  def __init__(self, n: int, sorting_view: Callable[[T], int]):
    super().__init__()
    self.n = n
    self.buffer = []
    self.sorting_view = sorting_view

  def add(self, value: T):
    insert_point = bisect.bisect(list(map(self.sorting_view, self.buffer)), self.sorting_view(value))
    self.buffer.insert(insert_point, value)

    if len(self.buffer) > self.n:
      self.buffer.pop(0)

  def get_n(self):
    return self.n

  def set_buffer(self, buffer: list):
    self.buffer = buffer
  
  def get_buffer(self) -> list:
    return self.buffer