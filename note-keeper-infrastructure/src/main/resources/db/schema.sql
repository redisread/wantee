DROP TABLE IF EXISTS `country`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `tag`;
DROP TABLE IF EXISTS `clothes_tag_relation`;
DROP TABLE IF EXISTS `outfits`;
DROP TABLE IF EXISTS `outfit_clothes_relation`;

create table `country`
(
    `id`           int          not null auto_increment,
    `country_name` varchar(255) null,
    `country_code` varchar(255) null,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
    `id` BIGINT AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `password_hash` VARCHAR(255) NOT NULL,
    `create_time` DATETIME,
    `update_time` DATETIME,
    PRIMARY KEY(`id`)
);

COMMENT ON TABLE `user` IS '用户表';

CREATE TABLE `clothes` (
	`id` BIGINT AUTO_INCREMENT,
	`user_id` BIGINT NOT NULL,
	`name` VARCHAR(100) NOT NULL,
	`type` VARCHAR(50) NOT NULL,
	`color` VARCHAR(50),
	`season` VARCHAR(50),
	`image_url` VARCHAR(255),
	`update_time` DATETIME,
	`create_time` DATETIME,
	PRIMARY KEY(`id`)
);

COMMENT ON TABLE `clothes` IS '衣物表';

CREATE TABLE `tag` (
	`id` BIGINT AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL UNIQUE,
	PRIMARY KEY(`id`)
);


CREATE TABLE IF NOT EXISTS clothes_tag_relation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    clothes_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_clothes_id ON clothes_tag_relation (clothes_id);


CREATE TABLE `outfits` (
	`id` BIGINT AUTO_INCREMENT,
	`user_id` BIGINT NOT NULL,
	`name` VARCHAR(100),
	`create_time` DATETIME,
	`update_time` DATETIME,
	PRIMARY KEY(`id`)
);


CREATE TABLE outfit_clothes_relation (
    outfit_id BIGINT NOT NULL,
    clothes_id BIGINT NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_outfit_id ON outfit_clothes_relation (outfit_id);