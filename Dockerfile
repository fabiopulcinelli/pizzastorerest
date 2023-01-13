FROM maven:3-openjdk-11 as builder
WORKDIR /opt
COPY . .
RUN mvn package

FROM openjdk:11

COPY --from=builder /opt/target/*.jar /opt/app.jar

EXPOSE 8080
CMD java -jar /opt/app.jar