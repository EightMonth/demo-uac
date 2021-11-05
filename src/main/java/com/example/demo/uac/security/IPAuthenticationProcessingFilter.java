package com.example.demo.uac.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/5 10:05
 */
public class IPAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public IPAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/ipVerify"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String host = request.getRemoteHost();
        return getAuthenticationManager().authenticate(new IPAuthenticationToken(host));
    }
}
