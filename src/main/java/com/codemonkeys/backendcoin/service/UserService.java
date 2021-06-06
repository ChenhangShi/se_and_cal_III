package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.UserActorVO;
import com.codemonkeys.backendcoin.VO.UserDirectorVO;
import com.codemonkeys.backendcoin.VO.UserMovieVO;
import com.codemonkeys.backendcoin.VO.UserVO;

import java.util.List;


public interface UserService {
    public void register(String username, String password, String roles);

    public UserVO getUser(int id);

    public void addUserActor(int userId,String actor);

    public void addUserDirector(int userId,String director);

    public void addUserMovie(int userId,String movie);

    public List<String> getUserActor(int userId);

    public List<String> getUserMovie(int userId);

    public List<String> getUserDirector(int userId);

    public void deleteUserActor(UserActorVO userActorVO);

    public void deleteUserMovie(UserMovieVO userMovieVO);

    public void deleteUserDirector(UserDirectorVO userDirectorVO);

}
