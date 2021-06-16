package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.PO.UserPO;
import com.codemonkeys.backendcoin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户登录的service实现类 <br>
 * @author wuminxuan
 */

@Service
@Transactional
public class LoginUserDetailsServiceImpl implements UserDetailsService {

    UserMapper userMapper;

    private String currentUsername;

    @Autowired
    public LoginUserDetailsServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(s==null||s.equals("")){
            throw new UsernameNotFoundException(s);
        }
        UserPO user=userMapper.getUserByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        this.currentUsername=user.getUsername();
        return user;
    }
}
