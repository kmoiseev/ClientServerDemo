FROM gradle:6.1.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-jre-slim

EXPOSE ${SERVER_PORT}
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-server.jar

ENTRYPOINT ["java","-jar","/app/spring-server.jar"]