DROP TABLE IF EXISTS User;

create table User (
userId int not null primary key auto_increment,
firstName varchar(50),
lastName varchar(50),
age int,
phoneNumber varchar(12),
email varchar(50)
);