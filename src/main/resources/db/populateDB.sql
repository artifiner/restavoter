DELETE FROM dishes;
DELETE FROM votes;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO restaurants (name)
VALUES ('KFC'),
       ('Il Patio'),
       ('Little Osaka');

INSERT INTO dishes (name, price, actual_date, restaurant_id)
VALUES ('Chicken Burger', 5000, CURRENT_DATE, 100002),
       ('Wings', 7000, CURRENT_DATE, 100002),
       ('Carbonara', 25000, CURRENT_DATE, 100003),
       ('Salmon', 45000, CURRENT_DATE, 100003),
       ('Sushi', 20000, CURRENT_DATE, 100004),
       ('Tom Yam', 30000, CURRENT_DATE, 100004);

