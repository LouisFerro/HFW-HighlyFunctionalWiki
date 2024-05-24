create sequence token_seq start with 1 increment by 50;

create table token
(
    id    bigint       not null,
    value varchar(128) not null unique,
    type  varchar(128) not null,
    primary key (id)
);