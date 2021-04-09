package com.codemonkeys.backendcoin.mapper;


import com.codemonkeys.backendcoin.PO.LinkPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkMapper {
    List<LinkPO> getAllLink();

    @Insert("insert into link(id,sourceId,targetId,graphId,relationName,type,description,isFullLine) value(#{l.id},#{l.sourceId}" +
            ",#{l.targetId},#{l.graphId},#{l.relationName},#{l.type},#{l.description},#{l.isFullLine})")
    void insertLink(@Param("l") LinkPO linkPO);

    @Delete("delete from link where sourceId=#{nodeId} or targetId=#{nodeId}")
    void deleteLink(@Param("nodeId") Long nodeId);


    @Update("update link set sourceId=#{l.sourceId},targetId=#{l.targetId},relationName=#{l.relationName},type=#{l.type}," +
            "description=#{l.description},isFullLine=#{l.isFullLine} where id=#{l.id} and graphId=#{l.graphId}")
    void updateLink(@Param("l") LinkPO linkPO);
}
