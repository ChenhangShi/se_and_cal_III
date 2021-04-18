package com.codemonkeys.backendcoin.ControllerTest;

import com.alibaba.fastjson.JSON;
import com.codemonkeys.backendcoin.VO.DeleteNodeVO;
import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.controller.EntityController;
import com.codemonkeys.backendcoin.service.EntityService;

import org.junit.Test;
import static org.mockito.Mockito.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityControllerTest {

    @Test
    public void testGetAllEntities() throws Exception {
        EntityService entityService = mock(EntityService.class);
        when(entityService.getAllEntities(1L)).thenReturn(
                new ArrayList<EntityVO>(){{
                    add(new EntityVO(1L,1L,"a","a","a","1","1","circle"));
                }});
        EntityController entityController = new EntityController(entityService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(entityController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/entity/getAllNode/1")).andExpect(status().isOk());
        verify(entityService).getAllEntities(1L);
    }

    @Test
    public void testAddEntities() throws Exception{
        List<EntityVO> entityVOList = Arrays.asList(new EntityVO(1L,1L,"a","a","a","1","1","circle"));
        String json_content = JSON.toJSONString(entityVOList);
        EntityService entityService = mock(EntityService.class);
        EntityController entityController = new EntityController(entityService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(entityController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/entity/addNodes")
                .content(json_content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(entityService).addEntities(entityVOList);
    }

    @Test
    public void testDeleteEntities() throws Exception{
        DeleteNodeVO deleteNodeVO = new DeleteNodeVO(Arrays.asList("1"),"1");
        String json_content = JSON.toJSONString(deleteNodeVO);
        EntityService entityService = mock(EntityService.class);
        EntityController entityController = new EntityController(entityService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(entityController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/entity/deleteNodes")
                .content(json_content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(entityService).deleteEntities(Arrays.asList(1L),1L);
    }

    @Test
    public void testUpdateEntities() throws Exception{
        List<EntityVO> entityVOList = Arrays.asList(new EntityVO(1L,1L,"a","a","a","1","1","circle"));
        String json_content = JSON.toJSONString(entityVOList);
        EntityService entityService = mock(EntityService.class);
        EntityController entityController = new EntityController(entityService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(entityController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/entity/updateNodes")
                .content(json_content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(entityService).updateEntities(entityVOList);
    }
}
