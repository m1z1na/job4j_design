--many-to-one

create table school(
    id serial primary key,
    name varchar(255)
);

create table pupil(
    id serial primary key,
    name varchar(255),
    school_id int references school(id)
);

--one-to-one
create table library(
    id serial primary key,
    library_card int
);

create table people(
    id serial primary key,
    name varchar(255),
    library_id int references library(id) unique
);


--many-to-many

create table woman(
    id serial primary key,
    name varchar(255)
);

create table man(
    id serial primary key,
    name varchar(255)
);


create table couple(
    id serial primary key,
    wife_id int references woman(id) unique,
    husband_id int references man(id) unique,
    ex_couple boolean
);