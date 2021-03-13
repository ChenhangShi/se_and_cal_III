package com.codemonkeys.backendcoin.VO;

import java.util.Objects;

/**
 * @author 吴旻轩
 * 一个EntityVO表示一个实体
 */
public class EntityVO {

    private String id;

    private String name;

    private String type;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public EntityVO(String id,String type,String name,String description){
        this.id=id;
        this.type=type;
        this.description=description;
        this.name=name;
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
        if(this.getClass()!=otherObject.getClass()){
            return false;
        }
        EntityVO entityVO=(EntityVO)otherObject;
        return this.id.equals(entityVO.id)&&
                this.name.equals(entityVO.name)&&
                this.description.equals(entityVO.description)&&
                this.type.equals(entityVO.type);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id,name,description,type);
    }



}
