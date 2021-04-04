FROM amazoncorretto:11-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY target/offero.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/app.jar"]