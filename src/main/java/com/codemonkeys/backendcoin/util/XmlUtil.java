package com.codemonkeys.backendcoin.util;

import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.VO.RelationVO;
import com.codemonkeys.backendcoin.VO.RelationGroupVO;
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

/**
 * @author 吴旻轩
 * 处理XML的辅助类，使用DOM进行XML文档解析
 */

public class XmlUtil {
    private final static String ENTITY_TAG="Entity";
    private final static String RELATION_TAG="Relation";
    private final static String ENTITY_PROPERTY_ATTRIBUTE_NAME="property";
    private final static String ENTITY_SOURCE_ATTRIBUTE="source";
    private final static String ENTITY_TARGET_ATTRIBUTE="target";
    private final static String ID_ATTRIBUTE_NAME="id";
    private final static String DESCRIPTION_ATTRIBUTE_NAME="description";
    private final static String TYPE_ATTRIBUTE_NAME="type";


    /**
     * @author 吴旻轩
     * 解析本地*.xml的方法
     * 接收xml文件的路径
     * 返回VO对象RelationGroup的列表
     * @param  path
     * @return List<RelationGroup>
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public List<RelationGroupVO> resolveXml(String path) throws ParserConfigurationException, IOException, SAXException {
        List<RelationGroupVO> res=new ArrayList<>();

        //通过DocumentBuilderFactory创建DocumentBuilder，再使用DocumentBuilder分析path对应的xml文件
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document document=db.parse(path);

        //以RelationGroup为父节点，获取xml中的RelationGroup的NodeList
        NodeList nodeList=document.getElementsByTagName("RelationGroup");

        //遍历NodeList中的每一个RelationGroup节点
        for(int i=0;i<nodeList.getLength();i++){
            Node node=nodeList.item(i);
            NodeList childNodeList=node.getChildNodes();
            EntityVO source=null;
            EntityVO target=null;
            RelationVO relation=null;

            /**
             * childNodeList中包含RelationGroup下的所有子节点，<RelationGroup>和 </RelationGroup>两个标签内的所有内容都被看为子节点
             * 空格和换行被当作一个文本节点，标签与结束标签被当作一个元素节点
             * 因此需要先判断当前子节点是否是ELEMENT_NODE
             */

            for(int j=0;j<childNodeList.getLength();j++){
                if(childNodeList.item(j).getNodeType()==Node.ELEMENT_NODE){
                    Node childNode=childNodeList.item(j);
                    Element element=(Element)childNode;
                    String id=element.getAttribute(ID_ATTRIBUTE_NAME);
                    String description=element.getAttribute(DESCRIPTION_ATTRIBUTE_NAME);
                    String type=element.getAttribute(TYPE_ATTRIBUTE_NAME);
                    //<Entity>节点
                    if(childNode.getNodeName().equals(ENTITY_TAG)){
                        //property="source"
                        if(element.getAttribute(ENTITY_PROPERTY_ATTRIBUTE_NAME).equals(ENTITY_SOURCE_ATTRIBUTE)){
                            source=new EntityVO(id,type,childNode.getTextContent(),description);

                        }
                        //property="target"
                        else if(element.getAttribute(ENTITY_PROPERTY_ATTRIBUTE_NAME).equals(ENTITY_TARGET_ATTRIBUTE)){
                            target=new EntityVO(id,type,childNode.getTextContent(),description);

                        }
                    }
                    //<Relation>节点
                    else if(childNode.getNodeName().equals(RELATION_TAG)){
                        relation=new RelationVO(id,type,childNode.getTextContent(),description);
                    }
                }
            }
            res.add(new RelationGroupVO(source,target,relation));
        }

        return res;



    }
}
