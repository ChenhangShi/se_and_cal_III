package com.codemonkeys.backendcoin.util;

import checkers.units.quals.A;
import com.codemonkeys.backendcoin.Exceptions.EmptyException;
import com.codemonkeys.backendcoin.PO.ActorNamePO;
import com.codemonkeys.backendcoin.PO.DirectorPO;
import com.codemonkeys.backendcoin.PO.GenrePO;
import com.codemonkeys.backendcoin.PO.MovieNamePO;
import com.codemonkeys.backendcoin.VO.UserTagVO;
import com.codemonkeys.backendcoin.mapper.*;
import org.apache.commons.collections.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RecommendationUtil {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private ActorMovieMapper actorMovieMapper;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreMovieMapper genreMovieMapper;
    @Autowired
    DirectorMapper directorMapper;
    @Autowired
    private DirectorMovieMapper directorMovieMapper;

    /**
     * 计算两个字符串之间的jaccard相似度，大于阈值就认为相似
     * @param a
     * @param b
     * @return
     */
    private static boolean isSimilarString(String a,String b, double threshold){
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
        return jaccard>=threshold;
    }

    /**
     * 判断字符串相似默认版本，阈值0.75
     * @param a
     * @param b
     * @return
     */
    private static boolean isSimilarString(String a,String b){
        return isSimilarString(a,b,0.75);
    }

    /**
     * 返回推荐的电影，不超过20条，按得分从高到低排序
     * @param userTagVO
     * @return
     */
    public List<String> generateRecommendMovies(UserTagVO userTagVO){
        Map<Integer,Integer> recommendedMovieIdAndScore = mergeMovieScore(
                recommendByMovies(userTagVO.getMovies()),
                recommendByActors(userTagVO.getActors()),
                recommendByDirectors(userTagVO.getDirectors()),
                recommendByGenres(userTagVO.getGenres()));
        // TODO
        return null;
    }

    /**
     * 根据电影tag推荐
     * 返回movieId和其得分
     * @param movies
     * @return
     */
    Map<Integer,Integer> recommendByMovies(List<String> movies){
        // 获取movie_id
        Set<String> movieTagSet = new HashSet<>(movies);
        List<MovieNamePO> movieNamePOList = movieMapper.getAllMovieNames();
        Set<Integer> probableMovieIds = new HashSet<>();
        // 进行电影名相似度匹配
        for(String movieTag:movieTagSet){
            Set<Integer> curProbableMovieIds = movieNamePOList
                    .stream()
                    .filter(
                            movieNamePO -> {
                                try {
                                    if (ProcessData.isContainChinese(movieTag))
                                        return isSimilarString(movieTag, movieNamePO.movie_chName);
                                    return isSimilarString(movieTag, movieNamePO.movie_foreName);
                                } catch (EmptyException e) {
                                    e.printStackTrace();
                                    return false;
                                }
                            }
                    )
                    .map(movieNamePO -> movieNamePO.movie_id)
                    .collect(Collectors.toSet());
            probableMovieIds.addAll(curProbableMovieIds);
        }
        return recommendByMovieIds(probableMovieIds);
    }


    private Map<Integer,Integer> recommendByMovieIds(Set<Integer> movieIds){
        // 获取电影的演员、导演、体裁
        Set<Integer> actorIds = new HashSet<>();
        Set<Integer> directorIds = new HashSet<>();
        Set<Integer> genreIds = new HashSet<>();
        for(Integer movieId:movieIds){
            actorIds.addAll(actorMovieMapper.getActorIdsByMovieId(movieId));
            directorIds.addAll(directorMovieMapper.getDirectorIdsByMovieId(movieId));
            genreIds.addAll(genreMovieMapper.getGenreIdsByMovieId(movieId));
        }
        Map<Integer,Integer> byActorRes = recommendByActorIds(actorIds);
        Map<Integer,Integer> byDirectorRes = recommendByDirectorIds(directorIds);
        Map<Integer,Integer> byGenreRes = recommendByGenreIds(genreIds);
        return mergeMovieScore(byActorRes,byDirectorRes,byGenreRes);
    }

    /**
     * 合并电影的得分
     * 可变参数
     * @param recommendResults
     * @return
     */
    private Map<Integer,Integer> mergeMovieScore(Map<Integer,Integer>... recommendResults){
        Map<Integer,Integer> mergedRes = new HashMap<>();
        for(Map<Integer,Integer> recommendResult:recommendResults){
            for(Integer movieId:recommendResult.keySet()){
                mergedRes.put(movieId,mergedRes.getOrDefault(movieId,0) + recommendResult.get(movieId));
            }
        }
        return mergedRes;
    }


    /**
     * 根据演员tag推荐
     * @param actorTags
     * @return
     */
    Map<Integer,Integer> recommendByActors(List<String> actorTags){
        // 获取actor_id
        Set<String> actorTagSet = new HashSet<>(actorTags);
        List<ActorNamePO> actorNamePOList = actorMapper.getAllActorNames();
        Set<Integer> probableActorIds = new HashSet<>();
        // 进行演员名相似度匹配
        for(String actorTag:actorTagSet){
            Set<Integer> curProbableActorIds = actorNamePOList
                    .stream()
                    .filter(
                            actorNamePO -> {
                                try {
                                    if (ProcessData.isContainChinese(actorTag))
                                        return isSimilarString(actorTag, actorNamePO.actor_chName);
                                    return isSimilarString(actorTag, actorNamePO.actor_foreName);
                                } catch (EmptyException e) {
                                    e.printStackTrace();
                                    return false;
                                }
                            }
                    )
                    .map(actorNamePO -> actorNamePO.actor_id)
                    .collect(Collectors.toSet());
            probableActorIds.addAll(curProbableActorIds);
        }
        return recommendByActorIds(probableActorIds);
    }

    private Map<Integer,Integer> recommendByActorIds(Set<Integer> actorIds){
        // 一部电影每与一个actor匹配，得一分
        Map<Integer,Integer> recommendedMovieIdAndScore = new HashMap<>();
        // 根据actor_id拿电影id，并增加得分
        for(Integer actorId:actorIds){
            Set<Integer> curMovieIds = new HashSet<>(actorMovieMapper.getMovieIdsByActorId(actorId));
            for(Integer movieId:curMovieIds){
                recommendedMovieIdAndScore.put(movieId,recommendedMovieIdAndScore.getOrDefault(movieId,0)+1);
            }
        }
        return recommendedMovieIdAndScore;
    }


    /**
     * 根据导演tag推荐
     * @param directors
     * @return
     */
    Map<Integer,Integer> recommendByDirectors(List<String> directors){
        Set<String> directorTagSet = new HashSet<>(directors);
        List<DirectorPO> directorPOList = directorMapper.getAllDirectors();
        Set<Integer> probableDirectorIds = new HashSet<>();
        for(String directorTag:directorTagSet){
            Set<Integer> curProbableDirectorIds = directorPOList
                    .stream()
                    .filter(
                            directorPO -> isSimilarString(directorTag, directorPO.director_name)
                    )
                    .map(directorPO -> directorPO.director_id)
                    .collect(Collectors.toSet());
            probableDirectorIds.addAll(curProbableDirectorIds);
        }
        return recommendByDirectorIds(probableDirectorIds);
    }

    private Map<Integer,Integer> recommendByDirectorIds(Set<Integer> directorIds){
        // 一部电影每与一个director匹配，得一分
        Map<Integer,Integer> recommendedMovieIdAndScore = new HashMap<>();
        for(Integer directorId:directorIds){
            Set<Integer> curMovieIds = new HashSet<>(directorMovieMapper.getMovieIdsByDirectorId(directorId));
            for(Integer movieId:curMovieIds){
                recommendedMovieIdAndScore.put(movieId,recommendedMovieIdAndScore.getOrDefault(movieId,0)+1);
            }
        }
        return recommendedMovieIdAndScore;
    }


    /**
     * 根据体裁tag推荐
     * @param genres
     * @return
     */
    Map<Integer,Integer> recommendByGenres(List<String> genres){
        // 获取可能的genre
        Set<String> genreTagSet = new HashSet<>(genres);
        List<GenrePO> genreList = genreMapper.getAllGenres();
        Set<Integer> probablegenreIds = new HashSet<>();
        for(String genreTag:genreTagSet){
            Set<Integer> curProbableGenreIds = genreList
                    .stream()
                    .filter(
                            genrePO -> isSimilarString(genreTag, genrePO.genre_name,0.5)
                    )
                    .map(genrePO -> genrePO.genre_id)
                    .collect(Collectors.toSet());
            probablegenreIds.addAll(curProbableGenreIds);
        }
        return recommendByGenreIds(probablegenreIds);
    }

    private Map<Integer,Integer> recommendByGenreIds(Set<Integer> genreIds){
        // 一部电影每与一个genre匹配，得一分
        Map<Integer,Integer> recommendedMovieIdAndScore = new HashMap<>();
        for(Integer genreId:genreIds){
            Set<Integer> curMovieIds = new HashSet<>(genreMovieMapper.getMovieIdsByGenreId(genreId));
            for(Integer movieId:curMovieIds){
                recommendedMovieIdAndScore.put(movieId,recommendedMovieIdAndScore.getOrDefault(movieId,0)+1);
            }
        }
        return recommendedMovieIdAndScore;
    }
}
