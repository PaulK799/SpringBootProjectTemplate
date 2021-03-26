FROM openjdk:8-jdk-alpine
MAINTAINER paul.kennedy452@gmail.com
COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
# Configure
ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]