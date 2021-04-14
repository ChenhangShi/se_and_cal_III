#!/bin/bash
mysql -uroot <<EOF
use coin;
source /var/lib/jenkins/workspace/backend-coin/sql/init.sql;
EOF