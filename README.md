# Ddangn Market - Product API Server

<img src="https://miro.medium.com/max/700/1*InTRJNvyco3ZAjYdiKYmzw.jpeg" width="150">

- This app is for study Spring Boot and MSA.
- This app is Product/Comment API Server.
- Visit ddangn.taesunny.com

## Using this app
- Use Products / Comments APIs
- API List

| Endpoint | Description | Secured | Roles |
|--|--|:--:|--|
| GET /api/v1/products | Product List 조회  | NO |  |
| GET /api/v1/products/{product id} | Product 단건 조회 | NO |  |
| GET /api/v1/products | Product 등록 | YES | USER |
| DELETE /api/v1/products/{product id} | Product 삭제 | YES | USER, ADMIN |
| PUT /api/v1/products/{product id}?status={selling/soldout} | Product 업데이트 (상태 변경) | YES | USER, ADMIN |
| GET /api/v1/products/{product id}/comments | Product의 Comments List 조회 | NO |  |
| POST /api/v1/products/{product id}/comments | Product의 Comments 등록 | YES | USER |
| DELETE /api/v1/products/{product id}/comments/{comment id} | Product의 Comments 삭제 | YES | USER, ADMIN |
| GET /api/v1/categories | Product Category List 조회  | NO |  |

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
