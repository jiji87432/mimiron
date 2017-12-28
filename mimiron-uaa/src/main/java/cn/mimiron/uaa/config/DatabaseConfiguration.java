package cn.mimiron.uaa.config;

import cn.mimiron.core.config.MimironConstants;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 * @author zhangxd
 */
@Configuration
@EnableJpaRepositories("cn.mimiron.uaa.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class DatabaseConfiguration {

    /**
     * Open the TCP port for the H2 database, so it is available remotely.
     *
     * @return the H2 database TCP server
     * @throws SQLException if the server failed to start
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile(MimironConstants.SPRING_PROFILE_DEVELOPMENT)
    public Server h2TCPServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers");
    }

}
