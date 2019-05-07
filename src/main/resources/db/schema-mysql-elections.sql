DROP TABLE IF EXISTS bf_election_commission_address;
DROP TABLE IF EXISTS bf_election_commission_phone;
DROP TABLE IF EXISTS bf_election_commission;
DROP TABLE IF EXISTS bf_election_candidate;
DROP TABLE IF EXISTS bf_election;


CREATE TABLE bf_election (
  id            INT          NOT NULL AUTO_INCREMENT,
  name          VARCHAR(255) NOT NULL,
  full_name     VARCHAR(255),
  election_date DATE         NOT NULL,
  enabled       BOOLEAN               DEFAULT TRUE,
  PRIMARY KEY (id),
  UNIQUE (name),
  UNIQUE (election_date)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_election_candidate (
  id             INT          NOT NULL AUTO_INCREMENT,
  name           VARCHAR(255) NOT NULL,
  election_id    INT          NOT NULL,
  left_out       BOOLEAN               DEFAULT NULL,
  our_cand       BOOLEAN               DEFAULT NULL,
  our_tech_cand  BOOLEAN               DEFAULT NULL,
  enabled        BOOLEAN               DEFAULT TRUE,
  top            BOOLEAN               DEFAULT FALSE,
  tech_of_parent INT                   DEFAULT NULL,
  ordering       INT                   DEFAULT 1000,
  color          VARCHAR(30),
  PRIMARY KEY (id),
  FOREIGN KEY (election_id) REFERENCES bf_election (id),
  FOREIGN KEY (tech_of_parent) REFERENCES bf_election_candidate (id) ON DELETE CASCADE ,
  UNIQUE (name, election_id)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_election_commission (
  id                   INT          NOT NULL AUTO_INCREMENT,
  name                 VARCHAR(255) NOT NULL,
  number               INT          NOT NULL,
  address              VARCHAR(255),
  location             VARCHAR(255),
  location_description VARCHAR(1000),
  enabled              BOOLEAN               DEFAULT TRUE,
  parent_id            INT                   DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (parent_id) REFERENCES bf_election_commission (id),
  UNIQUE (name, parent_id)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE bf_election_commission_phone (
  id            INT          NOT NULL AUTO_INCREMENT,
  name          VARCHAR(100) NOT NULL,
  phone         VARCHAR(20)  NOT NULL,
  commission_id INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (commission_id) REFERENCES bf_election_commission (id),
  UNIQUE (phone)
)
  AUTO_INCREMENT = 1000;

