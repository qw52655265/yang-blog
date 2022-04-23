FROM java:8
ARG JAR_FILE
ENV ENVIRONMENT=docker
ADD target/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${ENVIRONMENT}", "/app.jar"]