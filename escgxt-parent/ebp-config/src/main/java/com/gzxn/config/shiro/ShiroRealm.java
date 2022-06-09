package com.gzxn.config.shiro;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.gzxn.config.jwttoken.JwtTokenUtil;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.system.api.ISystemCommonApi;
import com.gzxn.core.system.dto.LoginUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @Package: com.gzxn.config.shiro
 * @ClassName: ShiroRealm
 * @Author: CodeBird
 * @Date: 2022-03-06 17:20
 * @Description: shiro 认证和授权
 */
@Component
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ISystemCommonApi iSystemCommonApi;

    /**
     * 必须重写此方法，不然Shiro会报错
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        JwtToken jwtToken = (JwtToken) token;
        String tokenStr = (String) jwtToken.getCredentials();
        // 校验有没有token异常
        if (StrUtil.isBlank(tokenStr)) {
            throw new NoTokenException("requst:" + request.getRequestURI() + ",没有token。");
        }
        // 查询数据库
        LoginUser loginUser = iSystemCommonApi.getCacheLoginUser(tokenStr);
        if (ObjectUtil.isEmpty(loginUser)) {
            // 找不到缓存
            throw new AuthenticationException("requst:" + request.getRequestURI() + ",token没有找到。");
        }
        if (jwtTokenUtil.canRefresh(tokenStr)) {
            throw new TokenFlushException("requst:" + request.getRequestURI() + ",token刷新。");
        }
        // token验证通过
        request.setAttribute(Constant.REQUEST_ATTRIBUTE_UID, loginUser.getId());
        request.setAttribute(Constant.REQUEST_ATTRIBUTE_LOGIN_USER, loginUser);
        request.setAttribute(Constant.REQUEST_ATTRIBUTE_DEPARTMENT_CODES, loginUser.getDepartmentCode());
        request.setAttribute(Constant.REQUEST_ATTRIBUTE_DEPARTMENT_CODE, loginUser.getDepartmentCodes());
        request.setAttribute(Constant.REQUEST_ATTRIBUTE_ROLE_CODES, loginUser.getRoleCodes());
        request.setAttribute(Constant.REQUEST_ATTRIBUTE_ROLE_CODE, loginUser.getRoleCode());
        request.setAttribute(Constant.REQUEST_ATTRIBUTE_PERMISSION_CODE, loginUser.getPermissionCode());

        return new SimpleAuthenticationInfo(tokenStr, tokenStr, getName());
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) request.getAttribute(Constant.REQUEST_ATTRIBUTE_PERMISSION_CODE));
        authorizationInfo.addRoles((Collection<String>) request.getAttribute(Constant.REQUEST_ATTRIBUTE_ROLE_CODE));
        return authorizationInfo;
    }

}
