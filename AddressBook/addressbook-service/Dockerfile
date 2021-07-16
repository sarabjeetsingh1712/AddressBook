FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar addressbookapp.jar
# java -jar /opt/app/addressbookapp.jar
ENTRYPOINT ["java","-jar","/addressbookapp.jar"]