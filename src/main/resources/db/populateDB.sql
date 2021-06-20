DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

insert into meals ( user_id, date_time, description, calories)
values (100000, to_date('20.06.2021','dd.mm.yyyy'), 'zavtrak', 500 ),
       (100000, to_date('20.06.2021','dd.mm.yyyy'), 'obed', 1500 ),
       (100000, to_date('20.06.2021','dd.mm.yyyy'), 'ujin', 200 ),
       (100001, to_date('21.06.2021','dd.mm.yyyy'), 'obed', 500 ),
       (100000, to_date('21.06.2021','dd.mm.yyyy'), 'zavtrak', 800 ),
       (100000, to_date('21.06.2021','dd.mm.yyyy'), 'obed', 1800 ),
       (100000, to_date('21.06.2021','dd.mm.yyyy'), 'ujin', 900 );