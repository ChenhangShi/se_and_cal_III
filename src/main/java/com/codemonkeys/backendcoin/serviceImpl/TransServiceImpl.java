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
     * extract接口的实现类
     * 其中使用了java反射来根据node的类型来将数据保存到对应的PO的属性中(Field),因此抛出IllegalAccessException
     * @param actorId
     * @param graphName
     * @throws IllegalAccessException
     */
    @Override
    public void extract(int actorId,String graphName) throws IllegalAccessException {
        GraphPO graphPO=new GraphPO();
        graphPO.graphName=graphName;
        graphMapper.insertGraph(graphPO);
        //根据graphName来向graph表中新插入一行，graph表中id是自增字段，通过mybatis来对graphPO中的id赋新值

        ActorPO actorPO=actorMapper.getActorById(actorId);
        List<EntityPO> entityPOList=new ArrayList<>();

        //先建中心节点
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
                //通过field来获取actorPO中对应属性的值作为entity的description
                entityPO.description= (String) fields[i].get(actorPO);
                entityPO.graphId= graphPO.graphId;
                entityPO.name=fieldName;
                entityPO.shape="circle";
                entityPO.x=String.valueOf(Math.random()*100);
                entityPO.y=String.valueOf(Math.random()*100);
                entityPOList.add(entityPO);
            }
        }
        //再将actor的描述性节点连接到actor节点上
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
        //如果有与actor关联的movie
        if(relatedMovieId!=null&&relatedMovieId.size()>0){
            for(int movieId:relatedMovieId){
                //先找到movie作为movie相关的中心节点，并且将其与actor相连。
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
                linkPO.relationName="演员出演过的电影";
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
        //先根据graphId找到对应的actor对象,并根据entity中的信息初始化actorPO
        for(EntityPO entityPO:entityPOList){
            if(enumUtil.isActorNode(entityPO.nodeType)){
                if(entityPO.nodeType==NodeType.Actor){
                    actorId=entityPO.id;
                }
                String fieldName=entityPO.nodeType.toString();
                Field field=actorPO.getClass().getDeclaredField(fieldName);
                field.set(actorPO,entityPO.description);
                //通过field将entityPO中的description提取到actorPO中对应的属性中
            }
        }
        //获取与当前actor联系的movieId
        List<Long> movieEntityIdList=linkMapper.getRelatedMovieId((long)graphId,actorId);

        //再根据movieId来初始化一系列moviePO
        for(Long movieEntityId:movieEntityIdList){
            //获取与当前电影相关的电影信息Entity
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
            //再提取与actor相关的电影
        }

        //根据数据库中是否已经存在actor
        if(actorMapper.isActorInTable(actorPO.actor_chName)==null){
            actorMapper.insertActor(actorPO);
        }
        else{
            actorMapper.updateActor(actorPO);
            actorPO.actor_id=actorMapper.getActorIdByActorName(actorPO.actor_chName);
        }

        //原有的actor_to_movie关系
        Set<Integer> actor_related_movieId=new HashSet<>(actorMovieMapper.getMovieIdsByActorId(actorPO.actor_id));

        for(MoviePO moviePO:moviePOList){
            System.out.println(moviePO.toString());
            System.out.println(movieMapper.isMovieInTable(moviePO.movie_chName));
            if(movieMapper.isMovieInTable(moviePO.movie_chName)==null){
                movieMapper.insertMovie(moviePO);
                //将actor_to_movie的关系插入表中(如果原表中没有movie)
                actorMapper.insertIntoActorToMovie(actorPO.actor_id,moviePO.movie_id);
            }
            else{
                movieMapper.updateMovie(moviePO);
                moviePO.movie_id=movieMapper.getMovieIdByMovieName(moviePO.movie_chName);
                //如果原表中有actor_movie的对应关系
                if(actor_related_movieId.contains(moviePO.movie_id)){
                    System.out.println("excute");
                    actor_related_movieId.remove(moviePO.movie_id);
                    //删除已经出现的，没有出现过的，就当作关系被删除，从actor_to_movie表中删除

                }
                //如果这个电影不是新建的,但是新增了和actor的关系
                else{
                    actorMapper.insertIntoActorToMovie(actorPO.actor_id,moviePO.movie_id);
                }
            }


            Set<Integer> director_related_movieId=new HashSet<>(directorMovieMapper.getDirectorIdsByMovieId(moviePO.movie_id));

            String[] director= processData.getDirectorName(moviePO.movie_director);
            for(String d:director){
                if(!d.equals("None")){
                    if(directorMapper.isDirectorInTable(d)==null){
                        directorMapper.addDirector(d);
                    }
                }
                int director_id= directorMapper.getDirectorIdByName(d);
                if(directorMapper.isDirectorToMovieInTable(director_id,moviePO.movie_id)==null){
                    directorMovieMapper.insertIntoDirectorToMovie(director_id,moviePO.movie_id);
                }
                else{
                    director_related_movieId.remove(director_id);
                }
            }
            for(int dId:director_related_movieId){
                directorMovieMapper.deleteDirectorMovie(dId,moviePO.movie_id);
            }

            Set<Integer> movie_related_genreId=new HashSet<>(genreMovieMapper.getGenreIdsByMovieId(moviePO.movie_id));
            String[] genreList=moviePO.movie_genre.split(" ");
            for(String genre:genreList){
                Integer genreId=genreMapper.getGenreId(genre);
                System.out.println(genreId);
                if(movie_related_genreId.contains(genreId)){
                    movie_related_genreId.remove(genreId);
                }
                else{
                    if(genreId!=null){
                        genreMovieMapper.insertIntoMovieToGenre(moviePO.movie_id,genreId);
                    }
                    else{
                        genreMovieMapper.insertIntoMovieToGenre(moviePO.movie_id,10);
                    }
                }
            }
            for(int gId:movie_related_genreId){
                genreMovieMapper.deleteMovieToGenre(moviePO.movie_id,gId);
            }
        }
        for(int mId:actor_related_movieId){
            System.out.println(mId);
            actorMovieMapper.deleteActorMovie(actorPO.actor_id,mId);
        }

        //从操作表中删除所有已提交的图对应的entity,link,graph
        graphMapper.deleteGraphById(graphId);
        entityMapper.deleteEntityByGraphId(graphId);
        linkMapper.deleteLinkByGraphId(graphId);
    }

    @Override
    public void loadIntoDirectorToMovie() {
        processData.generateDirectorToMovie();
    }


}
