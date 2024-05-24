-- Page: 4 Mountain Walk

insert into test.public.item ( id, page_id, parent_id, name, description, text, item_type, account_id, alteration, action_type )
values (
    nextval('item_seq'),
    null,
    null,
    '4 Mountain Walk',
    'What is the 4 Mountain Walk',
    null,
    'PAGE',
    1,
    '2023-03-05 20:30:00',
    'CREATION'
);

insert into test.public.item ( id, page_id, parent_id, name, description, text, item_type, account_id, alteration, action_type )
values (
    nextval('item_seq'),
    1,
    1,
    'Introduction',
    'Introductory text and table of contents',
    null,
    'SECTION',
    1,
    '2023-03-05 20:30:00',
    'CREATION'
);

insert into test.public.item ( id, page_id, parent_id, name, description, text, item_type, account_id, alteration, action_type )
values (
    nextval('item_seq'),
    1,
    51,
    'Generics',
    null,
    'The 4 Mountain walk is a annually event that takes places in Carinthia, Austria between 12 o clock am and 16 o clock pm. It was first being held in the years around 1500 ' ||
    'and since then it has been the tradition of the local people to walk the 55km on the second friday after Easter. The entire path leads over the 4 mountains of the ' ||
    '"Magdalensberg", "Ulrichsberg", "Veitsberg" and "Lorenziberg" which is close to the state capital of Klagenfurt.',
    'TEXT',
    1,
    '2023-03-05 20:30:00',
    'CREATION'
);

insert into test.public.item ( id, page_id, parent_id, name, description, text, item_type, account_id, alteration, action_type )
values (
    nextval('item_seq'),
    1,
    51,
    'Table of contents',
    'A list of all the sections and items',
    null,
    'LIST',
    1,
    '2023-03-06 08:15:00',
    'CREATION'
);

insert into test.public.item ( id, page_id, parent_id, name, description, text, item_type, account_id, alteration, action_type )
values (
    nextval('item_seq'),
    1,
    151,
    'Introduction',
    null,
    '1. Introduction',
    'LIST',
    1,
    '2023-03-05 08:45:00',
    'CREATION'
);

insert into test.public.item ( id, page_id, parent_id, name, description, text, item_type, account_id, alteration, action_type)
values (
    nextval('item_seq'),
    1,
    201,
    'Generics',
    null,
    '1.1. Generics',
    'SECTION',
    1,
    '2023-03-05 08:45:00',
    'CREATION'
);

insert into test.public.item ( id, page_id, parent_id, name, description, text, item_type, account_id, alteration, action_type )
values (
    nextval('item_seq'),
    1,
    201,
    'Table of Contents',
    null,
    '1.2. Table of Contents',
    'SECTION',
    1,
    '2023-03-05 08:45:00',
    'CREATION'
);

-- Page: X