package com.codemonkeys.backendcoin.controller;

import com.codemonkeys.backendcoin.VO.GraphVO;
import com.codemonkeys.backendcoin.service.GraphService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class GraphControllerTest {

    @Test
    public void testGetAllGraph() throws Exception{
        GraphService graphService = mock(GraphService.class);
        when(graphService.getAllGraph()).thenReturn(Arrays.asList(new GraphVO()));
        GraphController graphController = new GraphController(graphService);
        MockMvc mockMvc = standaloneSetup(graphController).build();
        mockMvc.perform(get("/graph/getAllGraph")).andExpect(status().isOk());
        verify(graphService).getAllGraph();
    }

    @Test
    public void testAddGraph() throws Exception{
        GraphService graphService = mock(GraphService.class);
        when(graphService.addGraph("a",1)).thenReturn(1L);
        GraphController graphController = new GraphController(graphService);
        MockMvc mockMvc = standaloneSetup(graphController).build();
        String res = mockMvc.perform(post("/graph/addGraph")
                .param("graphName","a")
                .param("userId","1"))
                .andReturn().getResponse().getContentAsString();
        Assert.assertEquals("1",res);
        verify(graphService).addGraph("a",1);
    }
}
