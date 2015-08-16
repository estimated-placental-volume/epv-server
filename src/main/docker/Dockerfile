FROM java:8
MAINTAINER Milad Fatenejad

RUN apt-get update && apt-get -y install netcat && \
    mkdir -p /opt/epv-server

COPY epvserver.jar /opt/epv-server/epvserver.jar
COPY epv-server.yml /opt/epv-server/epv-server.yml
COPY start-epv-server.sh /usr/local/bin/start-epv-server.sh

RUN chmod a+x /usr/local/bin/start-epv-server.sh

CMD ["start-epv-server.sh"]
