FROM java:8-jdk-alpine

RUN mkdir /usr/app

COPY ./target/pixogram-backend-0.0.1-SNAPSHOT.jar /usr/app

WORKDIR /usr/app

RUN sh -c 'touch pixogram-backend-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar", "-DEUREKA_URI=http://discovery:8761/eureka","pixogram-backend-0.0.1-SNAPSHOT.jar"]

