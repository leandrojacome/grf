#FROM ghcr.io/graalvm/graalvm-ce:latest as build
#FROM registry.poupex.com.br/java/spring-boot:jre-17
FROM openjdk:17-jdk-slim-buster
WORKDIR /opt/app

ARG JAR_FILE=target/gestao-recursos-financeiros-api-1.0.0.jar

COPY ${JAR_FILE} app.jar


ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-jar", "app.jar"]
