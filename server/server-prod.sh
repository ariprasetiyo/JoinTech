#!/bin/bash
WORKING_DIR=$(pwd)
SERVER_PORT=8181

JVM_ARGS="$JVM_ARGS -server -Xms128M -Xmx1024M "

do_stop(){
	fuser -k $SERVER_PORT/tcp
	echo "KILL port $SERVER_PORT"
	return 0
}
do_start(){
	nohup java -jar RESMOB-0.0.1-SNAPSHOT.war -Drun.jvmArguments="$JVM_ARGS" &
	return 0
}

case "$1" in
	start)
		do_start
		;;
	stop)
		do_stop
		;;
	restart)
		do_stop
		do_start
		;;
	*)
		echo "Usage: ./server-prod.sh start|stop|restart"
		;;
esac

cd ${WORKING_DIR}
tail -f nohup.out
exit 0