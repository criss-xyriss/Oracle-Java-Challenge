FROM adoptopenjdk/openjdk11:latest

EXPOSE 30 3000 8000 

RUN mkdir app

COPY target/fibonacci-1.0-SNAPSHOT.jar app
COPY config.yml app

WORKDIR /app


CMD ["java","-jar","fibonacci-1.0-SNAPSHOT.jar","server","config.yml"]

