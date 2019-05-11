DROP TABLE IF EXISTS bf_log_changes;
DROP TABLE IF EXISTS bf_log_main;
DROP TABLE IF EXISTS bf_log_type;


CREATE TABLE bf_log_type
(
  id   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100),
  PRIMARY KEY (id),
  CONSTRAINT log_type_name UNIQUE (name)
)
  AUTO_INCREMENT = 1000;


CREATE TABLE bf_log_main
(
  id            INT NOT NULL AUTO_INCREMENT,
  user_id       INT NOT NULL,
  subscriber_id INT NOT NULL,
  log_type_id   INT NOT NULL,
  changed       DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES bf_user (id),
  FOREIGN KEY (subscriber_id) REFERENCES bf_subscriber (id),
  FOREIGN KEY (log_type_id) REFERENCES bf_log_type (id)
)
  AUTO_INCREMENT = 1000000;


CREATE TABLE bf_log_changes
(
  id            INT NOT NULL AUTO_INCREMENT,
  field_name    VARCHAR(100),
  previousValue VARCHAR(255),
  newValue      VARCHAR(255),
  log_main_id   INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (log_main_id) REFERENCES bf_log_main (id)
)
  AUTO_INCREMENT = 1000000;
