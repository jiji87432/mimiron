package cn.mimiron.uaa.service.impl;

import cn.mimiron.uaa.entity.User;
import cn.mimiron.uaa.mapper.UserMapper;
import cn.mimiron.uaa.service.IUserService;
import cn.mimiron.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

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

}
