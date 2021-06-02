#!/bin/bash
mysql -uroot <<EOF
use coin;
source /var/lib/jenkins/workspace/backend-coin/sql/init.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_actor.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_actor_to_movie.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_director_to_movie.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_entity.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_genre.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_graph.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_link.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_movie.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_movie_to_genre.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_user.sql;
EOF
