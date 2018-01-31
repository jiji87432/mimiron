package cn.mimiron.uaa.entity;

import com.baomidou.mybatisplus.annotations.Version;
import cn.mimiron.core.entity.BaseEntity;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author zhangxd
 * @since 2018-02-01
 */
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色
     */
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", name=" + name +
        "}";
    }
}
