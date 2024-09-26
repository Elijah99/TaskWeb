create database if not exists epam_cafe;

use epam_cafe;

create table if not exists Users(
id bigint unsigned not null auto_increment,
login varchar(50),
password varchar(50),
role enum("Admin","Client"),
points double,
money decimal(9,2),
primary key(id)
);

create table if not exists Orders(
id bigint unsigned not null auto_increment,
user_id bigint unsigned,
creation_date datetime,
order_date datetime,
status enum("ACCEPTED", "PROCESSED","DONE"),
payment_type enum("CASH","CARD","PERSONAL_ACCOUNT"),
rating double,
comment varchar(255),
primary key(id),
foreign key (user_id) references Users(id)
);

create table if not exists Dishes(
id bigint unsigned not null auto_increment,
name varchar(50),
description varchar(255),
image_path varchar(255),
price decimal(9,2),
primary key (id)
);

create table if not exists Dishes_Orders(
id bigint unsigned not null auto_increment,
order_id bigint unsigned,
dish_id bigint unsigned,
number bigint unsigned,
primary key (id),
foreign key (order_id) references Orders(id),
foreign key (dish_id) references Dishes(id)
);

create table if not exists Menu(
id bigint unsigned not null auto_increment,
date datetime,
primary key (id)
);

create table if not exists Menu_Dishes(
id bigint unsigned not null auto_increment,
menu_id bigint unsigned,
dish_id bigint unsigned,
primary key (id),
foreign key (menu_id) references Menu(id),
foreign key (dish_id) references Dishes(id)
);

