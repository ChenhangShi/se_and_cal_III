package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.DirectorMoviePO;
import com.codemonkeys.backendcoin.PO.MoviePO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieMapper {
    @Select("select * from movie where movie_id=#{id}")
    MoviePO getMovieById(@Param("id") int id);

    @Select("select movie_director,movie_chName from movie")
    List<DirectorMoviePO> getAllDirectors();

    @Insert("insert into director_to_movie(director_chName,movie_chName)" +
            "values (#{director},#{movie})")
    void insertIntoDirectorToMovie(@Param("director")String director,@Param("movie")String movie);

}
