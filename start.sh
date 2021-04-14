#!/bin/bash
echo "Starting SpringBoot Application"
pwd
export JENKINS_NODE_COOKIE=dontKillMe
nohup java -jar target/*.jar --spring.profiles.active=prod >coin.log 2>&1 &
