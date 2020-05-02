FROM openjdk:8
ADD target/StudentRecordJB.jar  StudentRecordJB.jar
ADD ./config.yml config.yml
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "StudentRecordJB.jar", "server" ,"config.yml"]