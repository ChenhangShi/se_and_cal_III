package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.PO.UserPO;
import com.codemonkeys.backendcoin.VO.UserActorVO;
import com.codemonkeys.backendcoin.VO.UserDirectorVO;
import com.codemonkeys.backendcoin.VO.UserMovieVO;
import com.codemonkeys.backendcoin.VO.UserVO;
import com.codemonkeys.backendcoin.mapper.UserMapper;
import com.codemonkeys.backendcoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
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
    }

    @Override
    public void addUserDirector(int userId, String director) {
        userMapper.insertUserDirector(userId,director);
    }

    @Override
    public void addUserMovie(int userId, String movie) {
        userMapper.insertUserMovie(userId,movie);
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
    public void deleteUserActor(UserActorVO userActorVO) {
        userMapper.deleteUserActor(userActorVO.getUserId(),userActorVO.getActor());
    }

    @Override
    public void deleteUserMovie(UserMovieVO userMovieVO) {
        userMapper.deleteUserMovie(userMovieVO.getUserId(),userMovieVO.getMovie());

    }

    @Override
    public void deleteUserDirector(UserDirectorVO userDirectorVO) {
        userMapper.deleteUserDirector(userDirectorVO.getUserId(),userDirectorVO.getDirector());

    }


}
