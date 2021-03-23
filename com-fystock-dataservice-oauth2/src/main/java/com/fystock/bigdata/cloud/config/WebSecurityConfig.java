package com.fystock.bigdata.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 在WebSecurityConfigurerAdapter不拦截oauth要开放的资源
 *
 * @author He.Yong
 * @since 2021-03-23 18:41:08
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 加密方式
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //启用允许跨域访问
        http.cors();

        // 禁用csrf模式, 由于使用的是JWT,我们这里不需要csrf;
        http.csrf().disable().authorizeRequests()
                //定义/auth/*路径的访问权限
                .antMatchers("/auth/*")
                .permitAll() // 路径为/auth/* 请求可以任意访问
                //定义其它路径的访问权限
                .anyRequest()
                .authenticated();  //  除以上情况之外的请求必须认证通过
    }
}
