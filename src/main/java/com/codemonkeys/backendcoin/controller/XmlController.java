package com.codemonkeys.backendcoin.controller;


import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.VO.LinkVO;
import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import com.codemonkeys.backendcoin.VO.RelationVO;
import com.codemonkeys.backendcoin.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/xml")
public class XmlController {
    @Autowired
    XmlService xmlService;

    @GetMapping("/getRelationGroup")
    public List<RelationGroupVO> getRelations(){
        return xmlService.getAllRelationGroup();
    }

    @GetMapping("/getEntity")
    public Set<EntityVO> getEntity(){
        return xmlService.getAllEntity();
    }

    @GetMapping("/getRelation")
    public Set<RelationVO> getRelation(){
        return xmlService.getAllRelation();
    }

    @GetMapping("/getLink")
    public Set<LinkVO> getLink(){
        return xmlService.getAllLink();
    }

    @PostMapping("/postRelationGroup")
    public void addRelationGroup(@RequestBody List<RelationGroupVO> relationGroupVOList) throws TransformerException, ParserConfigurationException {
            xmlService.addRelationGroup(relationGroupVOList);
    }


}
