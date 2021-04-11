package com.codemonkeys.backendcoin.VO;

import java.util.Objects;

public class LinkVO {
    private Long id;
    private Long sourceId;
    private Long targetId;
    private String relation;
    private String type;
    private String description;
    private Long graphId;
    private boolean isFullLine;

    public LinkVO(Long id, Long sourceId, Long targetId, String relation,
                  String type, String description, Long graphId, boolean isFullLine) {
        this.id = id;
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.relation = relation;
        this.type = type;
        this.description = description;
        this.graphId = graphId;
        this.isFullLine = isFullLine;
    }


    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }


    public boolean getIsFullLine() {
        return isFullLine;
    }

    public void setIsFullLine(boolean fullLine) {
        isFullLine = fullLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkVO(){

    }
    public LinkVO(RelationGroupVO r){
        this.id=r.getRelation().getId();
        this.sourceId=r.getSource().getId();
        this.targetId=r.getTarget().getId();
        this.relation=r.getRelation().getName();
        this.type=r.getRelation().getType();
        this.description=r.getRelation().getDescription();
        this.isFullLine=r.getRelation().isFullLine();
        this.graphId=r.getSource().getGraphId();
    }

    @Override
    public boolean equals(Object otherObject){
        if(otherObject==this){
            return true;
        }
        if(otherObject==null){
            return false;
        }
        if(this.getClass()!=otherObject.getClass()){
            return false;
        }
        LinkVO linkVO=(LinkVO)otherObject;
        return (this.id.equals(linkVO.id)&&
            this.description.equals(linkVO.description)&&
                this.type.equals(linkVO.type)&&
                this.relation.equals(linkVO.relation)&&
                this.targetId.equals(linkVO.targetId)&&
                this.sourceId.equals(linkVO.sourceId))&&
                this.isFullLine==linkVO.isFullLine;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id,this.description,this.relation,this.type,this.sourceId,this.targetId,this.isFullLine);
    }

}
