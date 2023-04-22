FROM openjdk:17-jdk-alpine
MAINTAINER Mtarrr
ARG JAR_FILE=target/*.jar
COPY ./target/SurveyService-0.1.0.jar SurveyService-0.1.0.jar
ENTRYPOINT ["java","-jar","/SurveyService-0.1.0.jar"]