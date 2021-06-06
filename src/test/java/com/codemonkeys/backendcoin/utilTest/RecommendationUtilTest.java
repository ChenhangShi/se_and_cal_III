package com.codemonkeys.backendcoin.utilTest;

import com.codemonkeys.backendcoin.util.RecommendationUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class RecommendationUtilTest {
    @Autowired
    RecommendationUtil recommendationUtil;

    @Test
    public void testRecommendByActors() throws Exception{
        Set<String> res = recommendationUtil.recommendByActors(new ArrayList<String>(){{add("周星驰");add("张家辉");}});
        for (String movieName:res)
            System.out.println(movieName);
    }
}
