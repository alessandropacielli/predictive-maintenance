import json

class UpdateMessage():

  def __init__(self):
    super().__init__()
    self.msg = dict()
    self.msg['twin'] = dict()

  def add_attribute(self, attribute, value):
    self.msg['twin'][attribute] = {
        'actual': {
          'value': str(value)
        }
      }

  def toJSON(self):
    return json.dumps(self.msg)