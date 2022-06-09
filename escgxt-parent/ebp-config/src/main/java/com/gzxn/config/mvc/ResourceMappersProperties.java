package com.gzxn.config.mvc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Package: com.gzxn.config.mvc
 * @ClassName: ResourceMappersProperties
 * @Author: CodeBird
 * @Date: 2022-03-06 14:24
 * @Description: 资源映射配置
 */
@Slf4j
@ConfigurationProperties(prefix = "resource")
@Data
public class ResourceMappersProperties {

    private List<ResourceMapper> mappers;

    @Data
    public static class ResourceMapper {
        private String uri;
        private String file;

    }

}
