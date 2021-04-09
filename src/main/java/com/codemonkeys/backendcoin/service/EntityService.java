package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.EntityVO;

import java.util.List;

public interface EntityService {
    void addEntities(List<EntityVO> entityVOList);
    void deleteEntities(List<Long> entityIdList);
    void updateEntities(List<EntityVO> entityVOList);
}
