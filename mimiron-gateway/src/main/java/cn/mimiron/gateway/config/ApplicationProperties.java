package cn.mimiron.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Properties specific to Gateway.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link cn.mimiron.core.config.MimironProperties} for a good example.
 *
 * @author zhangxd
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Gateway gateway = new Gateway();

    public Gateway getGateway() {
        return gateway;
    }

    public static class Gateway {

        private final RateLimiting rateLimiting = new RateLimiting();

        public RateLimiting getRateLimiting() {
            return rateLimiting;
        }

        private Map<String, List<String>> authorizedMicroservicesEndpoints = new LinkedHashMap<>();

        public Map<String, List<String>> getAuthorizedMicroservicesEndpoints() {
            return authorizedMicroservicesEndpoints;
        }

        public void setAuthorizedMicroservicesEndpoints(Map<String, List<String>> authorizedMicroservicesEndpoints) {
            this.authorizedMicroservicesEndpoints = authorizedMicroservicesEndpoints;
        }

        public static class RateLimiting {

            private boolean enabled = false;

            private long limit = 100_000L;

            private int durationInSeconds = 3_600;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public long getLimit() {
                return this.limit;
            }

            public void setLimit(long limit) {
                this.limit = limit;
            }

            public int getDurationInSeconds() {
                return durationInSeconds;
            }

            public void setDurationInSeconds(int durationInSeconds) {
                this.durationInSeconds = durationInSeconds;
            }
        }
    }

}
