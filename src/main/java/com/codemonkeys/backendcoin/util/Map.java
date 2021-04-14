package com.codemonkeys.backendcoin.util;

import com.codemonkeys.backendcoin.PO.EntityPO;
import com.codemonkeys.backendcoin.PO.GraphPO;
import com.codemonkeys.backendcoin.PO.LinkPO;
import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.VO.GraphVO;
import com.codemonkeys.backendcoin.VO.LinkVO;
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

}
