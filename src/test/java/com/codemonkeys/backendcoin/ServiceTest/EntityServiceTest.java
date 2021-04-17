package com.codemonkeys.backendcoin.ServiceTest;

import com.codemonkeys.backendcoin.BackendCoinApplication;
import com.codemonkeys.backendcoin.PO.EntityPO;
import com.codemonkeys.backendcoin.PO.LinkPO;
import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.mapper.EntityMapper;
import com.codemonkeys.backendcoin.mapper.LinkMapper;
import com.codemonkeys.backendcoin.service.EntityService;
import com.codemonkeys.backendcoin.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BackendCoinApplication.class})
@Transactional
public class EntityServiceTest {
    @Autowired
    EntityService entityService;
    @Autowired
    EntityMapper entityMapper;
    @Autowired
    LinkMapper linkMapper;
    @Autowired
    Map map;

    @Test
    public void testGetAllEntities() throws Exception{
        List<EntityPO> entityPOList = Arrays.asList(
                new EntityPO(){{this.id=0L;this.graphId=Long.MAX_VALUE;this.description="";this.name="";this.shape="";this.type="";this.x="1";this.y="1";}},
                new EntityPO(){{this.id=1L;this.graphId=Long.MAX_VALUE;this.description="";this.name="";this.shape="";this.type="";this.x="1";this.y="1";}}
        );
        for(EntityPO entityPO:entityPOList){
            entityMapper.insertEntity(entityPO);
        }
        List<EntityVO> resList = entityService.getAllEntities(Long.MAX_VALUE);
        Assert.assertEquals(2,resList.size());
    }

    @Test
    public void testAddEntities() throws Exception{
        List<EntityVO> entityVOList = Arrays.asList(
                new EntityVO(0L,Long.MAX_VALUE,"","a","","1","1",""),
                new EntityVO(1L,Long.MAX_VALUE,"","b","","1","1","")
        );
        entityService.addEntities(entityVOList);
        EntityPO entityPO = entityMapper.getEntity(Long.MAX_VALUE,0L);
        Assert.assertEquals("a",entityPO.name);
        entityPO = entityMapper.getEntity(Long.MAX_VALUE,1L);
        Assert.assertEquals("b",entityPO.name);
    }

    @Test
    public void testDeleteEntities() throws Exception{
        List<EntityPO> entityPOList = Arrays.asList(
                new EntityPO(){{this.id=0L;this.graphId=Long.MAX_VALUE;this.description="";this.name="";this.shape="";this.type="";this.x="1";this.y="1";}},
                new EntityPO(){{this.id=1L;this.graphId=Long.MAX_VALUE;this.description="";this.name="";this.shape="";this.type="";this.x="1";this.y="1";}}
        );
        for(EntityPO entityPO:entityPOList){
            entityMapper.insertEntity(entityPO);
        }
        LinkPO linkPO = new LinkPO(){{this.id=Long.MAX_VALUE;this.graphId=Long.MAX_VALUE;this.sourceId=0L;this.targetId=1L;this.description="";this.relationName="";this.type="";this.isFullLine=false;}};
        linkMapper.insertLink(linkPO);
        entityService.deleteEntities(Arrays.asList(0L),Long.MAX_VALUE);

        List<EntityPO> res_entity = entityMapper.getAllEntity(Long.MAX_VALUE);
        List<LinkPO> res_link = linkMapper.getAllLink(Long.MAX_VALUE);
        Assert.assertEquals(1,res_entity.size());
        Assert.assertEquals(0,res_link.size());
    }

    @Test
    public void testUpdateEntities() throws Exception{
        EntityPO entityPO = new EntityPO(){{this.id=0L;this.graphId=Long.MAX_VALUE;this.description="";this.name="";this.shape="";this.type="";this.x="1";this.y="1";}};
        entityMapper.insertEntity(entityPO);
        entityService.updateEntities(Arrays.asList(
                new EntityVO(0L,Long.MAX_VALUE,"","a","","1","1","")
        ));
        entityPO = entityMapper.getEntity(Long.MAX_VALUE,0L);
        Assert.assertEquals("a",entityPO.name);
    }
}
