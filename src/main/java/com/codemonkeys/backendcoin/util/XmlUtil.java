package com.codemonkeys.backendcoin.util;

import com.codemonkeys.backendcoin.VO.Entity;
import com.codemonkeys.backendcoin.VO.Relation;
import com.codemonkeys.backendcoin.VO.RelationGroup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlUtil {
    private final static String ENTITY_TAG="Entity";
    private final static String RELATION_TAG="Relation";
    private final static String ENTITY_PROPERTY_ATTRIBUTE_NAME="property";
    private final static String ENTITY_SOURCE_ATTRIBUTE="source";
    private final static String ENTITY_TARGET_ATTRIBUTE="target";
    public List<RelationGroup> resolveXml(String path) throws ParserConfigurationException, IOException, SAXException {
        List<RelationGroup> res=new ArrayList<>();

        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document document=db.parse(path);

        NodeList nodeList=document.getElementsByTagName("RelationGroup");

        for(int i=0;i<nodeList.getLength();i++){
            Node node=nodeList.item(i);
            NodeList childNodeList=node.getChildNodes();
            Entity source=null;
            Entity target=null;
            Relation relation=null;
            for(int j=0;j<childNodeList.getLength();j++){
                if(childNodeList.item(j).getNodeType()==Node.ELEMENT_NODE){
                    Node childNode=childNodeList.item(j);
                    if(childNode.getNodeName().equals(ENTITY_TAG)){
                        Element element=(Element)childNode;
                        if(element.getAttribute(ENTITY_PROPERTY_ATTRIBUTE_NAME).equals(ENTITY_SOURCE_ATTRIBUTE)){
                            source=new Entity(childNode.getTextContent());
                        }
                        else if(element.getAttribute(ENTITY_PROPERTY_ATTRIBUTE_NAME).equals(ENTITY_TARGET_ATTRIBUTE)){
                            target=new Entity(childNode.getTextContent());
                        }
                    }
                    else if(childNode.getNodeName().equals(RELATION_TAG)){
                        relation=new Relation(childNode.getTextContent());
                    }
                }
            }
            res.add(new RelationGroup(source,target,relation));
        }

        return res;



    }
}
