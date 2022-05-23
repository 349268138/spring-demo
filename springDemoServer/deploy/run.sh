#!/bin/bash
ROOT=/log/spring-demo
logfile="$ROOT/server.run.log"

java -jar /app/spring-demo-server.jar >> $logfile 2>&1