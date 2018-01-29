package cn.mimiron.uaa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhangxd
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "cn.mimiron.uaa.mapper")
public class DatabaseConfiguration {

}
