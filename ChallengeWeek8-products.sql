CREATE DATABASE challengeWeek8;

use challengeWeek8;

CREATE TABLE products(
productId int auto_increment not null primary key,
name varchar(30) not null,
price double not null,
description varchar(255) not null);