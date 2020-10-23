#!/bin/sh
cd ..
mvn clean -U package -P $ENV  -Dmaven.test.skip=true
