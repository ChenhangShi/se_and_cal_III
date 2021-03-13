package com.codemonkeys.backendcoin.controller;


import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import com.codemonkeys.backendcoin.VO.RelationVO;
import com.codemonkeys.backendcoin.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/xml")
public class XmlController {
    @Autowired
    XmlService xmlService;
    @GetMapping("/getRelationGroup")
    public List<RelationGroupVO> getRelations(){
        return xmlService.getAllRelations();
    }

    @GetMapping("/getEntity")
    public Set<EntityVO> getEntity(){
        return xmlService.getAllEntity();
    }

    @GetMapping("/getRelation")
    public Set<RelationVO> getRelation(){
        return xmlService.getAllRelation();
    }
}
