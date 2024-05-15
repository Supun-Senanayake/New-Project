
drop database mrfoods;
create database mrfoods;


use mrfoods;
create table Customer(
                         name varchar(100) not null ,
                         nic varchar(12) not null,
                         Address varchar(100) not null,
                         contact varchar(10) primary key

);


create table orders(
                       order_id varchar(10),
                       date date ,
                       contact varchar (10) ,
                       constraint foreign key ( contact) references customer (contact) on delete cascade on update cascade
);


create table supplier(

                         name varchar(100) not null ,
                         nic varchar(12) not null,
                         Address varchar(100) not null,
                         contact varchar(10) primary key


);

create table item(
                     item_id varchar(10) primary key ,
                     item_name varchar(100) not null ,
                     item_des varchar(100) not null


);

create  table  supplier_details(
                                   supplier_id varchar(100),
                                   item_id varchar(100),
                                   constraint foreign key ( supplier_id) references supplier (contact) on delete cascade on update cascade,
                                   constraint foreign key ( item_id) references item (item_id )  on delete cascade on update cascade
);

create table user (
                      user_name varchar(100) primary key ,
                      user_password varchar(100)
);

create table employee(
                         service varchar(100) not null,
                         name varchar(100) not null ,
                         nic varchar(12) not null,
                         Address varchar(100) not null,
                         contact varchar(10) primary key

);
CREATE TABLE foods (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       price DOUBLE NOT NULL,
                        imgURL VARCHAR(255)
);



CREATE table product(
    id int AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR (100) NOT NULL ,
    product_name varchar (100) not null ,
    stock int (100)  not null,
    price DOUBLE NOT NULL,
    status varchar (100) not null ,
    image varchar (100) not null ,
    date date



);
CREATE TABLE Ordertablet(
                            id int AUTO_INCREMENT primary key ,
                            name varchar(100)  not null ,
                            price DOUBLE NOT NULL ,
                            qty varchar(100) not null,
                            total DOUBLE not null




);



