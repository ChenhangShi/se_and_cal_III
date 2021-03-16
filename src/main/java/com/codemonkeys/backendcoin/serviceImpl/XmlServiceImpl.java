package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.VO.LinkVO;
import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import com.codemonkeys.backendcoin.VO.RelationVO;
import com.codemonkeys.backendcoin.service.XmlService;
import com.codemonkeys.backendcoin.util.XmlUtil;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class XmlServiceImpl implements XmlService {
    XmlUtil xmlUtil=new XmlUtil();
    @Override
    public List<RelationGroupVO> getAllRelationGroup()  {
        List<RelationGroupVO> res=new ArrayList<>();
        try {
            res=xmlUtil.resolveXml();
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

    @Override
    public Set<EntityVO> getAllEntity(){
        List<RelationGroupVO> relationGroupVOList=getAllRelationGroup();
        Set<EntityVO> res=new HashSet<>();
        for(RelationGroupVO r:relationGroupVOList){
            res.add(r.getSource());
            res.add(r.getTarget());
        }
        return res;
    }

    @Override
    public Set<RelationVO> getAllRelation(){
        List<RelationGroupVO> relationGroupVOList=getAllRelationGroup();
        Set<RelationVO> res=new HashSet<>();
        for(RelationGroupVO r:relationGroupVOList){
            res.add(r.getRelation());
        }
        return res;
    }

    @Override
    public Set<LinkVO> getAllLink(){
        List<RelationGroupVO> relationGroupVOList=getAllRelationGroup();
        Set<LinkVO> res=new HashSet<>();
        for(RelationGroupVO r:relationGroupVOList){
            res.add(new LinkVO(r));
        }
        return res;
    }

    @Override
    public void addRelationGroup(List<RelationGroupVO> relationGroupVOList) throws TransformerException, ParserConfigurationException {
        xmlUtil.beanToXml(relationGroupVOList);
    }
}