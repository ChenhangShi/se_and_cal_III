package com.codemonkeys.backendcoin.VO;


//一个RelationGroup表示一个实体，关系，实体的三元组
public class RelationGroup {
    private Entity source;
    private Entity target;
    private Relation relation;

    public RelationGroup(Entity s,Entity t,Relation relation){
        this.source=s;
        this.target=t;
        this.relation=relation;
    }

    public Entity getSource() {
        return source;
    }

    public void setSource(Entity source) {
        this.source = source;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }


}
