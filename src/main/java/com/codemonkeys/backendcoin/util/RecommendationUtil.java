package com.codemonkeys.backendcoin.util;

import com.codemonkeys.backendcoin.mapper.ActorMapper;
import com.codemonkeys.backendcoin.mapper.MovieMapper;
import org.apache.commons.collections.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecommendationUtil {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private ActorMapper actorMapper;

    /**
     * 计算两个字符串之间的jaccard相似度，大于2/3(66%)就认为相似
     * @param a
     * @param b
     * @return
     */
    private static boolean isSimilarString(String a,String b){
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;
        if (a.length()+b.length()==0)
            return true;
        if (a.length()*b.length()==0)
            return false;

        Set<Character> aSet = new HashSet<>();
        Set<Character> bSet = new HashSet<>();
        for(int i = 0;i<a.length();i++)
            aSet.add(a.charAt(i));
        for(int i = 0;i<b.length();i++)
            bSet.add(b.charAt(i));

        Set<Character> intersection = new HashSet<>();
        Set<Character> unionSet = new HashSet<>();

        for(Character c: aSet){
            if (bSet.contains(c))
                intersection.add(c);
        }

        unionSet.addAll(aSet);
        unionSet.addAll(bSet);

        double jaccard = (double) intersection.size() / (double) unionSet.size();
        if (jaccard>=0.66)
            return true;
        return false;
    }

    public Set<String> recommendByMovies(List<String> movies){
        return null;
    }

    public Set<String> recommendByActors(List<String> actors){
        return null;
    }

    public Set<String> recommendByDirectors(List<String> directors){
        return null;
    }
}
