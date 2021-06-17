package com.codemonkeys.backendcoin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreMovieMapper {
    @Select("select movie_id from movie_to_genre where genre_id=#{genre_id}")
    List<Integer> getMovieIdsByGenreId(Integer genre_id);

    @Select("select genre_id from movie_to_genre where movie_id=#{movie_id}")
    List<Integer> getGenreIdsByMovieId(Integer movie_id);

    @Insert("insert into movie_to_genre(movie_id,genre_id) values (#{movie_id},#{genre_id})")
    void insertIntoMovieToGenre(@Param("movie_id")int movieId, @Param("genre_id")int genreId);

    @Delete("delete from movie_to_genre where movie_id=#{movie_id} and genre_id=#{genre_id}")
    void deleteMovieToGenre(int movie_id,int genre_id);
}
