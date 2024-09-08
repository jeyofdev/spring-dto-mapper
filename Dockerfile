FROM openjdk:22-jdk
ADD target/spring-dto-mapper.jar spring-dto-mapper.jar
ENTRYPOINT ["java", "-jar", "/spring-dto-mapper.jar"]