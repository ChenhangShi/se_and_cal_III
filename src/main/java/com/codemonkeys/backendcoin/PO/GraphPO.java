package com.codemonkeys.backendcoin.PO;

public class GraphPO {
    public Long graphId;
    public String graphName;

    public GraphPO(){

    }

    public GraphPO(Long graphId, String graphName, int userId) {
        this.graphId = graphId;
        this.graphName = graphName;
        this.userId = userId;
    }

    public int userId;
}
