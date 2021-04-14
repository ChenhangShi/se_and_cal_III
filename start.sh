#!/bin/bash
echo "Starting SpringBoot Application"
pwd
export JENKINS_NODE_COOKIE=dontKillMe
nohup java -jar target/*.jar /var/lib/jenkins/workspace/backend-coin/output.xml --spring.profiles.active=prod &