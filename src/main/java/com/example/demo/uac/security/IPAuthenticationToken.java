package com.example.demo.uac.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2021/11/5 9:58
 */
public class IPAuthenticationToken extends AbstractAuthenticationToken {

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public IPAuthenticationToken(String ip) {
        super(null);
        this.ip = ip;
        super.setAuthenticated(false);
    }

    public IPAuthenticationToken(String ip, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.ip = ip;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.ip;
    }

}
