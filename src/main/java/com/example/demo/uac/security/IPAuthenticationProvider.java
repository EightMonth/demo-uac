package com.example.demo.uac.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/5 10:09
 */
public class IPAuthenticationProvider implements AuthenticationProvider {

    final static Map<String, SimpleGrantedAuthority> ipAuthorityMap = new ConcurrentHashMap<>();

    static {
        ipAuthorityMap.put("127.0.0.1", new SimpleGrantedAuthority("admin"));
        ipAuthorityMap.put("10.236.69.103", new SimpleGrantedAuthority("admin"));
        ipAuthorityMap.put("10.236.69.104", new SimpleGrantedAuthority("user"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        IPAuthenticationToken ipAuthenticationToken = (IPAuthenticationToken) authentication;
        String ip = ipAuthenticationToken.getIp();
        SimpleGrantedAuthority simpleGrantedAuthority = ipAuthorityMap.get(ip);

        if (Objects.isNull(simpleGrantedAuthority)) {
            return null;
        } else {
            return new IPAuthenticationToken(ip, Collections.singletonList(simpleGrantedAuthority));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return IPAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
