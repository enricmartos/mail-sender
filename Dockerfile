#Multi-stage approach
FROM gradle:6.0.1-jdk8 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:8
USER root
WORKDIR /opt
RUN mkdir conf
ENV CONF_DIR=/opt/conf
WORKDIR $CONF_DIR
COPY devresources/mailsender.properties $CONF_DIR
ENV ARTIFACT_NAME=configuration-layer-0.0.1-SNAPSHOT.jar
WORKDIR /usr/app/
COPY --from=builder  /home/gradle/src/configuration-layer/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
CMD [ "sh", "-c", "java -Dserver.port=$PORT -jar configuration-layer-0.0.1-SNAPSHOT.jar" ]
