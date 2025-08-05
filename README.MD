remember to put your package (hackerrank_restapi) under the root package com.springboot_hinernate,
otherwise will have **problem with componentscan** !!!

the **"year"** must in a double or single quote !!!! it is key word, otherwise the identifier problem
Syntax error in SQL statement "create table if not exists football_match ( id SERIAL not null, team varchar (100) not null,
[*]year integer not null,
inserted_at timestamp default NOW(), primary key (id) )"; expected "identifier"; SQL statement:
```sql 
create table if not exists football_match
(
id SERIAL not null,
team varchar (100) not null,
"year" integer not null,
inserted_at timestamp default NOW(),
primary key (id)
);
```