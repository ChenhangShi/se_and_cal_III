package com.codemonkeys.backendcoin.controller;

import com.codemonkeys.backendcoin.VO.UserVO;
import com.codemonkeys.backendcoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserVO userVO){
        userService.register(userVO.getUsername(),userVO.getPassword(),userVO.getRole().name());
    }
    @GetMapping("/getUser/{id}")
    public UserVO getUser(@PathVariable int id){
        return userService.getUser(id);
    }

}
