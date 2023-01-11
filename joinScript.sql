create table author(
    id serial primary key,
    name varchar(255)
);


create table book(
    id serial primary key,
    name varchar(255),
    author_id int references author(id)
);


insert into author(name) values ('Пушкин А.С.');
insert into author(name) values ('Толстой Л.Н.');
insert into author(name) values ('Семенов В.Г.');

insert into people(name, author_id) values ('Капитанская дочка', 1);
insert into people(name, author_id) values ('Война и Мир', 2);

select bk.name as "Название книги", auth.name as "автор"
    from author auth join book bk on bk.author_id = auth.id;

--авторы которые хоть что-то написали
select distinct auth.name as "ФИО"
    from author auth join book bk on bk.author_id = auth.id;

select distinct auth.name, bk.name as book_name
    from author auth join book bk on bk.author_id = auth.id;