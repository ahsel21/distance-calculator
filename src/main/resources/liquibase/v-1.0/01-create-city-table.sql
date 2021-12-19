create table city
(
    id        bigint auto_increment
        primary key,
    latitude  double       not null,
    longitude double       not null,
    name      varchar(255) not null
);
