FROM openjdk:latest
COPY ./target/Group35_SEM-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group35_SEM-0.1.0.2-jar-with-dependencies.jar"]
