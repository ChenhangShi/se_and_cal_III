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

    @GetMapping("/getUserId")
    public int getUserId(@RequestParam("username")String username){
        return userService.getUserId(username);
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
        List<String> userMovieList=userService.getUserMovie(userId);
        List<String> userActorList=userService.getUserActor(userId);
        List<String> userDirectorList=userService.getUserDirector(userId);
        List<String> userGenreList=userService.getUserGenre(userId);

        UserTagVO userTagVO=new UserTagVO((long)userId,userMovieList,userActorList,userDirectorList,userGenreList);

        return userTagVO;
    }
}
