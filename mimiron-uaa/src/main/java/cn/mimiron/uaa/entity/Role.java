package cn.mimiron.uaa.entity;

import cn.mimiron.core.entity.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableLogic;

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
    /**
     * 删除标记
     */
    @TableLogic
    private Boolean deleted;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", name=" + name +
        ", deleted=" + deleted +
        "}";
    }
}
