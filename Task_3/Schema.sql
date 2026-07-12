create table product (
    id serial unique not null,
    name char(50) not null,
    price integer check(price > -1)
);
