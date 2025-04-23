
FROM gradle:7.6-jdk17 AS build
WORKDIR /home/gradle/project


COPY --chown=gradle:gradle . .


RUN gradle clean build -x test


FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app


COPY --from=build /home/gradle/project/build/libs/harjoitustyo-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java","-jar","/app/app.jar"]
