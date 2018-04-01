DROP TABLE IF EXISTS account_;
CREATE TABLE account_ (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  email varchar(320) DEFAULT NULL,
  password varchar(64) DEFAULT NULL,
  activated tinyint(1) DEFAULT NULL,
  activateValue varchar(8) DEFAULT NULL,
  opLock int(11) DEFAULT NULL,
  status char(1) DEFAULT NULL,
  role_id int(11) DEFAULT NULL,
  deputy_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY role1 (role_id) USING BTREE,
  KEY role2 (deputy_id) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=456 DEFAULT CHARSET=utf8;
insert into account_ (id, name, email, password, activated, activateValue, opLock, role_id) values ('1', 'ann', 'an%%n@live.cn','5a690d842935c51f26f473e025c1b97a','1','','1','1');
insert into account_ (id, name, email, password, activated, activateValue, opLock, role_id) values ('2', 'bob', 'bob@live.cn','5a690d842935c51f26f473e025c1b97a','1','','1','1');

DROP TABLE IF EXISTS account22;
CREATE TABLE account22 (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  email varchar(320) DEFAULT NULL,
  password varchar(64) DEFAULT NULL,
  activated tinyint(1) DEFAULT NULL,
  activateValue varchar(8) DEFAULT NULL,
  opLock int(11) DEFAULT NULL,
  status char(1) DEFAULT NULL,
  nickname varchar(32) DEFAULT NULL,
  role_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS detail_;
CREATE TABLE detail_ (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(32) DEFAULT NULL,
  loginlog_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY loginlog1 (loginlog_id) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS loginlog_;
CREATE TABLE loginlog_ (
  id int(11) NOT NULL AUTO_INCREMENT,
  loginIP varchar(50) DEFAULT NULL,
  accountId bigint DEFAULT NULL,
  loginTime datetime DEFAULT NULL,
  num int DEFAULT NULL,
  loginIP2 varchar(50) DEFAULT NULL,
  status char(1) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY account1 (accountId)
) ENGINE=InnoDB AUTO_INCREMENT=333 DEFAULT CHARSET=utf8;
insert into loginlog_ (id, loginIP, accountId) values ('1','l','2');
insert into loginlog_ (id, loginIP, accountId) values ('2','l2','2');

DROP TABLE IF EXISTS role_;
CREATE TABLE role_ (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
insert into role_ (id, name) values ('1', 'r');

DROP TABLE IF EXISTS product;
CREATE TABLE product (
  id varchar(100) NOT NULL,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

drop table if exists CART;

drop table if exists Commodity;

drop table if exists CART_COMMODITY;

create table CART
(
   ID         			varchar(40) not null ,
   DEAL                 tinyint ,
   DEAL_TIME            datetime ,
   PERSON_ID         	varchar(40),
   primary key (ID)
);

insert into CART
values ('1', '0', null,'1');

insert into CART
values ('2', '0', null,'2');

create table COMMODITY
(
   ID         			varchar(40) not null ,
   NAME                 varchar(64) ,
   PRICE            	integer ,
   primary key (ID)
);

insert into COMMODITY
values ('1', '牙刷A', '1200');

insert into COMMODITY
values ('2', '牙刷B', '1850');

insert into COMMODITY
values ('3', '牙刷C', '2100');

insert into COMMODITY
values ('4', '佳洁士牙膏', '1499');

insert into COMMODITY
values ('5', '六必治牙膏', '1999');

insert into COMMODITY
values ('6', '云南白药牙膏', '2499');

insert into COMMODITY
values ('7', '潘婷洗发露', '3500');

insert into COMMODITY
values ('8', '多芬洗发露', '3900');

insert into COMMODITY
values ('9', '海飞丝洗发露', '5100');

insert into COMMODITY
values ('10', '浴液-1500ML', '2800');

insert into COMMODITY
values ('11', '浴液-2000ML', '3200');

insert into COMMODITY
values ('12', '浴液-4000ML', '4900');

create table CART_COMMODITY
(
   ID         			bigint not null ,
   CART_ID              varchar(40) ,
   COMM_ID				varchar(40) ,
   AMOUNT            	integer ,
   primary key (ID)
);

insert into CART_COMMODITY
values ('1', '1', '1', '3');

insert into CART_COMMODITY
values ('2', '1', '5', '4');

insert into CART_COMMODITY
values ('3', '1', '8', '1');

insert into CART_COMMODITY
values ('4', '1', '12', '1');

insert into CART_COMMODITY
values ('5', '2', '2', '2');

insert into CART_COMMODITY
values ('6', '2', '4', '1');

insert into CART_COMMODITY
values ('7', '2', '9', '2');

insert into CART_COMMODITY
values ('8', '2', '11', '1');