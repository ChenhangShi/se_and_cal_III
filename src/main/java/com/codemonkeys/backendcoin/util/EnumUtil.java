package com.codemonkeys.backendcoin.util;

import com.codemonkeys.backendcoin.Enum.NodeType;
import org.springframework.stereotype.Component;

@Component
public class EnumUtil {
    public NodeType getNodeType(String type){
        NodeType[] a = NodeType.values();
        for(NodeType nodeType:a){
            if(type.equals(nodeType.toString())){
                return nodeType;
            }
        }
        return NodeType.Default;
    }

    public boolean isActorNode(NodeType nodeType){
        return nodeType==NodeType.Actor||nodeType==NodeType.Actor_Bio||
                nodeType==NodeType.Actor_Achiem||nodeType==NodeType.Actor_Birthplace||
                nodeType==NodeType.Actor_BirthDay||nodeType==NodeType.Actor_Brokerage||
                nodeType==NodeType.Actor_Constellation||nodeType==NodeType.Actor_ForeName||
                nodeType==NodeType.Actor_Nationality||nodeType==NodeType.Actor_RepWorks;
    }

    public boolean isMovieNode(NodeType nodeType){
        return nodeType==NodeType.Movie||nodeType==NodeType.Movie_Bio||
                nodeType==NodeType.Movie_Achiem||nodeType==NodeType.Movie_Director||
                nodeType==NodeType.Movie_ForeName||nodeType==NodeType.Movie_Genre||
                nodeType==NodeType.Movie_Language||nodeType==NodeType.Movie_Length||
                nodeType==NodeType.Movie_ProdCompany||nodeType==NodeType.Movie_ProdTime||
                nodeType==NodeType.Movie_RekeaseTime||nodeType==NodeType.Movie_ScreenWriter||
                nodeType==NodeType.Movie_Star;
    }
}
