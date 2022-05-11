package com.boning.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.boning.realm.AccountRealm;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);

        //权限设置
        Map<String, String> map = new Hashtable<>();
        map.put("/main", "authc");//必须登录（认证状态）
        map.put("/manage", "perms[manage]");//必须有权限
        map.put("/administrator", "roles[administrator]");//必须有角色
        factoryBean.setFilterChainDefinitionMap(map);
        //位置登陆页面
        factoryBean.setLoginUrl("/login");
        //设置未授权
        factoryBean.setUnauthorizedUrl("/unauth");
        return factoryBean;
    }


    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("accountRealm") AccountRealm accountRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm);
        return manager;
    }

    @Bean
    public AccountRealm accountRealm() {
        return new AccountRealm();
    }


    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }


}
