package com.gzxn.config.sms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Package: com.gzxn.config.sms
 * @ClassName: WebSocketConfig
 * @Author: CodeBird
 * @Date: 2022-03-06 17:22
 * @Description: websockent配置
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
