package cn.mimiron.uaa.service;

import cn.mimiron.core.security.SecurityUtils;
import cn.mimiron.uaa.dao.UserDao;
import cn.mimiron.uaa.model.User;
import cn.mimiron.uaa.service.dto.UserDTO;
import cn.mimiron.uaa.service.util.RandomUtil;
import cn.mimiron.uaa.web.rest.errors.EmailAlreadyUsedException;
import cn.mimiron.uaa.web.rest.errors.EmailNotFoundException;
import cn.mimiron.uaa.web.rest.errors.InternalServerErrorException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.time.Instant;
import java.util.Date;
import java.util.Locale;

/**
 * Service class for managing users.
 *
 * @author zhangxd
 */
@Service
@Transactional
public class UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void changePassword(String password) {
        String login = SecurityUtils.getCurrentUserLogin();
        User user = new User();
        user.setLogin(login);
        user = userDao.selectOne(user);
        user.setPassword(passwordEncoder.encode(password));
        user.setGmtModified(new Date());
        userDao.updateByPrimaryKeySelective(user);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        String login = SecurityUtils.getCurrentUserLogin();
        return userDao.selectOneWithAuthorityByLoginOrEmail(login);
    }

    public void updateCurrentAccount(UserDTO userDTO) {
        final String userLogin = SecurityUtils.getCurrentUserLogin();
        if (userLogin == null) {
            throw new InternalServerErrorException("Current user login not found");
        }

        Example emailExample = new Example(User.class);
        emailExample.createCriteria().andEqualTo("email", userDTO.getEmail());
        User existingUser = userDao.selectOneByExample(emailExample);
        if (existingUser != null && (!userLogin.equalsIgnoreCase(existingUser.getLogin()))) {
            throw new EmailAlreadyUsedException();
        }
        Example loginExample = new Example(User.class);
        loginExample.createCriteria().andEqualTo("login", userLogin);
        User user = userDao.selectOneByExample(loginExample);
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
        userDao.updateByPrimaryKeySelective(updateUser);
    }

    public User requestPasswordReset(String mail) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("email", mail.toLowerCase(Locale.ENGLISH));
        User user = userDao.selectOneByExample(example);
        if (user == null) {
            throw new EmailNotFoundException();
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setResetKey(RandomUtil.generateResetKey());
        updateUser.setResetDate(new Date());
        updateUser.setGmtModified(new Date());
        userDao.updateByPrimaryKeySelective(updateUser);

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
        User user = userDao.selectOneByExample(example);
        if (user == null) {
            throw new InternalServerErrorException("No user was found for this reset key");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetKey(null);
        user.setResetDate(null);
        user.setGmtModified(new Date());
        userDao.updateByPrimaryKey(user);
    }
}
