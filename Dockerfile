FROM maven:3.9.9-eclipse-temurin-23-alpine
COPY app /app
WORKDIR /app
RUN mvn clean compile
ENTRYPOINT ["mvn"]
CMD ["spring-boot:run"]