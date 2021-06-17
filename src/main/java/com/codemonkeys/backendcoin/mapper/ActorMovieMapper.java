package com.codemonkeys.backendcoin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorMovieMapper {
    @Select("select movie_id from actor_to_movie where actor_id=#{actor_id}")
    List<Integer> getMovieIdsByActorId(Integer actor_id);

    @Select("select actor_id from actor_to_movie where movie_id=#{movie_id}")
    List<Integer> getActorIdsByMovieId(Integer movie_id);

    @Delete("delete from actor_to_movie where actor_id=#{actor_id} and movie_id=#{movie_id}")
    void deleteActorMovie(int actor_id,int movie_id);
}
