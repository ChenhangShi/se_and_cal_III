#!/bin/bash
echo "Starting SpringBoot Application"
pwd
nohup java -Dhudson.util.ProcessTree.disable=true -jar target/*.jar --spring.profiles.active=prod &
