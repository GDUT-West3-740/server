package com.west740.config;

import com.west740.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lucifer
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override//用到userService
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(
                //这里重写了加密方法 原来的加密工具是BCryptPasswordEncoder
                new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                    }

                    /**
                     * @param charSequence 明文
                     * @param s 密文
                     * @return
                     */
                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        return s.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
                    }
                });
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/category/all").authenticated()
                .antMatchers("/admin/**","/reg").hasRole("超级管理员")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login_page").successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                //
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter printWriter = httpServletResponse.getWriter();
                printWriter.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
                printWriter.flush();
                printWriter.close();
            }
        }).failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                //
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter printWriter = httpServletResponse.getWriter();
                printWriter.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
                printWriter.flush();
                printWriter.close();
            }
        }).loginProcessingUrl("/login").
                usernameParameter("username").
                passwordParameter("password").
                permitAll().
                and().
                logout().
                permitAll().
                and().
                csrf().
                disable().exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
        }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/blogimg/**","/index.html","/static/**");
    }

    @Bean
    AccessDeniedHandler getAccessDeniedHandler(){
        return new AuthenticationAccessDeniedHandler();
    }
}
