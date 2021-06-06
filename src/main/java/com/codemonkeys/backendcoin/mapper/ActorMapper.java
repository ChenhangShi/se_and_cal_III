package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.ActorNamePO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorMapper {
    @Select("select actor_id,actor_chName,actor_foreName from actor where actor_id=#{actor_id}")
    ActorNamePO getActorNameById(Integer actor_id);

    @Select("select actor_id,actor_chName,actor_foreName from actor")
    List<ActorNamePO> getAllActorNames();
}
