package cn.mimiron.uaa.entity;

import cn.mimiron.core.entity.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author zhangxd
 * @since 2018-02-01
 */
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
    private String firstName;
    /**
     * 姓
     */
    private String lastName;
    /**
     * 邮件
     */
    private String email;
    /**
     * 头像地址
     */
    private String imageUrl;
    /**
     * 激活标记
     */
    private Boolean activated;
    /**
     * 激活验证码
     */
    private String activationKey;
    /**
     * 重置密码验证码
     */
    private String resetKey;
    /**
     * 充值密码时间
     */
    private Date resetDate;
    /**
     * 删除标记
     */
    @TableLogic
    private Boolean deleted;

    @TableField(exist=false)
    private Set<Role> roles = new HashSet<>();


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = StringUtils.lowerCase(email, Locale.ENGLISH);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Date getResetDate() {
        return resetDate;
    }

    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
            ", login=" + login +
            ", password=" + password +
            ", firstName=" + firstName +
            ", lastName=" + lastName +
            ", email=" + email +
            ", imageUrl=" + imageUrl +
            ", activated=" + activated +
            ", activationKey=" + activationKey +
            ", resetKey=" + resetKey +
            ", resetDate=" + resetDate +
            ", deleted=" + deleted +
            "}";
    }
}
