package com.codemonkeys.backendcoin.VO;

import com.codemonkeys.backendcoin.Enum.LinkType;

import java.util.Objects;

/**
 * @author 吴旻轩
 * 一个RelationVO表示一个关系
 */

public class RelationVO {

    private Long id;

    private String name;

    private LinkType type;

    private String description;

    private boolean isFullLine;

    public boolean isFullLine() {
        return isFullLine;
    }

    public void setFullLine(boolean fullLine) {
        isFullLine = fullLine;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RelationVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", isFullLine=" + isFullLine +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LinkType getType() {
        return type;
    }

    public void setType(LinkType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public RelationVO(Long id,LinkType type,String name,String description,boolean isFullLine){
        this.id=id;
        this.type=type;
        this.description=description;
        this.name=name;
        this.isFullLine=isFullLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object otherObject){
        if(otherObject==this){
            return true;
        }
        if(otherObject==null){
            return false;
        }
        if(otherObject.getClass()!=this.getClass()){
            return false;
        }

        RelationVO relationVO=(RelationVO)otherObject;
        return this.id.equals(relationVO.id)&&
                this.name.equals(relationVO.name)&&
                this.type.equals(relationVO.type)&&
                this.description.equals(relationVO.description)
                &&this.isFullLine==relationVO.isFullLine;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id,this.name,this.description,this.type,this.isFullLine);
    }
}
