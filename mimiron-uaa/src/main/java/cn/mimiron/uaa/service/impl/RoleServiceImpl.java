package cn.mimiron.uaa.service.impl;

import cn.mimiron.uaa.entity.Role;
import cn.mimiron.uaa.mapper.RoleMapper;
import cn.mimiron.uaa.service.IRoleService;
import cn.mimiron.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author zhangxd
 * @since 2018-02-01
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

}
