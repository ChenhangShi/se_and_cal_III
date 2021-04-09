package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.EntityPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
public interface EntityMapper {
    List<EntityPO> getAllEntity();


    @Insert("insert into entity(id,graphId,name,type,shape,description,x,y) value (#{p.id},#{p.graphId}" +
            ",#{p.name},#{p.type},#{p.shape},#{p.description},#{p.x},#{p.y}) ")
    void insertEntity(@Param("p") EntityPO entityPO);

    @Delete("delete from entity where id=#{id}")
    void deleteEntity(@Param("id") Long id);

    @Update("update entity set name=#{p.name},type=#{p.type},shape=#{p.shape}," +
            "description=#{p.description},x=#{p.x},y=#{p.y} where id=#{p.id} and graphId=#{p.graphId}")
    void updateEntity(@Param("p") EntityPO entityPO);
}
