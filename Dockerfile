FROM openjdk:latest
COPY ./target/Group35_SEM-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group35_SEM-0.1.0.2-jar-with-dependencies.jar"]
# Use the MySQL image version 8.3 (allows native password plugin)
FROM mysql:8.3
# Set the working directory
WORKDIR /tmp
# Copy all the files to the working directory of the container
COPY /world-db/world.sql/*.sql /tmp/
COPY /world-db/world.sql /docker-entrypoint-initdb.d
# Set the root password
ENV MYSQL_ROOT_PASSWORD example

