package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.GraphPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphMapper {

    @Select("select * from graph")
    List<GraphPO> getAllGraph();

    @Select("select * from graph where graphId = #{graphId}")
    GraphPO getGraphById(@Param("graphId") Long graphId);

    @Insert("insert into graph (graphName,userId) values (#{g.graphName},#{g.userId})")
    @Options(useGeneratedKeys = true,keyProperty = "graphId",keyColumn = "graphId")
    void insertGraph(@Param("g")GraphPO graphPO);

    @Select("select * from graph where userId=#{userId}")
    List<GraphPO> getGraphsByUserId(@Param("userId") int userId);

    @Delete("delete from graph where graphId=#{graphId}")
    void deleteGraphById(@Param("graphId")int graphId);
}
