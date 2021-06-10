package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.PO.UserPO;
import com.codemonkeys.backendcoin.PO.UserRecommendedMoviePO;
import com.codemonkeys.backendcoin.VO.*;
import com.codemonkeys.backendcoin.mapper.UserMapper;
import com.codemonkeys.backendcoin.mapper.UserRecommendedMovieMapper;
import com.codemonkeys.backendcoin.service.UserService;
import com.codemonkeys.backendcoin.util.RecommendationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserMapper userMapper;
    UserRecommendedMovieMapper userRecommendedMovieMapper;
    RecommendationUtil recommendationUtil;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,UserRecommendedMovieMapper userRecommendedMovieMapper ,RecommendationUtil recommendationUtil){
        this.userMapper=userMapper;
        this.userRecommendedMovieMapper=userRecommendedMovieMapper;
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
    public Set<String> addUserActor(int userId, String actor) {
        userMapper.insertUserActor(userId,actor);
        return changeUserRecommendedMovies(userId);
    }

    @Override
    public Set<String> addUserDirector(int userId, String director) {
        userMapper.insertUserDirector(userId,director);
        return changeUserRecommendedMovies(userId);
    }

    @Override
    public Set<String> addUserMovie(int userId, String movie) {
        userMapper.insertUserMovie(userId,movie);
        return changeUserRecommendedMovies(userId);
    }

    @Override
    public Set<String> addUserGenre(int userId, String genre){
        userMapper.insertUserGenre(userId, genre);
        return changeUserRecommendedMovies(userId);
    }

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
    public Set<String> deleteUserActor(UserActorVO userActorVO) {
        userMapper.deleteUserActor(userActorVO.getUserId(),userActorVO.getActor());
        return changeUserRecommendedMovies(userActorVO.getUserId());
    }

    @Override
    public Set<String> deleteUserMovie(UserMovieVO userMovieVO) {
        userMapper.deleteUserMovie(userMovieVO.getUserId(),userMovieVO.getMovie());
        return changeUserRecommendedMovies(userMovieVO.getUserId());
    }

    @Override
    public Set<String> deleteUserGenre(UserGenreVO userGenreVO) {
        userMapper.deleteUserGenre(userGenreVO.getUserId(),userGenreVO.getGenre());
        return changeUserRecommendedMovies(userGenreVO.getUserId());
    }

    @Override
    public Set<String> deleteUserDirector(UserDirectorVO userDirectorVO) {
        userMapper.deleteUserDirector(userDirectorVO.getUserId(),userDirectorVO.getDirector());
        return changeUserRecommendedMovies(userDirectorVO.getUserId());

    }

    @Override
    public UserTagVO getUserTag(Integer userId){
        List<String> userMovieList=getUserMovie(userId);
        List<String> userActorList=getUserActor(userId);
        List<String> userDirectorList=getUserDirector(userId);
        List<String> userGenreList=getUserGenre(userId);

        return new UserTagVO(userId,userMovieList,userActorList,userDirectorList,userGenreList);
    }

    @Override
    public Set<String> getUserRecommendedMovies(Integer userId){
        return new HashSet<>(userRecommendedMovieMapper.getRecommendedMovieNamesByUserId(userId));
    }

    /**
     * 当用户tag发送变化时，重新生成其推荐电影
     * @param userId
     */
    private Set<String> changeUserRecommendedMovies(Integer userId){
        userRecommendedMovieMapper.deleteRecommendedMoviesByUserId(userId);
        UserTagVO userTagVO = getUserTag(userId);
        Set<UserRecommendedMoviePO> userRecommendedMovies = recommendationUtil.generateUserRecommendedMovies(userTagVO);
        for(UserRecommendedMoviePO userRecommendedMoviePO:userRecommendedMovies)
            userRecommendedMovieMapper.addUserRecommendedMovie(userRecommendedMoviePO);
        return userRecommendedMovies
                .stream()
                .map(UserRecommendedMoviePO::getMovieName)
                .collect(Collectors.toSet());
    }
}
