FROM maven:3.6.1-jdk-8-alpine AS build

WORKDIR /tmp/
COPY . .
RUN mvn clean package -DskipTests=true

FROM openjdk:8-jre-alpine

COPY --from=build /tmp/target/jarolift-domotic-1.0.0.jar /home/jarolift-domotic-1.0.0.jar
WORKDIR /home

CMD ["java", "-jar", "jarolift-domotic-1.0.0.jar"]
