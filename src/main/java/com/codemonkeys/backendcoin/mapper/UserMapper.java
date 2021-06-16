package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.UserActorPO;
import com.codemonkeys.backendcoin.PO.UserDirectorPO;
import com.codemonkeys.backendcoin.PO.UserMoviePO;
import com.codemonkeys.backendcoin.PO.UserPO;
import com.codemonkeys.backendcoin.VO.UserActorVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    UserPO getUserById(@Param("id") int id);

    @Select("select id from user where username=#{username}")
    Integer getUserIdByUsername(@Param("username")String username);


    @Select("select * from user where username=#{username}")
    UserPO getUserByUsername(@Param("username") String username);

    @Insert("insert into user(username,password,role) values (#{username},#{password},#{role})")
    void insertUser(@Param("username")String username,@Param("password")String password,@Param("role")String role);

    @Insert("insert into user_tag_director(userId,director) values (#{userId},#{director})")
    void insertUserDirector(@Param("userId")int userId,@Param("director")String director);

    @Insert("insert into user_tag_movie(userId,movie) values (#{userId},#{movie})")
    void insertUserMovie(@Param("userId")int userId, @Param("movie")String movie);

    @Insert("insert into user_tag_actor(userId,actor) values (#{userId},#{actor})")
    void insertUserActor(@Param("userId")int userId,@Param("actor") String actor);

    @Select("select director from user_tag_director where userId=#{userId}")
    List<String> getUserDirector(@Param("userId")int userId);

    @Select("select movie from user_tag_movie where userId=#{userId}")
    List<String> getUserMovie(@Param("userId")int userId);

    @Select("select actor from user_tag_actor where userId=#{userId}")
    List<String> getUserActor(@Param("userId")int userId);

    @Select("select genre from user_tag_genre where userId=#{userId}")
    List<String> getUserGenre(@Param("userId")int userId);


    @Delete("delete from user_tag_actor where userId=#{userId} and actor=#{actor}")
    void deleteUserActor(@Param("userId")int userId,@Param("actor")String actor);

    @Delete("delete from user_tag_movie where userId=#{userId} and movie=#{movie}")
    void deleteUserMovie(@Param("userId")int userId,@Param("movie")String movie);

    @Delete("delete from user_tag_director where userId=#{userId} and director=#{director}")
    void deleteUserDirector(@Param("userId")int userId,@Param("director")String director);

    @Delete("delete from user_tag_genre where userId=#{userId} and genre=#{genre}")
    void deleteUserGenre(@Param("userId")int userId,@Param("genre")String genre);

}
