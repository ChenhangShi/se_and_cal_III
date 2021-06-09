package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.EntityPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityMapper {

    @Select("select * from entity where graphId=#{graphId} and id=#{id}")
    @Results(
            value = {
                    @Result(property = "nodeType",column = "type")
            }
    )
    EntityPO getEntity(@Param("graphId")Long graphId,@Param("id")Long id);


    @Results(
            value = {
                    @Result(property = "nodeType",column = "type")
            }
    )
    @Select("select * from entity where graphId=#{graphId}")
    List<EntityPO> getAllEntity(@Param("graphId")Long graphId);
    
    @Insert("insert into entity(graphId,name,type,shape,description,x,y) value (#{p.graphId}" +
            ",#{p.name},#{p.nodeType},#{p.shape},#{p.description},#{p.x},#{p.y}) ")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void insertEntity(@Param("p") EntityPO entityPO);

    @Delete("delete from entity where id=#{id} and graphId=#{graphId}")
    void deleteEntity(@Param("id") Long id,@Param("graphId")Long GraphId);

    @Update("update entity set name=#{p.name},type=#{p.type},shape=#{p.shape}," +
            "description=#{p.description},x=#{p.x},y=#{p.y} where id=#{p.id} and graphId=#{p.graphId}")
    void updateEntity(@Param("p") EntityPO entityPO);
}
