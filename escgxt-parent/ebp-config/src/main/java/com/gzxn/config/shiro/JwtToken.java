package com.gzxn.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Package: com.gzxn.config.shiro
 * @ClassName: JwtToken
 * @Author: CodeBird
 * @Date: 2022-03-06 17:19
 * @Description: Shiro token
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
