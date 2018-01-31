package cn.mimiron.uaa.entity;

import java.io.Serializable;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author zhangxd
 * @since 2018-02-01
 */
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色
     */
    private String roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        ", userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }
}
