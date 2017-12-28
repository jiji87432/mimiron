package cn.mimiron.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Consul.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link cn.mimiron.core.config.MimironProperties} for a good example.
 *
 * @author zhangxd
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

}
