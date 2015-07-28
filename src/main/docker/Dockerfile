FROM java:8
MAINTAINER Milad Fatenejad

RUN mkdir -p /opt/epv-server

COPY epvserver.jar /opt/epv-server/epvserver.jar
COPY hello-world.yml /opt/epv-server/hello-world.yml

ENTRYPOINT ["java", "-jar", "/opt/epv-server/epvserver.jar"]

CMD ["server", "/opt/epv-server/hello-world.yml"]
