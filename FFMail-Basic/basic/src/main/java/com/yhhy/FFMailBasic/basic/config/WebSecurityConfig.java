package com.yhhy.FFMailBasic.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 解决静态资源被拦截的问题
        web.ignoring().antMatchers("/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 允许所有用户访问"/"和"/home"
        http.authorizeRequests().antMatchers("/", "/basic").permitAll()
                // 其他地址的访问均需验证权限
                .anyRequest().authenticated().and().formLogin()
                // 指定登录页是"/login"
                .loginPage("/login")
                // 登录成功后默认跳转到
                .defaultSuccessUrl("/index").permitAll().and().logout().logoutUrl("/logout")
                // 退出登录后的默认url是"/login"
                .logoutSuccessUrl("/login").permitAll();
        // 解决非thymeleaf的form表单提交被拦截问题
        http.csrf().disable();

        // 解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 也可以将用户名密码写在内存，不推荐
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
    }
}
