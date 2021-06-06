package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.Enum.LinkType;
import com.codemonkeys.backendcoin.Enum.NodeType;
import com.codemonkeys.backendcoin.PO.ActorPO;
import com.codemonkeys.backendcoin.PO.EntityPO;
import com.codemonkeys.backendcoin.PO.LinkPO;
import com.codemonkeys.backendcoin.PO.MoviePO;
import com.codemonkeys.backendcoin.mapper.ActorMapper;
import com.codemonkeys.backendcoin.mapper.EntityMapper;
import com.codemonkeys.backendcoin.mapper.LinkMapper;
import com.codemonkeys.backendcoin.mapper.MovieMapper;
import com.codemonkeys.backendcoin.service.TransService;
import com.codemonkeys.backendcoin.util.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransServiceImpl implements TransService {
    ActorMapper actorMapper;
    EntityMapper entityMapper;
    EnumUtil enumUtil;
    LinkMapper linkMapper;
    MovieMapper movieMapper;
    @Autowired
    public TransServiceImpl(ActorMapper actorMapper,EntityMapper entityMapper,EnumUtil enumUtil,
                            LinkMapper linkMapper,MovieMapper movieMapper){
        this.actorMapper=actorMapper;
        this.entityMapper=entityMapper;
        this.enumUtil=enumUtil;
        this.linkMapper=linkMapper;
        this.movieMapper=movieMapper;
    }
    @Override
    public void extract(int actorId,int graphId) throws IllegalAccessException {
        ActorPO actorPO=actorMapper.getActorById(actorId);
        List<EntityPO> entityPOList=new ArrayList<>();

        EntityPO actorEntityPO=new EntityPO();
        actorEntityPO.nodeType= NodeType.Actor;
        actorEntityPO.shape="rectangle";
        actorEntityPO.description=actorPO.actor_chName;
        actorEntityPO.graphId=(long)graphId;
        actorEntityPO.name=NodeType.Actor.name();
        actorEntityPO.x=String.valueOf(Math.random()*100);
        actorEntityPO.y=String.valueOf(Math.random()*100);

        entityMapper.insertEntity(actorEntityPO);


        Field[] fields=actorPO.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            String fieldName=fields[i].getName();
            if(!fieldName.equals("actor_id")&&!fieldName.equals("actor_chName")){
                EntityPO entityPO=new EntityPO();
                entityPO.nodeType= enumUtil.getNodeType(fieldName);
                entityPO.description= (String) fields[i].get(actorPO);
                entityPO.graphId= (long) graphId;
                entityPO.name=fieldName;
                entityPO.shape="circle";
                entityPO.x=String.valueOf(Math.random()*100);
                entityPO.y=String.valueOf(Math.random()*100);
                entityPOList.add(entityPO);
            }
        }
        for(EntityPO entityPO:entityPOList){
            entityMapper.insertEntity(entityPO);
            LinkPO linkPO=new LinkPO();
            linkPO.graphId=(long)graphId;
            linkPO.type= LinkType.Actor_Info;
            linkPO.description=LinkType.Actor_Info.name();
            linkPO.isFullLine=true;
            linkPO.relationName=entityPO.nodeType.toString();
            linkPO.sourceId=actorEntityPO.id;
            linkPO.targetId=entityPO.id;
            linkMapper.insertLink(linkPO);
        }

        List<Integer> relatedMovieId=actorMapper.getMovieByActorId(actorId);
        if(relatedMovieId!=null&&relatedMovieId.size()>0){
            for(int movieId:relatedMovieId){
                MoviePO moviePO=movieMapper.getMovieById(movieId);
                List<EntityPO> relatedMovieInfoList=new ArrayList<>();
                EntityPO movieEntityPO=new EntityPO();
                movieEntityPO.nodeType=NodeType.Movie;
                movieEntityPO.name=NodeType.Movie.name();
                movieEntityPO.description=moviePO.movie_chName;
                movieEntityPO.graphId=(long)graphId;
                movieEntityPO.shape="triangle";
                movieEntityPO.x=String.valueOf(Math.random()*200);
                movieEntityPO.y=String.valueOf(Math.random()*200);
                entityMapper.insertEntity(movieEntityPO);

                LinkPO linkPO=new LinkPO();
                linkPO.graphId=(long)graphId;
                linkPO.type= LinkType.Actor_Movie;
                linkPO.description=LinkType.Actor_Movie.name();
                linkPO.isFullLine=true;
                linkPO.relationName=LinkType.Actor_Movie.name();
                linkPO.sourceId=movieEntityPO.id;
                linkPO.targetId=actorEntityPO.id;
                linkMapper.insertLink(linkPO);

                Field[] movieFields=moviePO.getClass().getDeclaredFields();
                for(int i=0;i<movieFields.length;i++){
                    String fieldName=movieFields[i].getName();
                    if(!fieldName.equals("movie_id")&&!fieldName.equals("movie_chName")){
                        EntityPO entityPO=new EntityPO();
                        entityPO.nodeType= enumUtil.getNodeType(fieldName);
                        entityPO.description= (String) movieFields[i].get(moviePO);
                        entityPO.graphId= (long) graphId;
                        entityPO.name=fieldName;
                        entityPO.shape="circle";
                        entityPO.x=String.valueOf(Math.random()*200);
                        entityPO.y=String.valueOf(Math.random()*200);
                        relatedMovieInfoList.add(entityPO);
                    }
                }
                for(EntityPO entityPO:relatedMovieInfoList){
                    entityMapper.insertEntity(entityPO);
                    LinkPO linkPO1=new LinkPO();
                    linkPO1.graphId=(long)graphId;
                    linkPO1.type= LinkType.Movie_Info;
                    linkPO1.description=LinkType.Actor_Movie.name();
                    linkPO1.isFullLine=true;
                    linkPO1.relationName=entityPO.nodeType.toString();
                    linkPO1.sourceId=movieEntityPO.id;
                    linkPO1.targetId=entityPO.id;
                    linkMapper.insertLink(linkPO1);
                }
            }
        }
    }
}
