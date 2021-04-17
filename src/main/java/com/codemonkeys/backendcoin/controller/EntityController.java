package com.codemonkeys.backendcoin.controller;


import com.codemonkeys.backendcoin.VO.DeleteNodeVO;
import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/entity")
public class EntityController {
    EntityService entityService;
    @Autowired
    public EntityController(EntityService entityService){
        this.entityService=entityService;
    }

    @GetMapping("/getAllNode")
    public List<EntityVO> getAllEntities(@RequestParam Long graphId){
        return entityService.getAllEntities(graphId);
    }

    @PostMapping("/addNodes")
    public void addEntities(@RequestBody List<EntityVO> entityVOList){
        entityService.addEntities(entityVOList);
    }

    @PostMapping("/deleteNodes")
    public void deleteEntities(@RequestBody DeleteNodeVO deleteNodeVO){
        List<String> entityNodeStringList= deleteNodeVO.entityNodeIdList;
        List<Long> entityNodeList=new ArrayList<>();
        System.out.println(entityNodeStringList==null);
        for(String s:entityNodeStringList){
            entityNodeList.add(Long.parseLong(s));
        }
        Long graphId= Long.parseLong(deleteNodeVO.graphId);
        entityService.deleteEntities(entityNodeList,graphId);
    }

    @PostMapping("/updateNodes")
    public void updateEntities(@RequestBody List<EntityVO> entityVOList){
        entityService.updateEntities(entityVOList);
    }


}
