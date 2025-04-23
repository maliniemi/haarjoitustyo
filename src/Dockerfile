# --- Build stage ---
FROM gradle:7.6-jdk17 AS build
WORKDIR /home/gradle/project

# Kopioi koko projekti ja rakennusskriptit
COPY --chown=gradle:gradle . .

# Käännä ja paketoi jar (testejä ei aja tässä)
RUN gradle clean build -x test

# --- Runtime stage ---
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Kopioi buildissa luotu jar
COPY --from=build /home/gradle/project/build/libs/harjoitustyo-0.0.1-SNAPSHOT.jar app.jar

# Portti, jota Spring Boot kuuntelee
EXPOSE 8080

# Käynnistä applicaatio
ENTRYPOINT ["java","-jar","/app/app.jar"]
