package com.gzxn.core.constant;

/**
 * @Package: com.gzxn.core.constant
 * @ClassName: CacheConstant
 * @Author: CodeBird
 * @Date: 2022-03-06 12:21
 * @Description: 缓存常量
 */
public class CacheConstant {

    /**
     * key分割符
     */
    public static final String CACHE_SEPARATOR = ":";

    /**
     * jwtToken前缀
     */
    public static final String CACHE_JWT_TOKEN_PREFIX = "ebp" + CACHE_SEPARATOR + "sys" + CACHE_SEPARATOR + "jwt";

    /**
     * user缓存前缀
     */
    public static final String CACHE_VERIFYCODE_PREFIX = "ebp" + CACHE_SEPARATOR + "sys" + CACHE_SEPARATOR + "verifycode";
    /**
     * 数据字典缓存前缀
     */
    public static final String CACHE_DICTIONARY_DICTIONARY_PREFIX = "ebp" + CACHE_SEPARATOR + "sys" + CACHE_SEPARATOR + "dictionary" + CACHE_SEPARATOR + "dictionary";
    /**
     * 系统设置缓存前缀
     */
    public static final String CACHE_DICTIONARY_SYSTEMSETTING_PREFIX = "ebp" + CACHE_SEPARATOR + "sys" + CACHE_SEPARATOR + "dictionary" + CACHE_SEPARATOR + "systemsetting";
    /**
     * email设置缓存前缀
     */
    public static final String CACHE_DICTIONARY_EMAILSETTING_PREFIX = "ebp" + CACHE_SEPARATOR + "sys" + CACHE_SEPARATOR + "dictionary" + CACHE_SEPARATOR + "emailsetting";
    /**
     * 查找密码邮件前缀
     */
    public static final String CACHE_FIND_PWD_EMAIL_PREFIX = "ebp" + CACHE_SEPARATOR + "sys" + CACHE_SEPARATOR + "findPwdEmail";

    /**
     * 选项字典缓存前缀
     */
    public static final String CACHE_DICTIONARY_OPTIONDICTIONARY_PREFIX = "ebp" + CACHE_SEPARATOR + "sys" + CACHE_SEPARATOR + "dictionary" + CACHE_SEPARATOR + "optiondictionary";

    /**
     * 地区前缀
     */
    public static final String CACHE_AREA_PREFIX = "ebp" + CACHE_SEPARATOR + "sys" + CACHE_SEPARATOR + "area";

    /**
     * 生成jwttoken缓存key<br>
     * 前缀"+CACHE_SEPARATOR+"用户id"+CACHE_SEPARATOR+"token
     *
     * @param uid
     * @param token
     * @return
     */
    public static String generateCacheJwtToken(String uid, String token) {
        return generateCacheJwtTokenPrefix(uid) + CACHE_SEPARATOR + token;
    }

    /**
     * 生成jwttoken缓存key<br>
     * 前缀"+CACHE_SEPARATOR+"用户id
     *
     * @param uid
     * @return
     */
    public static String generateCacheJwtTokenPrefix(String uid) {
        return CACHE_JWT_TOKEN_PREFIX + CACHE_SEPARATOR + uid;
    }
}
