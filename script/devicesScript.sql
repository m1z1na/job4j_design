create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values ('Галя');
insert into people(name) values ('Валя');
insert into people(name) values ('Ваня');

insert into devices(name, price) values ('Телефон', 5000 );
insert into devices(name, price) values ('Наушники', 500 );
insert into devices(name, price) values ('Холодильникн', 70000);
insert into devices(name, price) values ('Утюг', 2500.00);



insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (2, 3);

Select s.name, avg(pp.price)
from devices_people as ss
join people s
on ss.people_id = s.id
join devices pp
on pp.id = ss.device_id
group by s.name;

Select s.name, avg(pp.price) as sum
from devices_people as ss
join people s
on ss.people_id = s.id
join devices pp
on pp.id = ss.device_id
group by s.name
having  avg(pp.price) > 5000;
