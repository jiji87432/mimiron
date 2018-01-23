package cn.mimiron.core.security.uaa;

import cn.mimiron.core.config.MimironProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * @author zhangxd
 */
@Configuration
@ConditionalOnClass({ClientCredentialsResourceDetails.class, LoadBalancerClient.class})
@ConditionalOnProperty("mimiron.security.client-authorization.client-id")
public class UaaAutoConfiguration {

    private MimironProperties mimironProperties;

    public UaaAutoConfiguration(MimironProperties mimironProperties) {
        this.mimironProperties = mimironProperties;
    }

    @Bean
    public LoadBalancedResourceDetails loadBalancedResourceDetails(LoadBalancerClient loadBalancerClient) {
        LoadBalancedResourceDetails loadBalancedResourceDetails = new LoadBalancedResourceDetails(loadBalancerClient);
        MimironProperties.Security.ClientAuthorization clientAuthorization = mimironProperties.getSecurity()
            .getClientAuthorization();
        loadBalancedResourceDetails.setAccessTokenUri(clientAuthorization.getAccessTokenUri());
        loadBalancedResourceDetails.setTokenServiceId(clientAuthorization.getTokenServiceId());
        loadBalancedResourceDetails.setClientId(clientAuthorization.getClientId());
        loadBalancedResourceDetails.setClientSecret(clientAuthorization.getClientSecret());
        return loadBalancedResourceDetails;
    }
}
