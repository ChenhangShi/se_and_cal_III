package com.codemonkeys.backendcoin.VO;


/**
 * @author 吴旻轩
 * 一个EntityVO表示一个实体
 */
public class EntityVO {
    private String name;

    public EntityVO(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
