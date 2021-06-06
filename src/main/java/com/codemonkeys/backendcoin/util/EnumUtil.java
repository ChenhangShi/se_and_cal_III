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
}
