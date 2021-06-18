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
        userService.addUserMovie(Integer.MAX_VALUE,"长江7号");
        userService.addUserMovie(Integer.MAX_VALUE,"唐伯虎点秋香");
        userService.addUserActor(Integer.MAX_VALUE,"周星驰");
        userService.addUserActor(Integer.MAX_VALUE,"张家辉");
        userService.addUserDirector(Integer.MAX_VALUE,"周星驰");
        userService.addUserDirector(Integer.MAX_VALUE,"徐克");
        userService.addUserGenre(Integer.MAX_VALUE,"科幻");
        userService.addUserGenre(Integer.MAX_VALUE,"动画");
        Set<String> res = userService.getUserRecommendedMovies(Integer.MAX_VALUE);
        for(String movieName:res)
            System.out.println(movieName);

        System.out.println("\n-------------\n");

        userService.deleteUserMovie(new UserMovieVO(){{setUserId(Integer.MAX_VALUE);setMovie("唐伯虎点秋香");}});
        userService.deleteUserActor(new UserActorVO(){{setUserId(Integer.MAX_VALUE);setActor("周星驰");}});
        userService.deleteUserDirector(new UserDirectorVO(){{setUserId(Integer.MAX_VALUE);setDirector("徐克");}});
        userService.deleteUserGenre(new UserGenreVO(){{setUserId(Integer.MAX_VALUE);setGenre("动画");}});
        res = userService.getUserRecommendedMovies(Integer.MAX_VALUE);
        for(String movieName:res)
            System.out.println(movieName);
    }
}
