DROP INDEX street_name_unique_idx
ON bf_streets;
# DROP TABLE IF EXISTS bf_food_sets;
# DROP TABLE IF EXISTS bf_questionaire_answers;
# DROP TABLE IF EXISTS bf_questionaire;
DROP TABLE IF EXISTS bf_addresses;
DROP TABLE IF EXISTS bf_phones;
DROP TABLE IF EXISTS bf_streets;
DROP TABLE IF EXISTS bf_districts;
DROP TABLE IF EXISTS bf_cities;
DROP TABLE IF EXISTS bf_regions;
DROP TABLE IF EXISTS bf_subscribers;
# DROP TABLE IF EXISTS bf_history_details;
# DROP TABLE IF EXISTS bf_history;
# DROP TABLE IF EXISTS bf_history_types;

CREATE TABLE bf_regions
(
  id   INT          NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_cities
(
  id        INT          NOT NULL AUTO_INCREMENT,
  name      VARCHAR(255) NOT NULL,
  region_id INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (region_id) REFERENCES bf_regions (id)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_districts
(
  id      INT          NOT NULL AUTO_INCREMENT,
  name    VARCHAR(255) NOT NULL,
  city_id INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (city_id) REFERENCES bf_cities (id)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_streets
(
  id   INT          NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
)
  AUTO_INCREMENT = 1000;

CREATE UNIQUE INDEX street_name_unique_idx
  ON bf_streets (name);

CREATE TABLE bf_subscribers
(
  id            INT         NOT NULL AUTO_INCREMENT,
  lastname      VARCHAR(50) NOT NULL,
  firstname     VARCHAR(50) NOT NULL,
  middlename    VARCHAR(50),
  birthday      DATE,
  subscribe_day DATE,
  status        VARCHAR(255),
  PRIMARY KEY (id)
)
  AUTO_INCREMENT = 1000000;

CREATE TABLE bf_addresses
(
  id            INT         NOT NULL AUTO_INCREMENT,
  area_number   VARCHAR(20),
  sector        VARCHAR(20),
  mail_index    VARCHAR(20),
  house         VARCHAR(10) NOT NULL,
  corps         VARCHAR(10),
  corps_word    VARCHAR(5),
  flat          VARCHAR(10),
  subscriber_id INT         NOT NULL,
  region_id     INT         NOT NULL,
  city_id       INT         NOT NULL,
  district_id   INT         NOT NULL,
  street_id     INT         NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (region_id) REFERENCES bf_regions (id),
  FOREIGN KEY (city_id) REFERENCES bf_cities (id),
  FOREIGN KEY (district_id) REFERENCES bf_districts (id),
  FOREIGN KEY (street_id) REFERENCES bf_streets (id),
  FOREIGN KEY (subscriber_id) REFERENCES bf_subscribers (id)
    ON DELETE CASCADE
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_phones
(
  id            INT NOT NULL AUTO_INCREMENT,
  cellPhone    VARCHAR(100),
  home_phone    VARCHAR(100),
  subscriber_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (subscriber_id) REFERENCES bf_subscribers (id)
    ON DELETE CASCADE
)
  AUTO_INCREMENT = 1000;

#------------------------------------
#-------- Food sets -----------------
#------------------------------------
CREATE TABLE bf_food_sets
(
  id            INT         NOT NULL AUTO_INCREMENT,
  first_touch   DATE        NOT NULL,
  second_touch  DATE        NOT NULL,
  third_touch   DATE        NOT NULL,
  note          VARCHAR(100),
  stage         VARCHAR(50) NOT NULL,
  subscriber_id INT         NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (subscriber_id) REFERENCES bf_subscribers (id)
    ON DELETE CASCADE
)
  AUTO_INCREMENT = 100000;

#--------------------------------------------
#------- Questinaire ------------------------
#--------------------------------------------
CREATE TABLE bf_questionaire
(
  id       INT          NOT NULL AUTO_INCREMENT,
  question VARCHAR(200) NOT NULL,
  name     VARCHAR(200) NOT NULL,
  comment  VARCHAR(250),
  PRIMARY KEY (id)
)
  AUTO_INCREMENT = 10000;

CREATE TABLE bf_questionaire_answers
(
  id            INT NOT NULL AUTO_INCREMENT,
  question_id   INT NOT NULL,
  subscriber_id INT NOT NULL,
  answer        VARCHAR(200),
  PRIMARY KEY (id),
  FOREIGN KEY (question_id) REFERENCES bf_questionaire (id)
    ON DELETE CASCADE,
  FOREIGN KEY (subscriber_id) REFERENCES bf_subscribers (id)
    ON DELETE CASCADE
)
  AUTO_INCREMENT = 1000000;

#---------------------------------------------
#----- History of changes --------------------
#---------------------------------------------
CREATE TABLE bf_history_types
(
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE bf_history
(
  id              BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  time_changes    TIMESTAMP   NOT NULL,
  history_type_id INT         NOT NULL,
  username        VARCHAR(50) NOT NULL,
  FOREIGN KEY (history_type_id) REFERENCES bf_history_types (id),
  FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE bf_history_details
(
  id             BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  field_name     VARCHAR(50),
  previous_value VARCHAR(255),
  new_value      VARCHAR(255),
  deleted        BOOLEAN,
  history_id     BIGINT NOT NULL,
  FOREIGN KEY (history_id) REFERENCES bf_history (id)
);












