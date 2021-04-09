package com.codemonkeys.backendcoin.controller;


import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entity")
public class EntityController {
    EntityService entityService;
    @Autowired
    public EntityController(EntityService entityService){
        this.entityService=entityService;
    }

    @PostMapping("/addNodes")
    public void addEntities(@RequestBody List<EntityVO> entityVOList){
        entityService.addEntities(entityVOList);
    }

    @PostMapping("/deleteNodes")
    public void deleteEntities(@RequestBody List<Long> entityNodeList){
        entityService.deleteEntities(entityNodeList);
    }

    @PostMapping("updateNodes")
    public void updateEntities(@RequestBody List<EntityVO> entityVOList){
        entityService.updateEntities(entityVOList);
    }
}
