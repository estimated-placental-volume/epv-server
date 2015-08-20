#!/bin/bash
set -e

sed -i "s/%MYSQL_PORT_3306_TCP_PORT%/${MYSQL_PORT_3306_TCP_PORT?}/" /opt/epv-server/epv-server.yml
sed -i "s/%MYSQL_USER%/${MYSQL_ENV_MYSQL_USER?}/" /opt/epv-server/epv-server.yml
sed -i "s/%MYSQL_PASSWORD%/${MYSQL_ENV_MYSQL_PASSWORD?}/" /opt/epv-server/epv-server.yml
sed -i "s/%EPV_SERVER_SHA256_PASSWORD%/${EPV_SERVER_SHA256_PASSWORD:-2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b}/" /opt/epv-server/epv-server.yml
sed -i "s/%EPV_SERVER_USER%/${EPV_SERVER_USER:-epvapp}/" /opt/epv-server/epv-server.yml

# Wait for MySQL to Start:
num_attempts="120"
i=0
while ! nc mysql ${MYSQL_PORT_3306_TCP_PORT} >/dev/null 2>&1 < /dev/null; do
    i=$(expr $i + 1)
    if [ $i -ge "${num_attempts}" ]; then
        echo "$(date) - mysql:${MYSQL_PORT_3306_TCP_PORT?} still not reachable, giving up"
        exit 1
    fi
    echo "$(date) - waiting for mysql:${MYSQL_PORT_3306_TCP_PORT?}..."
    sleep 1
done

# Start EPV Server:
DEBUG=${DEBUG:-false}
if [ "${DEBUG}" == "true" ]; then
    debug_opts="-agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n"
fi

java ${debug_opts} -jar /opt/epv-server/epvserver.jar server /opt/epv-server/epv-server.yml
