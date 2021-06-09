package com.codemonkeys.backendcoin.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;

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
}
