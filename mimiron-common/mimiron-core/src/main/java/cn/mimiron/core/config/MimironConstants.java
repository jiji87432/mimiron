package cn.mimiron.core.config;

import org.springframework.http.MediaType;

/**
 * Mimiron constants.
 *
 * @author zhangxd
 */
public final class MimironConstants {

    /**
     * Spring profiles for development, test and production
     */
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    /**
     * Spring profile used to disable swagger
     */
    public static final String SPRING_PROFILE_SWAGGER = "swagger";

    public final static String TEXT_HTML_UTF8_VALUE = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8";

    private MimironConstants() {
    }
}
