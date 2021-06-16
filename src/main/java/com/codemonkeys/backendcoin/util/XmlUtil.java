package com.codemonkeys.backendcoin.util;

import com.codemonkeys.backendcoin.Enum.LinkType;
import com.codemonkeys.backendcoin.Enum.NodeType;
import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.VO.RelationVO;
import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴旻轩
 * 处理XML的辅助类，使用DOM进行XML文档解析
 */
@Component
public class XmlUtil {
    private static String xmlPath;

    private final static String ENTITY_TAG="Entity";
    private final static String RELATION_TAG="Relation";
    private final static String ENTITY_PROPERTY_ATTRIBUTE_NAME="property";
    private final static String ENTITY_SOURCE_ATTRIBUTE="source";
    private final static String ENTITY_TARGET_ATTRIBUTE="target";
    private final static String ID_ATTRIBUTE_NAME="id";
    private final static String DESCRIPTION_ATTRIBUTE_NAME="description";
    private final static String TYPE_ATTRIBUTE_NAME="type";
    private final static String ISFULLLINE_ATTRIBUTE_NAME="isFullLine";
    private final static String X_ATTRIBUTE_NAME="x";
    private final static String Y_ATTRIBUTE_NAME="y";
    private final static String SHAPE_ATTRIBUTE_NAME="shape";
    private final static String GRAPHID_ATTRIBUTE_NAME="graphId";

    /**
     * 设置xml文件的路径
     * @param path
     * path由main函数的args获得
     * 在main函数开头被调用
     */
    public static void setXmlPath(String path){
        xmlPath = path;
    }


    /**
     * @author 吴旻轩
     * 解析本地*.xml的方法
     * 接收xml文件的路径
     * 返回VO对象RelationGroup的列表
     * @return List<RelationGroup>
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public List<RelationGroupVO> resolveXml(String tmpLocalPath) throws ParserConfigurationException, IOException, SAXException {
        List<RelationGroupVO> res=new ArrayList<>();

        //通过DocumentBuilderFactory创建DocumentBuilder，再使用DocumentBuilder分析path对应的xml文件
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document document=db.parse(new FileInputStream(tmpLocalPath));

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
                    Long id=Long.parseLong(element.getAttribute(ID_ATTRIBUTE_NAME));
                    String description=element.getAttribute(DESCRIPTION_ATTRIBUTE_NAME);
                    String type=element.getAttribute(TYPE_ATTRIBUTE_NAME);
                    //<Entity>节点
                    if(childNode.getNodeName().equals(ENTITY_TAG)){
                        String x=element.getAttribute(X_ATTRIBUTE_NAME);
                        String y=element.getAttribute(Y_ATTRIBUTE_NAME);
                        String shape=element.getAttribute(SHAPE_ATTRIBUTE_NAME);
                        Long graphId=Long.parseLong(element.getAttribute(GRAPHID_ATTRIBUTE_NAME));
                        //property="source"
                        if(element.getAttribute(ENTITY_PROPERTY_ATTRIBUTE_NAME).equals(ENTITY_SOURCE_ATTRIBUTE)){

                            source=new EntityVO(id,graphId, NodeType.valueOf(type),childNode.getTextContent(),description,x,y,shape);

                        }
                        //property="target"
                        else if(element.getAttribute(ENTITY_PROPERTY_ATTRIBUTE_NAME).equals(ENTITY_TARGET_ATTRIBUTE)){
                            target=new EntityVO(id,graphId,NodeType.valueOf(type),childNode.getTextContent(),description,x,y,shape);

                        }
                    }
                    //<Relation>节点
                    else if(childNode.getNodeName().equals(RELATION_TAG)){
                        boolean isFullLine=element.getAttribute(ISFULLLINE_ATTRIBUTE_NAME).equals("true");
                        relation=new RelationVO(id, LinkType.valueOf(type),childNode.getTextContent(),description,isFullLine);
                    }
                }
            }
            res.add(new RelationGroupVO(source,target,relation));
        }
        return res;
    }

    /**
     * @author 吴旻轩
     * 将List<RelationGroupVO>持久化为本地xml文件的方法
     * @param relationGroupVOList
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void beanToXml(List<RelationGroupVO> relationGroupVOList,File file) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
        Document document=documentBuilder.newDocument();

        Element root=document.createElement("root");
        for(RelationGroupVO r:relationGroupVOList){
            Element relationGroup=document.createElement("RelationGroup");


            Element source=document.createElement(ENTITY_TAG);
            source.setTextContent(r.getSource().getName());
            source.setAttribute(ENTITY_PROPERTY_ATTRIBUTE_NAME,ENTITY_SOURCE_ATTRIBUTE);
            source.setAttribute(ID_ATTRIBUTE_NAME,String.valueOf(r.getSource().getId()));
            source.setAttribute(TYPE_ATTRIBUTE_NAME,r.getSource().getType().name());
            source.setAttribute(DESCRIPTION_ATTRIBUTE_NAME,r.getSource().getDescription());
            source.setAttribute(X_ATTRIBUTE_NAME,r.getSource().getX());
            source.setAttribute(Y_ATTRIBUTE_NAME,r.getSource().getY());
            source.setAttribute(SHAPE_ATTRIBUTE_NAME,r.getSource().getShape());
            source.setAttribute(GRAPHID_ATTRIBUTE_NAME,String.valueOf(r.getSource().getGraphId()));

            Element relation=document.createElement(RELATION_TAG);
            relation.setTextContent(r.getRelation().getName());
            relation.setAttribute(ID_ATTRIBUTE_NAME,String.valueOf(r.getRelation().getId()));
            relation.setAttribute(TYPE_ATTRIBUTE_NAME,r.getRelation().getType().name());
            relation.setAttribute(DESCRIPTION_ATTRIBUTE_NAME,r.getRelation().getDescription());
            relation.setAttribute(ISFULLLINE_ATTRIBUTE_NAME,r.getRelation().isFullLine()?"true":"false");

            Element target=document.createElement(ENTITY_TAG);
            target.setTextContent(r.getTarget().getName());
            target.setAttribute(ENTITY_PROPERTY_ATTRIBUTE_NAME,ENTITY_TARGET_ATTRIBUTE);
            target.setAttribute(ID_ATTRIBUTE_NAME,String.valueOf(r.getTarget().getId()));
            target.setAttribute(TYPE_ATTRIBUTE_NAME,r.getTarget().getType().name());
            target.setAttribute(DESCRIPTION_ATTRIBUTE_NAME,r.getTarget().getDescription());
            target.setAttribute(X_ATTRIBUTE_NAME,r.getTarget().getX());
            target.setAttribute(Y_ATTRIBUTE_NAME,r.getTarget().getY());
            target.setAttribute(SHAPE_ATTRIBUTE_NAME,r.getTarget().getShape());
            target.setAttribute(GRAPHID_ATTRIBUTE_NAME,String.valueOf(r.getTarget().getGraphId()));

            relationGroup.appendChild(source);
            relationGroup.appendChild(relation);
            relationGroup.appendChild(target);

            root.appendChild(relationGroup);

        }
        document.appendChild(root);
        TransformerFactory tff=TransformerFactory.newInstance();
        Transformer tf=tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(document),new StreamResult(file));

    }
}
