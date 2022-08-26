FROM openjdk:17-jdk-alpine
MAINTAINER Adrien
COPY target/ClaimService-0.0.1-SNAPSHOT.jar   ClaimService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ClaimService-0.0.1-SNAPSHOT.jar" ,"--spring.rabbitmq.host=host.docker.internal" , "--axon.axonserver.servers=host.docker.internal:8124"]