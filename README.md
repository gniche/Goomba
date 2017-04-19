# Goomba
Spring Boot REST service for a virtual vacuum cleaning robot like a Roomba.

Use Maven to build or run 
Either
1. Run the application using ./mvnw spring-boot:run. 
Or 
2. You can build the JAR file with ./mvnw clean package. Then you can run the JAR file: java -jar target/goomba-1.0-SNAPSHOT.jar

Application is configured to run on port 8082 - this can be changed by setting server.port={port} in src/main/resources/application.properties
