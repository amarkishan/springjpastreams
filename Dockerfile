FROM openjdk:17

#add path of the jar file and  give the name to the image for the docker
ADD target/Springjpastreams-0.0.1-SNAPSHOT.jar springdatajpa-docker-demo.jar

#The EXPOSE instruction in a Dockerfile only documents which ports the container is intended to use

ENTRYPOINT ["java","-jar","springdatajpa-docker-demo.jar"]
