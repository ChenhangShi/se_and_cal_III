package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.Entity;
import com.codemonkeys.backendcoin.VO.Relation;
import com.codemonkeys.backendcoin.VO.RelationGroup;
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
    public List<RelationGroup> getAllRelations()  {
        List<RelationGroup> res=new ArrayList<>();
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
