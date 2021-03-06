drop table if exists CART;

drop table if exists COMMODITY;

drop table if exists CART_COMMODITY;

create table CART
(
   ID         			varchar(40) not null ,
   DEAL                 tinyint ,
   DEAL_TIME            datetime ,
   PERSON_ID			varchar(40),
   primary key (ID)
);

create table COMMODITY
(
   ID         			varchar(40) not null ,
   NAME                 varchar(64) ,
   PRICE            	integer ,
   primary key (ID)
);

create table CART_COMMODITY
(
   ID         			varchar(40) not null ,
   CART_ID              varchar(40) ,
   COMM_ID				varchar(40) ,
   AMOUNT            	integer ,
   primary key (ID)
);