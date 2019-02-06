drop database forestry_app;
create database forestry_app default character set utf8;
use forestry_app;

create table test (
    id int,
    name varchar(20)
) engine = InnoDB default charset = utf8;

create table user (
    id int unsigned not null primary key auto_increment,
    username varchar(20) not null unique key,
    password varchar(255) not null,
    code varchar(255),
    status tinyint(1) unsigned not null default 0,
    last_modify_time timestamp not null default current_timestamp ,
) engine = InnoDB default charset = utf8;

create table role (
    id int unsigned not null primary key auto_increment,
    name varchar(255) not null,
    nameZh varchar(255) not null
) engine = InnoDB default charset = utf8;

create table user_role (
    id int unsigned not null primary key auto_increment,
    uid int not null,
    rid int not null
) engine = InnoDB default charset = utf8;

create table menu (
    id int unsigned not null primary key auto_increment,
    path varchar(64),
    component varchar(64),
    name varchar(64),
    icon varchar(64),
    parentId int(11)
) engine = InnoDB default charset = utf8;

create table menu_role (
    id int unsigned not null primary key auto_increment,
    mid int not null,
    rid int not null
) engine = InnoDB default charset = utf8;

CREATE PROCEDURE delete_code()
BEGIN
UPDATE `user` SET `code` = NULL WHERE TIME_TO_SEC(TIMEDIFF(NOW(), last_modify_time)) > 300 AND `code` IS NOT NULL;
END

CREATE EVENT delete_code_event
ON SCHEDULE EVERY 30 SECOND STARTS NOW() ON COMPLETION PRESERVE DO
CALL delete_code()