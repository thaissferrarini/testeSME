FROM adoptopenjdk/openjdk11:latest 
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/SpringRestApi-0.0.1-SNAPSHOT.jar backend.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar backend.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar backend.jar
