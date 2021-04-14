package com.codemonkeys.backendcoin.controller;


import com.codemonkeys.backendcoin.VO.GraphVO;
import com.codemonkeys.backendcoin.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphController {
    GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/getAllGraph")
    public List<GraphVO> getAllGraph(){
        return graphService.getAllGraph();
    }

    @RequestMapping("/addGraph")
    public Long addGraph(@RequestParam String graphName){
        return graphService.addGraph(graphName);
    }
}
