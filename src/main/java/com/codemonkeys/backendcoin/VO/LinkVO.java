package com.codemonkeys.backendcoin.VO;

import java.util.Objects;

public class LinkVO {
    private String id;
    private String source;
    private String target;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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

    private String relation;
    private String type;
    private String description;

    public LinkVO(RelationGroupVO r){
        this.id=r.getRelation().getId();
        this.source=r.getSource().getId();
        this.target=r.getTarget().getId();
        this.relation=r.getRelation().getName();
        this.type=r.getRelation().getType();
        this.description=r.getRelation().getDescription();
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
                this.target.equals(linkVO.target)&&
                this.source.equals(linkVO.source));
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id,this.description,this.relation,this.type,this.source,this.target);
    }

}
