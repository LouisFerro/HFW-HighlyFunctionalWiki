insert into test.public.account
(
    id,
    full_name,
    username,
    password,
    account_type
)
values
(
    nextval('account_seq'),
    'Max Mustermann',
    'MaxiMuster',
    'Password',
    'O'
);