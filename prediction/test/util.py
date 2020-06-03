import numpy as np
import pandas as pd
import random

def sample_test_data():
  test_data_path = '../estimator/training/data/PM_test.txt'
  columns = ['id', 'cycle', 'setting1', 'setting2', 'setting3', 's1', 's2', 's3', 
    's4', 's5', 's6', 's7', 's8', 's9', 's10', 's11','s12', 's13', 's14', 
    's15', 's16', 's17', 's18', 's19','s20', 's21']
  sensor_cols = ['s' + str(i) for i in range(1,22)]
  sequence_cols = ['setting1', 'setting2', 'setting3', 'cycle']
  sequence_cols.extend(sensor_cols)

  # Load data
  df = pd.read_csv(test_data_path, sep=' ', header=None)
  df.drop(df[[26, 27]], axis=1, inplace=True)
  df.columns = columns
  df.sort_values(['id', 'cycle'], inplace=True)

  random_id = -1
  while len(df[df['id'] == random_id]) < 50:
    random_id = random.randint(1, df.max()['id'])
  
  random_data = df[df['id'] == random_id]
  return random_data[sequence_cols]
