#!/bin/bash

user=vagrant
version=1.13.10

while getopts ":v:u" opt; do
  case ${opt} in
    v ) # process version
      version=${OPTARG}
      ;;
    u ) # process user
      user=${OPTARG}
      ;;
    \? ) echo "Usage: $0 [-v <version>] [-u <user>]"
      ;;
  esac
done
shift $((OPTIND-1))

wget "https://dl.google.com/go/go$version.linux-amd64.tar.gz" -O "/tmp/go$version.linux-amd64.tar.gz"
if [ $? -eq 0 ]; then
  tar -C '/usr/local' -zxf "/tmp/go$version.linux-amd64.tar.gz"
  echo 'export PATH=$PATH:/usr/local/go/bin' >> /etc/profile
  echo "export GOPATH=/home/$user/go" >> ~/.bashrc
  source '/etc/profile'
fi

