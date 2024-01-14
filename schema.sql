create sequence account_seq start with 1 increment by 50;

create sequence comment_seq start with 1 increment by 50;

create sequence item_seq start with 1 increment by 50;

create sequence page_seq start with 1 increment by 50;

create sequence section_seq start with 1 increment by 50;

create table account
(
    date         timestamp(6),
    id           bigint                                               not null,
    password     varchar(128)                                         not null,
    username     varchar(128)                                         not null unique,
    full_name    varchar(265),
    account_type CHAR(1) CHECK (account_type in ('U', 'E', 'A', 'O')) not null,
    action_type  CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table comment
(
    date        timestamp(6),
    id          bigint         not null,
    page_id     bigint         not null,
    text        varchar(16192) not null,
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table item
(
    date        timestamp(6),
    id          bigint                                            not null,
    section_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    item_type   CHAR(1) CHECK (item_type in ('L', 'T', 'I', 'V')) not null,
    text        varchar(255),
    image       blob,
    primary key (id)
);

create table page
(
    date        timestamp(6),
    id          bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table section
(
    date        timestamp(6),
    id          bigint       not null,
    page_id     bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

alter table if exists comment
    add constraint FKt4yeadjuykn5tnbaw2uk2grka
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint FKijboj90qb1uk23hd9lbwyr4q7
    foreign key (section_id)
    references section;

alter table if exists section
    add constraint FKm1cs0iua4b7hcepgwv1nih1lq
    foreign key (page_id)
    references page;

create sequence account_seq start with 1 increment by 50;

create sequence comment_seq start with 1 increment by 50;

create sequence item_seq start with 1 increment by 50;

create sequence page_seq start with 1 increment by 50;

create sequence section_seq start with 1 increment by 50;

create table account
(
    date         timestamp(6),
    id           bigint                                               not null,
    password     varchar(128)                                         not null,
    username     varchar(128)                                         not null unique,
    full_name    varchar(265),
    account_type CHAR(1) CHECK (account_type in ('U', 'E', 'A', 'O')) not null,
    action_type  CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table comment
(
    date        timestamp(6),
    id          bigint         not null,
    page_id     bigint         not null,
    text        varchar(16192) not null,
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table item
(
    date        timestamp(6),
    id          bigint                                            not null,
    section_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    item_type   CHAR(1) CHECK (item_type in ('L', 'T', 'I', 'V')) not null,
    text        varchar(255),
    image       blob,
    primary key (id)
);

create table page
(
    date        timestamp(6),
    id          bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table section
(
    date        timestamp(6),
    id          bigint       not null,
    page_id     bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

alter table if exists comment
    add constraint FKt4yeadjuykn5tnbaw2uk2grka
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint FKijboj90qb1uk23hd9lbwyr4q7
    foreign key (section_id)
    references section;

alter table if exists section
    add constraint FKm1cs0iua4b7hcepgwv1nih1lq
    foreign key (page_id)
    references page;

create sequence account_seq start with 1 increment by 50;

create sequence comment_seq start with 1 increment by 50;

create sequence item_seq start with 1 increment by 50;

create sequence page_seq start with 1 increment by 50;

create sequence section_seq start with 1 increment by 50;

create table account
(
    date         timestamp(6),
    id           bigint                                               not null,
    password     varchar(128)                                         not null,
    username     varchar(128)                                         not null unique,
    full_name    varchar(265),
    account_type CHAR(1) CHECK (account_type in ('U', 'E', 'A', 'O')) not null,
    action_type  CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table comment
(
    date        timestamp(6),
    id          bigint         not null,
    page_id     bigint         not null,
    text        varchar(16192) not null,
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table item
(
    date        timestamp(6),
    id          bigint                                            not null,
    section_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    item_type   CHAR(1) CHECK (item_type in ('L', 'T', 'I', 'V')) not null,
    text        varchar(255),
    image       blob,
    primary key (id)
);

create table page
(
    date        timestamp(6),
    id          bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table section
(
    date        timestamp(6),
    id          bigint       not null,
    page_id     bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

alter table if exists comment
    add constraint FKt4yeadjuykn5tnbaw2uk2grka
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint FKijboj90qb1uk23hd9lbwyr4q7
    foreign key (section_id)
    references section;

alter table if exists section
    add constraint FKm1cs0iua4b7hcepgwv1nih1lq
    foreign key (page_id)
    references page;

create sequence account_seq start with 1 increment by 50;

create sequence comment_seq start with 1 increment by 50;

create sequence item_seq start with 1 increment by 50;

create sequence page_seq start with 1 increment by 50;

create sequence section_seq start with 1 increment by 50;

create table account
(
    date         timestamp(6),
    id           bigint                                               not null,
    password     varchar(128)                                         not null,
    username     varchar(128)                                         not null unique,
    full_name    varchar(265),
    account_type CHAR(1) CHECK (account_type in ('U', 'E', 'A', 'O')) not null,
    action_type  CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table comment
(
    date        timestamp(6),
    id          bigint         not null,
    page_id     bigint         not null,
    text        varchar(16192) not null,
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table item
(
    date        timestamp(6),
    id          bigint                                            not null,
    section_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    item_type   CHAR(1) CHECK (item_type in ('L', 'T', 'I', 'V')) not null,
    text        varchar(255),
    image       blob,
    primary key (id)
);

create table page
(
    date        timestamp(6),
    id          bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table section
(
    date        timestamp(6),
    id          bigint       not null,
    page_id     bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

alter table if exists comment
    add constraint FKt4yeadjuykn5tnbaw2uk2grka
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint FKijboj90qb1uk23hd9lbwyr4q7
    foreign key (section_id)
    references section;

alter table if exists section
    add constraint FKm1cs0iua4b7hcepgwv1nih1lq
    foreign key (page_id)
    references page;

create sequence account_seq start with 1 increment by 50;

create sequence comment_seq start with 1 increment by 50;

create sequence item_seq start with 1 increment by 50;

create sequence page_seq start with 1 increment by 50;

create sequence section_seq start with 1 increment by 50;

create table account
(
    date         timestamp(6),
    id           bigint                                               not null,
    password     varchar(128)                                         not null,
    username     varchar(128)                                         not null unique,
    full_name    varchar(265),
    account_type CHAR(1) CHECK (account_type in ('U', 'E', 'A', 'O')) not null,
    action_type  CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table comment
(
    date        timestamp(6),
    id          bigint         not null,
    page_id     bigint         not null,
    text        varchar(16192) not null,
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table item
(
    date        timestamp(6),
    id          bigint                                            not null,
    section_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    item_type   CHAR(1) CHECK (item_type in ('L', 'T', 'I', 'V')) not null,
    text        varchar(255),
    image       blob,
    primary key (id)
);

create table page
(
    date        timestamp(6),
    id          bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table section
(
    date        timestamp(6),
    id          bigint       not null,
    page_id     bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

alter table if exists comment
    add constraint FKt4yeadjuykn5tnbaw2uk2grka
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint FKijboj90qb1uk23hd9lbwyr4q7
    foreign key (section_id)
    references section;

alter table if exists section
    add constraint FKm1cs0iua4b7hcepgwv1nih1lq
    foreign key (page_id)
    references page;

create sequence account_seq start with 1 increment by 50;

create sequence comment_seq start with 1 increment by 50;

create sequence item_seq start with 1 increment by 50;

create sequence page_seq start with 1 increment by 50;

create sequence section_seq start with 1 increment by 50;

create table account
(
    date         timestamp(6),
    id           bigint                                               not null,
    password     varchar(128)                                         not null,
    username     varchar(128)                                         not null unique,
    full_name    varchar(265),
    account_type CHAR(1) CHECK (account_type in ('U', 'E', 'A', 'O')) not null,
    action_type  CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table comment
(
    date        timestamp(6),
    id          bigint         not null,
    page_id     bigint         not null,
    text        varchar(16192) not null,
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table item
(
    date        timestamp(6),
    id          bigint                                            not null,
    section_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    item_type   CHAR(1) CHECK (item_type in ('L', 'T', 'I', 'V')) not null,
    text        varchar(255),
    image       blob,
    primary key (id)
);

create table page
(
    date        timestamp(6),
    id          bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table section
(
    date        timestamp(6),
    id          bigint       not null,
    page_id     bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

alter table if exists comment
    add constraint FKt4yeadjuykn5tnbaw2uk2grka
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint FKijboj90qb1uk23hd9lbwyr4q7
    foreign key (section_id)
    references section;

alter table if exists section
    add constraint FKm1cs0iua4b7hcepgwv1nih1lq
    foreign key (page_id)
    references page;

create sequence account_seq start with 1 increment by 50;

create sequence comment_seq start with 1 increment by 50;

create sequence item_seq start with 1 increment by 50;

create sequence page_seq start with 1 increment by 50;

create sequence section_seq start with 1 increment by 50;

create table account
(
    date         timestamp(6),
    id           bigint                                               not null,
    password     varchar(128)                                         not null,
    username     varchar(128)                                         not null unique,
    full_name    varchar(265),
    account_type CHAR(1) CHECK (account_type in ('U', 'E', 'A', 'O')) not null,
    action_type  CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table comment
(
    date        timestamp(6),
    id          bigint         not null,
    page_id     bigint         not null,
    text        varchar(16192) not null,
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table item
(
    date        timestamp(6),
    id          bigint                                            not null,
    section_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    item_type   CHAR(1) CHECK (item_type in ('L', 'T', 'I', 'V')) not null,
    text        varchar(255),
    image       blob,
    primary key (id)
);

create table page
(
    date        timestamp(6),
    id          bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table section
(
    date        timestamp(6),
    id          bigint       not null,
    page_id     bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

alter table if exists comment
    add constraint FKt4yeadjuykn5tnbaw2uk2grka
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint FKijboj90qb1uk23hd9lbwyr4q7
    foreign key (section_id)
    references section;

alter table if exists section
    add constraint FKm1cs0iua4b7hcepgwv1nih1lq
    foreign key (page_id)
    references page;

create sequence account_seq start with 1 increment by 50;

create sequence comment_seq start with 1 increment by 50;

create sequence item_seq start with 1 increment by 50;

create sequence page_seq start with 1 increment by 50;

create sequence section_seq start with 1 increment by 50;

create table account
(
    date         timestamp(6),
    id           bigint                                               not null,
    password     varchar(128)                                         not null,
    username     varchar(128)                                         not null unique,
    full_name    varchar(265),
    account_type CHAR(1) CHECK (account_type in ('U', 'E', 'A', 'O')) not null,
    action_type  CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table comment
(
    date        timestamp(6),
    id          bigint         not null,
    page_id     bigint         not null,
    text        varchar(16192) not null,
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table item
(
    date        timestamp(6),
    id          bigint                                            not null,
    section_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    item_type   CHAR(1) CHECK (item_type in ('L', 'T', 'I', 'V')) not null,
    text        varchar(255),
    image       blob,
    primary key (id)
);

create table page
(
    date        timestamp(6),
    id          bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table section
(
    date        timestamp(6),
    id          bigint       not null,
    page_id     bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

alter table if exists comment
    add constraint FKt4yeadjuykn5tnbaw2uk2grka
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint FKijboj90qb1uk23hd9lbwyr4q7
    foreign key (section_id)
    references section;

alter table if exists section
    add constraint FKm1cs0iua4b7hcepgwv1nih1lq
    foreign key (page_id)
    references page;

create sequence account_seq start with 1 increment by 50;

create sequence comment_seq start with 1 increment by 50;

create sequence item_seq start with 1 increment by 50;

create sequence page_seq start with 1 increment by 50;

create sequence section_seq start with 1 increment by 50;

create table account
(
    date         timestamp(6),
    id           bigint                                               not null,
    password     varchar(128)                                         not null,
    username     varchar(128)                                         not null unique,
    full_name    varchar(265),
    account_type CHAR(1) CHECK (account_type in ('U', 'E', 'A', 'O')) not null,
    action_type  CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table comment
(
    date        timestamp(6),
    id          bigint         not null,
    page_id     bigint         not null,
    text        varchar(16192) not null,
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table item
(
    date        timestamp(6),
    id          bigint                                            not null,
    section_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    item_type   CHAR(1) CHECK (item_type in ('L', 'T', 'I', 'V')) not null,
    text        varchar(255),
    image       blob,
    primary key (id)
);

create table page
(
    date        timestamp(6),
    id          bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table section
(
    date        timestamp(6),
    id          bigint       not null,
    page_id     bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

alter table if exists comment
    add constraint FKt4yeadjuykn5tnbaw2uk2grka
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint FKijboj90qb1uk23hd9lbwyr4q7
    foreign key (section_id)
    references section;

alter table if exists section
    add constraint FKm1cs0iua4b7hcepgwv1nih1lq
    foreign key (page_id)
    references page;

create sequence account_seq start with 1 increment by 50;

create sequence comment_seq start with 1 increment by 50;

create sequence item_seq start with 1 increment by 50;

create sequence page_seq start with 1 increment by 50;

create sequence section_seq start with 1 increment by 50;

create table account
(
    date         timestamp(6),
    id           bigint                                               not null,
    password     varchar(128)                                         not null,
    username     varchar(128)                                         not null unique,
    full_name    varchar(265),
    account_type CHAR(1) CHECK (account_type in ('U', 'E', 'A', 'O')) not null,
    action_type  CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table comment
(
    date        timestamp(6),
    id          bigint         not null,
    page_id     bigint         not null,
    text        varchar(16192) not null,
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table item
(
    date        timestamp(6),
    id          bigint                                            not null,
    section_id  bigint                                            not null,
    name        varchar(265)                                      not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    item_type   CHAR(1) CHECK (item_type in ('L', 'T', 'I', 'V')) not null,
    text        varchar(255),
    image       blob,
    primary key (id)
);

create table page
(
    date        timestamp(6),
    id          bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

create table section
(
    date        timestamp(6),
    id          bigint       not null,
    page_id     bigint       not null,
    name        varchar(265) not null unique,
    description varchar(4048),
    action_type CHAR(1) CHECK (action_type in ('C', 'E', 'D')),
    primary key (id)
);

alter table if exists comment
    add constraint FKt4yeadjuykn5tnbaw2uk2grka
    foreign key (page_id)
    references page;

alter table if exists item
    add constraint FKijboj90qb1uk23hd9lbwyr4q7
    foreign key (section_id)
    references section;

alter table if exists section
    add constraint FKm1cs0iua4b7hcepgwv1nih1lq
    foreign key (page_id)
    references page;
