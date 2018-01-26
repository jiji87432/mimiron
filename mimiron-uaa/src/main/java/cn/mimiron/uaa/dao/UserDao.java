package cn.mimiron.uaa.dao;

import cn.mimiron.core.dao.BaseDao;
import cn.mimiron.uaa.model.User;

public interface UserDao extends BaseDao<User> {

    User selectOneWithAuthorityByLoginOrEmail(String login);

}
