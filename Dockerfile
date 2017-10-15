FROM openjdk:8-jdk-alpine
ADD target/com.metric.saver* app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar