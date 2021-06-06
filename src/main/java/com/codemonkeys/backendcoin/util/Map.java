package com.codemonkeys.backendcoin.util;

import com.codemonkeys.backendcoin.PO.*;
import com.codemonkeys.backendcoin.VO.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface Map {

    EntityVO from(EntityPO entityPO);

    EntityPO from(EntityVO entityVO);

    @Mapping(target = "relation",source = "relationName")
    LinkVO from(LinkPO linkPO);

    @Mapping(target = "relationName",source="relation")
    LinkPO from(LinkVO linkVO);

    GraphPO from(GraphVO graphVO);

    GraphVO from(GraphPO graphPO);

    UserDirectorVO from(UserDirectorPO directorPO);

    UserDirectorPO from(UserDirectorVO directorVO);

    UserMovieVO from(UserMoviePO moviePO);

    UserMoviePO from(UserMovieVO movieVO);

    UserActorVO from(UserActorPO userActorPO);

    UserActorPO from(UserActorVO userActorVO);
}
