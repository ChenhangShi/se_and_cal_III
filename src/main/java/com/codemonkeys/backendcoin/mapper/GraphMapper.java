package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.GraphPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphMapper {

    @Select("select * from graph")
    List<GraphPO> getAllGraph();

    @Insert("insert into graph (graphName) values (#{g.graphName})")
    @Options(useGeneratedKeys = true,keyProperty = "graphId",keyColumn = "graphId")
    void insertGraph(@Param("g")GraphPO graphPO);
}
