FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/timesheet-0.0.6-SNAPSHOT.jar timesheet-0.0.6-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/timesheet-0.0.6-SNAPSHOT.jar"]