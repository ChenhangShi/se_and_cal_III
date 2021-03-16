package com.codemonkeys.backendcoin;

import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import com.codemonkeys.backendcoin.VO.RelationVO;
import com.codemonkeys.backendcoin.util.XmlUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class XmlUtilTests {
    XmlUtil xmlUtil=new XmlUtil();
    @BeforeEach
    public void setUp(){
        XmlUtil.setXmlPath("output.xml");
        xmlUtil=new XmlUtil();
        System.out.println("pre");
    }

    @Test
    public void testResolveXml() throws TransformerException, ParserConfigurationException, IOException, SAXException {
//        XmlUtil.setXmlPath("output.xml");
        String json="[{\"source\":{\"id\":\"1\",\"name\":\"s\",\"type\":\"type_0\",\"description\":\"d1\"},\"target\":{\"id\":\"2\",\"name\":\"t\",\"type\":\"type_1\",\"description\":\"d2\"},\"relation\":{\"id\":\"0\",\"name\":\"relation\",\"type\":\"relation_type\",\"description\":\"r1\"}}]";
        EntityVO source=new EntityVO("1","type_0","s","d1");
        EntityVO target=new EntityVO("2","type_1","t","s2");
        RelationVO relation=new RelationVO("0","relation_type","relation","r1");
        RelationGroupVO relationGroupVO=new RelationGroupVO(source,target,relation);

        List<RelationGroupVO> r=new ArrayList<>();
        r.add(relationGroupVO);
        xmlUtil.beanToXml(r);
        Assert.assertEquals(r.get(0).equals(xmlUtil.resolveXml().get(0)),true);

    }
}
