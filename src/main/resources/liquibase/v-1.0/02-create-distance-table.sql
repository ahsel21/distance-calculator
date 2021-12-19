create table distance
(
    id        bigint auto_increment
        primary key,
    distance  double null,
    from_city bigint null,
    to_city   bigint null,
    constraint FK7y4v8qplaejmcexm426osoq7e
        foreign key (to_city) references city (id),
    constraint FKcvbfehc62ynjdi5pex4v301jt
        foreign key (from_city) references city (id)
);