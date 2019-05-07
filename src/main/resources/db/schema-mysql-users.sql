DROP TABLE IF EXISTS bf_module_group;
DROP TABLE IF EXISTS bf_menu_category;
DROP TABLE IF EXISTS bf_user;
DROP TABLE IF EXISTS bf_group;
DROP TABLE IF EXISTS bf_module;

CREATE TABLE bf_group (
  id          INT          NOT NULL AUTO_INCREMENT,
  name        VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  enabled     BOOLEAN               DEFAULT TRUE,
  visible     BOOLEAN               DEFAULT TRUE,
  PRIMARY KEY (id),
  UNIQUE (name)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_user (
  id           INT          NOT NULL AUTO_INCREMENT,
  name         VARCHAR(255) NOT NULL,
  ident_number VARCHAR(255),
  passport     VARCHAR(255),
  login        VARCHAR(32)  NOT NULL,
  password     VARCHAR(32)  NOT NULL,
  enabled      BOOLEAN      NOT NULL DEFAULT TRUE,
  visible      BOOLEAN      NOT NULL DEFAULT TRUE,
  last_enter   VARCHAR(50),
  ip_address   VARCHAR(15),
  group_id     INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (group_id) REFERENCES bf_group (id),
  UNIQUE (login)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_module (
  id          INT          NOT NULL AUTO_INCREMENT,
  name        VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  reference   VARCHAR(255) NOT NULL,
  enabled     BOOLEAN      NOT NULL DEFAULT TRUE,
  visible     BOOLEAN      NOT NULL DEFAULT TRUE,
  permission  VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (name),
  UNIQUE (permission)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_module_group (
  module_id INT NOT NULL,
  group_id  INT NOT NULL,
  PRIMARY KEY (group_id, module_id),
  CONSTRAINT module_group_ibfk_1 FOREIGN KEY (module_id) REFERENCES bf_module (id),
  CONSTRAINT module_group_ibfk_2 FOREIGN KEY (group_id) REFERENCES bf_group (id)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_menu_category (
  id          INT         NOT NULL AUTO_INCREMENT,
  name        VARCHAR(50) NOT NULL,
  description VARCHAR(255),
  parent      INT DEFAULT NULL,
  menu_icon   VARCHAR(255),
  ordered     INT DEFAULT 10000,
  module_id   INT DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (module_id) REFERENCES bf_module (id),
  UNIQUE (name)
)
  AUTO_INCREMENT = 1000;
