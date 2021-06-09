package com.codemonkeys.backendcoin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorMovieMapper {
    @Insert("insert into director_to_movie(director_id,movie_id)" +
            "values (#{director},#{movie})")
    void insertIntoDirectorToMovie(@Param("director")int director, @Param("movie")Integer movie);
}
