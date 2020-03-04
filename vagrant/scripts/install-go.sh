wget https://dl.google.com/go/go1.13.8.linux-amd64.tar.gz -O /tmp/go1.13.8.linux-amd64.tar.gz
tar -C /usr/local -zxf /tmp/go1.13.8.linux-amd64.tar.gz
echo 'export PATH=$PATH:/usr/local/go/bin' >> /etc/profile
echo 'export GOPATH=~/go' >> ~/.bashrc
