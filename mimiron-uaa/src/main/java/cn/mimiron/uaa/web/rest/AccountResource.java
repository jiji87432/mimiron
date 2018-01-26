package cn.mimiron.uaa.web.rest;

import cn.mimiron.uaa.model.User;
import cn.mimiron.uaa.service.UserService;
import cn.mimiron.uaa.service.dto.UserDTO;
import cn.mimiron.uaa.web.rest.errors.EmailAlreadyUsedException;
import cn.mimiron.uaa.web.rest.errors.InternalServerErrorException;
import cn.mimiron.uaa.web.rest.errors.InvalidPasswordException;
import cn.mimiron.uaa.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * REST controller for managing the current user's account.
 *
 * @author zhangxd
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserService userService;

    public AccountResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET  /authenticate : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request
     * @return the login if the user is authenticated
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * GET  /account : get the current user.
     *
     * @return the current user
     * @throws RuntimeException 500 (Internal Server Error) if the user couldn't be returned
     */
    @GetMapping("/account")
    public UserDTO getAccount() {
        User user = userService.getUserWithAuthorities();
        if (user == null) {
            throw new InternalServerErrorException("User could not be found");
        }
        return new UserDTO(user);
    }

    /**
     * PUT  /account : update the current user information.
     *
     * @param userDTO the current user information
     * @throws EmailAlreadyUsedException 400 (Bad Request) if the email is already used
     * @throws RuntimeException 500 (Internal Server Error) if the user login wasn't found
     */
    @PutMapping("/account")
    public void saveAccount(@Valid @RequestBody UserDTO userDTO) {
        userService.updateCurrentAccount(userDTO);
    }

    /**
     * PUT  /account/change-password : changes the current user's password
     *
     * @param password the new password
     * @throws InvalidPasswordException 400 (Bad Request) if the new password is incorrect
     */
    @PutMapping(path = "/account/change-password")
    public void changePassword(@RequestBody String password) {
        if (!checkPasswordLength(password)) {
            throw new InvalidPasswordException();
        }
        userService.changePassword(password);
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}
