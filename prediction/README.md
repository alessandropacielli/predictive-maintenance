# Prediction Agent
This is a [Faust](https://faust.readthedocs.io/) stateful stream processing agent, collecting events from a Kafka topic and processing them using a recurrent neural network.

![Prediction stream processing agent](https://github.com/alessandropacielli/predictive-maintenance/raw/master/docs/images/prediction.png "Prediction stream processing agent")

Dependencies are managed with [Poetry](https://python-poetry.org/).

To run the agent make sure Kafka is running, then run the following command from this directory: 
```bash
pip install -r requirements.txt
faust -A app -l info
```

## Configuration

The agent can be configured with the following environment variables (or with a .env file):
+ APP_NAME: name of the Kafka consumer group
+ BROKER_LIST: URL of the Kafka broker to be used
+ INPUT_TOPIC: topic to be consumed
+ OUTPUT_TOPIC: topic where the prediction will be published
+ PREPROCESSOR_PATH: path to a scikit-learn preprocessor pipeline used to normalize the data
+ ESTIMATOR_PATH: path to a recurrent neural network implemented in Keras and serialized in HDF5 format

## Input / Output formats
The agent expects JSON formatted data on the INPUT_TOPIC and produces JSON results on the OUTPUT_TOPIC.
Input format example:
```javascript
{
    "device": "49",
    "timestamp": 1591095276,
    "data": {
        "setting1": -0.0020, 
        "setting2": -0.0003, 
        "setting3": 100.0,
        "cycle": 1,
        "s1": 518.67,
        "s2": 642.19,
        "s3": 1587.28,
        "s4": 1405.05, 
        "s5": 14.62,
        "s6": 21.61, 
        "s7": 554.28, 
        "s8": 2387.99, 
        "s9": 9063.06, 
        "s10": 1.30, 
        "s11": 47.37,
        "s12": 522.21, 
        "s13": 2388.02,
        "s14": 8139.40, 
        "s15": 8.3809, 
        "s16": 0.03, 
        "s17": 391, 
        "s18": 2388, 
        "s19": 100.00,
        "s20": 38.95,
        "s21": 23.3610
    }
}
```

Output format example:
```javascript
{
    "timestamp": 1591095890, 
    "device": "1", 
    "prediction": 2.139801263809204
}
```
