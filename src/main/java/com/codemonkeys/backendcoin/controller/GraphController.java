package com.codemonkeys.backendcoin.controller;


import com.codemonkeys.backendcoin.VO.GraphVO;
import com.codemonkeys.backendcoin.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addGraph")
    public Long addGraph(@RequestParam String graphName,@RequestParam int userId){
        return graphService.addGraph(graphName,userId);
    }

    @PostMapping("/deleteGraph")
    public void deleteGraph(@RequestParam int graphId){
        graphService.deleteGraph(graphId);
    }

    @GetMapping("/getUserGraph")
    public List<GraphVO> getUserGraph(@RequestParam int userId){
        return graphService.getUserGraph(userId);
    }
}
