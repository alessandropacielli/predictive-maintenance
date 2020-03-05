import json

# Data format:
# {
#     "event_id":"",
#     "timestamp": 0,
#     "twin": {
#         "temperature-status":{
#             "actual": {
#                 "value":"4C"
#             },
#         }
#     }
# }

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