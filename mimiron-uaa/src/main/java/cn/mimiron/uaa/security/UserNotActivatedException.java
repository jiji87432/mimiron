package cn.mimiron.uaa.security;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is thrown in case of a not activated user trying to authenticate.
 *
 * @author zhangxd
 */
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }
}
