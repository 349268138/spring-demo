#!/bin/bash
ROOT=/log/spring-demo
logfile="$ROOT/server.run.log"

#JAVA_OPTS=$JVM_TEST_ARGS" -server -XX:PermSize=128m -XX:MaxPermSize=512m  -Xms4g -Xmx4g -XX:NewRatio=3   -XX:ParallelCMSThreads=8  -XX:+CMSParallelRemarkEnabled -XX:+ExplicitGCInvokesConcurrent -XX:+CMSClassUnloadingEnabled  -XX:+CMSClassUnloadingEnabled -XX:+DisableExplicitGC -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution -XX:+UseConcMarkSweepGC -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps  -XX:CMSFullGCsBeforeCompaction=0 -XX:+UseCMSCompactAtFullCollection
#-XX:CMSInitiatingOccupancyFraction=80 -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -Xloggc:${ROOT}/job.gc.log -XX:ErrorFile=${ROOT}/payplatform.vmerr.log -XX:HeapDumpPath=${ROOT}/payplatform.heaperr.log"
###
#exec /usr/local/java/bin/java $JAVA_OPTS -Dfile.encoding=UTF-8 -Ddruid.mysql.usePingMethod=false -jar /opt/meituan/soft/jetty/start.jar  jetty.base=/opt/meituan/soft/jetty  jetty.port=8080   >> $logfile 2>&1

java -jar /app/spring-demo-server.jar >> $logfile 2>&1