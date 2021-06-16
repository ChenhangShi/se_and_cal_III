package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.*;

import java.util.List;
import java.util.Set;


public interface UserService {
    int getUserId(String username);

    void register(String username, String password, String roles);

    UserVO getUser(int id);

    boolean isNameRepeat(String username);

    Set<String> addUserActor(int userId, String actor);

    Set<String> addUserDirector(int userId, String director);

    Set<String> addUserMovie(int userId, String movie);

    Set<String> addUserGenre(int userId, String genre);

    List<String> getUserActor(int userId);

    List<String> getUserMovie(int userId);

    List<String> getUserDirector(int userId);

    List<String> getUserGenre(int userId);

    Set<String> deleteUserActor(UserActorVO userActorVO);

    Set<String> deleteUserMovie(UserMovieVO userMovieVO);

    Set<String> deleteUserGenre(UserGenreVO userGenreVO);

    Set<String> deleteUserDirector(UserDirectorVO userDirectorVO);

    UserTagVO getUserTag(Integer userId);

    Set<String> getUserRecommendedMovies(Integer userId);
}
