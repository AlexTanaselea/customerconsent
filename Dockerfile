FROM alpine/git as base

WORKDIR /app

RUN git clone https://github.com/AlexTanaselea/customerconsent.git

#
# Build stage
#

FROM maven:3.6.0-jdk-11-slim as build
WORKDIR /app

COPY --from=base /app/customerconsent /app
RUN mvn install

#
# Run stage
#

FROM fabric8/java-alpine-openjdk11-jre:1.8
WORKDIR /app/

COPY --from=build /app/target/customer-consent-1.0-SNAPSHOT.jar /app

EXPOSE 8080/tcp

ENTRYPOINT ["java","-jar","customer-consent-1.0-SNAPSHOT.jar"]
