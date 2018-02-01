package cn.mimiron.uaa.service;

import cn.mimiron.uaa.entity.User;

/**
 * <p>
 * 邮件 服务类
 * </p>
 *
 * @author zhangxd
 * @since 2018-02-01
 */
public interface IMailService {

    void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

    void sendEmailFromTemplate(User user, String templateName, String titleKey);

    void sendActivationEmail(User user);

    void sendCreationEmail(User user);

    void sendPasswordResetMail(User user);

}
