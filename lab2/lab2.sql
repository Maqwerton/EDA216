-- Delete the tables if they exist.
-- Disable foreign key checks, so the tables can
-- be dropped in arbitrary order.
PRAGMA foreign_keys=OFF;
DROP TABLE IF EXISTS users;
...
PRAGMA foreign_keys=ON;

-- Create the tables.

CREATE TABLE users (
    username      varchar(20) not null,
    adress        varchar(20) not null,
    phone         varchar(20) not null,
    primary key   (username)
);

CREATE TABLE reservation (
  resNbr          int not null AUTO_INCREMENT,
  ToSee           varchar(255) not null,
  booker          varchar(20) not null
  primary key     resNbr
);

CREATE TABLE performance (
  id              int not null AUTO_INCREMENT,
  location        varchar(255) not null,
  movie           varchar(255) not null,
  time            varchar(20) not null,
  date            varchar(20) not null,
  foreign key     (movie) REFERENCES movies(movie_name),
  foreign key     (theater) REFERENCES theater(theater_name),
  primary key     (location, date, movie)
);

CREATE TABLE movies (
  movie_name       varchar(255) not null,
  primary key     (movie_name)
);

CREATE TABLE theater (
  theater_name    varchar(20) not null,
  adress          varchar(255) not null,
  seats           integer(20) not null,
  primary key     (theater_name)
);

-- Insert data into the tables.
INSERT
INTO    users
VALUES (Martin, hejvägen 51, 073057535)
INöTO    users
VALUES (Daniel, vägensväg 35, 53525325)
INTO    reservation
VALUES ( hejvägen 51, 073057535)
INTO    users
VALUES (Martin, hejvägen 51, 073057535)
INTO    users
VALUES (Martin, hejvägen 51, 073057535)
INTO    users
VALUES (Martin, hejvägen 51, 073057535)

...
