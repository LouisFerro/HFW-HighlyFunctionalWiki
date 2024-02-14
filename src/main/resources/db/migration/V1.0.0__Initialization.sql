create sequence account_seq start with 1 increment by 50;
create sequence comment_seq start with 1 increment by 50;
create sequence item_seq start with 1 increment by 50;
create sequence page_seq start with 1 increment by 50;
create sequence section_seq start with 1 increment by 50;

create table account
(
    id           bigint                                                  not null,
    name         varchar(265),
    username     varchar(128)                                            not null unique,
    password     varchar(128)                                            not null,
    account_type varchar(1) check (account_type in ('U', 'E', 'A', 'O')) not null,
    primary key (id)
);

create table comment
(
    id          bigint                                            not null,
    page_id     bigint                                            not null,
    account_id  bigint                                            not null,
    alteration  timestamp(6)                                      not null unique,
    action_type varchar(1) check (action_type in ('C', 'E', 'D')) not null,
    text        varchar(16192)                                    not null,
    primary key (id)
);

create table item
(
    id          bigint                                               not null,
    section_id  bigint                                               not null,
    account_id  bigint                                               not null,
    name        varchar(265)                                         not null unique,
    description varchar(4048),
    text        varchar(255),
    alteration  timestamp(6)                                         not null unique,
    action_type varchar(1) check (action_type in ('C', 'E', 'D'))    not null,
    item_type   varchar(1) check (item_type in ('L', 'T', 'I', 'V')) not null,
    image       oid,
    item_id     bigint,
    primary key (id)
);

create table page
(
    id          bigint                                            not null,
    account_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    alteration  timestamp(6)                                      not null unique,
    action_type varchar(1) check (action_type in ('C', 'E', 'D')) not null,
    primary key (id)
);

create table section
(
    id          bigint                                            not null,
    page_id     bigint                                            not null,
    account_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    alteration  timestamp(6)                                      not null unique,
    action_type varchar(1) check (action_type in ('C', 'E', 'D')) not null,
    section_id  bigint,
    primary key (id)
);

alter table if exists comment
    add constraint pageComment
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint sectionItem
    foreign key (section_id)
    references section;
alter table if exists item
    add constraint parentItem
    foreign key (item_id)
    references item;

alter table if exists section
    add constraint pageSection
    foreign key (page_id)
    references page;
alter table if exists section
    add constraint parentSection
    foreign key (section_id)
    references section;