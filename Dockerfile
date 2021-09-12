FROM adoptopenjdk
EXPOSE 8006
ADD target/creditCardGeneration-1.0.0.jar  generateCreditCard.jar
ENTRYPOINT [ "java", "-jar", "/generateCreditCard.jar"]