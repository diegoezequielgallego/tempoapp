FROM openjdk:17-jdk-slim

COPY ./target/api-1.jar /app/api-1.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "api-1.jar"]
