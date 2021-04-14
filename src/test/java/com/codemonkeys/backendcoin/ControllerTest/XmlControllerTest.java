package com.codemonkeys.backendcoin.ControllerTest;

import com.codemonkeys.backendcoin.controller.XmlController;
import com.codemonkeys.backendcoin.service.XmlService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest
@WebAppConfiguration

public class XmlControllerTest {


    @Test
    public void testGetRelationGroup() throws Exception {
        XmlController mockXmlController=mock(XmlController.class);
        MockMvc mockMvc=standaloneSetup(mockXmlController).build();

        MvcResult mvcResult=mockMvc.perform(get("/xml/getRelationGroup")).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetEntity() throws Exception {
        XmlController mockXmlController=mock(XmlController.class);
        MockMvc mockMvc=standaloneSetup(mockXmlController).build();

        MvcResult mvcResult=mockMvc.perform(get("/xml/getEntity")).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetRelation() throws Exception {
        XmlController mockXmlController=mock(XmlController.class);
        MockMvc mockMvc=standaloneSetup(mockXmlController).build();

        MvcResult mvcResult=mockMvc.perform(get("/xml/getRelation")).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetLink() throws Exception {
        XmlController mockXmlController=mock(XmlController.class);
        MockMvc mockMvc=standaloneSetup(mockXmlController).build();

        MvcResult mvcResult=mockMvc.perform(get("/xml/getLink")).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }

}
