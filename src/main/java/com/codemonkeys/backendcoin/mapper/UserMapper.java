package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> getAllUser();
}
