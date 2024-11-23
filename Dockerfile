FROM openjdk:24-jdk
LABEL authors="elton"
ADD target/electricity-billing.jar electricity-billing.jar
ENTRYPOINT ["java", "-jar", "/electricity-billing.jar"]