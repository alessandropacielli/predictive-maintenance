# Kubernetes deployment
The project is designed to be deployed on Kubernetes, this folder contains the necessary configuration files to do so. The app was tested on a 2 nodes K3s cluster installed in two VirtualBox VMs running Ubuntu Server 18.04. 

![VMs](https://github.com/alessandropacielli/predictive-maintenance/blob/master/docs/images/vms.png)

## Prerequisites
+ Kubernetes cluster and configure kubectl and helm v3.
+ The nodes should have access to the Internet.
+ MQTT broker, in the examples we assume it's running on the k3s-server (192.168.2.2:1883)

## Kafka
ZooKeeper and Kafka are installed (each runs in its own pod) with the following command:
```bash
kubectl create -f kafka
```

Wait until the deployments are ready then proceed to the next step.

## MQTT to Kafka
To bridge MQTT and Kafka, run the (simple-kafka-mqtt-connector)[https://hub.docker.com/r/arthurgrigo/simple-kafka-mqtt-connector] with:
```bash
kubectl create -f mqtt2Kafka
```
## Prediction Agent
The agent running the prediction logic is not pushed on dockerhub. In order to deploy it to Kubernetes you need to push the image onto a Docker registry.

I ran a local registry on my host machine using Docker:
```bash
docker run -d -p 5000:5000 --restart always --name registry registry:2
```

Now cd into the [prediction](https://github.com/alessandropacielli/predictive-maintenance/tree/master/prediction) folder then we can build the prediction image, tag it adequately and push it to the registry:
```bash
docker build . -t 192.168.2.1:5000/prediction
docker push 192.168.2.1:5000/prediction
```
(I used 192.168.2.1 because in my example the registry is running on the host machine, change the URL according to your configuration)

If you use a local registry, make sure to enable it in __every__ Kubernetes worker node. This is fairly easy [with k3s](https://rancher.com/docs/k3s/latest/en/installation/private-registry/).

## TICK stack
The alerting and monitoring strategy is implemented with the [TICK stack](https://www.influxdata.com/blog/introduction-to-influxdatas-influxdb-and-tick-stack/). To deploy the tools on Kubernetes I used helm:
```bash
helm repo add influxdata https://helm.influxdata.com/
helm install influxdb influxdata/influxdb
helm install -f tick/telegraf.yaml telegraf influxdata/telegraf
helm install -f tick/kapacitor.yaml kapacitor influxdata/kapacitor
helm install chronograf influxdata/chronograf
```

Chronograf's web interface can be exposed on the kubernetes control plane machine:
```bash
kubectl port-forward --namespace default $(kubectl get pods --namespace default -l app=chronograf-chronograf -o jsonpath='{ .items[0].metadata.name }') 8888
```
If the virtual machine is headless (like mine) and you want to access the console from the VM host, you can forward port 8888 using ssh. From the host machine run:
```bash
ssh -L 8888:localhost:8888 192.168.2.2
```
Now you can navigate to http://localhost:8888 and configure Chronograf to your needs.
