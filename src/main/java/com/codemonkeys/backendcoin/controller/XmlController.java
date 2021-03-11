package com.codemonkeys.backendcoin.controller;


import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import com.codemonkeys.backendcoin.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/xml")
public class XmlController {
    @Autowired
    XmlService xmlService;
    @GetMapping("/getRelations")
    public List<RelationGroupVO> getRelations(){
        return xmlService.getAllRelations();
    }
}
