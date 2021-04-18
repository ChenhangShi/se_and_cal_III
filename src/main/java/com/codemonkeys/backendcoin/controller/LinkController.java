package com.codemonkeys.backendcoin.controller;


import com.codemonkeys.backendcoin.VO.LinkVO;
import com.codemonkeys.backendcoin.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/link")
public class LinkController {
    LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/getAllLink/{graphId}")
    public List<LinkVO> getAllLinks(@PathVariable String graphId){
        return linkService.getAllLinks(Long.parseLong(graphId));
    }

    @PostMapping("/addLinks")
    public void addLinks(@RequestBody List<LinkVO> linkVOList){
        linkService.addLink(linkVOList);
    }

    @PostMapping("/updateLinks")
    public void updateLinks(@RequestBody List<LinkVO> linkVOList){
        linkService.updateLink(linkVOList);
    }
}
