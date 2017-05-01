FROM openjdk:8-jdk-alpine

MAINTAINER Rodrigo Vallejo "rvallejo@thoughtworks.com"

EXPOSE 8080

COPY build/libs/*.jar /opt/app.jar

WORKDIR /opt/

ENTRYPOINT java -Dspring.profiles.active=docker -jar app.jar