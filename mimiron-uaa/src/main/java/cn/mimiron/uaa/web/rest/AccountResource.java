package cn.mimiron.uaa.web.rest;

import cn.mimiron.uaa.model.User;
import cn.mimiron.uaa.service.MailService;
import cn.mimiron.uaa.service.UserService;
import cn.mimiron.uaa.service.dto.UserDTO;
import cn.mimiron.uaa.web.rest.errors.*;
import cn.mimiron.uaa.web.rest.vm.KeyAndPasswordVM;
import cn.mimiron.uaa.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    private final MailService mailService;

    public AccountResource(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    /**
     * POST  /register : register the user.
     *
     * @param managedUserVM the managed user View Model
     * @throws InvalidPasswordException 400 (Bad Request) if the password is incorrect
     * @throws EmailAlreadyUsedException 400 (Bad Request) if the email is already used
     * @throws LoginAlreadyUsedException 400 (Bad Request) if the login is already used
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
        mailService.sendActivationEmail(user);
    }

    /**
     * GET  /activate : activate the registered user.
     *
     * @param key the activation key
     * @throws RuntimeException 500 (Internal Server Error) if the user couldn't be activated
     */
    @GetMapping("/activate")
    public void activateAccount(@RequestParam(value = "key") String key) {
        userService.activateRegistration(key);
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
     * @throws RuntimeException          500 (Internal Server Error) if the user login wasn't found
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

    /**
     * POST   /account/reset-password/init : Send an email to reset the password of the user
     *
     * @param mail the mail of the user
     * @throws EmailNotFoundException 400 (Bad Request) if the email address is not registered
     */
    @PostMapping(path = "/account/reset-password/init")
    public void requestPasswordReset(@RequestBody String mail) {
        User user = userService.requestPasswordReset(mail);
        mailService.sendPasswordResetMail(user);
    }

    /**
     * POST   /account/reset-password/finish : Finish to reset the password of the user
     *
     * @param keyAndPassword the generated key and the new password
     * @throws InvalidPasswordException 400 (Bad Request) if the password is incorrect
     * @throws RuntimeException         500 (Internal Server Error) if the password could not be reset
     */
    @PostMapping(path = "/account/reset-password/finish")
    public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}
