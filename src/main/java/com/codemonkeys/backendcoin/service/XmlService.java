package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.VO.LinkVO;
import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import com.codemonkeys.backendcoin.VO.RelationVO;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;
import java.util.Set;

public interface XmlService {
    List<RelationGroupVO> getAllRelationGroup();

    Set<EntityVO> getAllEntity();

    Set<RelationVO> getAllRelation();

    Set<LinkVO> getAllLink();

    void addRelationGroup(List<RelationGroupVO> relationGroupVOList) throws TransformerException, ParserConfigurationException;
}
