package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.mapper.EntityMapper;
import com.codemonkeys.backendcoin.mapper.LinkMapper;
import com.codemonkeys.backendcoin.service.EntityService;
import com.codemonkeys.backendcoin.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addEntities(List<EntityVO> entityVOList) {
        for(EntityVO entityVO:entityVOList){
            entityMapper.insertEntity(map.from(entityVO));
        }
    }

    @Override
    public void deleteEntities(List<Long> entityIdList) {
        for(Long id:entityIdList){
            entityMapper.deleteEntity(id);
            linkMapper.deleteLink(id);
        }
    }

    @Override
    public void updateEntities(List<EntityVO> entityVOList) {
        for(EntityVO entityVO:entityVOList){
            entityMapper.updateEntity(map.from(entityVO));
        }
    }
}
