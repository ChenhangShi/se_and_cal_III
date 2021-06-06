package com.codemonkeys.backendcoin.mapper;

import com.codemonkeys.backendcoin.PO.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    UserPO getUserById(@Param("id") int id);


    @Select("select * from user where username=#{username}")
    UserPO getUserByUsername(@Param("username") String username);

    @Insert("insert into user(username,password,role) values (#{username},#{password},#{role})")
    void insertUser(@Param("username")String username,@Param("password")String password,@Param("role")String role);

}
