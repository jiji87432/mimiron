package cn.mimiron.uaa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zhangxd
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrect password")
public class InvalidPasswordException extends RuntimeException {

}
