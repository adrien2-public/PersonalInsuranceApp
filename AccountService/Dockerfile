FROM openjdk:17-jdk-alpine
MAINTAINER Adrien
COPY target/AccountService-0.0.1-SNAPSHOT.jar   AccountService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/AccountService-0.0.1-SNAPSHOT.jar" ,"--spring.rabbitmq.host=host.docker.internal" , "--axon.axonserver.servers=host.docker.internal:8124"]