FROM openjdk:17-jdk-alpine
MAINTAINER askanberg.com
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]