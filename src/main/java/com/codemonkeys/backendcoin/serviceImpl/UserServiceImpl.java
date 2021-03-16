package com.codemonkeys.backendcoin.serviceImpl;


import com.codemonkeys.backendcoin.entity.User;
import com.codemonkeys.backendcoin.mapper.UserMapper;
import com.codemonkeys.backendcoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }
}
