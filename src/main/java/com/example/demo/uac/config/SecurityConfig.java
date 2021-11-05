package com.example.demo.uac.config;

import com.example.demo.uac.rbac.CustomUserService;
import com.example.demo.uac.security.IPAuthenticationProcessingFilter;
import com.example.demo.uac.security.IPAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/1 15:01
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
        auth.authenticationProvider(new IPAuthenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(ipAuthenticationProcessingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable().authorizeRequests()
                .antMatchers("/swagger**/**", "/**/api-docs/**", "/swagger-resources/**", "/webjars/**", "/ipLogin").permitAll()
                .anyRequest().access("@rbacService.hasPermission(request, authentication)")
        .and().formLogin().permitAll()
        .and().formLogin().loginProcessingUrl("/ipLogin").failureForwardUrl("/ipLogin?error").permitAll()
        .and().logout().logoutSuccessUrl("/logoutSuccess").permitAll()
        .and().httpBasic();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //------------------------- ip登录设置 -------------

    IPAuthenticationProcessingFilter ipAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        IPAuthenticationProcessingFilter ipAuthenticationProcessingFilter = new IPAuthenticationProcessingFilter();
        ipAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);

        ipAuthenticationProcessingFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/ipLogin?error"));
        return ipAuthenticationProcessingFilter;
    }

    //------------------------- ip登录设置 -------------
}
