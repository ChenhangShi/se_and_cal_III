package com.codemonkeys.backendcoin.controller;

import com.codemonkeys.backendcoin.VO.*;
import com.codemonkeys.backendcoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/addUserActor")
    public void addUserActor(@RequestBody UserActorVO userActorVO){
        userService.addUserActor(userActorVO.getUserId(),userActorVO.getActor());
    }

    @PostMapping("/addUserMovie")
    public void addUserMovie(@RequestBody UserMovieVO userMovieVO){
        userService.addUserMovie(userMovieVO.getUserId(), userMovieVO.getMovie());
    }

    @PostMapping("/addUserDirector")
    public void addUserDirector(@RequestBody UserDirectorVO userDirectorVO){
        userService.addUserDirector(userDirectorVO.getUserId(), userDirectorVO.getDirector());
    }

    @PostMapping("/deleteUserActor")
    public void deleteUserActor(@RequestBody UserActorVO userActorVO){
        userService.deleteUserActor(userActorVO);
    }

    @GetMapping("/getUserTag/{userId}")
    public UserTagVO getUserTag(@PathVariable int userId){
        return userService.getUserTag(userId);
    }
}
