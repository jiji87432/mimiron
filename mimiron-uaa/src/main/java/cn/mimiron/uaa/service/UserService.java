package cn.mimiron.uaa.service;

import cn.mimiron.core.security.AuthoritiesConstants;
import cn.mimiron.core.security.SecurityUtils;
import cn.mimiron.uaa.mapper.UserAuthorityMapper;
import cn.mimiron.uaa.mapper.UserMapper;
import cn.mimiron.uaa.model.User;
import cn.mimiron.uaa.model.UserAuthority;
import cn.mimiron.uaa.service.dto.UserDTO;
import cn.mimiron.uaa.service.util.RandomUtil;
import cn.mimiron.uaa.web.rest.errors.EmailAlreadyUsedException;
import cn.mimiron.uaa.web.rest.errors.EmailNotFoundException;
import cn.mimiron.uaa.web.rest.errors.InternalServerErrorException;
import cn.mimiron.uaa.web.rest.errors.LoginAlreadyUsedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Service class for managing users.
 *
 * @author zhangxd
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserMapper userMapper;

    private final UserAuthorityMapper userAuthorityMapper;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, UserAuthorityMapper userAuthorityMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userAuthorityMapper = userAuthorityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserDTO userDTO, String password) {
        Example loginExample = new Example(User.class);
        loginExample.createCriteria().andEqualTo("login", userDTO.getLogin().toLowerCase());
        User loginUser = userMapper.selectOneByExample(loginExample);
        if (loginUser != null) {
            throw new LoginAlreadyUsedException();
        }

        Example emailExample = new Example(User.class);
        emailExample.createCriteria().andEqualTo("email", userDTO.getEmail().toLowerCase());
        User emailUser = userMapper.selectOneByExample(emailExample);
        if (emailUser != null) {
            throw new EmailAlreadyUsedException();
        }

        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setImageUrl(userDTO.getImageUrl());
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        newUser.setGmtCreate(new Date());
        newUser.setGmtModified(new Date());
        userMapper.insertUseGeneratedKeys(newUser);

        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserId(newUser.getId());
        userAuthority.setAuthorityName(AuthoritiesConstants.USER);
        userAuthorityMapper.insert(userAuthority);

        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public void activateRegistration(String key) {
        Example example = new Example(User.class);
        example.createCriteria()
            .andEqualTo("activationKey", key)
        ;
        User user = userMapper.selectOneByExample(example);
        if (user == null) {
            throw new InternalServerErrorException("No user was found for this activation key");
        }

        user.setActivated(true);
        user.setActivationKey(null);
        user.setGmtModified(new Date());
        userMapper.updateByPrimaryKey(user);
    }

    public void changePassword(String password) {
        String login = SecurityUtils.getCurrentUserLogin();
        User user = new User();
        user.setLogin(login);
        user = userMapper.selectOne(user);
        user.setPassword(passwordEncoder.encode(password));
        user.setGmtModified(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        String login = SecurityUtils.getCurrentUserLogin();
        return userMapper.selectOneWithAuthorityByLoginOrEmail(login);
    }

    public void updateCurrentAccount(UserDTO userDTO) {
        final String userLogin = SecurityUtils.getCurrentUserLogin();
        if (userLogin == null) {
            throw new InternalServerErrorException("Current user login not found");
        }

        Example emailExample = new Example(User.class);
        emailExample.createCriteria().andEqualTo("email", userDTO.getEmail());
        User existingUser = userMapper.selectOneByExample(emailExample);
        if (existingUser != null && (!userLogin.equalsIgnoreCase(existingUser.getLogin()))) {
            throw new EmailAlreadyUsedException();
        }
        Example loginExample = new Example(User.class);
        loginExample.createCriteria().andEqualTo("login", userLogin);
        User user = userMapper.selectOneByExample(loginExample);
        if (user == null) {
            throw new InternalServerErrorException("User could not be found");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setFirstName(userDTO.getFirstName());
        updateUser.setLastName(userDTO.getLastName());
        updateUser.setEmail(userDTO.getEmail());
        updateUser.setImageUrl(userDTO.getImageUrl());
        updateUser.setGmtModified(new Date());
        userMapper.updateByPrimaryKeySelective(updateUser);
    }

    public User requestPasswordReset(String mail) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("email", mail.toLowerCase(Locale.ENGLISH));
        User user = userMapper.selectOneByExample(example);
        if (user == null) {
            throw new EmailNotFoundException();
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setResetKey(RandomUtil.generateResetKey());
        updateUser.setResetDate(new Date());
        updateUser.setGmtModified(new Date());
        userMapper.updateByPrimaryKeySelective(updateUser);

        user.setResetKey(updateUser.getResetKey());
        user.setResetDate(updateUser.getResetDate());
        user.setGmtModified(updateUser.getGmtModified());
        return user;
    }

    public void completePasswordReset(String newPassword, String key) {
        Example example = new Example(User.class);
        example.createCriteria()
            .andEqualTo("resetKey", key)
            .andGreaterThanOrEqualTo("resetDate", Instant.now().minusSeconds(86400))
        ;
        User user = userMapper.selectOneByExample(example);
        if (user == null) {
            throw new InternalServerErrorException("No user was found for this reset key");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetKey(null);
        user.setResetDate(null);
        user.setGmtModified(new Date());
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        Example example = new Example(User.class);
        example.createCriteria()
            .andEqualTo("activated", false)
            .andLessThan("gmtCreate", Instant.now().minus(3, ChronoUnit.DAYS))
        ;
        List<User> users = userMapper.selectByExample(example);
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getLogin());
            userMapper.delete(user);
        }
    }

}
