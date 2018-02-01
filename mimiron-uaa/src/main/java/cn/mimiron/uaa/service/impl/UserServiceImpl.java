package cn.mimiron.uaa.service.impl;

import cn.mimiron.core.exception.InternalServerErrorException;
import cn.mimiron.core.security.AuthoritiesConstants;
import cn.mimiron.core.security.SecurityUtils;
import cn.mimiron.core.service.impl.BaseServiceImpl;
import cn.mimiron.uaa.entity.Role;
import cn.mimiron.uaa.entity.User;
import cn.mimiron.uaa.entity.UserRole;
import cn.mimiron.uaa.exception.EmailAlreadyUsedException;
import cn.mimiron.uaa.exception.EmailNotFoundException;
import cn.mimiron.uaa.exception.LoginAlreadyUsedException;
import cn.mimiron.uaa.mapper.RoleMapper;
import cn.mimiron.uaa.mapper.UserMapper;
import cn.mimiron.uaa.mapper.UserRoleMapper;
import cn.mimiron.uaa.service.IUserService;
import cn.mimiron.uaa.service.dto.UserDTO;
import cn.mimiron.uaa.service.util.RandomUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author zhangxd
 * @since 2018-02-01
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    private final UserRoleMapper userRoleMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, UserRoleMapper userRoleMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserDTO userDTO, String password) {
        User loginUser = new User();
        loginUser.setLogin(userDTO.getLogin());
        loginUser = userMapper.selectOne(loginUser);
        if (loginUser != null) {
            throw new LoginAlreadyUsedException();
        }

        User emailUser = new User();
        emailUser.setEmail(userDTO.getEmail());
        emailUser = userMapper.selectOne(emailUser);
        if (emailUser != null) {
            throw new EmailAlreadyUsedException();
        }

        String encryptedPassword = passwordEncoder.encode(password);
        User newUser = new User();
        newUser.setLogin(userDTO.getLogin());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setImageUrl(userDTO.getImageUrl());
        newUser.setActivated(false);
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        newUser.setGmtCreate(new Date());
        newUser.setGmtModified(new Date());
        userMapper.insert(newUser);

        Role role = new Role();
        role.setName(AuthoritiesConstants.USER);
        role = roleMapper.selectOne(role);

        UserRole userRole = new UserRole();
        userRole.setUserId(newUser.getId());
        userRole.setRoleId(role.getId());
        userRoleMapper.insert(userRole);

        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    @Override
    public void activateRegistration(String key) {
        User user = new User();
        user.setActivationKey(key);
        user = userMapper.selectOne(user);
        if (user == null) {
            throw new InternalServerErrorException("No user was found for this activation key");
        }

        user.setActivated(true);
        user.setActivationKey(null);
        user.setGmtModified(new Date());
        userMapper.updateAllColumnById(user);
    }

    @Override
    public void changePassword(String password) {
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("login", SecurityUtils.getCurrentUserLogin());
        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setGmtModified(new Date());
        userMapper.update(user, wrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserWithRoles() {
        String login = SecurityUtils.getCurrentUserLogin();
        return userMapper.selectOneWithRoleByLoginOrEmail(login);
    }

    @Override
    public void updateCurrentAccount(UserDTO userDTO) {
        final String userLogin = SecurityUtils.getCurrentUserLogin();
        if (userLogin == null) {
            throw new InternalServerErrorException("Current user login not found");
        }

        User emailUser = new User();
        emailUser.setEmail(userDTO.getEmail());
        emailUser = userMapper.selectOne(emailUser);
        if (emailUser != null && (!userLogin.equalsIgnoreCase(emailUser.getLogin()))) {
            throw new EmailAlreadyUsedException();
        }

        User loginUser = new User();
        loginUser.setLogin(userLogin);
        loginUser = userMapper.selectOne(loginUser);
        if (loginUser == null) {
            throw new InternalServerErrorException("User could not be found");
        }
        User updateUser = new User();
        updateUser.setId(loginUser.getId());
        updateUser.setFirstName(userDTO.getFirstName());
        updateUser.setLastName(userDTO.getLastName());
        updateUser.setEmail(userDTO.getEmail());
        updateUser.setImageUrl(userDTO.getImageUrl());
        updateUser.setGmtModified(new Date());
        userMapper.updateById(updateUser);
    }

    @Override
    public User requestPasswordReset(String mail) {
        User user = new User();
        user.setEmail(mail);
        user = userMapper.selectOne(user);
        if (user == null) {
            throw new EmailNotFoundException();
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setResetKey(RandomUtil.generateResetKey());
        updateUser.setResetDate(new Date());
        updateUser.setGmtModified(new Date());
        userMapper.updateById(updateUser);

        user.setResetKey(updateUser.getResetKey());
        user.setResetDate(updateUser.getResetDate());
        user.setGmtModified(updateUser.getGmtModified());
        return user;
    }

    @Override
    public void completePasswordReset(String newPassword, String key) {
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("resetKey", key)
            .ge("resetDate", Instant.now().minusSeconds(86400));
        List<User> userList = userMapper.selectList(wrapper);
        if (userList == null || userList.isEmpty()) {
            throw new InternalServerErrorException("No user was found for this reset key");
        }
        User user = userList.get(0);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetKey(null);
        user.setResetDate(null);
        user.setGmtModified(new Date());
        userMapper.updateAllColumnById(user);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("activated", false);
        wrapper.lt("gmtCreate", Instant.now().minus(3, ChronoUnit.DAYS));
        List<User> users = userMapper.selectList(wrapper);
        Set<Long> ids = new HashSet<>();
        for (User user : users) {
            ids.add(user.getId());
        }
        userMapper.deleteBatchIds(ids);
    }

}
