package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.PO.GraphPO;
import com.codemonkeys.backendcoin.VO.GraphVO;
import com.codemonkeys.backendcoin.mapper.GraphMapper;
import com.codemonkeys.backendcoin.service.GraphService;
import com.codemonkeys.backendcoin.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GraphServiceImpl implements GraphService {
    GraphMapper graphMapper;
    Map map;

    @Autowired
    public GraphServiceImpl(GraphMapper graphMapper,Map map) {
        this.graphMapper = graphMapper;
        this.map=map;
    }

    @Override
    public List<GraphVO> getAllGraph() {
        List<GraphPO> graphPOList=graphMapper.getAllGraph();
        List<GraphVO> graphVOList=new ArrayList<>();
        for(GraphPO graphPO:graphPOList){
            graphVOList.add(map.from(graphPO));
        }
        return graphVOList;
    }

    @Override
    public Long addGraph(String graphName) {
        if(graphName==null||graphName.equals("")){
            return -1L;
        }
        GraphPO graphPO=new GraphPO();
        graphPO.graphName=graphName;
        graphMapper.insertGraph(graphPO);
        return graphPO.graphId;
    }
}
