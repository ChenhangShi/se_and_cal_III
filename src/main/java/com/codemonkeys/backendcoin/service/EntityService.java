package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.EntityVO;

import java.util.List;

public interface EntityService {
    List<EntityVO> getAllEntities();
    void addEntities(List<EntityVO> entityVOList);
    void deleteEntities(List<Long> entityIdList,Long graphId);
    void updateEntities(List<EntityVO> entityVOList);
}
