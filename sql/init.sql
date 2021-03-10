set names utf8mb4;

drop table if exists user;
create table user(
    id          int             not null auto_increment,
    username    varchar(20)     not null,
    password    varchar(255)    not null,
    primary key (id)

);
insert into user(username,password) values('123','123456');