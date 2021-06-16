package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.UserRecommendedMoviePO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRecommendedMovieMapper {
    @Delete("delete from user_recommended_movie where userId=#{userId}")
    void deleteRecommendedMoviesByUserId(Integer userId);

    @Select("select movieName from user_recommended_movie where userId=#{userId}")
    List<String> getRecommendedMovieNamesByUserId(Integer userId);

    @Insert("insert into user_recommended_movie(userId, movieId, movieName) values (#{userId}, #{movieId}, #{movieName})")
    void addUserRecommendedMovie(UserRecommendedMoviePO userRecommendedMoviePO);
}
