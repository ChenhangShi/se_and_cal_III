package com.codemonkeys.backendcoin.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorMovieMapper {
    @Select("select movie_id from actor_to_movie where actor_id=#{actor_id}")
    List<Integer> getMovieIdsByActorId(Integer actor_id);
}
