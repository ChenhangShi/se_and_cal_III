package com.codemonkeys.backendcoin.service;


import com.codemonkeys.backendcoin.entity.User;
import com.codemonkeys.backendcoin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }
}
