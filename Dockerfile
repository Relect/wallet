FROM maven:3.9.9-amazoncorretto-21-alpine
COPY app /app
WORKDIR /app
RUN mvn clean compile
ENTRYPOINT ["mvn"]
CMD ["spring-boot:run"]