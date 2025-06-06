
FROM amazoncorretto:21
LABEL maintainer="diegoreso@me.com"
WORKDIR /app
COPY target/recipe-it-0.0.1-SNAPSHOT.jar /app/docker.jar
ENTRYPOINT ["java", "-jar", "app.jar"]