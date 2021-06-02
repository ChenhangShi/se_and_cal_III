package com.codemonkeys.backendcoin.VO;

import java.util.Objects;

/**
 * @author 吴旻轩
 * 一个EntityVO表示一个实体
 */
public class EntityVO {

    private Long id;
    private Long graphId;
    private String name;
    private String type;
    private String description;
    private String x;
    private String y;
    private String shape;

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EntityVO(Long id,Long graphId,String type,String name,String description,String x,String y,String shape){
        this.id=id;
        this.graphId=graphId;
        this.type=type;
        this.description=description;
        this.name=name;
        this.x=x;
        this.y=y;
        this.shape=shape;
    }

    public EntityVO(){};


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
        if(this.getClass()!=otherObject.getClass()){
            return false;
        }
        EntityVO entityVO=(EntityVO)otherObject;
        return this.id.equals(entityVO.id)&&
                this.graphId.equals(entityVO.graphId)&&
                this.name.equals(entityVO.name)&&
                this.description.equals(entityVO.description)&&
                this.type.equals(entityVO.type)&&
                this.x.equals(entityVO.x)&&
                this.y.equals(entityVO.y)&&
                this.shape.equals(entityVO.shape);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id,graphId,name,description,type,x,y,shape);
    }



}
