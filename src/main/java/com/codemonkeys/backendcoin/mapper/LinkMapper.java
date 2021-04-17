package com.codemonkeys.backendcoin.mapper;


import com.codemonkeys.backendcoin.PO.LinkPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkMapper {
    @Select("select * from link where graphId=#{graphId}")
    List<LinkPO> getAllLink(@Param("graphId")Long graphId);

    @Insert("insert into link(id,sourceId,targetId,graphId,relationName,type,description,isFullLine) value(#{l.id},#{l.sourceId}" +
            ",#{l.targetId},#{l.graphId},#{l.relationName},#{l.type},#{l.description},#{l.isFullLine})")
    void insertLink(@Param("l") LinkPO linkPO);

    @Delete("delete from link where graphId=#{graphId} and (sourceId=#{nodeId} or targetId=#{nodeId})")
    void deleteLink(@Param("nodeId") Long nodeId,@Param("graphId")Long graphId);

    @Update("update link set sourceId=#{l.sourceId},targetId=#{l.targetId},relationName=#{l.relationName},type=#{l.type}," +
            "description=#{l.description},isFullLine=#{l.isFullLine} where id=#{l.id} and graphId=#{l.graphId}")
    void updateLink(@Param("l") LinkPO linkPO);

    @Select("select * from link where id=#{id} and graphId=#{graphId}")
    LinkPO getLink(@Param("id") Long id,@Param("graphId")Long graphId);
}
