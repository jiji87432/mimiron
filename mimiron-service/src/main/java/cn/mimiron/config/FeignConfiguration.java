package cn.mimiron.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "cn.zhangxd.mimiron")
public class FeignConfiguration {

}
