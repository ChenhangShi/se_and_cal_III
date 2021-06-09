package com.codemonkeys.backendcoin.controller;

import com.codemonkeys.backendcoin.service.TransService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trans")
public class TransController {
    TransService transService;

    @Autowired
    public TransController(TransService transService) {
        this.transService = transService;
    }
    @PostMapping("/extract")
    public void extract(@Param("actorId") int actorId, @Param("graphName")String graphName){
        try {
            transService.extract(actorId,graphName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/submit")
    public void submit(@Param("graphId")int graphId){
        try {
            transService.submit(graphId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/reload")
    public void reload(){
        transService.loadIntoDirectorToMovie();
    }

}
