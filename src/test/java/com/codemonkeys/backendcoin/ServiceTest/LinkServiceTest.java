package com.codemonkeys.backendcoin.ServiceTest;

import com.codemonkeys.backendcoin.BackendCoinApplication;
import com.codemonkeys.backendcoin.VO.LinkVO;
import com.codemonkeys.backendcoin.mapper.LinkMapper;
import com.codemonkeys.backendcoin.service.LinkService;
import com.codemonkeys.backendcoin.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BackendCoinApplication.class})
public class LinkServiceTest {
    @Autowired
    LinkService linkService;
    @Autowired
    LinkMapper linkMapper;
    @Autowired
    Map map;

    @Test
    public void testAddLink(){
        LinkVO linkVO=new LinkVO(35L,8L,9L,"testLink"
                ,"blue","testAddLink",2L,false);
        List<LinkVO> testLinkVOList=new ArrayList<>();
        testLinkVOList.add(linkVO);
        linkService.addLink(testLinkVOList);
        Assert.assertEquals(linkVO,map.from(linkMapper.getLink(35L)));
    }

    @Test
    public void testUpdateLink(){
        LinkVO linkVO=new LinkVO(35L,10L,9L,"testLink"
                ,"blue","testUpdateLink",2L,true);

        List<LinkVO> testLinkVOList=new ArrayList<>();
        testLinkVOList.add(linkVO);
        linkService.updateLink(testLinkVOList);
        Assert.assertEquals(linkVO,map.from(linkMapper.getLink(35L)));
    }
}
