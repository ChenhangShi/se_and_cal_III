package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.ActorPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorMapper {
    @Select("select * from actor where actor_id=#{actorId}")
    ActorPO getActorById(@Param("actorId") int actorId);

    @Select("select movie_id from actor_to_movie where actor_id=#{actorId}")
    List<Integer> getMovieByActorId(@Param("actorId")int actorId);
}
