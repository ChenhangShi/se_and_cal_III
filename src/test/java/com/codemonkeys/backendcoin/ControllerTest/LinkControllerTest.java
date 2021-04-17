package com.codemonkeys.backendcoin.ControllerTest;

import com.alibaba.fastjson.JSON;
import com.codemonkeys.backendcoin.VO.LinkVO;
import com.codemonkeys.backendcoin.controller.LinkController;
import com.codemonkeys.backendcoin.service.LinkService;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LinkControllerTest {
    @Test
    public void testGetAllLinks() throws Exception{
        LinkService linkService = mock(LinkService.class);
        when(linkService.getAllLinks(1L)).thenReturn(Arrays.asList(new LinkVO(1L,1L,2L,"r","rt","d",1L,true)));
        LinkController linkController = new LinkController(linkService);
        MockMvc mockMvc = standaloneSetup(linkController).build();
        mockMvc.perform(get("/link/getAllLink").param("graphId","1")).andExpect(status().isOk());
        verify(linkService).getAllLinks(1L);
    }

    @Test
    public void testAddLinks() throws Exception{
        List<LinkVO> linkVOList = Arrays.asList(new LinkVO(1L,1L,2L,"r","rt","d",1L,true));
        LinkService linkService = mock(LinkService.class);
        LinkController linkController = new LinkController(linkService);
        MockMvc mockMvc = standaloneSetup(linkController).build();
        mockMvc.perform(post("/link/addLinks").content(JSON.toJSONString(linkVOList)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(linkService).addLink(linkVOList);
    }

    @Test
    public void testUpdateLinks() throws Exception{
        List<LinkVO> linkVOList = Arrays.asList(new LinkVO(1L,1L,2L,"r","rt","d",1L,true));
        LinkService linkService = mock(LinkService.class);
        LinkController linkController = new LinkController(linkService);
        MockMvc mockMvc = standaloneSetup(linkController).build();
        mockMvc.perform(post("/link/updateLinks").content(JSON.toJSONString(linkVOList)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(linkService).updateLink(linkVOList);
    }
}
