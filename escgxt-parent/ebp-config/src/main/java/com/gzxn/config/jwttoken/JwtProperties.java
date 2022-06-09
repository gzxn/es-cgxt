package com.gzxn.config.jwttoken;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Package: com.gzxn.config.jwttoken
 * @ClassName: JwtProperties
 * @Author: CodeBird
 * @Date: 2022-03-06 13:58
 * @Description: Jwt配置
 */
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    /**
     * 密钥（4位或以上）
     */
    private String secret;

    /**
     * 时效
     */
    private Long duration = 7200000L;

    /**
     * 刷新时间
     */
    private Long flush = 1800000L;

}
