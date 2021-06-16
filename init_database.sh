#!/bin/bash
mysql -uroot <<EOF
use coin;
source sql/coin_actor.sql;
source sql/coin_actor_to_movie.sql;
source sql/coin_director.sql;
source sql/coin_director_to_movie.sql;
source sql/coin_entity.sql;
source sql/coin_genre.sql;
source sql/coin_graph.sql;
source sql/coin_link.sql;
source sql/coin_movie.sql;
source sql/coin_movie_to_genre.sql;
source sql/coin_user.sql;
source sql/coin_user_recommended_movie.sql;
source sql/coin_user_tag_actor.sql;
source sql/coin_user_tag_director.sql;
source sql/coin_user_tag_genre.sql;
source sql/coin_user_tag_movie.sql;
EOF
