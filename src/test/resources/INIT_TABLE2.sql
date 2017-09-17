drop table if exists ROLE;

drop table if exists PERSON;

create table ROLE
(
   ID         			varchar(40) not null ,
   NAME                 varchar(40) ,
   VALUE            	varchar(20) ,
   primary key (ID)
);

create table PERSON
(
   ID         			varchar(40) not null ,
   NAME                 varchar(40) ,
   ROLE_ID            	varchar(40) ,
   primary key (ID)
);
