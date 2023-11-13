create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);
---Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар (нужно прибавить налог к цене товара).
--Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';
create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

---Триггер должен срабатывать до вставки данных и насчитывать налог на товар
--(нужно прибавить налог к цене товара). Здесь используем row уровень.
create or replace function tax_row()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_row_trigger
    after insert
    on products
    for each row
    execute procedure tax_row();

---Нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products,
--будет заносить имя, цену и текущую дату в таблицу history_of_price

create or replace function history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date) values (NEW.name,NEW.price,now());
        return new;
    END;
$$
LANGUAGE 'plpgsql';
create trigger history_trigger
    after insert on products
    FOR EACH ROW
    execute procedure history();


--За основу возьмите таблицу, с которой мы работали в описании. В описании мы рассмотрели вариант вставки данных, изменения данных. Добавьте процедуру и функцию, которая будет удалять записи.
 --Условия выбирайте сами – удаление по id, удалить если количество товара равно 0 и т.п.
create or replace function f_delete_data(u_count integer, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
---Если кол-во меньше 10, то удаляем
        if u_count < 10 THEN
            delete from products where id = u_id;
        end if;
        return result;
    end;
$$;
