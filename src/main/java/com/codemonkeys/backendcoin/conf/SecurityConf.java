package com.codemonkeys.backendcoin.conf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConf(UserDetailsService userDetailsService){
        this.userDetailsService=userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()   //自定义自己编写的登录页面
                .loginProcessingUrl("/login/**")   //登录访问路径
                .and().
                csrf().disable()
                .authorizeRequests() // 这个确定了哪些需要权限认证
                .antMatchers("/user/**").permitAll()
                .antMatchers("/entity/**").hasRole("USER")

                .antMatchers("/link/**").hasRole("USER")

                .antMatchers("/test/**").hasRole("ADMIN") //设置哪些路径可以直接访问，不需要认证

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
