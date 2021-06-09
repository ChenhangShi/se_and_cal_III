package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.DirectorMoviePO;
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

    @Select("select movie_director,movie_id from movie")
    List<DirectorMoviePO> getAllDirectors();

    @Insert("insert into director_to_movie(director_id,movie_id)" +
            "values (#{director},#{movie})")
    void insertIntoDirectorToMovie(@Param("director")int director,@Param("movie")Integer movie);

    @Insert("insert into movie_to_genre(movie_id,genre_id) values (#{movie_id},#{genre_id})")
    void insertIntoMovieToGenre(@Param("movie_id")int movieId,@Param("genre_id")int genreId);

    @Insert("insert into movie(movie_bio,movie_chName,movie_foreName,movie_prodTime,movie_prodCompany,movie_director,movie_screenwriter," +
            "movie_genre,movie_star,movie_length,movie_rekeaseTime,movie_language,movie_achiem) values" +
            "(#{movie.movie_bio},#{movie.movie_chName},#{movie.movie_foreName},#{movie.movie_prodTime},#{movie.movie_prodCompany},#{movie.movie_director}," +
            "#{movie_screenwriter}," +
            "#{movie_genre},#{movie_star},#{movie_length},#{movie_rekeaseTime},#{movie_language},#{movie_achiem})")
    @Options(useGeneratedKeys = true,keyProperty = "movie_id",keyColumn = "movie_id")
    int insertMovie(@Param("movie")MoviePO moviePO);

    @Select("select IFNULL((select movie_id from movie where movie_chName=#{movie_Name}),null)")
    String isMovieInTable(@Param("movie_Name")String movieName);


    @Update("update movie set movie_bio=#{movie.movie_bio},movie_foreName=#{movie.movie_foreName}," +
            "movie_prodTime=#{movie.movie_prodTime},movie_prodCompany=#{movie.movie_prodCompany}," +
            "movie_director=#{movie.movie_director},movie_screenwriter=#{movie.movie_screenwriter}," +
            "movie_genre=#{movie.movie_genre},movie_star=#{movie.movie_star},movie_length=#{movie.movie_length}," +
            "movie_rekeaseTime=#{movie.movie_rekeaseTime},movie_language=#{movie.movie_language},movie_achiem=#{movie.movie_achiem}" +
            "where movie_chName=#{movie.movie_chName}")
    void updateMovie(@Param("movie")MoviePO moviePO);

    @Select("select genre_id from genre where genre_name=#{genre_name}")
    Integer getGenreId(@Param("genre_name")String genreName);

    @Select("select movie_id from movie where movie_name=#{movieName}")
    int getMovieIdByMovieName(@Param("movieName")String movieName);

}
