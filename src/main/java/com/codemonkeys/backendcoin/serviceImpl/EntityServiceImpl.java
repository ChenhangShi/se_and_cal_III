package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.PO.EntityPO;
import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.mapper.EntityMapper;
import com.codemonkeys.backendcoin.mapper.LinkMapper;
import com.codemonkeys.backendcoin.service.EntityService;
import com.codemonkeys.backendcoin.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {
    EntityMapper entityMapper;
    LinkMapper linkMapper;
    Map map;

    @Autowired
    public EntityServiceImpl(EntityMapper entityMapper,LinkMapper linkMapper, Map map) {
        this.entityMapper = entityMapper;
        this.map = map;
        this.linkMapper=linkMapper;
    }

    @Override
    public List<EntityVO> getAllEntities(Long graphId) {
        List<EntityPO> entityPOList=entityMapper.getAllEntity(graphId);
        List<EntityVO> entityVOList=new ArrayList<>();
        for(EntityPO entityPO:entityPOList){
            entityVOList.add(map.from(entityPO));
        }
        return entityVOList;
    }

    @Override
    public void addEntities(List<EntityVO> entityVOList) {
        for(EntityVO entityVO:entityVOList){
            entityMapper.insertEntity(map.from(entityVO));
        }
    }

    @Override
    public void deleteEntities(List<Long> entityIdList,Long graphId) {
        for(Long id:entityIdList){
            entityMapper.deleteEntity(id,graphId);
            linkMapper.deleteLink(id,graphId);
        }
    }

    @Override
    public void updateEntities(List<EntityVO> entityVOList) {
        for(EntityVO entityVO:entityVOList){
            entityMapper.updateEntity(map.from(entityVO));
        }
    }
}