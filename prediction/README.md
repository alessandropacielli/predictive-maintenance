# Prediction Agent
This is a [Faust](https://faust.readthedocs.io/) stateful stream processing agent, collecting events from a Kafka topic and processing them using a recurrent neural network.

Dependencies are managed with [Poetry] (https://python-poetry.org/).

To run the agent make sure Kafka is running, then run the following command from this directory: 
```bash
pip install -r requirements.txt
faust -A app -l info
```

The agent can be configured with the following environment variables (or with a .env file):
+ APP_NAME: name of the Kafka consumer group
+ BROKER_LIST: URL of the Kafka broker to be used
+ INPUT_TOPIC
+ OUTPUT_TOPIC
+ PREPROCESSOR_PATH: path to a scikit-learn preprocessor pipeline used to normalize the data
+ ESTIMATOR_PATH: path to a recurrent neural network implemented in Keras and serialized in HDF5 format
