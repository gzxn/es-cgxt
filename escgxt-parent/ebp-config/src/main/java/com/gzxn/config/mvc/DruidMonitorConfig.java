package com.gzxn.config.mvc;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.gzxn.config.mvc
 * @ClassName: DruidMonitorConfig
 * @Author: CodeBird
 * @Date: 2022-03-06 14:24
 * @Description: Druid监控配置
 */
@Slf4j
@Configuration
public class DruidMonitorConfig {

    /**
     * 访问druid监控信息servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServletRegistration() {
        log.info("druid监控配置完成。。。");
        ServletRegistrationBean registration = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加初始化参数：initParams
        // 白名单：
        registration.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
        // permitted to view this page.
        registration.addInitParameter("deny", "");
        // 登录查看信息的账号密码.
        registration.addInitParameter("loginUsername", "admin");
        registration.addInitParameter("loginPassword", "admin");
        // 是否能够重置数据.
        registration.addInitParameter("resetEnable", "false");
        return registration;
    }

    /**
     * 过滤druid
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
