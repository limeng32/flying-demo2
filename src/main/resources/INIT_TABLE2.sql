drop table if exists PERSON;

drop table if exists ROLE;

create table ROLE
(
   ID         			varchar(40) not null ,
   NAME                 varchar(40) ,
   VALUE            	varchar(20) ,
   primary key (ID)
);

insert into ROLE
values ('1', '普通会员', 'normal');

insert into ROLE
values ('2', '银牌会员', 'silver');

insert into ROLE
values ('3', '金牌会员', 'gold');

create table PERSON
(
   _ID         			varchar(40) not null ,
   NAME                 varchar(40) ,
   ROLE_ID            	varchar(40) ,
   primary key (_ID)
);

insert into PERSON
values ('1', '张三', '1');

insert into PERSON
values ('2', '李四', '2');

insert into PERSON
values ('3', '王五', '3');