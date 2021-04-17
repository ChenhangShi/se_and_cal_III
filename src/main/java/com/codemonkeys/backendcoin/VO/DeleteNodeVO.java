package com.codemonkeys.backendcoin.VO;

import java.util.List;

public class DeleteNodeVO {
    public DeleteNodeVO(List<String> entityNodeIdList, String graphId) {
        this.entityNodeIdList = entityNodeIdList;
        this.graphId = graphId;
    }

    public DeleteNodeVO(){}

    public List<String> entityNodeIdList;
    public String graphId;
}
