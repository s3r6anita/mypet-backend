FROM gradle:8.4-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:21
EXPOSE 80:80
COPY ./build/libs/fat.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
