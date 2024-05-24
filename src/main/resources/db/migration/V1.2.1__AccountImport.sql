insert into test.public.account ( id, name, username, password, account_type )
values (
    nextval('account_seq'),
    'Louis Ferro',
    'LF',
    'LouisFerro123456789!',
    'OWNER'
);

insert into test.public.account ( id, name, username, password, account_type )
values (
    nextval('account_seq'),
    'Unger Klaus',
    'UK',
    'UngerKlaus123456789!',
    'ADMINISTRATOR'
);

insert into test.public.account ( id, name, username, password, account_type )
values (
    nextval('account_seq'),
    'Andreas Schenk',
    'AS',
    'AndreasSchenk123456789!',
    'EDITOR'
);

insert into test.public.account ( id, name, username, password, account_type )
values (
    nextval('account_seq'),
    'Niklas Hasenbacher',
    'NH',
    'NiklasHasenbacher123456789!',
    'READER'
);