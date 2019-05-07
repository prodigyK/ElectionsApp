SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE bf_election_commission_phone;
TRUNCATE bf_election_commission;
TRUNCATE bf_election_candidate;
TRUNCATE bf_election;
SET FOREIGN_KEY_CHECKS = 1;
ALTER TABLE bf_election AUTO_INCREMENT=1000;
ALTER TABLE bf_election_candidate AUTO_INCREMENT=1000;
ALTER TABLE bf_election_commission AUTO_INCREMENT=1000;
ALTER TABLE bf_election_commission_phone AUTO_INCREMENT=1000;


# Election
INSERT INTO bf_election(id, name, full_name, election_date) VALUES(1000, 'Президент 2019', 'Вибори Президента України', '2019-03-30');

# Election candidates
INSERT INTO bf_election_candidate (id, name, election_id, left_out, our_cand, our_tech_cand, enabled, top, tech_of_parent)
  VALUES (1000, 'Порошенко Петро Олекс', 1000, FALSE, FALSE, FALSE, TRUE, FALSE, NULL);
INSERT INTO bf_election_candidate (id, name, election_id, left_out, our_cand, our_tech_cand, enabled, top, tech_of_parent)
  VALUES (1001, 'Тимошенко Юлия', 1000, FALSE, FALSE, FALSE, TRUE, TRUE, NULL);
INSERT INTO bf_election_candidate (id, name, election_id, left_out, our_cand, our_tech_cand, enabled, top, tech_of_parent)
  VALUES (1002, 'Зеленский Владимир', 1000, FALSE, FALSE, FALSE, TRUE, TRUE, NULL);
INSERT INTO bf_election_candidate (id, name, election_id, left_out, our_cand, our_tech_cand, enabled, top, tech_of_parent)
  VALUES (1003, 'Балашов Геннадий', 1000, FALSE, FALSE, FALSE, TRUE, FALSE, NULL);
INSERT INTO bf_election_candidate (id, name, election_id, left_out, our_cand, our_tech_cand, enabled, top, tech_of_parent)
  VALUES (1004, 'Гриценко Анатолий', 1000, FALSE, FALSE, FALSE, TRUE, FALSE, NULL);
INSERT INTO bf_election_candidate (id, name, election_id, left_out, our_cand, our_tech_cand, enabled, top, tech_of_parent)
  VALUES (1005, 'Валентин Наливайченко', 1000, FALSE, FALSE, FALSE, TRUE, TRUE, NULL);
INSERT INTO bf_election_candidate (id, name, election_id, left_out, our_cand, our_tech_cand, enabled, top, tech_of_parent)
  VALUES (1006, 'Бойко Юрий', 1000, FALSE, FALSE, FALSE, TRUE, TRUE, NULL);
INSERT INTO bf_election_candidate (id, name, election_id, left_out, our_cand, our_tech_cand, enabled, top, tech_of_parent)
  VALUES (1007, 'Ляшко Олег', 1000, FALSE, FALSE, FALSE, TRUE, FALSE, NULL);
INSERT INTO bf_election_candidate (id, name, election_id, left_out, our_cand, our_tech_cand, enabled, top, tech_of_parent)
  VALUES (1008, 'Тарута Сергей', 1000, FALSE, FALSE, FALSE, TRUE, TRUE, NULL);

# Election commission
INSERT INTO bf_election_commission (id, name, number, enabled, parent_id)
  VALUES (1000, 'Окружна виборча комісія з виборів Призидента України', 133, TRUE, NULL);
INSERT INTO bf_election_commission (id, name, number, enabled, parent_id, address, location,location_description)
  VALUES (1001, 'Избирательный участок 1', 511073, TRUE, NULL, 'просп.Небесної Сотні, 12А, м.Одеса, Одеська обл., 65121', 'школа №82, хол, 1-й поверх', 'м.Одеса – просп.Небесної Сотні: 6, 10, 10А–10Г, 14;');
INSERT INTO bf_election_commission (id, name, number, enabled, parent_id, address, location,location_description)
  VALUES (1002, 'Избирательный участок 2', 511077, TRUE, NULL, 'вул.Академіка Корольова, 5, корп.2, м.Одеса, Одеська обл., 65101', 'Одеський коледж комп’ютерних технологій, хол, 1-й поверх', 'м.Одеса – вул.Академіка Корольова: 1–1А, 5 к.1–5 к.4; вул.Тополина');

# Election commission phone
INSERT INTO bf_election_commission_phone (id, name, phone, commission_id) VALUES (1000, 'Приемная', '123456789', 1000);
INSERT INTO bf_election_commission_phone (id, name, phone, commission_id) VALUES (1001, 'Голова', '654654654', 1000);
INSERT INTO bf_election_commission_phone (id, name, phone, commission_id) VALUES (1002, 'Секретарь', '321321312', 1000);
INSERT INTO bf_election_commission_phone (id, name, phone, commission_id) VALUES (1003, 'Приемная', '8546546546', 1001);
INSERT INTO bf_election_commission_phone (id, name, phone, commission_id) VALUES (1004, 'Голова', '5846546469', 1001);
INSERT INTO bf_election_commission_phone (id, name, phone, commission_id) VALUES (1005, 'Секретарь', '5845641365', 1001);










