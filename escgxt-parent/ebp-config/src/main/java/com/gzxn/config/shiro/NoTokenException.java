package com.gzxn.config.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @Package: com.gzxn.config.shiro
 * @ClassName: NoTokenException
 * @Author: CodeBird
 * @Date: 2022-03-06 17:19
 * @Description: 没有token异常
 */
public class NoTokenException extends AuthenticationException {

    public NoTokenException(String explanation) {
        super(explanation);
    }

    public NoTokenException() {
        super();
    }

    private static final long serialVersionUID = 3678497619904568096L;

}
