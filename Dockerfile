FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/timesheet-1.0.1.jar timesheet-1.0.1.jar.original
ENTRYPOINT ["java","-jar","/timesheet-1.0.1.jar"]

