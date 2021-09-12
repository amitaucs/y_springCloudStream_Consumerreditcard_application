FROM adoptopenjdk
EXPOSE 8006
ADD target/creditCardGeneration-1.0.0.jar  generateCreditCard.jar
ENV MSQL_HOSTNAME=mysql
ENV RMQ_HOSTNAME=rabbitmq
ENTRYPOINT [ "java", "-jar", "/generateCreditCard.jar"]