FROM amazoncorretto:21-alpine-jdk as builder
WORKDIR /workspace
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY --from=builder /workspace/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]