package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.Enum.LinkType;
import com.codemonkeys.backendcoin.Enum.NodeType;
import com.codemonkeys.backendcoin.PO.*;
import com.codemonkeys.backendcoin.mapper.*;
import com.codemonkeys.backendcoin.service.TransService;
import com.codemonkeys.backendcoin.util.EnumUtil;
import com.codemonkeys.backendcoin.util.ProcessData;
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
    GraphMapper graphMapper;
    ProcessData processData;
    @Autowired
    public TransServiceImpl(ActorMapper actorMapper,EntityMapper entityMapper,EnumUtil enumUtil,
                            LinkMapper linkMapper,MovieMapper movieMapper,GraphMapper graphMapper
    ,ProcessData processData){
        this.actorMapper=actorMapper;
        this.entityMapper=entityMapper;
        this.enumUtil=enumUtil;
        this.linkMapper=linkMapper;
        this.movieMapper=movieMapper;
        this.graphMapper=graphMapper;
        this.processData=processData;
    }
    @Override
    public void extract(int actorId,String graphName) throws IllegalAccessException {
        GraphPO graphPO=new GraphPO();
        graphPO.graphName=graphName;
        graphMapper.insertGraph(graphPO);


        ActorPO actorPO=actorMapper.getActorById(actorId);
        List<EntityPO> entityPOList=new ArrayList<>();

        EntityPO actorEntityPO=new EntityPO();
        actorEntityPO.nodeType= NodeType.Actor;
        actorEntityPO.shape="rectangle";
        actorEntityPO.description=actorPO.actor_chName;
        actorEntityPO.graphId=graphPO.graphId;
        actorEntityPO.name=NodeType.Actor.toString();
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
                entityPO.graphId= graphPO.graphId;
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
            linkPO.graphId=graphPO.graphId;
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
                movieEntityPO.name=NodeType.Movie.toString();
                movieEntityPO.description=moviePO.movie_chName;
                movieEntityPO.graphId=graphPO.graphId;
                movieEntityPO.shape="triangle";
                movieEntityPO.x=String.valueOf(Math.random()*200);
                movieEntityPO.y=String.valueOf(Math.random()*200);
                entityMapper.insertEntity(movieEntityPO);

                LinkPO linkPO=new LinkPO();
                linkPO.graphId=graphPO.graphId;
                linkPO.type= LinkType.Actor_Movie;
                linkPO.description=LinkType.Actor_Movie.name();
                linkPO.isFullLine=true;
                linkPO.relationName=LinkType.Actor_Movie.toString();
                linkPO.sourceId=actorEntityPO.id;
                linkPO.targetId=movieEntityPO.id;
                linkMapper.insertLink(linkPO);

                Field[] movieFields=moviePO.getClass().getDeclaredFields();
                for(int i=0;i<movieFields.length;i++){
                    String fieldName=movieFields[i].getName();
                    if(!fieldName.equals("movie_id")&&!fieldName.equals("movie_chName")){
                        EntityPO entityPO=new EntityPO();
                        entityPO.nodeType= enumUtil.getNodeType(fieldName);
                        entityPO.description= (String) movieFields[i].get(moviePO);
                        entityPO.graphId= graphPO.graphId;
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
                    linkPO1.graphId=graphPO.graphId;
                    linkPO1.type= LinkType.Movie_Info;
                    linkPO1.description=LinkType.Movie_Info.name();
                    linkPO1.isFullLine=true;
                    linkPO1.relationName=entityPO.nodeType.toString();
                    linkPO1.sourceId=movieEntityPO.id;
                    linkPO1.targetId=entityPO.id;
                    linkMapper.insertLink(linkPO1);
                }
            }
        }
    }

    @Override
    public void submit(int graphId) throws NoSuchFieldException, IllegalAccessException {
        List<EntityPO> entityPOList=entityMapper.getAllEntity((long)graphId);
        ActorPO actorPO=new ActorPO();
        long actorId=0l;
        List<MoviePO> moviePOList=new ArrayList<>();
        for(EntityPO entityPO:entityPOList){
            if(enumUtil.isActorNode(entityPO.nodeType)){
                if(entityPO.nodeType==NodeType.Actor){
                    actorId=entityPO.id;
                }
                String fieldName=entityPO.nodeType.toString();
                Field field=actorPO.getClass().getDeclaredField(fieldName);
                field.set(actorPO,entityPO.description);
            }
        }
        List<Long> movieEntityIdList=linkMapper.getRelatedMovieId((long)graphId,actorId);

        for(Long movieEntityId:movieEntityIdList){
            List<Long> movieInfoEntityIdList=linkMapper.getMovieInfoId((long)graphId,movieEntityId);
            MoviePO moviePO=new MoviePO();
            EntityPO moviechNameEntity=entityMapper.getEntity((long)graphId,movieEntityId);
            moviePO.movie_chName=moviechNameEntity.description;
            for(Long movieInfoEntityId:movieInfoEntityIdList){
                EntityPO entityPO=entityMapper.getEntity((long)graphId,movieInfoEntityId);
                String fieldName=entityPO.nodeType.toString();
                Field field=moviePO.getClass().getDeclaredField(fieldName);
                field.set(moviePO,entityPO.description);
            }
            moviePOList.add(moviePO);
        }
        if(actorMapper.isActorInTable(actorPO.actor_chName).equals("null")){
            actorMapper.insertActor(actorPO);
        }
        else{
            actorMapper.updateActor(actorPO);
        }

        for(MoviePO moviePO:moviePOList){
            System.out.println(moviePO.toString());
            System.out.println(movieMapper.isMovieInTable(moviePO.movie_chName));
            if(movieMapper.isMovieInTable(moviePO.movie_chName).equals("null")){
                movieMapper.insertMovie(moviePO);
            }
            else{
                movieMapper.updateMovie(moviePO);
            }
            actorMapper.insertIntoActorToMovie(actorPO.actor_id,moviePO.movie_id);
            movieMapper.insertIntoDirectorToMovie(moviePO.movie_director,moviePO.movie_id);
            String[] genreList=moviePO.movie_genre.split(" ");
            for(String genre:genreList){
                Integer genreId=movieMapper.getGenreId(genre);
                if(genreId!=null){
                    movieMapper.insertIntoMovieToGenre(moviePO.movie_id,genreId);
                }
                else{
                    movieMapper.insertIntoMovieToGenre(moviePO.movie_id,10);
                }
            }
        }
    }

    @Override
    public void loadIntoDirectorToMovie() {
        processData.generateDirectorToMovie();
    }


}
