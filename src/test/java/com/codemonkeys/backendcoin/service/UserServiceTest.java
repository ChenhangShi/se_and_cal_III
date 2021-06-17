package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.UserActorVO;
import com.codemonkeys.backendcoin.VO.UserDirectorVO;
import com.codemonkeys.backendcoin.VO.UserGenreVO;
import com.codemonkeys.backendcoin.VO.UserMovieVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void testRecommend() throws Exception{
        userService.addUserDirector(1,"周星驰");
        userService.addUserDirector(1,"徐克");
        userService.addUserDirector(1,"1");
        Set<String> res = userService.getUserRecommendedMovies(1);
        for(String movieName:res)
            System.out.println(movieName);

        System.out.println("\n-------------\n");

        userService.deleteUserDirector(new UserDirectorVO(){{setUserId(1);setDirector("1");}});
        res = userService.getUserRecommendedMovies(1);
        for(String movieName:res)
            System.out.println(movieName);
    }
}
