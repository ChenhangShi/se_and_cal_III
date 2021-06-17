package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.GenrePO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreMapper {
    @Select("select genre_id from genre where genre_name=#{genre_name}")
    Integer getGenreId(@Param("genre_name")String genreName);

    @Select("select genre_id,genre_name from genre")
    List<GenrePO> getAllGenres();


}
