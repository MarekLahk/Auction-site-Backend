# Ibay production setup

## Hardware  
We are using Amazon Web Services EC2 server.
Ours is running in Stockholm.  
The configuration is t3.micro with 30 GB of storage.
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
We also changed the swappiness of the virtual memory.
 This step is probably not necessary but it should boost the performance.  
```
sudo nano /etc/sysctl.conf
add vm.swappness=10 to the end of file
```  
## Installing Docker
```
sudo apt-get update
sudo apt-get upgrade
sudo apt install docker
```

## Installing Gitlab Runner
```
apt-get install gitlab-runner
```

## Configuring Gitlab Runner
```
gitlab-runner register
```
1. Enter your gitlab url (https://gitlab.cs.ttu.ee/)
2. Go to your repository -> Settings -> Runners. Copy your registration token.
3. Paste the token to your commandline.
4. Enter a description
5. For backend enter tag IbayRunner. For frontend enter 
## Installing Nginx  
```
sudo apt intall nginx
```
