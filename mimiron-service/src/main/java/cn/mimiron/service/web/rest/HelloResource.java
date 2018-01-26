package cn.mimiron.service.web.rest;

import cn.mimiron.core.security.AuthoritiesConstants;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Resource to return information about the currently running Spring profiles.
 * @author zhangxd
 */
@RestController
@RequestMapping("/api")
public class HelloResource {

    @GetMapping("/hello")
    @Secured(AuthoritiesConstants.ADMIN)
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello1")
    public String hello1() {
        return "hello1";
    }
}
