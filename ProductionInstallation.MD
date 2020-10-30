# Ibay production setup

## Hardware  
We are using Amazon Web Services EC2 server.
Ours is running in Stockholm.  
The configuration is t3.micro with 30Gb of storage.
The server is running Ubuntu Server 20.04 LTS.

## Server configuration

In order for the server to be able to run our tests it needs
virtual memory, because the server does not have enough ram to run them.  
```
sudo fallocate -l 2G /swapfile
sudo chmod 600 /swapfile
sudo mkswap /swapfile
echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab
```
We also changed the swappiness of the virtual memory:  
```
sudo nano /etc/sysctl.conf
add vm.swappness=10 to the end of file
```  
## Installing Java jdk
```
sudo apt-get update
sudo apt-get upgrade
sudo apt install openjdk-13-jre-headless
```

## Installing Gitlab Runner
```
apt-get install gitlab-runner
```
