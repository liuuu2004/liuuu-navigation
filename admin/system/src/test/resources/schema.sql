CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL,
                            `username` varchar(40) NOT NULL DEFAULT '',
                            `nick_name` varchar(40) NULL DEFAULT '',
                            `password` varchar(100) NULL DEFAULT '',
                            `sex` tinyint(1) NULL DEFAULT 1,
                            `user_type` tinyint(1) NULL DEFAULT 1,
                            `email` varchar(50) NULL DEFAULT '',
                            `mobile_phone` varchar(11) NULL DEFAULT '',
                            `avatar` varchar(100) NULL DEFAULT '',
                            `status` tinyint(1) NULL DEFAULT 1,
                            `remark` varchar(500) NULL DEFAULT '',
                            `del_flag` tinyint(1) NULL DEFAULT 1,
                            `fk_create_user_id` bigint(20) NULL DEFAULT NULL,
                            `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                            `fk_modify_user_id` bigint(20) NULL DEFAULT NULL,
                            `gmt_modify` datetime NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE INDEX `unique_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB ROW_FORMAT = DYNAMIC;

CREATE TABLE `log_operation` (
                                 `id` bigint(20) NOT NULL,
                                 `module_name` varchar(50) NULL DEFAULT '',
                                 `business_type` tinyint(1) NULL DEFAULT 1,
                                 `operate_type` tinyint(1) NULL DEFAULT 1,
                                 `fk_user_id` bigint(20) NULL DEFAULT NULL,
                                 `request_method` varchar(10) NULL DEFAULT '',
                                 `class_method` varchar(255) NULL DEFAULT '',
                                 `request_url` varchar(255) NULL DEFAULT '',
                                 `ip_address` varchar(200) NULL DEFAULT '',
                                 `operate_location` varchar(255) NULL DEFAULT '',
                                 `request_param` longtext NULL,
                                 `return_result` longtext NULL,
                                 `status` tinyint(1) NULL DEFAULT 1,
                                 `gmt_operate` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                 `error_message` longtext NULL,
                                 `browser_type` varchar(50) NULL DEFAULT '',
                                 `operate_system` varchar(50) NULL DEFAULT '',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB ROW_FORMAT = DYNAMIC;


