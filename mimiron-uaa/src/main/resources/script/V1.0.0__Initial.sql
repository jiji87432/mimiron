CREATE TABLE `user` (
    `id`             BIGINT(20)          NOT NULL AUTO_INCREMENT
    COMMENT '主键',
    `login`          VARCHAR(50)         NOT NULL
    COMMENT '登录名',
    `password`       VARCHAR(60)                  DEFAULT NULL
    COMMENT '密码',
    `first_name`     VARCHAR(50)                  DEFAULT NULL
    COMMENT '名',
    `last_name`      VARCHAR(50)                  DEFAULT NULL
    COMMENT '姓',
    `email`          VARCHAR(100)                 DEFAULT NULL
    COMMENT '邮件',
    `image_url`      VARCHAR(256)                 DEFAULT NULL
    COMMENT '头像地址',
    `is_activated`   TINYINT(1) UNSIGNED NOT NULL DEFAULT 0
    COMMENT '激活标记',
    `activation_key` VARCHAR(20)                  DEFAULT NULL
    COMMENT '激活验证码',
    `reset_key`      VARCHAR(20)                  DEFAULT NULL
    COMMENT '重置密码验证码',
    `reset_date`     DATETIME(0)                  DEFAULT NULL
    COMMENT '充值密码时间',
    `gmt_create`     DATETIME(0)                  DEFAULT NULL
    COMMENT '创建时间',
    `gmt_modified`   DATETIME(0)                  DEFAULT NULL
    COMMENT '更新时间',
    `is_deleted`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 0
    COMMENT '删除标记',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    COMMENT = '用户';

CREATE TABLE `role` (
    `id`           BIGINT(20)          NOT NULL AUTO_INCREMENT
    COMMENT '主键',
    `name`         VARCHAR(50)         NOT NULL
    COMMENT '角色',
    `gmt_create`   DATETIME(0)                  DEFAULT NULL
    COMMENT '创建时间',
    `gmt_modified` DATETIME(0)                  DEFAULT NULL
    COMMENT '更新时间',
    `is_deleted`   TINYINT(1) UNSIGNED NOT NULL DEFAULT 0
    COMMENT '删除标记',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    COMMENT = '角色';

CREATE TABLE `user_role` (
    `user_id` BIGINT(20) NOT NULL
    COMMENT '用户ID',
    `role_id` BIGINT(20) NOT NULL
    COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`)
)
    ENGINE = InnoDB
    COMMENT = '用户角色';

INSERT INTO `user` (`id`, `login`, `password`, `first_name`, `last_name`, `email`, `image_url`, `is_activated`) VALUES
    ('1', 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System',
     'system@localhost', '', 1);
INSERT INTO `user` (`id`, `login`, `password`, `first_name`, `last_name`, `email`, `image_url`, `is_activated`) VALUES
    ('2', 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User',
     'anonymous@localhost', '', 1);
INSERT INTO `user` (`id`, `login`, `password`, `first_name`, `last_name`, `email`, `image_url`, `is_activated`) VALUES
    ('3', 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator',
     'admin@localhost', '', 1);
INSERT INTO `user` (`id`, `login`, `password`, `first_name`, `last_name`, `email`, `image_url`, `is_activated`) VALUES
    ('4', 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', '',
     1);

INSERT INTO `role` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `role` (`id`, `name`) VALUES ('2', 'ROLE_USER');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '2');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('3', '1');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('3', '2');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('4', '2');
