FROM maven:3.6.1-jdk-8-alpine AS build

WORKDIR /tmp/
COPY src src
COPY pom.xml pom.xml

RUN wget https://project-downloads.drogon.net/wiringpi-latest.deb
RUN mvn clean package -DskipTests=true

FROM balenalib/raspberry-pi-debian:latest

COPY --from=build /tmp/wiringpi-latest.deb /home/wiringpi-latest.deb
COPY --from=build /tmp/target/jarolift-domotic-1.0.0.jar /home/jarolift-domotic-1.0.0.jar

WORKDIR /home

# RUN apt update && apt install openjdk-8-jre
# RUN dpkg -i wiringpi-latest.deb

EXPOSE 8080

ENTRYPOINT apt update && apt install openjdk-8-jre && dpkg -i wiringpi-latest.deb

CMD ["java", "-jar", "jarolift-domotic-1.0.0.jar"]
# ENTRYPOINT apt-get update && apt-get install wiringpi && java -jar jarolift-domotic-1.0.0.jar
