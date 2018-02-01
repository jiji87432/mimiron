package cn.mimiron.uaa.entity;

import cn.mimiron.core.entity.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.util.Objects;

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
    @TableField("name")
    private String name;
    /**
     * 删除标记
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(name, role.name) &&
            Objects.equals(deleted, role.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, deleted);
    }

    @Override
    public String toString() {
        return "Role{" +
        ", name=" + name +
        ", deleted=" + deleted +
        "}";
    }
}
