FROM openjdk:8
EXPOSE 8080
ADD target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1-SNAPSHOT.war Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java" ,"-war","Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1-SNAPSHOT.war"]