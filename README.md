
# Ddangn-market - Product API Server

<img src="https://miro.medium.com/max/700/1*InTRJNvyco3ZAjYdiKYmzw.jpeg" width="150">

- This app is for study Spring Boot and MSA.
- This app is Product/Comment API Server.
- Visit ddangn.taesunny.com

## Using this app
- Use Products / Comments APIs
- API List
<img src="https://miro.medium.com/max/700/1*YlwOBDmOwAFpNP9qJSBoeQ.png" width="100%">

## How to build docker image
You can `image name`, by setting application.yml's app name variable
You can set `tag`, by setting application.yml's app version variable
##### Run command below
```
./gradlew clean jar dockerBuildImage
docker images # check the created image
```

## How to run docker with the created image

##### Run command below
```
docker run -d -p 8761:8761 -it {package path:docker image name}:{set tag used when docker image build}  
docker ps # check the running docker container
```

## Contacts

Taesun Lee - superbsun@gmail.com
