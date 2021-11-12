FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/timesheet-1.3.0-SNAPSHOT.jar timesheet-1.3.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/timesheet-1.3.0-SNAPSHOT.jar"]
