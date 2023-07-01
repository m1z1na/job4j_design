
-- 1. Создать таблицы и заполнить их начальными данными
create table departments(
    id serial primary key,
    name varchar(255)
);

create table employee(
    id serial primary key,
    name varchar(255),
	dep_id int references departments(id)

);



insert into departments(name) values ('Отдел HR');
insert into departments(name) values ('Отдел продаж');
insert into departments(name) values ('Отдел разработки');
insert into employee(name, dep_id) values ('Иванова И.Я.', 1);
insert into employee(name, dep_id) values ('Петрова Д.А.', 1);
insert into employee(name, dep_id) values ('Петров Д.А.', 3);
insert into employee(name, dep_id) values ('Коновалов В.П.', 3);



--2. Выполнить запросы с left, right, full, cross соединениями
select *
from departments d
left join employee e on d.id = e.dep_id;

select *
from departments d
right join employee e on d.id = e.dep_id;

select *
from departments d
full join  employee e on d.id = e.dep_id;

select *
from departments d
cross join  employee e;
--3. Используя left join найти департаменты, у которых нет работников
select *
from departments d
left join employee e on d.id = e.dep_id
where e.id is null;


--4. Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
select d.name, e.name
from departments d
left join employee e on d.id = e.dep_id
WHERE e.dep_id is not null;

select d.name, e.name
from departments d
right join employee e on d.id = e.dep_id
WHERE e.dep_id is not null;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens (
  id serial primary key,
  name varchar(255),
  gender varchar(1)
);

insert into teens (name, gender) values ('Коновалов В.П', 'М');
insert into teens (name, gender) values ('Петров В.Г', 'М');
insert into teens (name, gender) values ('Иванова И.Я', 'Ж');
insert into teens (name, gender) values ('Петрова Д.А', 'Ж');

select t1.name, t2.name from teens as t1
cross join teens as t2
where t1.gender != t2.gender and t1.gender != 'М';