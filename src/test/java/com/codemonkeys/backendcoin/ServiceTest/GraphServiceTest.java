package com.codemonkeys.backendcoin.ServiceTest;

import com.codemonkeys.backendcoin.BackendCoinApplication;
import com.codemonkeys.backendcoin.VO.GraphVO;
import com.codemonkeys.backendcoin.service.GraphService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BackendCoinApplication.class})
public class GraphServiceTest {
    @Autowired
    GraphService graphService;

    @Test
    public void testAddGraph(){
        Long res=graphService.addGraph("newGraph");
        Assert.assertEquals(2l,(long)res);
    }

    @Test
    public void testGetAllGraph(){
        List<GraphVO> graphVOList=graphService.getAllGraph();
        for(GraphVO graphVO:graphVOList){
            System.out.print(graphVO.graphName);
        }
    }
}
