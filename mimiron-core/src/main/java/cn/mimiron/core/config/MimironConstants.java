package cn.mimiron.core.config;

/**
 * Mimiron constants.
 * @author zhangxd
 */
public final class MimironConstants {

    // Spring profiles for development, test and production

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    // Spring profile used to disable swagger

    public static final String SPRING_PROFILE_SWAGGER = "swagger";

    public static final String PROPERTY_SERVER_SSL_KEYSTORE = "server.ssl.key-store";

    private MimironConstants() {
    }
}
