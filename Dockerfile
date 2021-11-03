FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/timesheet-2.0.0-SNAPSHOT.jar timesheet-2.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/timesheet-2.0.0-SNAPSHOT.jar"]
