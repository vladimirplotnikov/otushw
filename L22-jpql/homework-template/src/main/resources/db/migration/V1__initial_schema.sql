-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
drop table if exists client cascade;
drop table if exists address;
drop table if exists phone;
create sequence hibernate_sequence start with 1 increment by 1;
create table client (
    id bigserial primary key,
    name varchar not null,
    address_id bigint,
    phone_id bigint
);

create table address (
    address_id bigserial primary key,
    street varchar not null
);

create table phone (
    phone_id bigserial primary key,
    client_id bigint,
    number varchar not null
);


ALTER TABLE client ADD CONSTRAINT fk_address_id FOREIGN KEY (address_id) references address(address_id);

ALTER TABLE client ADD CONSTRAINT fk_phone_id FOREIGN KEY (phone_id) references phone(phone_id);