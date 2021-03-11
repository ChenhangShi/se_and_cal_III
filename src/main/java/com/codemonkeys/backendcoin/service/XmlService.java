package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import com.codemonkeys.backendcoin.util.XmlUtil;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service

public class XmlService {
    XmlUtil xmlUtil=new XmlUtil();
    public List<RelationGroupVO> getAllRelations()  {
        List<RelationGroupVO> res=new ArrayList<>();
        try {
            res=xmlUtil.resolveXml("src/main/resources/static/test.xml");
        }
        catch (ParserConfigurationException parserConfigurationException){
            parserConfigurationException.printStackTrace();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        catch (SAXException saxException){
            saxException.printStackTrace();
        }
        return res;


    }
}
