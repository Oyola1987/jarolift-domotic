FROM maven:3.6.1-jdk-8-alpine AS build

WORKDIR /tmp/
COPY src src
COPY pom.xml pom.xml

RUN wget https://project-downloads.drogon.net/wiringpi-latest.deb
RUN mvn clean package -DskipTests=true

FROM frankwolf/rpi-oracle-java8-jdk

COPY --from=build /tmp/wiringpi-latest.deb /home/wiringpi-latest.deb
COPY --from=build /tmp/target/jarolift-domotic-1.0.0.jar /home/jarolift-domotic-1.0.0.jar

WORKDIR /home

EXPOSE 8080

ENTRYPOINT apt-get update && apt-get install wiringpi && java -jar jarolift-domotic-1.0.0.jar
