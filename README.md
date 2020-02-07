# Client Server Demo 

## Technologies stack

### Implementation
Spring Boot

Spring Data Jdbc

React

MySQL

Lombok

### Build Tools
Gradle

Npm

### CI/CD

docker

docker-compose

## Running using docker-compose

Go to folder `docker-compose`

Run command:

`docker-compose up -d --build`

Where 

`-d` - means run the command as daemon

`--build` - means rebuild images before starting. No need if sources were not updated 
 
Edit `.env` file to override configurations, such as port bindings, db schema/username/password, security username/passsword/role

## Folders structure

`docker-compose` - docker compose configuration and environment

`react-app` - npm based react application

`spring-server` - gradle based spring boot application
