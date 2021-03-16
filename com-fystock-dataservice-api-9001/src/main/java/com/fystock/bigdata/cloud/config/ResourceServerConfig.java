package com.fystock.bigdata.cloud.config;

import com.fystock.bigdata.cloud.handler.AuthAccessDeniedHandler;
import com.fystock.bigdata.cloud.handler.AuthenticationEntryPointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * 资源服务器，管理那些url资源需要被认证和权限检查，资源服务器ResourceServerConfiguration会配置需要检查权限的url
 *
 * @author He.Yong
 * @since 2021-03-15 15:33:00
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * resourceId 用于分配给可授予的clientId
     */
    public static final String RESOURCE_ID = "resource-1";

    @Resource
    private TokenStore tokenStore;

    @Autowired
    private AuthAccessDeniedHandler authAccessDeniedHandler;

    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;

    /**
     * 添加客户端资源ID
     *
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
                //自定义资源访问认证异常，没有token,或token错误，使用AuthenticationEntryPointHandler处理
                .accessDeniedHandler(authAccessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPointHandler) //认证异常流程处理返回
                .tokenStore(tokenStore) //token的存储方式
                .stateless(true); //stateless  标记以指示在这些资源上仅允许基于令牌的身份验证
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**")
                //定义 "/**" 所有的请求都需要拥有以下权限才可访问
                .access("#oauth2.hasAnyScope('ROLE_ADMIN','ROLE_USER','ROLE_API')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}