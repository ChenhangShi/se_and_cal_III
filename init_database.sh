#!/bin/bash
mysql -uroot <<EOF
use coin;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_actor.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_actor_to_movie.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_director.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_director_to_movie.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_entity.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_genre.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_graph.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_link.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_movie.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_movie_to_genre.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_user.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_user_recommended_movie.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_user_tag_actor.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_user_tag_director.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_user_tag_genre.sql;
source /var/lib/jenkins/workspace/backend-coin/sql/coin_user_tag_movie.sql;
EOF
