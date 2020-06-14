# Mapper
Simple python script that loads a turbofan dataset, chooses a device at random and sends its measurements to an MQTT topic.

```
usage: main.py [-h] [--host HOST] [--port PORT] [-t TOPIC] [-s SLEEP]
               [--dataset DATASET] [-n NUMBER]

Mapper. Sends dataset data to an MQTT topic at fixed intervals.

optional arguments:
  -h, --help            show this help message and exit
  --host HOST           MQTT host
  --port PORT           MQTT port
  -t TOPIC, --topic TOPIC
                        MQTT topic to send data to
  -s SLEEP, --sleep SLEEP
                        Sleep time
  --dataset DATASET     CSV file to use as dataset
  -n NUMBER, --number NUMBER
                        Number of concurrent devices sending data
```
