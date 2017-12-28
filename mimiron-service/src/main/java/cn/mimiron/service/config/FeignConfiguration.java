package cn.mimiron.service.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxd
 */
@Configuration
@EnableFeignClients(basePackages = "cn.mimiron.service")
public class FeignConfiguration {

}
