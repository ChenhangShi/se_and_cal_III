package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.PO.UserPO;
import com.codemonkeys.backendcoin.VO.*;
import com.codemonkeys.backendcoin.mapper.UserMapper;
import com.codemonkeys.backendcoin.service.UserService;
import com.codemonkeys.backendcoin.util.RecommendationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    UserMapper userMapper;
    RecommendationUtil recommendationUtil;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RecommendationUtil recommendationUtil){
        this.userMapper=userMapper;
        this.recommendationUtil = recommendationUtil;
    }

    @Override
    public void register(String username, String password, String roles) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encryptPassword=passwordEncoder.encode(password);

        userMapper.insertUser(username,encryptPassword,roles);
    }

    @Override
    public UserVO getUser(int id) {
        UserPO userPO=userMapper.getUserById(id);
        UserVO userVO=new UserVO();
        userVO.setUsername(userPO.getUsername());
        userVO.setId(id);
        userVO.setRole(userPO.getRole());
        return userVO;
    }

    @Override
    public void addUserActor(int userId, String actor) {
        userMapper.insertUserActor(userId,actor);
        // TODO 返回推荐
    }

    @Override
    public void addUserDirector(int userId, String director) {
        userMapper.insertUserDirector(userId,director);
        // TODO 返回推荐
    }

    @Override
    public void addUserMovie(int userId, String movie) {
        userMapper.insertUserMovie(userId,movie);
        // TODO 返回推荐
    }

    // TODO 添加体裁

    @Override
    public List<String> getUserActor(int userId) {
        return userMapper.getUserActor(userId);
    }

    @Override
    public List<String> getUserMovie(int userId) {

        return userMapper.getUserMovie(userId);
    }

    @Override
    public List<String> getUserDirector(int userId) {
        return userMapper.getUserDirector(userId);
    }

    @Override
    public List<String> getUserGenre(int userId) {
        return userMapper.getUserGenre(userId);
    }

    @Override
    public void deleteUserActor(UserActorVO userActorVO) {
        userMapper.deleteUserActor(userActorVO.getUserId(),userActorVO.getActor());
        // TODO 返回推荐
    }

    @Override
    public void deleteUserMovie(UserMovieVO userMovieVO) {
        userMapper.deleteUserMovie(userMovieVO.getUserId(),userMovieVO.getMovie());
        // TODO 返回推荐
    }

    @Override
    public void deleteUserGenre(UserGenreVO userGenreVO) {
        userMapper.deleteUserGenre(userGenreVO.getUserId(),userGenreVO.getGenre());
        // TODO 返回推荐
    }

    @Override
    public void deleteUserDirector(UserDirectorVO userDirectorVO) {
        userMapper.deleteUserDirector(userDirectorVO.getUserId(),userDirectorVO.getDirector());
        // TODO 返回推荐

    }

    @Override
    public UserTagVO getUserTag(Integer userId){
        List<String> userMovieList=getUserMovie(userId);
        List<String> userActorList=getUserActor(userId);
        List<String> userDirectorList=getUserDirector(userId);
        List<String> userGenreList=getUserGenre(userId);

        UserTagVO userTagVO=new UserTagVO(userId,userMovieList,userActorList,userDirectorList,userGenreList);

        return userTagVO;
    }

    @Override
    public Set<String> getUserRecommendedMovies(UserTagVO userTagVO){
        // TODO 从数据库中取
        return null;
    }


}
