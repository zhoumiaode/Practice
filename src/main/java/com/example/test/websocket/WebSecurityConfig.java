package com.example.test.websocket;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ProjectName: test
 * @Package: com.example.test.websocket
 * @ClassName: WebSecurityConfig
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/09 15:38
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/09 15:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http)throws Exception{
                http.authorizeRequests()
                    .antMatchers("/","/login")//设置不拦截的路径
                    .permitAll()
                    .anyRequest().authenticated()
                    .and().formLogin()
                    .loginPage("/login")//设置登陆页面访问的路径
                    .defaultSuccessUrl("/chat")//登陆成功后跳转的路径
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
    }

    protected void configure(AuthenticationManagerBuilder auth)throws  Exception{
        auth.inMemoryAuthentication()
                .withUser("wyf").password("wyf").roles("USERS")
                .and().withUser("wisely").password("wisely").roles("USERS");

    }
    public void configure(WebSecurity web)throws  Exception{
        web.ignoring().antMatchers("/resources/static/**");
    }
}
