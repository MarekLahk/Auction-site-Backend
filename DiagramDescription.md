# Components Diagram
We are using one AWS EC2 server for building, testing and deployment. The server is using Ubuntu 20.04x.  
All the configuration has been done by hand. Our configuration guide can be found [Here](ProductionInstallation.MD).  


## Backend pipeline
#### Build
Building is done by gradle. The resulting jar is but into a docker image.
#### Testing
Testing is also executeb by gradle.
#### Deployment
Deployment consists of running a Postgreqsl docker container and the image from build is used to start a new backend container


## Frontend pipeline
#### Build
Building the frontend consists of downloading the requirements and building the application
#### Testing
Currently, no tests for frontend.
#### Deployment
Deployment process removes old files from our webserver folder and copies the new built files.

## Data paths

## Frontend
Nginx serves files from our webserver folder to the end user.

## Backend
Requests sent from our frontend are routed to our backend which uses the data from the Postgresql container to fulfill the
 requests and then sends replies back.  
 Postgresql container uses a volume for persistent storage.