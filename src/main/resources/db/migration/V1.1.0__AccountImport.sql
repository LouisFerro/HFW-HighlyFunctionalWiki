insert into test.public.account (
    id,
    full_name,
    username,
    password,
    account_type
)
values (
    nextval('account_seq'),
    'Louis Ferro',
    'Louisthemagic',
    'Password',
    'O'
);

insert into test.public.account (
    id,
    full_name,
    username,
    password,
    account_type
)
values (
    nextval('account_seq'),
    'Niklas Hasenbacher',
    'Niklas2019',
    'Password',
    'O'
);