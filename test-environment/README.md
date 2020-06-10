# Test environment
This docker compose file was used to experiment with container images and to test the integration of all the components.
Running:
```bash
docker-compose up
```
Spins up:
+ ZooKeeper + Kafka
+ MQTT broker (mosquitto)
+ MQTT-to-Kafka connector bridging the "turbofan_data" topic from MQTT to Kafka
+ InfluxDB + Telegraf to ingest the "turbofan_prediction" Kafka topic to InfluxDB
+ The prediction image consuming the "turbofan_data" Kafka topic and producing to the "turbofan_prediction" topic.

Sending a well formatted (see [the prediction app](https://github.com/alessandropacielli/predictive-maintenance/tree/master/prediction)) JSON message to the "turbofan_data" MQTT topic should be processed by the prediction app.
