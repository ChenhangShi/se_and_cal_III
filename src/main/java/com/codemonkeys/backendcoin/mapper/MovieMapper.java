package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.DirectorNameMoviePO;
import com.codemonkeys.backendcoin.PO.MovieNamePO;
import com.codemonkeys.backendcoin.PO.MoviePO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieMapper {
    @Select("select * from movie where movie_id=#{id}")
    MoviePO getMovieById(@Param("id") int id);

    @Select("select movie_chName from movie where movie_id=#{movie_id}")
    String getMovieNameById(int movie_id);

    @Select("select movie_id, movie_chName, movie_foreName from movie")
    List<MovieNamePO> getAllMovieNames();

    @Select("select movie_director,movie_id from movie")
    List<DirectorNameMoviePO> getAllDirectors();


    @Insert("insert into movie(movie_bio,movie_chName,movie_foreName,movie_prodTime,movie_prodCompany,movie_director,movie_screenwriter," +
            "movie_genre,movie_star,movie_length,movie_rekeaseTime,movie_language,movie_achiem) values" +
            "(#{movie.movie_bio},#{movie.movie_chName},#{movie.movie_foreName},#{movie.movie_prodTime},#{movie.movie_prodCompany},#{movie.movie_director}," +
            "#{movie_screenwriter}," +
            "#{movie_genre},#{movie_star},#{movie_length},#{movie_rekeaseTime},#{movie_language},#{movie_achiem})")
    @Options(useGeneratedKeys = true,keyProperty = "movie_id",keyColumn = "movie_id")
    int insertMovie(@Param("movie")MoviePO moviePO);

    @Select("select movie_id from movie where movie_chName=#{movie_Name}")
    String isMovieInTable(@Param("movie_Name")String movieName);


    @Update("update movie set movie_bio=#{movie.movie_bio},movie_foreName=#{movie.movie_foreName}," +
            "movie_prodTime=#{movie.movie_prodTime},movie_prodCompany=#{movie.movie_prodCompany}," +
            "movie_director=#{movie.movie_director},movie_screenwriter=#{movie.movie_screenwriter}," +
            "movie_genre=#{movie.movie_genre},movie_star=#{movie.movie_star},movie_length=#{movie.movie_length}," +
            "movie_rekeaseTime=#{movie.movie_rekeaseTime},movie_language=#{movie.movie_language},movie_achiem=#{movie.movie_achiem}" +
            "where movie_chName=#{movie.movie_chName}")
    void updateMovie(@Param("movie")MoviePO moviePO);


    @Select("select movie_id from movie where movie_name=#{movieName}")
    int getMovieIdByMovieName(@Param("movieName")String movieName);

}
