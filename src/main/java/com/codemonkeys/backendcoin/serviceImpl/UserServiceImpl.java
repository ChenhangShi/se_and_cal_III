package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.Enum.Roles;
import com.codemonkeys.backendcoin.PO.UserPO;
import com.codemonkeys.backendcoin.VO.UserVO;
import com.codemonkeys.backendcoin.mapper.UserMapper;
import com.codemonkeys.backendcoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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


}
