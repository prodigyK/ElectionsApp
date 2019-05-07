DROP TABLE IF EXISTS bf_subscriber_subscriber_type;
DROP TABLE IF EXISTS bf_subscriber_type;
DROP TABLE IF EXISTS bf_address;
DROP TABLE IF EXISTS bf_postman;
DROP TABLE IF EXISTS bf_area;
DROP TABLE IF EXISTS bf_phone;
DROP TABLE IF EXISTS bf_house;
DROP TABLE IF EXISTS bf_street;
DROP TABLE IF EXISTS bf_district;
DROP TABLE IF EXISTS bf_city;
DROP TABLE IF EXISTS bf_region;
DROP TABLE IF EXISTS bf_subscriber;


CREATE TABLE bf_region
(
  id   INT          NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT name_region UNIQUE (name)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_city
(
  id        INT          NOT NULL AUTO_INCREMENT,
  name      VARCHAR(255) NOT NULL,
  region_id INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (region_id) REFERENCES bf_region (id)
    ON DELETE CASCADE,
  CONSTRAINT name_region_city_idx UNIQUE (name, region_id),
  UNIQUE (name)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_district
(
  id      INT          NOT NULL AUTO_INCREMENT,
  name    VARCHAR(255) NOT NULL,
  city_id INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (city_id) REFERENCES bf_city (id)
    ON DELETE CASCADE,
  CONSTRAINT name_city_district_idx UNIQUE (name, city_id),
  UNIQUE (name)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_street
(
  id      INT          NOT NULL AUTO_INCREMENT,
  name    VARCHAR(255) NOT NULL,
  city_id INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (city_id) REFERENCES bf_city (id)
    ON DELETE CASCADE,
  CONSTRAINT name_city_street_idx UNIQUE (name, city_id),
  UNIQUE (name)
)
  AUTO_INCREMENT = 1000;

CREATE INDEX street_name_street_idx
  ON bf_street (name);

CREATE TABLE bf_subscriber
(
  id            INT NOT NULL AUTO_INCREMENT,
  lastname      VARCHAR(100),
  firstname     VARCHAR(100),
  middlename    VARCHAR(100),
  birthday      DATE,
  subscribe_day DATE,
  status        VARCHAR(255),
  iin           VARCHAR(20),
  passport      VARCHAR(20),
  date_of_issue DATE,
  email         VARCHAR(50),
  PRIMARY KEY (id)
)
  AUTO_INCREMENT = 1000000;

CREATE INDEX lastname_subscriber_idx
  ON bf_subscriber (lastname);
CREATE INDEX firstname_subscriber_idx
  ON bf_subscriber (firstname);
CREATE INDEX middlename_subscriber_idx
  ON bf_subscriber (middlename);

CREATE TABLE bf_house
(
  id           INT         NOT NULL AUTO_INCREMENT,
  street_id    INT         NOT NULL,
  house        VARCHAR(20) NOT NULL,
  corps        VARCHAR(20),
  corps_letter VARCHAR(10),
  district_id  INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (street_id) REFERENCES bf_street (id),
  FOREIGN KEY (district_id) REFERENCES bf_district (id),
  CONSTRAINT street_house_cops_letter_idx UNIQUE (street_id, house, corps, corps_letter, district_id)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_area
(
  id      INT          NOT NULL AUTO_INCREMENT,
  name    VARCHAR(100) NOT NULL,
  city_id INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (city_id) REFERENCES bf_city (id)
    ON DELETE CASCADE,
  CONSTRAINT name_city_area_idx UNIQUE (name, city_id),
  UNIQUE (name)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_postman
(
  id      INT          NOT NULL AUTO_INCREMENT,
  name    VARCHAR(255) NOT NULL,
  area_id INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (area_id) REFERENCES bf_area (id)
    ON DELETE CASCADE,
  UNIQUE (name)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_address
(
  id                 INT NOT NULL AUTO_INCREMENT,
  area_id            INT,
  sector             VARCHAR(20),
  mail_index         VARCHAR(20),
  region_id          INT NOT NULL,
  city_id            INT NOT NULL,
  district_id        INT NOT NULL,
  house_id           INT NOT NULL,
  flat               VARCHAR(20),
  living_place       INT NOT NULL,
  subscriber_id      INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (area_id) REFERENCES bf_area (id),
  FOREIGN KEY (region_id) REFERENCES bf_region (id),
  FOREIGN KEY (city_id) REFERENCES bf_city (id),
  FOREIGN KEY (district_id) REFERENCES bf_district (id),
  FOREIGN KEY (house_id) REFERENCES bf_house (id),
  FOREIGN KEY (subscriber_id) REFERENCES bf_subscriber (id)
    ON DELETE CASCADE
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_phone
(
  id            INT NOT NULL AUTO_INCREMENT,
  cellPhone     VARCHAR(100),
  home_phone    VARCHAR(100),
  subscriber_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (subscriber_id) REFERENCES bf_subscriber (id)
    ON DELETE CASCADE
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_subscriber_type
(
  id   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100),
  PRIMARY KEY (id),
  CONSTRAINT name_subscriber_type_idx UNIQUE (name)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_subscriber_subscriber_type (
  subscriber_id INT NOT NULL,
  type_id       INT NOT NULL,
  PRIMARY KEY (subscriber_id, type_id),
  CONSTRAINT subscriber_subscriber_type_ibfk_1 FOREIGN KEY (subscriber_id) REFERENCES bf_subscriber (id),
  CONSTRAINT subscriber_subscriber_type_ibfk_2 FOREIGN KEY (type_id) REFERENCES bf_subscriber_type (id)
)
  AUTO_INCREMENT = 100000;






