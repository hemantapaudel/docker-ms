FROM openjdk:8-jdk-alpine

RUN mkdir -p /application
COPY target/docker-ms-1.10.jar /application/docker-ms-1.10.jar

ENTRYPOINT ["java","-jar","/application/docker-ms-1.10.jar"]