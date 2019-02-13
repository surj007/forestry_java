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
    status tinyint(1) unsigned not null default 1,
    last_modify_time timestamp not null default current_timestamp ,
) engine = InnoDB default charset = utf8;

create table role (
    id int unsigned not null primary key auto_increment,
    name varchar(255) not null,
    nameZh varchar(255) not null
) engine = InnoDB default charset = utf8;

create table user_role (
    id int unsigned not null primary key auto_increment,
    uid int unsigned not null,
    rid int unsigned not null
) engine = InnoDB default charset = utf8;

create table company (
    id int unsigned not null primary key auto_increment,
    name varchar(50) not null unique key,
    corporation varchar(20) not null,
    phone varchar(20) not null,
    address varchar(100) not null,
    store varchar(200) not null,
    companyType varchar(20) not null,
    source varchar(50) not null,
    outCityCompany tinyint(1) unsigned,
    outCityCompanyName varchar(200),
    kind varchar(200) not null,
    saw varchar(20),
    sawOutput varchar(20),
    other varchar(20),
    otherOutput varchar(20),
    product varchar(100),
    saleArea varchar(100),
    saleMount varchar(20),
    remark varchar(200),
    licencePic varchar(1000) not null,
    cardFrontPic varchar(1000) not null,
    cardOppositePic varchar(1000) not null,
    notificationPic varchar(1000) not null,
    commitPic varchar(1000) not null
) engine = InnoDB default charset = utf8;

create table company_user (
    id int unsigned not null primary key auto_increment,
    uid int unsigned not null,
    cid int unsigned not null
) engine = InnoDB default charset = utf8;

CREATE PROCEDURE delete_code()
BEGIN
UPDATE `user` SET `code` = NULL WHERE TIME_TO_SEC(TIMEDIFF(NOW(), last_modify_time)) > 300 AND `code` IS NOT NULL;
END

CREATE EVENT delete_code_event
ON SCHEDULE EVERY 30 SECOND STARTS NOW() ON COMPLETION PRESERVE DO
CALL delete_code()