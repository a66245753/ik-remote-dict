CREATE TABLE `ik_ext_dict` (
                               `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `dict` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '扩展词' COLLATE 'utf8mb3_general_ci',
                               `created_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE,
                               UNIQUE INDEX `dict` (`dict`) USING BTREE
)
    COMMENT='ik扩展词数据'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `ik_stop_dict` (
                                `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `dict` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '停用词' COLLATE 'utf8mb3_general_ci',
                                `created_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                                PRIMARY KEY (`id`) USING BTREE,
                                UNIQUE INDEX `dict` (`dict`) USING BTREE
)
    COMMENT='ik停用词数据'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB
;
