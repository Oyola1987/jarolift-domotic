FROM maven:3.6.1-jdk-8-alpine AS build

WORKDIR /tmp/
COPY src src
COPY pom.xml pom.xml

RUN mvn clean package -DskipTests=false

FROM oyola/raspberry-pi4-debian-openjdk-8-jre:latest

COPY --from=build /tmp/target/jarolift-domotic-1.0.0.jar /home/jarolift-domotic-1.0.0.jar

WORKDIR /home

EXPOSE 8080

CMD ["java", "-jar", "jarolift-domotic-1.0.0.jar"]