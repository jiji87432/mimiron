package cn.mimiron.uaa.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 登录名
     */
    private String login;

    /**
     * 密码
     */
    private String password;

    /**
     * 名
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * 姓
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * 邮件
     */
    private String email;

    /**
     * 头像地址
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 激活标记
     */
    @Column(name = "is_activated")
    private Boolean activated;

    /**
     * 激活验证码
     */
    @Column(name = "activation_key")
    private String activationKey;

    /**
     * 重置密码验证码
     */
    @Column(name = "reset_key")
    private String resetKey;

    /**
     * 充值密码时间
     */
    @Column(name = "reset_date")
    private Date resetDate;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    private Set<Authority> authorities = new HashSet<>();

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取登录名
     *
     * @return login - 登录名
     */
    public String getLogin() {
        return login;
    }

    /**
     * 设置登录名
     *
     * @param login 登录名
     */
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取名
     *
     * @return first_name - 名
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 设置名
     *
     * @param firstName 名
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 获取姓
     *
     * @return last_name - 姓
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 设置姓
     *
     * @param lastName 姓
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 获取邮件
     *
     * @return email - 邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮件
     *
     * @param email 邮件
     */
    public void setEmail(String email) {
        this.email = StringUtils.lowerCase(email, Locale.ENGLISH);
    }

    /**
     * 获取头像地址
     *
     * @return image_url - 头像地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置头像地址
     *
     * @param imageUrl 头像地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取激活标记
     *
     * @return is_activated - 激活标记
     */
    public Boolean getActivated() {
        return activated;
    }

    /**
     * 设置激活标记
     *
     * @param activated 激活标记
     */
    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    /**
     * 获取激活验证码
     *
     * @return activation_key - 激活验证码
     */
    public String getActivationKey() {
        return activationKey;
    }

    /**
     * 设置激活验证码
     *
     * @param activationKey 激活验证码
     */
    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    /**
     * 获取重置密码验证码
     *
     * @return reset_key - 重置密码验证码
     */
    public String getResetKey() {
        return resetKey;
    }

    /**
     * 设置重置密码验证码
     *
     * @param resetKey 重置密码验证码
     */
    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    /**
     * 获取充值密码时间
     *
     * @return reset_date - 充值密码时间
     */
    public Date getResetDate() {
        return resetDate;
    }

    /**
     * 设置充值密码时间
     *
     * @param resetDate 充值密码时间
     */
    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
    }

    /**
     * 获取创建时间
     *
     * @return gmt_create - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取更新时间
     *
     * @return gmt_modified - 更新时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置更新时间
     *
     * @param gmtModified 更新时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLogin() == null ? other.getLogin() == null : this.getLogin().equals(other.getLogin()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getFirstName() == null ? other.getFirstName() == null : this.getFirstName().equals(other.getFirstName()))
            && (this.getLastName() == null ? other.getLastName() == null : this.getLastName().equals(other.getLastName()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
            && (this.getActivated() == null ? other.getActivated() == null : this.getActivated().equals(other.getActivated()))
            && (this.getActivationKey() == null ? other.getActivationKey() == null : this.getActivationKey().equals(other.getActivationKey()))
            && (this.getResetKey() == null ? other.getResetKey() == null : this.getResetKey().equals(other.getResetKey()))
            && (this.getResetDate() == null ? other.getResetDate() == null : this.getResetDate().equals(other.getResetDate()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLogin() == null) ? 0 : getLogin().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
        result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getActivated() == null) ? 0 : getActivated().hashCode());
        result = prime * result + ((getActivationKey() == null) ? 0 : getActivationKey().hashCode());
        result = prime * result + ((getResetKey() == null) ? 0 : getResetKey().hashCode());
        result = prime * result + ((getResetDate() == null) ? 0 : getResetDate().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", login=").append(login);
        sb.append(", password=").append(password);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", activated=").append(activated);
        sb.append(", activationKey=").append(activationKey);
        sb.append(", resetKey=").append(resetKey);
        sb.append(", resetDate=").append(resetDate);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}
