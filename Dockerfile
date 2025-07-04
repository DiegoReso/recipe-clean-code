
FROM amazoncorretto:21
LABEL maintainer="diegoreso@me.com"
WORKDIR /app
COPY target/recipe-it-0.0.1-SNAPSHOT.jar /app/recipe.jar
ENTRYPOINT ["java", "-jar", "recipe.jar"]