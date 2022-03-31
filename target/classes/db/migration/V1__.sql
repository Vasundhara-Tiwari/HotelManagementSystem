create table Employee(
    id int8 not null,
    name varchar(20),
    primary key (id)
);


create table Guest(
    id int8 not null,
    name varchar(20),
    email varchar(50),
    password varchar(10),
    primary key (id)
);

create table Rooms (
    number int8 not null,
    type varchar(20),
    price int8,
    primary key (number)
);

--needed for hibernate running on postgresql
create sequence hibernate_sequence;
