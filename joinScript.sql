create table author(
    id serial primary key,
    name varchar(255),
);


create table book(
    id serial primary key,
    name varchar(255),
    author_id int references author(id)
);

select bk.name as "Название книги", auth.name as "автор"
    from author auth at join book bk on bk.author_id = auth.id;

--авторы которые хоть что-то написали
select distinct auth.name as "ФИО"
    from author auth at join book bk on bk.author_id = auth.id;

select distinct auth.name, bk.name as book_name
    from author auth at join book bk on bk.author_id = auth.id;