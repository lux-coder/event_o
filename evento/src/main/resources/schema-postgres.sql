DROP TABLE IF EXISTS city_size, events, city, or_type, or_unit;

CREATE TABLE or_unit(
    unit_id serial,
    unit_name VARCHAR(50),
    unit_description VARCHAR(255),
    or_unit_id serial,
    PRIMARY KEY(unit_id),
    CONSTRAINT fk_unit
        FOREIGN KEY(or_unit_id)
        REFERENCES or_unit(unit_id)
        ON DELETE CASCADE

);

CREATE TABLE or_type(
    type_id serial,
    type_name VARCHAR(10),
    active BOOL DEFAULT TRUE,
	unit_id serial,
    PRIMARY KEY(type_id),
	CONSTRAINT fk_unit
		FOREIGN KEY(unit_id)
		REFERENCES or_unit(unit_id)
		ON DELETE CASCADE

);

CREATE TABLE city(
   city_id serial,
   city_name VARCHAR(255) NOT NULL,
   or_unit_id INT,
   PRIMARY KEY(city_id),
   CONSTRAINT fk_or_type
      FOREIGN KEY(or_unit_id)
      REFERENCES or_unit(unit_id)
      ON DELETE CASCADE

);

CREATE TABLE city_size(
   size_id serial,
   size_value VARCHAR(25) NOT NULL,
   active BOOL DEFAULT TRUE,
   city_id serial,
   PRIMARY KEY(size_id),
   CONSTRAINT fk_city
		FOREIGN KEY(city_id)
		REFERENCES city(city_id)
		ON DELETE CASCADE
);

CREATE TABLE events(
   event_id serial,
   event_name VARCHAR(255) NOT NULL,
   event_start TIMESTAMP NOT NULL,
   event_end TIMESTAMP,
   entrance BOOL DEFAULT FALSE,
   city_id serial,
   PRIMARY KEY(event_id),
   CONSTRAINT fk_city
        FOREIGN KEY(city_id)
        REFERENCES city(city_id)
        ON DELETE CASCADE
);


INSERT INTO or_unit(unit_name, unit_description) VALUES ('Zagrebačka regija', 'makro-regija');
INSERT INTO or_unit(unit_name, unit_description) VALUES ('Varaždinska regija', 'nad-regija');
INSERT INTO or_unit(unit_name, unit_description) VALUES ('Osječka regija', 'makro-regija');
INSERT INTO or_unit(unit_name, unit_description) VALUES ('Slavonsko brodska regija', 'nad-regija');
INSERT INTO or_unit(unit_name, unit_description) VALUES ('Riječka regija', 'makro-regija');
INSERT INTO or_unit(unit_name, unit_description) VALUES ('Zadarska regija', 'nad-regija');
INSERT INTO or_unit(unit_name, unit_description) VALUES ('Splitska regija', 'makro-regija');

INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Zagrebačka', 1);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Karlovačka', 1);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Sisačko-moslavačka', 1);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Bjelovarsko-bilogorska', 1);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Krapinsko-zagorska', 1);

INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Varaždinska', 2);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Međimurska', 2);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Koprivničko Križevačka', 2);

INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Osječko-baranjska', 3);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Vukovarsko-srijemska', 3);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Virovitičko-podravska', 3);

INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Brodsko-posavska', 4);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Požeško-slavonska', 4);

INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Primorsko-goranska', 5);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Istarska', 5);

INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Zadarska', 6);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Šibensko-kninska', 6);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Ličko-senjska', 6);

INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Splitsko-dalmatinska', 7);
INSERT INTO or_unit(unit_name, or_unit_id) VALUES ('Dubrovačka', 7);

INSERT INTO or_type(type_name, unit_id) VALUES('REGIJA', 1);
INSERT INTO or_type(type_name, unit_id) VALUES('REGIJA', 2);
INSERT INTO or_type(type_name, unit_id) VALUES('REGIJA', 3);
INSERT INTO or_type(type_name, unit_id) VALUES('REGIJA', 4);
INSERT INTO or_type(type_name, unit_id) VALUES('REGIJA', 5);
INSERT INTO or_type(type_name, unit_id) VALUES('REGIJA', 6);
INSERT INTO or_type(type_name, unit_id) VALUES('REGIJA', 7);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 8);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 9);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 10);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 11);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 12);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 13);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 14);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 15);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 16);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 17);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 18);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 19);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 20);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 21);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 22);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 23);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 24);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 25);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 26);
INSERT INTO or_type(type_name, unit_id) VALUES('ŽUPANIJA', 27);

INSERT INTO city(city_name, or_unit_id) VALUES ('Hum', 22);
INSERT INTO city(city_name, or_unit_id) VALUES ('Križevci', 15);
INSERT INTO city(city_name, or_unit_id) VALUES ('Split', 26);
INSERT INTO city(city_name, or_unit_id) VALUES ('Zagreb', 8);
INSERT INTO city(city_name, or_unit_id) VALUES ('Samobor', 8);
INSERT INTO city(city_name, or_unit_id) VALUES ('Makarska', 26);
INSERT INTO city(city_name, or_unit_id) VALUES ('Imotski', 26);


INSERT INTO city_size(size_value, city_id) VALUES('MALI', 1);
INSERT INTO city_size(size_value, city_id) VALUES('SREDNJI', 2);
INSERT INTO city_size(size_value, city_id) VALUES('VELIK', 3);
INSERT INTO city_size(size_value, city_id) VALUES('VELIK', 4);
INSERT INTO city_size(size_value, city_id) VALUES('MALI', 5);
INSERT INTO city_size(size_value, city_id, active) VALUES('MALI', 6, false);
INSERT INTO city_size(size_value, city_id, active) VALUES('MALI', 7, false);


insert into events(event_name, event_start, event_end, city_id)
values('prvi event', '2016-06-22 19:10:25-07', '2016-06-22 19:10:00-07', 1);

insert into events(event_name, event_start, event_end, city_id)
values('drugi event', '2017-06-22 19:10:25-07', '2016-06-22 19:10:00-07', 2);

insert into events(event_name, event_start, event_end, city_id)
values('treći event', '2018-06-22 19:10:25-07', '2016-06-22 19:10:00-07', 3);

insert into events(event_name, event_start, event_end, city_id)
values('četvrti event', '2018-06-22 19:10:25-07', '2016-06-22 19:10:00-07', 4);

insert into events(event_name, event_start, event_end, city_id)
values('peti event', '2018-06-22 19:10:25-07', '2016-06-22 19:10:00-07', 5);

insert into events(event_name, event_start, event_end, city_id)
values('šesti event', '2018-06-22 19:10:25-07', '2016-06-22 19:10:00-07', 6);

insert into events(event_name, event_start, event_end, city_id)
values('sedmi event', '2021-02-11 23:00:00', '2016-06-22 19:10:00-07', 7);

insert into events(event_name, event_start, event_end, city_id, entrance)
values('osmi event', '2020-02-22 19:10:25-07', '2016-06-22 19:10:00-07', 4, true);

insert into events(event_name, event_start, event_end, city_id, entrance)
values('deveti event', '2021-02-11 19:10:25-07', '2016-06-22 19:10:00-07', 4, true);