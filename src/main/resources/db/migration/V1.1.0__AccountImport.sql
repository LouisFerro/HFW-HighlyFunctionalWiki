insert into test.public.account ( id, name, username, password, account_type )
values (
    nextval('account_seq'),
    'Louis Ferro',
    'Louisthemagic',
    'Password',
    'OWNER'
);

insert into test.public.account ( id, name, username, password, account_type )
values (
    nextval('account_seq'),
    'Niklas Hasenbacher',
    'Niklas2019',
    'Password',
    'ADMINISTRATOR'
);

insert into test.public.account ( id, name, username, password, account_type )
values (
    nextval('account_seq'),
    'Maximilian Mustermann',
    'MaxMuster',
    'Password',
    'EDITOR'
);

insert into test.public.account ( id, name, username, password, account_type )
values (
    nextval('account_seq'),
    'Alan Turing',
    'tuneIn',
    'IGotEnigma',
    'READER'
);