FROM openjdk:17-jdk-alpine
MAINTAINER Adrien
COPY target/PayoutService-0.0.1-SNAPSHOT.jar   PayoutService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/PayoutService-0.0.1-SNAPSHOT.jar" ,"--spring.rabbitmq.host=host.docker.internal" , "--axon.axonserver.servers=host.docker.internal:8124"]