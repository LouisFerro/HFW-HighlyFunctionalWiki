create sequence account_seq start with 1 increment by 50;
create sequence item_seq start with 1 increment by 50;
create sequence comment_seq start with 1 increment by 50;

create table account
(
    id           bigint                                                                              not null,
    password     varchar(128)                                                                        not null,
    username     varchar(128)                                                                        not null,
    name         varchar(256),
    account_type varchar(256) check (account_type in ('OWNER', 'ADMINISTRATOR', 'EDITOR', 'READER')) not null,
    primary key (id)
);

create table item
(
    id          bigint                                                                                  not null,
    page_id     bigint,
    parent_id   bigint,
    name        varchar(256)                                                                            not null,
    description varchar(4048),
    text        varchar(16192),
    image       oid,
    item_type   varchar(256) check (item_type in ('PAGE', 'SECTION', 'LIST', 'VIDEO', 'IMAGE', 'TEXT')) not null,
    account_id  bigint                                                                                  not null,
    alteration  timestamp(6)                                                                            not null,
    action_type varchar(256) check (action_type in ('CREATION', 'EDIT', 'DELETION'))                    not null,
    primary key (id)
);

create table comment
(
    id          bigint                                                             not null,
    page_id     bigint                                                             not null,
    text        varchar(16192)                                                     not null,
    account_id  bigint                                                             not null,
    alteration  timestamp(6)                                                       not null,
    action_type varchar(1) check (action_type in ('CREATION', 'EDIT', 'DELETION')) not null,
    primary key (id)
);

alter table if exists comment add constraint commentAccount foreign key (account_id) references account;
alter table if exists comment add constraint commentPage foreign key (account_id) references account;

alter table if exists item add constraint itemAccount foreign key (account_id) references account;
alter table if exists item add constraint itemPage foreign key (page_id) references item;
alter table if exists item add constraint itemParent foreign key (parent_id) references item;