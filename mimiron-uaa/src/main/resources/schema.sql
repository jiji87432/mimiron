DROP TABLE IF EXISTS `user`;
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
    `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_login`(`login`),
    UNIQUE INDEX `uk_email`(`email`)
) ENGINE = InnoDB COMMENT = '用户';

DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
    `name` varchar(50) NOT NULL COMMENT '角色',
    PRIMARY KEY (`name`)
) ENGINE = InnoDB COMMENT = '角色';

DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority`  (
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `authority_name` varchar(50) NOT NULL COMMENT '角色',
    PRIMARY KEY (`user_id`, `authority_name`)
) ENGINE = InnoDB COMMENT = '用户角色';
