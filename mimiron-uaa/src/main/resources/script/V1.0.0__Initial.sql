CREATE TABLE `user`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `login` varchar(50) NOT NULL COMMENT '登录名',
    `password` varchar(60) DEFAULT NULL COMMENT '密码',
    `first_name` varchar(50) DEFAULT NULL COMMENT '名',
    `last_name` varchar(50) DEFAULT NULL COMMENT '姓',
    `email` varchar(100) DEFAULT NULL COMMENT '邮件',
    `image_url` varchar(256) DEFAULT NULL COMMENT '头像地址',
    `is_activated` tinyint(1) UNSIGNED NOT NULL COMMENT '激活标记',
    `activation_key` varchar(20) DEFAULT NULL COMMENT '激活验证码',
    `reset_key` varchar(20) DEFAULT NULL COMMENT '重置密码验证码',
    `reset_date` datetime(0) DEFAULT NULL COMMENT '充值密码时间',
    `gmt_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime(0) DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_login`(`login`),
    UNIQUE INDEX `uk_email`(`email`)
) ENGINE = InnoDB COMMENT = '用户';

CREATE TABLE `authority`  (
    `name` varchar(50) NOT NULL COMMENT '角色',
    PRIMARY KEY (`name`)
) ENGINE = InnoDB COMMENT = '角色';

CREATE TABLE `user_authority`  (
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `authority_name` varchar(50) NOT NULL COMMENT '角色',
    PRIMARY KEY (`user_id`, `authority_name`)
) ENGINE = InnoDB COMMENT = '用户角色';

INSERT INTO `user` (`id`, `login`, `password`, `first_name`, `last_name`, `email`, `image_url`, `is_activated`) VALUES ('1', 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System', 'system@localhost', '', 1);
INSERT INTO `user` (`id`, `login`, `password`, `first_name`, `last_name`, `email`, `image_url`, `is_activated`) VALUES ('2', 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User', 'anonymous@localhost', '', 1);
INSERT INTO `user` (`id`, `login`, `password`, `first_name`, `last_name`, `email`, `image_url`, `is_activated`) VALUES ('3', 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', '', 1);
INSERT INTO `user` (`id`, `login`, `password`, `first_name`, `last_name`, `email`, `image_url`, `is_activated`) VALUES ('4', 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', '', 1);

INSERT INTO `authority` (`name`) VALUES ('ROLE_ADMIN');
INSERT INTO `authority` (`name`) VALUES ('ROLE_USER');

INSERT INTO `user_authority` (`user_id`, `authority_name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `user_authority` (`user_id`, `authority_name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `user_authority` (`user_id`, `authority_name`) VALUES ('3', 'ROLE_ADMIN');
INSERT INTO `user_authority` (`user_id`, `authority_name`) VALUES ('3', 'ROLE_USER');
INSERT INTO `user_authority` (`user_id`, `authority_name`) VALUES ('4', 'ROLE_USER');
