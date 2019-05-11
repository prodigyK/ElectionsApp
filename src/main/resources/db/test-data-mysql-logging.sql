SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE bf_log_changes;
TRUNCATE bf_log_main;
TRUNCATE bf_log_type;
SET FOREIGN_KEY_CHECKS = 1;
ALTER TABLE bf_log_changes AUTO_INCREMENT = 1000000;
ALTER TABLE bf_log_main AUTO_INCREMENT = 1000000;
ALTER TABLE bf_log_type AUTO_INCREMENT = 1000;

# Log types
INSERT INTO bf_log_type(id, name) VALUES(1000, "Користувачи");
INSERT INTO bf_log_type(id, name) VALUES(1001, "Люди");
