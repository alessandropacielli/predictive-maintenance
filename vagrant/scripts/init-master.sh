kubeadm init --pod-network-cidr=192.168.50.0/24 --apiserver-advertise-address=192.168.1.137
mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config
kubectl apply -f ./conf/calico.yaml
