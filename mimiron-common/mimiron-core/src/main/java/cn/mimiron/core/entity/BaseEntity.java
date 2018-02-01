package cn.mimiron.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangxd
 */
public abstract class BaseEntity implements Serializable {

    /**
     * 主键
     */
    protected Long id;

    /**
     * 创建时间
     */
    protected Date gmtCreate;
    /**
     * 更新时间
     */
    protected Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
            "id=" + id +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
            '}';
    }
}
