package cn.mimiron.gateway.config;

import cn.mimiron.core.config.MimironConstants;
import cn.mimiron.gateway.aop.logging.LoggingAspect;


import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * @author zhangxd
 */
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile(MimironConstants.SPRING_PROFILE_DEVELOPMENT)
    public LoggingAspect loggingAspect(Environment env) {
        return new LoggingAspect(env);
    }
}
