package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.Enum.Roles;
import com.codemonkeys.backendcoin.VO.UserVO;


public interface UserService {
    public void register(String username, String password, String roles);

    public UserVO getUser(int id);

}
