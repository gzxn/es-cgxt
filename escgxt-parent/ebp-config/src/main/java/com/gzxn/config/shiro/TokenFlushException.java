package com.gzxn.config.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @Package: com.gzxn.config.shiro
 * @ClassName: TokenFlushException
 * @Author: CodeBird
 * @Date: 2022-03-06 17:21
 * @Description: token刷新异常
 */
public class TokenFlushException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public TokenFlushException(String explanation) {
        super(explanation);
    }

    public TokenFlushException() {
        super();
    }

}
