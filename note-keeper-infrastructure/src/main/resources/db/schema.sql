DROP TABLE IF EXISTS `country`;

create table `country`
(
    `id`           int          not null auto_increment,
    `country_name` varchar(255) null,
    `country_code` varchar(255) null,
    PRIMARY KEY (`id`)
);
