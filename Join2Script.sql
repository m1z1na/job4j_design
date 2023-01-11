

create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
	dep_id int references departments(id)

);
insert into departments(name) values ('Отдел HR');
insert into departments(name) values ('Отдел продаж');
insert into departments(name) values ('Отдел разработки');
insert into employee(name, dep_id) values ('Иванова И.Я.', 1);
insert into employee(name, dep_id) values ('Петрова Д.А.', 1);
insert into employee(name, dep_id) values ('Коновалов В.П.', 3);

select * from departments d left join employee e on d.id = e.dep_id;
select * from departments d right join employee e on d.id = e.dep_id;
select * from departments d full join  employee e on d.id = e.dep_id;
select * from departments d cross join  employee e;

select * from departments d left join employee e on d.id = e.dep_id where e.id is null;



select d.name, e.name from departments d left join employee e on d.id = e.dep_id WHERE e.id is not null;

select d.name, e.name from departments d right join employee e on d.id = e.dep_id;