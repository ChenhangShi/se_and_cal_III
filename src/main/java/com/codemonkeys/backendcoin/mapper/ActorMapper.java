package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.ActorNamePO;
import com.codemonkeys.backendcoin.PO.ActorPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorMapper {
    @Select("select * from actor where actor_id=#{actorId}")
    ActorPO getActorById(@Param("actorId") int actorId);

    @Select("select actor_id, actor_chName, actor_foreName from actor")
    List<ActorNamePO> getAllActorNames();

    @Select("select movie_id from actor_to_movie where actor_id=#{actorId}")
    List<Integer> getMovieByActorId(@Param("actorId")int actorId);

    @Insert("insert into actor(actor_bio,actor_chName,actor_foreName,actor_nationality," +
            "actor_constellation,actor_birthPlace,actor_birthDay,actor_repWorks," +
            "actor_achiem,actor_brokerage) values " +
            "(#{actor.actor_bio},#{actor.actor_chName},#{actor.actor_foreName},#{actor.actor_nationality}," +
            "#{actor.actor_constellation},#{actor.actor_birthPlace},#{actor.actor_birthDay}," +
            "#{actor.actor_repWorks},#{actor.actor_achiem},#{actor.actor_brokerage})")
    @Options(useGeneratedKeys = true,keyProperty = "actor_id",keyColumn = "actor_id")
    int insertActor(@Param("actor")ActorPO actorPO);

    @Select("select actor_id from actor where actor_chName=#{actor_Name}")
    String isActorInTable(@Param("actor_Name")String actorName);

    @Update("update actor set actor_bio=#{a.actor_bio}," +
            "actor_foreName=#{a.actor_foreName},actor_nationality=#{a.actor_nationality}," +
            "actor_constellation=#{a.actor_constellation},actor_birthPlace=#{a.actor_birthPlace}" +
            ",actor_birthDay=#{a.actor_birthDay},actor_repWorks=#{a.actor_repWorks}," +
            "actor_achiem=#{a.actor_achiem},actor_brokerage=#{a.actor_brokerage}" +
            "where actor_chName=#{a.actor_chName}")
    void updateActor(@Param("a")ActorPO actorPO);

    @Insert("insert into actor_to_movie(actor_id,movie_id) values (#{actor_id},#{movie_id})")
    void insertIntoActorToMovie(@Param("actor_id")int actorId,@Param("movie_id")int movieId);

    @Select("select actor_id from actor where actor_chName=#{actorName} ")
    int getActorIdByActorName(@Param("actorName")String actorName);
}
