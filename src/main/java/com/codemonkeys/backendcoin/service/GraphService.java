package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.GraphVO;

import java.util.List;

public interface GraphService {
    List<GraphVO> getAllGraph();
    Long addGraph(String graphName,int userId);
    List<GraphVO> getUserGraph(int userId);
    void deleteGraph(int graphId);
}
