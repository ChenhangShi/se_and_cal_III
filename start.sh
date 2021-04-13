#!/bin/bash
echo "Starting SpringBoot Application"
pwd
nohup java -jar /var/lib/jenkins/workspace/backend-coin/target/backend-coin-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod &
