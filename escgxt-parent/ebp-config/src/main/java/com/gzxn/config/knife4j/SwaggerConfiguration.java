package com.gzxn.config.knife4j;

import cn.hutool.core.collection.CollUtil;
import com.gzxn.core.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.List;

/**
 * @Package: com.gzxn.config.knife4j
 * @ClassName: SwaggerConfiguration
 * @Author: CodeBird
 * @Date: 2022-03-06 14:07
 * @Description: knife4j配置文件
 */
@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {
    @Bean(value = "systemApi")
    public Docket systemApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder()
                // .title("swagger-bootstrap-ui-demo RESTful APIs")
                .description("# swagger-bootstrap-ui-demo RESTful APIs").termsOfServiceUrl("http://www.xx.com/").contact("xx@qq.com").version("1.0").build())
                // 分组名称
                .groupName("系统模块").select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.gzxn.ebp.system")).paths(PathSelectors.any()).build()
                .securityContexts(CollUtil.newArrayList(securityContext()))
                .securitySchemes(CollUtil.newArrayList(apiKey()));
        return docket;
    }
    @Bean(value = "monitorApi")
    public Docket monitorApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder()
                // .title("swagger-bootstrap-ui-demo RESTful APIs")
                .description("# swagger-bootstrap-ui-demo RESTful APIs").termsOfServiceUrl("http://www.xx.com/").contact("xx@qq.com").version("1.0").build())
                // 分组名称
                .groupName("监控模块").select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.gzxn.ebp.monitor")).paths(PathSelectors.any()).build()
                .securityContexts(CollUtil.newArrayList(securityContext()))
                .securitySchemes(CollUtil.newArrayList(apiKey()));
        return docket;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
    }

    private ApiKey apiKey() {
        return new ApiKey(Constant.REQUEST_HEADER_TOKEN, Constant.REQUEST_HEADER_TOKEN, "header");
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return CollUtil.newArrayList(new SecurityReference(Constant.REQUEST_HEADER_TOKEN, authorizationScopes));
    }

}
