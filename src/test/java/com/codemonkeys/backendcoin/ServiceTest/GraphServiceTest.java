package com.codemonkeys.backendcoin.ServiceTest;

import com.codemonkeys.backendcoin.BackendCoinApplication;
import com.codemonkeys.backendcoin.VO.GraphVO;
import com.codemonkeys.backendcoin.mapper.GraphMapper;
import com.codemonkeys.backendcoin.service.GraphService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BackendCoinApplication.class})
@Transactional
public class GraphServiceTest {
    @Autowired
    GraphService graphService;
    @Autowired
    GraphMapper graphMapper;

    @Test
    public void testAddGraph(){
        Long res=graphService.addGraph("newGraph");
        Assert.assertEquals("newGraph",graphMapper.getGraphById(res).graphName);
    }

    @Test
    public void testGetAllGraph(){
        List<GraphVO> graphVOList=graphService.getAllGraph();
        for(GraphVO graphVO:graphVOList){
            System.out.print(graphVO.graphName);
        }
    }
}
