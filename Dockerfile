FROM openjdk:8-jdk-alpine
MAINTAINER Sergio Rodriguez

ENV PORT 6000
ENV MONGO_HOST "db"
ENV TIMEZONE "America/Bogota"

RUN mkdir /code
COPY build/libs /code

ENTRYPOINT ["sh", "-c", "exec java -jar -Duser.timezone=$TIMEZONE /code/*.jar" ]
