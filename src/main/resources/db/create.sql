SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS teams (
  id int PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR,
  description VARCHAR
);

CREATE TABLE IF NOT EXISTS members (
  id int PRIMARY KEY AUTO_INCREMENT,
  teamid INTEGER,
  firstname VARCHAR,
  lastname VARCHAR,
  description VARCHAR,
  age INTEGER
);

