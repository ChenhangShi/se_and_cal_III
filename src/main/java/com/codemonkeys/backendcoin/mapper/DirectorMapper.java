package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.DirectorPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorMapper {
    @Select("select director_name from director where director_name=#{director_name}")
    String isDirectorInTable(@Param("director_name")String director_name);

    @Insert("insert into director(director_name) values (#{director_name})")
    void addDirector(@Param("director_name")String director_name);

    @Select("select director_id from director where director_name=#{director_name}")
    Integer getDirectorIdByName(@Param("director_name")String director_name);

    @Select("select director_id from director_to_movie where director_id=#{director_id} and movie_id=#{movie_id}")
    String isDirectorToMovieInTable(@Param("director_id")int director_id, @Param("movie_id")int movie_id);

    @Select("select director_id,director_name from director")
    List<DirectorPO> getAllDirectors();
}
