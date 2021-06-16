package com.codemonkeys.backendcoin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorMovieMapper {
    @Insert("insert into director_to_movie(director_id,movie_id)" +
            "values (#{director},#{movie})")
    void insertIntoDirectorToMovie(@Param("director")int director, @Param("movie")Integer movie);

    @Select("select movie_id from director_to_movie where director_id=#{director_id}")
    List<Integer> getMovieIdsByDirectorId(Integer director_id);

    @Select("select director_id from director_to_movie where movie_id=#{movie_id}")
    List<Integer> getDirectorIdsByMovieId(Integer movie_id);
}
