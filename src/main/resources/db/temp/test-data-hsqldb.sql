TRUNCATE TABLE bf_address RESTART IDENTITY AND COMMIT;
TRUNCATE TABLE BF_POSTMAN RESTART IDENTITY AND COMMIT;
TRUNCATE TABLE BF_AREA RESTART IDENTITY AND COMMIT;
TRUNCATE TABLE bf_phone RESTART IDENTITY AND COMMIT;
TRUNCATE TABLE bf_house RESTART IDENTITY AND COMMIT;
TRUNCATE TABLE bf_street RESTART IDENTITY AND COMMIT;
TRUNCATE TABLE bf_district RESTART IDENTITY AND COMMIT;
TRUNCATE TABLE bf_city RESTART IDENTITY AND COMMIT;
TRUNCATE TABLE bf_region RESTART IDENTITY AND COMMIT;
TRUNCATE TABLE bf_subscriber RESTART IDENTITY AND COMMIT;

-- #Subscriber
INSERT INTO bf_subscriber(id, lastname, firstname, middlename, birthday, subscribe_day, status)
VALUES (1000001, 'Смоляков', 'Виктор', 'Задунайский', '1981-01-12', '2018-05-12', 'unknown status smolyakov');
INSERT INTO bf_subscriber(id, lastname, firstname, middlename, birthday, subscribe_day, status)
VALUES (1000002, 'Герц', 'Андрей', 'Адисабебавич', '1986-06-28', '2018-03-03', 'unknown status gerts');
INSERT INTO bf_subscriber(id, lastname, firstname, middlename, birthday, subscribe_day, status)
VALUES (1000003, 'Ткач', 'Юрий', 'Аргининович', '1981-01-12', '2018-05-12', 'unknown status tkach');

-- #Region
INSERT INTO bf_region(id, name) VALUES (1001, 'Одесская');
INSERT INTO bf_region(id, name) VALUES (1002, 'Николаевская');

-- #City
INSERT INTO bf_city(id, name, region_id) VALUES (1001, 'Одесса', 1001);
INSERT INTO bf_city(id, name, region_id) VALUES (1002, 'Николаев', 1002);

-- #District
INSERT INTO bf_district(id, name, city_id) VALUES (1001, 'empty', '1001');
INSERT INTO bf_district(id, name, city_id) VALUES (1002, 'Приморский', 1001);
INSERT INTO bf_district(id, name, city_id) VALUES (1003, 'Киевский', 1001);
INSERT INTO bf_district(id, name, city_id) VALUES (1004, 'Малиновский', 1001);
INSERT INTO bf_district(id, name, city_id) VALUES (1005, 'Суворовский', 1001);
INSERT INTO bf_district(id, name, city_id) VALUES (1006, 'Центральный', 1002);

-- #Street
INSERT INTO bf_street(id, name, city_id) VALUES (1001, 'Фонтанская дорога', 1001);
INSERT INTO bf_street(id, name, city_id) VALUES (1002, 'Говорова', 1001);

-- #House
INSERT INTO bf_house(id, street_id, house, corps, corps_letter) VALUES (1001, 1001, '50', null, null);
INSERT INTO bf_house(id, street_id, house, corps, corps_letter) VALUES (1002, 1001, '60', '2', null);
INSERT INTO bf_house(id, street_id, house, corps, corps_letter) VALUES (1003, 1001, '100', null, 'a');

-- #Area
INSERT INTO bf_area(id, name, city_id) VALUES (1001, '511127', 1001);
INSERT INTO bf_area(id, name, city_id) VALUES (1002, '511114', 1001);
INSERT INTO bf_area(id, name, city_id) VALUES (1003, '511100', 1002);

-- #Postman
INSERT INTO bf_postman(id, name, area_id) VALUES (1001, 'postman 1', 1001);
INSERT INTO bf_postman(id, name, area_id) VALUES (1002, 'postman 2', 1001);
INSERT INTO bf_postman(id, name, area_id) VALUES (1003, 'postman 3', 1001);

-- #Address
INSERT INTO bf_address(id, area_id, sector, mail_index, region_id, city_id, district_id, house_id, flat, subscriber_id)
VALUES (1001, 1001, null, '65009', 1001, 1001, 1001, 1001, '123', 1000001);
INSERT INTO bf_address(id, area_id, sector, mail_index, region_id, city_id, district_id, house_id, flat, subscriber_id)
VALUES (1002, 1001, null, '65000', 1001, 1001, 1001, 1002, '165', 1000002);
INSERT INTO bf_address(id, area_id, sector, mail_index, region_id, city_id, district_id, house_id, flat, subscriber_id)
VALUES (1003, 1002, null, '65000', 1001, 1001, 1002, 1003, '189', 1000003);
