FROM openjdk:8-jdk-alpine
ADD build/libs/producer.jar producer.jar
EXPOSE 5001
ENTRYPOINT ["java","-jar","producer.jar"]
