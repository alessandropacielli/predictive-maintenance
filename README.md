# Distributed systems project work
This repo contains code and configuration for my project work in Distributed Sistems developed during my last year of MSc in Computer Science at the University of Bologna.

The goal is to build a stream processing application in the domain of predictive-maintenance and deploy it to k3s in an industrial edge computing scenario.

![Architecture](https://github.com/alessandropacielli/predictive-maintenance/raw/master/docs/images/architecture.png "Architecture")

## Folders
The project is divided in the following folders:
+ [training](https://github.com/alessandropacielli/predictive-maintenance/tree/master/training): jupyter notebook used to train the prediction model, dataset and a dump of the model
+ [prediction](https://github.com/alessandropacielli/predictive-maintenance/tree/master/prediction): a Faust stream processing application used to process the events on a Kafka topic using the prediction model
+ [mappers](https://github.com/alessandropacielli/predictive-maintenance/tree/master/mappers): a simple python script that publishes sample data to an MQTT topic
+ [test-environment](https://github.com/alessandropacielli/predictive-maintenance/tree/master/test-environment): a docker-compose file to test the application
+ [kubernetes](https://github.com/alessandropacielli/predictive-maintenance/tree/master/kubernetes): configuration files used to deploy to kubernetes or k3s
+ [tests](https://github.com/alessandropacielli/predictive-maintenance/tree/master/tests): performance tests
