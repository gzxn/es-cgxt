package com.gzxn.config.mvc;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.gzxn.config.mvc.ResourceMappersProperties.ResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.List;
/**
 * @Package: com.gzxn.config.mvc
 * @ClassName: MvcConfig
 * @Author: CodeBird
 * @Date: 2022-03-06 14:23
 * @Description: Mvc配置
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ResourceMappersProperties.class)
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private ResourceMappersProperties mappersProperties;

    /**
     * 外部文件访问
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (mappersProperties != null && mappersProperties.getMappers() != null && mappersProperties.getMappers().size() > 0) {
            for (ResourceMapper resourceMapper : mappersProperties.getMappers()) {
                registry.addResourceHandler("/" + resourceMapper.getUri() + "/**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceMapper.getFile() + File.separator);
            }
        }
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1.创建一个convert消息转换对象
        FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        // 2.创建一个fastJson的配置对象,然后配置格式化信息
        FastJsonConfig config = new FastJsonConfig();
//		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 3.在convert中添加配置信息
        fastConvert.setFastJsonConfig(config);
        // 4.将convert添加到converts里面
        // SpringBoot 2.0.1版本中加载WebMvcConfigurer的顺序发生了变动，故需使用converters.add(0,
        // converter);指定FastJsonHttpMessageConverter在converters内的顺序，否则在SpringBoot
        // 2.0.1及之后的版本中将优先使用Jackson处理。
        converters.add(0, fastConvert);
    }

}
