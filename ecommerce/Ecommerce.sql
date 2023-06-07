CREATE DATABASE ecommerce;

use ecommerce;

CREATE TABLE tb_products(
productId int auto_increment not null primary key,
name varchar(30) not null,
price double not null,
description varchar(255) not null);