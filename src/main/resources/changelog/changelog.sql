create table note
(
    id               int auto_increment primary key,
    content          text         not null,
    tags             varchar(128),
    visibility enum ('PUBLIC','PRIVATE','PROTECT') not null default 'PUBLIC',
    status   enum ('NORMAL','ARCHIVED')          not null default 'NORMAL',
    user_id       int not null,
    create_time      datetime     not null DEFAULT CURRENT_TIMESTAMP,
    update_time      datetime     not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
    comment '笔记';


create table tag
(
    id          int auto_increment primary key,
    name        varchar(50) not null,
    user_id     int         not null,
    create_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT idx_user_tag UNIQUE KEY (user_id,name)
)
    comment '标签';


create table user
(
    id            int primary key auto_increment,
    username      varchar(30)  not null,
    password_hash varchar(100) not null,
    email         varchar(100),
    display_name  varchar(30),
    bio           varchar(64),
    create_time   datetime                     default current_timestamp,
    update_time   datetime                     default current_timestamp on update current_timestamp,
    `role`        enum ('HOST','ADMIN','USER') default 'USER',
    avatar_url    varchar(150),
    unique (username)
)
    comment '用户';


alter table user add `open_id` varchar(64);