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
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TransServiceImpl implements TransService {
    ActorMapper actorMapper;
    ActorMovieMapper actorMovieMapper;
    EntityMapper entityMapper;
    EnumUtil enumUtil;
    LinkMapper linkMapper;
    MovieMapper movieMapper;
    GraphMapper graphMapper;
    GenreMapper genreMapper;
    ProcessData processData;
    DirectorMapper directorMapper;
    GenreMovieMapper genreMovieMapper;
    DirectorMovieMapper directorMovieMapper;
    @Autowired
    public TransServiceImpl(ActorMapper actorMapper,EntityMapper entityMapper,EnumUtil enumUtil,
                            LinkMapper linkMapper,MovieMapper movieMapper,GraphMapper graphMapper
    ,GenreMapper genreMapper,ProcessData processData,DirectorMapper directorMapper
            , GenreMovieMapper genreMovieMapper, DirectorMovieMapper directorMovieMapper
    ,ActorMovieMapper actorMovieMapper){
        this.actorMapper=actorMapper;
        this.entityMapper=entityMapper;
        this.enumUtil=enumUtil;
        this.linkMapper=linkMapper;
        this.movieMapper=movieMapper;
        this.graphMapper=graphMapper;
        this.genreMapper=genreMapper;
        this.processData=processData;
        this.directorMapper=directorMapper;
        this.genreMovieMapper=genreMovieMapper;
        this.actorMovieMapper=actorMovieMapper;
        this.directorMovieMapper=directorMovieMapper;
    }

    /**
     * extract??????????????????
     * ???????????????java???????????????node???????????????????????????????????????PO????????????(Field),????????????IllegalAccessException
     * @param actorId
     * @param graphName
     * @throws IllegalAccessException
     */
    @Override
    public void extract(int actorId,String graphName) throws IllegalAccessException {
        GraphPO graphPO=graphMapper.getGraphByName(graphName);
        //??????graphName??????graph????????????????????????graph??????id????????????????????????mybatis??????graphPO??????id?????????

        ActorPO actorPO=actorMapper.getActorById(actorId);
        List<EntityPO> entityPOList=new ArrayList<>();
        int id_index=1;

        //??????????????????
        EntityPO actorEntityPO=new EntityPO();
        actorEntityPO.nodeType= NodeType.Actor;
        actorEntityPO.id=(long)id_index++;
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
            if(!fieldName.equals("actor_id")&&!fieldName.equals("actor_chName")&&fields[i].get(actorPO)!=null){
                EntityPO entityPO=new EntityPO();
                entityPO.nodeType= enumUtil.getNodeType(fieldName);
                //??????field?????????actorPO???????????????????????????entity???description
                entityPO.description= (String) fields[i].get(actorPO);
                entityPO.graphId= graphPO.graphId;
                entityPO.id=(long)id_index++;
                entityPO.name=fieldName;
                entityPO.shape="circle";
                entityPO.x=String.valueOf(Math.random()*100);
                entityPO.y=String.valueOf(Math.random()*100);
                entityPOList.add(entityPO);
            }
        }
        //??????actor???????????????????????????actor?????????
        for(EntityPO entityPO:entityPOList){
            entityMapper.insertEntity(entityPO);
            LinkPO linkPO=new LinkPO();
            linkPO.graphId=graphPO.graphId;
            linkPO.type= LinkType.Actor_Info;
            linkPO.description=LinkType.Actor_Info.name();
            linkPO.isFullLine=true;
            linkPO.relationName=entityPO.nodeType.name();
            linkPO.sourceId=actorEntityPO.id;
            linkPO.targetId=entityPO.id;
            linkMapper.insertLink(linkPO);
        }

        List<Integer> relatedMovieId=actorMapper.getMovieByActorId(actorId);
        //????????????actor?????????movie
        if(relatedMovieId!=null&&relatedMovieId.size()>0){
            for(int movieId:relatedMovieId){
                //?????????movie??????movie???????????????????????????????????????actor?????????
                MoviePO moviePO=movieMapper.getMovieById(movieId);
                List<EntityPO> relatedMovieInfoList=new ArrayList<>();
                EntityPO movieEntityPO=new EntityPO();
                movieEntityPO.nodeType=NodeType.Movie;
                movieEntityPO.name=NodeType.Movie.toString();
                movieEntityPO.description=moviePO.movie_chName;
                movieEntityPO.id=(long)id_index++;
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
                linkPO.relationName="????????????????????????";
                linkPO.sourceId=actorEntityPO.id;
                linkPO.targetId=movieEntityPO.id;
                linkMapper.insertLink(linkPO);


                Field[] movieFields=moviePO.getClass().getDeclaredFields();
                for(int i=0;i<movieFields.length;i++){
                    String fieldName=movieFields[i].getName();
                    if(!fieldName.equals("movie_id")&&!fieldName.equals("movie_chName")&&movieFields[i].get(moviePO)!=null){
                        EntityPO entityPO=new EntityPO();
                        entityPO.nodeType= enumUtil.getNodeType(fieldName);
                        entityPO.id=(long)id_index++;
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
        //?????????graphId???????????????actor??????,?????????entity?????????????????????actorPO
        for(EntityPO entityPO:entityPOList){
            if(enumUtil.isActorNode(entityPO.nodeType)){
                if(entityPO.nodeType==NodeType.Actor){
                    actorId=entityPO.id;
                }
                String fieldName=entityPO.nodeType.toString();
                Field field=actorPO.getClass().getDeclaredField(fieldName);
                field.set(actorPO,entityPO.description);
                //??????field???entityPO??????description?????????actorPO?????????????????????
            }
        }
        //???????????????actor?????????movieId
        List<Long> movieEntityIdList=linkMapper.getRelatedMovieId((long)graphId,actorId);

        //?????????movieId?????????????????????moviePO
        for(Long movieEntityId:movieEntityIdList){
            //??????????????????????????????????????????Entity
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
            //????????????actor???????????????
        }

        //????????????????????????????????????actor
        if(actorMapper.isActorInTable(actorPO.actor_chName)==null){
            actorMapper.insertActor(actorPO);
        }
        else{
            actorMapper.updateActor(actorPO);
            actorPO.actor_id=actorMapper.getActorIdByActorName(actorPO.actor_chName);
        }

        //?????????actor_to_movie??????
        Set<Integer> actor_related_movieId=new HashSet<>(actorMovieMapper.getMovieIdsByActorId(actorPO.actor_id));

        for(MoviePO moviePO:moviePOList){
            System.out.println(moviePO.toString());
            System.out.println(movieMapper.isMovieInTable(moviePO.movie_chName));
            if(movieMapper.isMovieInTable(moviePO.movie_chName)==null){
                movieMapper.insertMovie(moviePO);
                //???actor_to_movie?????????????????????(?????????????????????movie)
                actorMapper.insertIntoActorToMovie(actorPO.actor_id,moviePO.movie_id);
            }
            else{
                movieMapper.updateMovie(moviePO);
                moviePO.movie_id=movieMapper.getMovieIdByMovieName(moviePO.movie_chName);
                //??????????????????actor_movie???????????????
                if(actor_related_movieId.contains(moviePO.movie_id)){
                    System.out.println("excute");
                    actor_related_movieId.remove(moviePO.movie_id);
                    //???????????????????????????????????????????????????????????????????????????actor_to_movie????????????

                }
                //?????????????????????????????????,??????????????????actor?????????
                else{
                    actorMapper.insertIntoActorToMovie(actorPO.actor_id,moviePO.movie_id);
                }
            }


            Set<Integer> director_related_movieId=new HashSet<>(directorMovieMapper.getDirectorIdsByMovieId(moviePO.movie_id));

            if(moviePO.movie_director!=null){
                String[] director= processData.getDirectorName(moviePO.movie_director);
                for(String d:director){
                    if(!d.equals("None")){
                        if(directorMapper.isDirectorInTable(d)==null){
                            directorMapper.addDirector(d);
                        }
                    }
                    Integer director_id= directorMapper.getDirectorIdByName(d);
                    if(director_id!=null){
                        if(directorMapper.isDirectorToMovieInTable(director_id,moviePO.movie_id)==null){
                            directorMovieMapper.insertIntoDirectorToMovie(director_id,moviePO.movie_id);
                        }
                        else{
                            director_related_movieId.remove(director_id);
                        }
                    }

                }
                for(int dId:director_related_movieId){
                    directorMovieMapper.deleteDirectorMovie(dId,moviePO.movie_id);
                }
            }

            if(moviePO.movie_genre!=null&&moviePO.movie_genre!="None"){
                Set<Integer> movie_related_genreId=new HashSet<>(genreMovieMapper.getGenreIdsByMovieId(moviePO.movie_id));
                String[] genreList=moviePO.movie_genre.split(" |,|???|???");
                boolean flag=false;
                try{
                    for(String genre:genreList){
                        Integer genreId=genreMapper.getGenreId(genre);
                        System.out.println(genreId);
                        if(movie_related_genreId.contains(genreId)){
                            movie_related_genreId.remove(genreId);
                        }
                        else{
                            if(genreId!=null){
                                if(genreId!=10){
                                    genreMovieMapper.insertIntoMovieToGenre(moviePO.movie_id,genreId);
                                }
                                else if(!flag){
                                    genreMovieMapper.insertIntoMovieToGenre(moviePO.movie_id,genreId);
                                    flag=true;
                                }
                            }
                            else if(!flag){
                                genreMovieMapper.insertIntoMovieToGenre(moviePO.movie_id,10);
                                flag=true;
                            }
                        }
                    }
                    for(int gId:movie_related_genreId){
                        genreMovieMapper.deleteMovieToGenre(moviePO.movie_id,gId);
                    }
                }catch (Exception e){
                }

            }
        }
        for(int mId:actor_related_movieId){
            System.out.println(mId);
            actorMovieMapper.deleteActorMovie(actorPO.actor_id,mId);
        }

        //???????????????????????????????????????????????????entity,link,graph
        graphMapper.deleteGraphById(graphId);
        entityMapper.deleteEntityByGraphId(graphId);
        linkMapper.deleteLinkByGraphId(graphId);
    }

    @Override
    public void loadIntoDirectorToMovie() {
        processData.generateDirectorToMovie();
    }


}
