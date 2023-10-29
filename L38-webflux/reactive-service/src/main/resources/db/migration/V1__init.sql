create table products (
    id bigserial primary key,
    name varchar(255)
);

insert into products(id,name) values (1,'Apple');
insert into products(id,name) values (2,'Cucumber');