#!/bin/bash
echo "Starting SpringBoot Application"
pwd
nohup java -jar target/*.jar --spring.profiles.active=prod &
