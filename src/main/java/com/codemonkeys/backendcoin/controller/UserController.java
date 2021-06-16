package com.codemonkeys.backendcoin.controller;

import com.codemonkeys.backendcoin.VO.*;
import com.codemonkeys.backendcoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public Set<String> addUserActor(@RequestBody UserActorVO userActorVO){
        return userService.addUserActor(userActorVO.getUserId(),userActorVO.getActor());
    }

    @PostMapping("/addUserMovie")
    public Set<String> addUserMovie(@RequestBody UserMovieVO userMovieVO){
        return userService.addUserMovie(userMovieVO.getUserId(), userMovieVO.getMovie());
    }

    @PostMapping("/addUserDirector")
    public Set<String> addUserDirector(@RequestBody UserDirectorVO userDirectorVO){
        return userService.addUserDirector(userDirectorVO.getUserId(), userDirectorVO.getDirector());
    }

    @PostMapping("/addUserGenre")
    public Set<String> addUserGenre(@RequestBody UserGenreVO userGenreVO){
        return userService.addUserGenre(userGenreVO.getUserId(), userGenreVO.getGenre());
    }

    @PostMapping("/deleteUserActor")
    public Set<String> deleteUserActor(@RequestBody UserActorVO userActorVO){
        return userService.deleteUserActor(userActorVO);
    }

    @PostMapping("/deleteUserMovie")
    public Set<String> deleteUserMovie(@RequestBody UserMovieVO userMovieVO){
        return userService.deleteUserMovie(userMovieVO);
    }

    @PostMapping("/deleteUserGenre")
    public Set<String> deleteUserGenre(@RequestBody UserGenreVO userGenreVO){
        return userService.deleteUserGenre(userGenreVO);
    }

    @PostMapping("/deleteUserDirector")
    public Set<String> deleteUserDirector(UserDirectorVO userDirectorVO){
        return userService.deleteUserDirector(userDirectorVO);
    }

    @GetMapping("/getUserTag/{userId}")
    public UserTagVO getUserTag(@PathVariable int userId){
        return userService.getUserTag(userId);
    }

    @GetMapping("/getUserRecommendedMovies/{userId}")
    public Set<String> getUserRecommendedMovies(@PathVariable int userId){
        return userService.getUserRecommendedMovies(userId);
    }
}
