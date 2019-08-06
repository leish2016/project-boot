#!/bin/sh
SHELL_PROG=./init.sh

# 要运行的jar包名称,添加执行权限 chmod -R 777 /usr/local/project-boot
JAR_NAME=project-boot-1.0.jar


start() {
        echo "[`date`] Begin starting $JAR_NAME ... "
        nohup java -jar $JAR_NAME &
        if [ $? -eq 0 ]
        then
			echo "[`date`] Startup $JAR_NAME success."
			return 0
        else
			echo "[`date`] Startup $JAR_NAME fail."
			return 1
        fi
}


stop() {
    echo "[`date`] Begin stop $JAR_NAME... "
    PROGID=`ps -ef|grep "$JAR_NAME"|grep -v "grep"|sed -n '1p'|awk '{print $2" "$3}'`
	if [ -z "$PROGID" ]
	then
		echo "[`date`] Stop $JAR_NAME fail, service is not exist."
		return 1
	fi
	
    kill -9 $PROGID
    if [ $? -eq 0 ]
    then
		echo "[`date`] Stop $JAR_NAME success."
		return 0
    else
		echo "[`date`] Stop $JAR_NAME fail."
		return 1
    fi
}


case "$1" in
start)
  start
  exit $?
  ;;
stop)
  stop
  exit $?
  ;;
restart)
  stop
  start
  exit $?
  ;;
*)
  echo "[`date`] Usage: $SHELL_PROG {start|stop|restart}"
  exit 1
  ;;
esac
