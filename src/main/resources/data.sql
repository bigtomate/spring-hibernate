

/**
the "year" must in a double or single quote !!!! it is key word, otherwise the identifier problem
Syntax error in SQL statement "create table if not exists football_match ( id SERIAL not null, team varchar (100) not null, [*]year integer not null,
 inserted_at timestamp default NOW(), primary key (id) )"; expected "identifier"; SQL statement:
*/
create table if not exists foot_ball_match
(
      id BIGSERIAL not null,
      team varchar (100) not null,
      "year" integer not null,
      goal int,
      draw int,
      inserted_at timestamp default NOW(),
      primary key (id)
);

 create table if not exists person
  (
      id int not null,
      name varchar (255) not null,
      location varchar (255),
      birth_date timestamp,
      primary key (id)
  );

   create table if not exists book
  (
      id BIGSERIAL not null,
      name varchar (255) not null,
      published timestamp,
      primary key (id)
  );


insert into person(id, name, location, birth_date) values (10001, 'Xing','peterborough',NOW());

