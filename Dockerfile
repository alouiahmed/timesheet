FROM openjdk:8
EXPOSE 8083
COPY target/timesheet-0.0.6-SNAPSHOT.jar timesheet-0.0.6-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/timesheet-0.0.6-SNAPSHOT.jar"]