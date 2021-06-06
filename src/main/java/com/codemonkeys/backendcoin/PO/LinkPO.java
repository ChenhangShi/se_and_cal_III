package com.codemonkeys.backendcoin.PO;

import com.codemonkeys.backendcoin.Enum.LinkType;

public class LinkPO {
    public Long id;
    public Long sourceId;
    public Long targetId;
    public Long graphId;
    public String relationName;
    public LinkType type;
    public String description;
    public boolean isFullLine;
}
