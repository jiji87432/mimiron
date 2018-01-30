package cn.mimiron.uaa.model;

import javax.persistence.*;

public class Authority {
    /**
     * 角色
     */
    @Id
    private String name;

    /**
     * 获取角色
     *
     * @return name - 角色
     */
    public String getName() {
        return name;
    }

    public Authority withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * 设置角色
     *
     * @param name 角色
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Authority other = (Authority) that;
        return (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}