SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE bf_subscriber_subscriber_type;
TRUNCATE bf_subscriber_type;
SET FOREIGN_KEY_CHECKS = 1;
ALTER TABLE bf_subscriber_subscriber_type AUTO_INCREMENT = 100000;
ALTER TABLE bf_subscriber_type AUTO_INCREMENT = 1000;


# Subscriber types
INSERT INTO bf_subscriber_type (id, name) VALUES (1000, 'Сторонник');
INSERT INTO bf_subscriber_type (id, name) VALUES (1001, 'Подписчик');

