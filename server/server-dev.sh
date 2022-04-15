#!/bin/bash
WORKING_DIR=$(pwd)
DEBUG_PORT=4001
SERVER_PORT=8181
JMX_PORT=9011
RMI_PORT=9021

JVM_ARGS="$JVM_ARGS -server -Xms128M -Xmx1024M "
#JVM_ARGS="$JVM_ARGS -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.ssl=false"
#JVM_ARGS="$JVM_ARGS -Dcom.sun.management.jmxremote.authenticate=true"
#JVM_ARGS="$JVM_ARGS -Dcom.sun.management.jmxremote.password.file=$HOME/config/jmxremote.password"
#JVM_ARGS="$JVM_ARGS -Dcom.sun.management.jmxremote.access.file=$HOME/config/jmxremote.access"
#JVM_ARGS="$JVM_ARGS -Djava.rmi.server.hostname=$LISTEN_IP"
#JVM_ARGS="$JVM_ARGS -Dcom.sun.management.jmxremote.rmi.port=$RMI_PORT"
#JVM_ARGS="$JVM_ARGS -Dcom.sun.management.jmxremote.port=$JMX_PORT"
JVM_ARGS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT"

cd ../
do_stop(){
	fuser -k $SERVER_PORT/tcp
	echo "KILL port $SERVER_PORT"
	return 0
}
do_start(){
	# mvn clean package spring-boot:run -DskipTests exec:exec -Dexec.executable="java" -Dexec.args="-classpath %classpath -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT"
	nohup mvn spring-boot:run -DskipTests -Drun.jvmArguments="$JVM_ARGS" &
	# mvn clean install exec:exec
	# nohup mvn spring-boot:run -DskipTests exec:exec -Dexec.executable="java" -Dexec.args="-classpath %classpath $JVM_ARGS" &
	return 0
}
do_start_clean_package(){
	nohup mvn clean package spring-boot:run -DskipTests -Drun.jvmArguments="$JVM_ARGS" &
	return 0	
}

case "$1" in
	clean)
		do_stop
		do_start_clean_package
		;;
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
		echo "Usage: ./server.sh clean|start|stop|restart"
		;;
esac

cd ${WORKING_DIR}
tail -f ../nohup.out
exit 0