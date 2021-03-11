#!/bin/bash
echo "starting springboot application"
pwd
export JENKINS_NODE_COOKIE=dontKillMe
nohup java -Dhudson.util.ProcessTree.disable=true -jar target/*.jar &