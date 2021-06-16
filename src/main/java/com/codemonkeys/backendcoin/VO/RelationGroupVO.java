package com.codemonkeys.backendcoin.VO;

/**
 * @author 吴旻轩
 * 一个RelationGroupVO表示一个实体，关系，实体的三元组
 */
public class RelationGroupVO {
    @Override
    public String toString() {
        return "RelationGroupVO{" +
                "source=" + source.toString() +
                ", target=" + target.toString() +
                ", relation=" + relation.toString() +
                '}';
    }

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

    public RelationVO getRelation() {
        return relation;
    }

    public void setRelation(RelationVO relation) {
        this.relation = relation;
    }

    public EntityVO getTarget() {
        return target;
    }

    public void setTarget(EntityVO target) {
        this.target = target;
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
        RelationGroupVO other=(RelationGroupVO)otherObject;
        return this.relation.equals(other.relation)&&this.source.equals(other.source)&&this.target.equals(other.target);
    }
}
