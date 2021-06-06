package com.codemonkeys.backendcoin.util;

import com.codemonkeys.backendcoin.Exceptions.EmptyException;
import com.codemonkeys.backendcoin.PO.DirectorMoviePO;
import com.codemonkeys.backendcoin.mapper.MovieMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProcessData {
    MovieMapper movieMapper;

    @Autowired
    public ProcessData(MovieMapper movieMapper){
        this.movieMapper=movieMapper;
    }

    public void generateDirectorToMovie(){
        List<DirectorMoviePO> directorsList=movieMapper.getAllDirectors();

        for(DirectorMoviePO directorToMovie:directorsList){
            try {
                if(isContainChinese(directorToMovie.getMovie_director())){
                    String[] directors=directorToMovie.getMovie_director().split(" |,|、|，");
                    for(String d:directors){
                        movieMapper.insertIntoDirectorToMovie(d,directorToMovie.getMovie_id());
                    }
                }
                else{
                    if(directorToMovie.getMovie_director().equals("None")){
                        continue;
                    }
                    else{
                        String[] directors=directorToMovie.getMovie_director().split("、|,|，");
                        for(String d:directors){
                            movieMapper.insertIntoDirectorToMovie(d,directorToMovie.getMovie_id());
                        }
                    }
                }

            } catch (EmptyException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字符串是否包含中文
     *
     * @param str 待校验字符串
     * @return true 包含中文字符  false 不包含中文字符
     * @throws EmptyException
     */
    public static boolean isContainChinese(String str) throws EmptyException {

        if (StringUtils.isEmpty(str)) {
            throw new EmptyException("sms context is empty!");
        }
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
}
}
