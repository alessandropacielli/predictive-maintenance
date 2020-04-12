from kafka import KafkaProducer
from kafka.errors import KafkaError
from abc import ABC, abstractmethod

class EventPublisher(ABC):

  @abstractmethod
  def publish(self, prediction):
    pass

class KafkaEventPublisher():

  """
  Simple wrapper around KafkaProducer
  """
  def __init__(self, topic, **configs):
    super().__init__()
    self.topic = topic
    self.producer = KafkaProducer(**configs)

  def __del__(self):
    self.producer.close()

  def publish(self, prediction):
    self.producer.send(self.topic, prediction)