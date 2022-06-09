package com.gzxn.core.system.api;

import cn.hutool.core.util.StrUtil;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.system.dto.EmailSetting;
import com.gzxn.core.system.dto.LoginUser;
import com.gzxn.core.system.dto.SmsDto;
import com.gzxn.core.system.dto.SystemSetting;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * @Package: com.gzxn.core.system.api
 * @ClassName: ISystemCommonApi
 * @Author: CodeBird
 * @Date: 2022-03-03 10:14
 * @Description: 系统公共业务接口
 */
public interface ISystemCommonApi {

    /**
     * 生成token
     *
     * @param uid
     * @return
     */
    String generateToken(String uid);

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    LoginUser getLoginUser(String token);

    /**
     * 根据token缓存获取用户信息（第一次没有会查询数据库，然后放进缓存）
     *
     * @param token
     * @return
     */
    LoginUser getCacheLoginUser(String token);

    /**
     * 根据token缓存获取用户信息（第一次没有会查询数据库，然后放进缓存）<br>
     * 并且删除该用户已有token
     *
     * @param token
     * @return
     */
    LoginUser getCacheLoginUserAndRemoveOther(String token);

    /**
     * 根据token重新缓存LoginUser
     *
     * @param token
     * @return
     */
    LoginUser refreshTokenCacheLoginUser(String token);

    /**
     * 删除缓存token信息
     *
     * @param token
     * @return
     */
    void removeCacheLoginUserByToken(String token);

    /**
     * 根据用户id删除token
     *
     * @param uid
     */
    void removeCacheLoginUserByUid(String uid);

    /**
     * 获取token缓存key
     *
     * @param token
     */
    String getCacheTokenKey(String token);

    /**
     * 获取用户token缓存
     *
     * @param uid
     */
    String getCacheTokenPrefix(String uid);

    /**
     * 删除缓存token生成新token，并且缓存用户信息
     *
     * @param token
     * @return
     */
    String refreshTokenAndCacheLoginUser(String token);

    /**
     * 删除缓存token生成新token
     *
     * @param token
     * @return
     */
    String refreshToken(String token);

    /**
     * 获取系统设置
     *
     * @return
     */
    SystemSetting getSystemSetting();

    /**
     * 获取邮件设置
     *
     * @return
     */
    EmailSetting getEmailSetting();

    /**
     * 更新系统设置
     *
     * @return
     */
    Boolean updateSystemSetting(SystemSetting systemSetting);

    /**
     * 更新邮件设置
     *
     * @return
     */
    Boolean updateEmailSetting(EmailSetting emailSetting);

    /**
     * 根据token返回uid
     *
     * @return
     */
    String getUidByToken(String token);

    /**
     * 获取用户未读信息
     *
     * @param uid
     * @return
     */
    List<SmsDto> getUnReadSms(String uid);

    /**
     * 用户信息更新
     *
     * @param uid
     * @return
     */
    void notifyUpdateSms(String uid);

    /**
     * 发送邮件
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 内容
     * @param isHtml  是否html
     * @param files   文件
     * @return
     */
    public String sendEmail(String to, String subject, String content, boolean isHtml, File... files);

    /**
     * 发送邮件(批量)
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 内容
     * @param isHtml  是否html
     * @param files   文件
     * @return
     */
    public String sendEmail(Collection<String> tos, String subject, String content, boolean isHtml, File... files);

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    static String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader(Constant.REQUEST_HEADER_TOKEN);
        if (StrUtil.isEmpty(token)) {
            token = request.getParameter(Constant.REQUEST_HEADER_TOKEN);
        }
        return token;
    }

}
