package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.UserActorVO;
import com.codemonkeys.backendcoin.VO.UserDirectorVO;
import com.codemonkeys.backendcoin.VO.UserGenreVO;
import com.codemonkeys.backendcoin.VO.UserMovieVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void testRecommend() throws Exception{
        userService.addUserMovie(1,"长江7号");
        userService.addUserMovie(1,"唐伯虎点秋香");
        userService.addUserActor(1,"周星驰");
        userService.addUserActor(1,"张家辉");
        userService.addUserDirector(1,"周星驰");
        userService.addUserDirector(1,"徐克");
        userService.addUserGenre(1,"科幻");
        userService.addUserGenre(1,"动画");
        Set<String> res = userService.getUserRecommendedMovies(1);
        for(String movieName:res)
            System.out.println(movieName);

        System.out.println("\n-------------\n");

        userService.deleteUserMovie(new UserMovieVO(){{setUserId(1);setMovie("唐伯虎点秋香");}});
        userService.deleteUserActor(new UserActorVO(){{setUserId(1);setActor("周星驰");}});
        userService.deleteUserDirector(new UserDirectorVO(){{setUserId(1);setDirector("徐克");}});
        userService.deleteUserGenre(new UserGenreVO(){{setUserId(1);setGenre("动画");}});
        res = userService.getUserRecommendedMovies(1);
        for(String movieName:res)
            System.out.println(movieName);
    }
}
