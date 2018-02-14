-- Delete the tables if they exist.
-- Disable foreign key checks, so the tables can
-- be dropped in arbitrary order.
PRAGMA foreign_keys=OFF;
DROP TABLE IF EXISTS users;
PRAGMA foreign_keys=ON;

-- Create the tables.

CREATE TABLE users (
    username      varchar(20) not null PRIMARY KEY
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

CREATE TABLE performances (
  id              INTEGER PRIMARY KEY,
  movie           varchar(255) not null,
  theater         varchar(20) not null,
  play_date       varchar(20),
  availableSeats  INTEGER,
  foreign key     (movie) REFERENCES movies(movie_name),
  foreign key     (theater) REFERENCES theaters(theater_name)
);

CREATE TABLE reservations (
  resNbr          INTEGER PRIMARY KEY,
  to_see          varchar(255) not null,
  booker          varchar(20) not null,
  foreign key     (to_see) REFERENCES performances(id),
  foreign key     (booker) REFERENCES users(username)
);
-- Insert data into the tables.
INSERT
INTO users
VALUES ("Martin");

INSERT
INTO    users
VALUES ("Robin");

INSERT
INTO    users
VALUES ("Martin65");

INSERT
INTO movies
VALUES ("hejsan");

INSERT
INTO movies
VALUES ("Robin");

INSERT
INTO movies
VALUES ("du");

INSERT
INTO movies
VALUES ("är");

INSERT
INTO movies
VALUES ("söt");

INSERT
INTO movies
VALUES (",");

INSERT
INTO movies
VALUES ("ibland");

INSERT
INTO theaters
VALUES ("Robins rum", "Robbans vag", 200);


INSERT
INTO theaters
VALUES ("Martins rum", "The best road ever", 100);

INSERT 
INTO performances(movie, theater, availableSeats, play_date)
VALUES ("hejsan", "Robins rum", 200, "180102");

INSERT 
INTO performances(movie, theater, availableSeats, play_date)
VALUES ("ibland", "Robins rum", 200, "180102");

INSERT 
INTO performances(movie, theater, availableSeats, play_date)
VALUES ("söt", "Robins rum", 200, "180103");

INSERT 
INTO performances(movie, theater, availableSeats, play_date)
VALUES (",", "Robins rum", 200, "180103");

INSERT 
INTO performances(movie, theater, availableSeats, play_date)
VALUES ("du", "Martins rum", 100, "180103");

INSERT 
INTO performances(movie, theater, availableSeats, play_date)
VALUES ("hejsan", "Martins rum", 100, "180103");

INSERT
INTO reservations(to_see, booker)
VALUES (1, "Martin");

INSERT
INTO reservations(to_see, booker)
VALUES (1, "Robin");

INSERT
INTO reservations(to_see, booker)
VALUES (2, "Martin");

INSERT
INTO reservations(to_see, booker)
VALUES (3, "Martin");

INSERT
INTO reservations(to_see, booker)
VALUES (1, "Martin65");

