## 백엔드 도커 설정 방법


#### Dockerfile을  기반으로 이미지 만들기
```
# docker build -t <이미지 이름>:<태그> <Dockerfile 경로>

$ docker build -t cwnu-backend:latest ./spring-backend
```

#### 도커 이미지 확인하기 
```
$ docker image ls
또는
$ docker images
```

#### 도커 컨테이너 실행하기
```
# docker run -d -p <호스트 포트>:<컨테이너 포트> --name <컨테이너 이름> <이미지 이름>:<태그>
$ docker run -d -p 8080:8080 --name spring-container cwnu-backend:latest
```

#### 실행중인 도커 컨테이너 확인하기
```
$ docker ps
```

#### 도커 컨테이너 중지하기
```
# docker stop <컨테이너 이름 또는 ID>
$ docker stop spring-container
```

#### 도커 컴포즈 재빌드
```
$ docker-compose build backend
$ docker-compose up -d backend

```
