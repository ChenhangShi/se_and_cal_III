package com.codemonkeys.backendcoin.VO;

/**
 * @author 吴旻轩
 * 一个RelationVO表示一个关系
 */

public class RelationVO {
    private String name;

    public RelationVO(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
