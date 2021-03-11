package com.codemonkeys.backendcoin.VO;



/**
 * @author 吴旻轩
 * 一个RelationGroupVO表示一个实体，关系，实体的三元组
 */
public class RelationGroupVO {
    private EntityVO source;
    private EntityVO target;
    private RelationVO relation;

    public RelationGroupVO(EntityVO s, EntityVO t, RelationVO relation){
        this.source=s;
        this.target=t;
        this.relation=relation;
    }

    public EntityVO getSource() {
        return source;
    }

    public void setSource(EntityVO source) {
        this.source = source;
    }

    public EntityVO getTarget() {
        return target;
    }

    public void setTarget(EntityVO target) {
        this.target = target;
    }

    public RelationVO getRelation() {
        return relation;
    }

    public void setRelation(RelationVO relation) {
        this.relation = relation;
    }


}
