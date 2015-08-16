#!/bin/bash
set -e

sed -i "s/%MYSQL_PORT_3306_TCP_PORT%/${MYSQL_PORT_3306_TCP_PORT}/" /opt/epv-server/epv-server.yml
sed -i "s/%MYSQL_USER%/${MYSQL_ENV_MYSQL_USER}/" /opt/epv-server/epv-server.yml
sed -i "s/%MYSQL_PASSWORD%/${MYSQL_ENV_MYSQL_PASSWORD}/" /opt/epv-server/epv-server.yml

# Wait for MySQL to Start:
MYSQL_LOOPS="120"
i=0
while ! nc mysql ${MYSQL_PORT_3306_TCP_PORT} >/dev/null 2>&1 < /dev/null; do
    i=$(expr $i + 1)
    if [ $i -ge "${MYSQL_LOOPS}" ]; then
        echo "$(date) - mysql:${MYSQL_PORT_3306_TCP_PORT} still not reachable, giving up"
        exit 1
    fi
    echo "$(date) - waiting for ${MYSQL_HOST}:${MYSQL_PORT_3306_TCP_PORT}..."
    sleep 1
done

# Start EPV Server:
if [ "${DEBUG}" == "true" ]; then
    DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n"
fi

java ${DEBUG_OPTS} -jar /opt/epv-server/epvserver.jar server /opt/epv-server/epv-server.yml
