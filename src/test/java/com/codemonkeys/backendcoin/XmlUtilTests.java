package com.codemonkeys.backendcoin;

import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import com.codemonkeys.backendcoin.util.XmlUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class XmlUtilTests {
    @Test
    public void testResolveXml(){
        XmlUtil xmlUtil = new XmlUtil();
        try {
            List<RelationGroupVO> list = xmlUtil.resolveXml();
            for (RelationGroupVO relationGroupVO:list){
                System.out.println(relationGroupVO.getSource().getName());
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }catch (Exception e){

        }
    }
}
