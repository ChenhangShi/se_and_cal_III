package com.codemonkeys.backendcoin.util;

import com.codemonkeys.backendcoin.PO.UserRecommendedMoviePO;
import com.codemonkeys.backendcoin.VO.*;
import com.codemonkeys.backendcoin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class RecommendationUtilTest {
    @Autowired
    RecommendationUtil recommendationUtil;

    @Test
    public void testRecommendByActors() throws Exception{
        Map<Integer,Integer> res = recommendationUtil.recommendByActors(new ArrayList<String>(){{add("周星驰");add("张家辉");}});
        for (Integer movieId:res.keySet())
            System.out.println(movieId);
    }

    @Test
    public void testRecommendByGenres() throws Exception{
        Map<Integer,Integer> res = recommendationUtil.recommendByGenres(new ArrayList<String>(){{add("科幻");add("动画");}});
        for (Integer movieId:res.keySet())
            System.out.println(movieId);
    }

    @Test
    public void testRecommendByDirectors() throws Exception{
        Map<Integer,Integer> res = recommendationUtil.recommendByDirectors(new ArrayList<String>(){{add("周星驰");add("徐克");}});
        for (Integer movieId:res.keySet())
            System.out.println(movieId);
    }

    @Test
    public void testRecommendByMovies() throws Exception{
        Map<Integer,Integer> res = recommendationUtil.recommendByMovies(new ArrayList<String>(){{add("长江7号");add("唐伯虎点秋香");}});
        for (Integer movieId:res.keySet()){
            System.out.println(movieId);
            System.out.println(res.get(movieId));
        }
    }

    @Test
    public void testGenerateRecommendMovies() throws Exception{
        UserTagVO userTagVO = new UserTagVO(1,
                new ArrayList<String>(){{add("长江7号");add("唐伯虎点秋香");}},
                new ArrayList<String>(){{add("周星驰");add("张家辉");}},
                new ArrayList<String>(){{add("周星驰");add("徐克");}},
                new ArrayList<String>(){{add("科幻");add("动画");}});
        Set<UserRecommendedMoviePO> res = recommendationUtil.generateUserRecommendedMovies(userTagVO);
        for (UserRecommendedMoviePO userRecommendedMoviePO:res)
            System.out.println(userRecommendedMoviePO.getMovieName());
    }
}
