create table users(
    id varchar(32) primary key comment '用户id',
    userName varchar(32) not null unique comment '用户名',
    password varchar(128) not null comment '密码',
    role varchar(1) not null default '0' comment '角色'
) comment '用户表';

-- 创建书本表
create table books(
    id varchar(32) primary key comment '书本id',
    bookName varchar(32) not null comment '书名',
    bookAuthor varchar(32) not null comment '作者',
    publisher varchar(32) not null comment '出版社',
    price double	not null comment '价格',
    bookNum int(11) not null comment '书号',
    publishDate date not null comment '出版日期'
) comment '书本表';