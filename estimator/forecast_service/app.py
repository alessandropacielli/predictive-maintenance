from flask import Flask, request
from forecast_service.estimator import RnnEstimator
from forecast_service.normalization import PickledScaler
from forecast_service.db import InfluxDB

app = Flask(__name__)

db = InfluxDB('localhost', 8086, 'predictive-maintenance')
estimator = RnnEstimator('./resources/models/rul_regression_v1.h5')
preprocessor = PickledScaler('./resources/preprocessors/turbofan_scaler.pkl')

sensor_cols = ['s' + str(i) for i in range(1,22)]
sequence_cols = ['setting1', 'setting2', 'setting3', 'cycle']
sequence_cols.extend(sensor_cols)

@app.route('/turbofan', methods=['GET', 'POST'])
def turbofan():
  device = int(request.args['device'])
  measurement = request.args['measurement']
  data = db.get_last(estimator.get_sequence_length(), device, measurement, order=sequence_cols)
  data_norm = preprocessor.transform(data)
  return estimator.predict(data_norm)
