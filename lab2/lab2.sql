-- Delete the tables if they exist.
-- Disable foreign key checks, so the tables can
-- be dropped in arbitrary order.
PRAGMA foreign_keys=OFF;
DROP TABLE IF EXISTS users;
PRAGMA foreign_keys=ON;

-- Create the tables.

CREATE TABLE users (
    username      varchar(20) not null PRIMARY KEY,
    adress        varchar(20) not null,
    phone         varchar(20) not null
);

CREATE TABLE movies (
  movie_name       varchar(255) not null,
  primary key     (movie_name)
);

CREATE TABLE theaters (
  theater_name    varchar(20) not null PRIMARY KEY,
  adress          varchar(255) not null,
  seats           integer(20) not null
);

CREATE TABLE performance (
  id              INTEGER PRIMARY KEY,
  location        varchar(255) not null,
  movie           varchar(255) not null,
  theater         varchar(20) not null,
  time            varchar(20) not null,
  date            varchar(20) not null,
  foreign key     (movie) REFERENCES movies(movie_name),
  foreign key     (theater) REFERENCES theaters(theater_name)
);


CREATE TABLE reservation (
  resNbr          INTEGER PRIMARY KEY,
  to_see          varchar(255) not null,
  booker          varchar(20) not null,
  foreign key     (to_see) REFERENCES performance(id),
  foreign key     (booker) REFERENCES users(username)
);
-- Insert data into the tables.
INSERT
INTO users
VALUES ("Martin", "hejvägen 51", "073057535");

INSERT
INTO movies
VALUES ("hejsan");

INSERT
INTO theaters
VALUES ("Robins rum", "Robbans vag", 200);

INSERT 
INTO performance(location, movie, theater, time, date)
VALUES ("Robins rum", "hejsan", "Robins rum", "20:20", "180102");

INSERT
INTO reservation(to_see, booker)
VALUES (1, "Martin");

INSERT
INTO    users
VALUES ("Martin43", "hejvägen 53", "073057535");

INSERT
INTO    users
VALUES ("Martin65"," hejvägen 52", "073057535");
