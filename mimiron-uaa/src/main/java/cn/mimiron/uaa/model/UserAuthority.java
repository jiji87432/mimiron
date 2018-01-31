//package cn.mimiron.uaa.model;
//
//import javax.persistence.*;
//
//@Table(name = "user_authority")
//public class UserAuthority {
//    /**
//     * 用户ID
//     */
//    @Id
//    @Column(name = "user_id")
//    private Long userId;
//
//    /**
//     * 角色
//     */
//    @Id
//    @Column(name = "authority_name")
//    private String authorityName;
//
//    /**
//     * 获取用户ID
//     *
//     * @return user_id - 用户ID
//     */
//    public Long getUserId() {
//        return userId;
//    }
//
//    public UserAuthority withUserId(Long userId) {
//        this.setUserId(userId);
//        return this;
//    }
//
//    /**
//     * 设置用户ID
//     *
//     * @param userId 用户ID
//     */
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    /**
//     * 获取角色
//     *
//     * @return authority_name - 角色
//     */
//    public String getAuthorityName() {
//        return authorityName;
//    }
//
//    public UserAuthority withAuthorityName(String authorityName) {
//        this.setAuthorityName(authorityName);
//        return this;
//    }
//
//    /**
//     * 设置角色
//     *
//     * @param authorityName 角色
//     */
//    public void setAuthorityName(String authorityName) {
//        this.authorityName = authorityName;
//    }
//
//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        UserAuthority other = (UserAuthority) that;
//        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
//            && (this.getAuthorityName() == null ? other.getAuthorityName() == null : this.getAuthorityName().equals(other.getAuthorityName()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
//        result = prime * result + ((getAuthorityName() == null) ? 0 : getAuthorityName().hashCode());
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", userId=").append(userId);
//        sb.append(", authorityName=").append(authorityName);
//        sb.append("]");
//        return sb.toString();
//    }
//}
