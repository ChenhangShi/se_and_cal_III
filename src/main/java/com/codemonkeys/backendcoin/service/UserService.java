package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.*;

import java.util.List;
import java.util.Set;


public interface UserService {
    void register(String username, String password, String roles);

    UserVO getUser(int id);

    void addUserActor(int userId, String actor);

    void addUserDirector(int userId, String director);

    void addUserMovie(int userId, String movie);

    // TODO 添加体裁

    List<String> getUserActor(int userId);

    List<String> getUserMovie(int userId);

    List<String> getUserDirector(int userId);

    List<String> getUserGenre(int userId);

    void deleteUserActor(UserActorVO userActorVO);

    void deleteUserMovie(UserMovieVO userMovieVO);

    void deleteUserGenre(UserGenreVO userGenreVO);

    void deleteUserDirector(UserDirectorVO userDirectorVO);

    UserTagVO getUserTag(Integer userId);

    Set<String> getUserRecommendedMovies(UserTagVO userTagVO);

}
