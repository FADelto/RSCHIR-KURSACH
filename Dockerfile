FROM openjdk:8
EXPOSE 8080
ADD target/Club-Pain.jar Club-Pain.jar
ENTRYPOINT ["java","-jar","/Club-Pain.jar"]