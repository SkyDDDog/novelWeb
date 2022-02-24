package com.lyd.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        /*
            anon:无需认证即可访问
            authc:必须认证了才能访问
            user:必须拥有 记住我 功能才能访问
            perms:拥有对某个资源的权限才能访问
            role:拥有某个角色权限才能访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/login","anon");
        filterMap.put("/user/register","anon");
        filterMap.put("/user/*","authc");


        //设置登录请求
//        bean.setLoginUrl("/noauth");

        bean.setFilterChainDefinitionMap(filterMap);


        return bean;
    }

    //DefaultWebSecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
