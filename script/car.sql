
---Создать таблицы:
--1) "Кузова" car_bodies с атрибутами id и name;
create table car_bodies (
    id serial primary key,
    name varchar(255)
);
--2) "Двигатели" car_engines с атрибутами id и name;
create table car_transmissions (
    id serial primary key,
    name varchar(255)
);
--3) "Коробки передач" car_transmissions с атрибутами id и name;
create table car_engines (
    id serial primary key,
    name varchar(255)
);

--4) "Автомобили" cars с атрибутами id, name, body_id, engine_id и transmission_id. Последние 3 атрибута должны быть внешними ключами к соответствующим таблицам.
create table cars (
    id serial primary key,
    name varchar(255),
    owner_id int references owners(id),
    body_id int references car_bodies(id),
    trans_id int references car_transmissions(id),
    engine_id int references car_engines(id)
);

--Заполнить таблицы данными.

insert into car_bodies (name) values ( 'sedan');
insert into car_bodies (name) values ( 'hatchback');
insert into car_bodies (name) values ( 'universal');
insert into car_bodies (name) values ( 'wagon');

insert into car_transmissions (name) values ('manual');
insert into car_transmissions (name) values ('auto');
insert into car_transmissions (name) values ('robot');

insert into car_engines (name) values ( 'gasoline');
insert into car_engines (name) values ( 'diesel');
insert into car_engines (name) values ( 'hybrid');

--insert into cars (name, body_id, trans_id, engine_id) values ('Kia Rio', 1, 2, 1);
--insert into cars (name, body_id, trans_id, engine_id) values ('Kia Rio', 2, 2, 2);
--insert into cars (name, body_id, trans_id, engine_id) values ('Kia Rio', 3, 1, 1);


insert into cars (name, body_id, trans_id, engine_id, owner_id) values ('Kia Rio', 1, 2, 1, 1);
insert into cars (name, body_id, trans_id, engine_id, owner_id) values ('Kia Rio', 2, 2, 2, 2);
insert into cars (name, body_id, trans_id, engine_id, owner_id) values ('Kia Rio', 3, 1, 1, 2);

--Вывести список всех машин и все привязанные к ним детали. Нужно учесть, что каких-то деталей машина может и не содержать. В таком случае значение может быть null при выводе (например, название двигателя null);


--id, car_name, body_name, engine_name, transmission_name
Select car.id, car.name as car_name, body.name as body_name, engine.name as engine_name, trans.name as transmission_name
from cars as car
left join car_bodies as body on car.body_id = body.id
left join car_engines as engine on car.engine_id = engine.id
left join car_transmissions as trans on car.trans_id = trans.id ;

---Вывести кузова, которые не используются НИ в одной машине. "Не используются" значит, что среди записей таблицы cars отсутствует внешние ключи, ссылающие на таблицу car_bodies. Например, Вы добавили в car_bodies "седан", "хэтчбек" и "пикап", а при добавлении в таблицу cars указали только внешние ключи на записи "седан" и "хэтчбек". Запрос, касающийся этого пункта, должен вывести "пикап", т.к. среди машин нет тех, что обладают таким кузовом;
Select body.name
from car_bodies as body
left join cars as car on car.body_id = body.id
where car.id is null;


--Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2;
Select engine.name
from car_engines as engine
left join cars as car on car.engine_id = engine.id
where car.id is null;

--Вывести коробки передач, которые не используются НИ в одной машине, аналогично п.2.
Select trans.name
from car_transmissions as trans
left join cars as car on car.trans_id = trans.id
where car.id is null;


---1. Представления [#504792]
create table owners (
    id serial primary key,
    name varchar(255)
);


insert into owners(name) values ('Галя');
insert into owners(name) values ('Валя');
insert into owners(name) values ('Ваня');
---Представление для выбора владельцев одной и более машины
create view show_owners_with_1_or_more_cars
    as select owner.name as owner, count(car.name)
		 from owners as owner
         join cars car on owner.id = car.owner_id
         group by (owner.name, car.name) having count(car.name) >= 1;


select * from show_owners_with_1_or_more_cars;