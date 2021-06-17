package com.codemonkeys.backendcoin.conf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConf(UserDetailsService userDetailsService){
        this.userDetailsService=userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors() // 允许跨域
                .and()
                .formLogin() // 前端要发送x-www-form-urlencoded或者multipart/form-data格式的数据 参数username和password
                .loginProcessingUrl("/login") // 前端给这个接口发送数据
                .successForwardUrl("/login/success")
                .failureForwardUrl("/login/failure")
                .and()
                .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())  // 未登录时，返回403 默认返回的是302重定向到自带的登录页面，不利于前后端分离
                .and()
                .csrf().disable()
                .authorizeRequests() // 这个确定了哪些需要权限认证
                .antMatchers("/**").permitAll()
//                .antMatchers("/user/register").permitAll()
//                .antMatchers("/entity/**").hasRole("USER")
//                .antMatchers("/link/**").hasRole("USER")
//                .antMatchers("/test/**").hasRole("ADMIN") //设置哪些路径可以直接访问，不需要认证
                .anyRequest().authenticated();
    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }
}
