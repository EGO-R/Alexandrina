FROM gradle:jdk21 as builder

WORKDIR /app

COPY src/main src/main/

COPY build.gradle .

COPY settings.gradle .

COPY versions.gradle .

COPY lombok.config .

RUN ["gradle", "--no-daemon", "build"]

FROM openjdk:21

WORKDIR /app

COPY --from=builder /app/build/libs/Alexandrina-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "/app/Alexandrina-0.0.1-SNAPSHOT.jar"]