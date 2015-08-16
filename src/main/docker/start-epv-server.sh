#!/bin/bash
set -e

sed -i "s/%MYSQL_PORT_3306_TCP_PORT%/${MYSQL_PORT_3306_TCP_PORT}/" /opt/epv-server/epv-server.yml
sed -i "s/%MYSQL_USER%/${MYSQL_ENV_MYSQL_USER}/" /opt/epv-server/epv-server.yml
sed -i "s/%MYSQL_PASSWORD%/${MYSQL_ENV_MYSQL_PASSWORD}/" /opt/epv-server/epv-server.yml


MYSQL_LOOPS="120"

#wait for mysql
i=0
while ! nc mysql ${MYSQL_PORT_3306_TCP_PORT} >/dev/null 2>&1 < /dev/null; do
  i=$(expr $i + 1)
  if [ $i -ge "${MYSQL_LOOPS}" ]; then
    echo "$(date) - mysql:${MYSQL_PORT_3306_TCP_PORT} still not reachable, giving up"
    exit 1
  fi
  echo "$(date) - waiting for ${MYSQL_HOST}:${MYSQL_PORT_3306_TCP_PORT}..."
  sleep 5
done

#continue with further steps

#start the daemon
exec $START_CMD

java -jar /opt/epv-server/epvserver.jar server /opt/epv-server/epv-server.yml
