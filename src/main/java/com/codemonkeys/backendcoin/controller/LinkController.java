package com.codemonkeys.backendcoin.controller;


import com.codemonkeys.backendcoin.VO.LinkVO;
import com.codemonkeys.backendcoin.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("link")
public class LinkController {
    LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
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
