create table cars
(
  id_car int auto_increment
    primary key,
  gos_number varchar(10) not null,
  mark varchar(20) not null,
  model varchar(20) not null,
  year_out date not null,
  type varchar(4) not null,
  full_cost float not null,
  cost_1_day float not null,
  is_deleted varchar(10) not null,
  created_when datetime not null,
  modify_when datetime not null,
  constraint gos_number
  unique (gos_number)
)
;

create table damage_cost
(
  id_damage int auto_increment
    primary key,
  name varchar(50) not null,
  cost float not null,
  is_deleted varchar(10) not null,
  create_when datetime not null,
  modify_when datetime not null
)
;

create table roles
(
  id_role int auto_increment
    primary key,
  name_role varchar(45) not null,
  type_role varchar(45) not null
)
;

create table users
(
  id_user int auto_increment
    primary key,
  first_name varchar(45) not null,
  last_name varchar(45) not null,
  id_passport varchar(12) not null,
  phone_number varchar(45) not null,
  user_name varchar(45) not null,
  user_password varchar(45) not null,
  created_when datetime not null,
  modify_when datetime not null,
  is_deleted varchar(10) not null
)
;

create table rents
(
  id_rent int auto_increment
    primary key,
  id_user int not null,
  id_car int not null,
  rental_start_date datetime not null,
  rental_finish_date datetime not null,
  created_when datetime not null,
  modify_when datetime not null,
  constraint rents_fk0
  foreign key (id_user) references users (id_user),
  constraint rents_fk1
  foreign key (id_car) references cars (id_car)
)
;

create table damage_rent
(
  id_damage int not null,
  id_rent int not null,
  constraint damage_rent_fk0
  foreign key (id_damage) references damage_cost (id_damage),
  constraint damage_rent_fk1
  foreign key (id_rent) references rents (id_rent)
)
;

create index damage_rent_fk0
  on damage_rent (id_damage)
;

create index damage_rent_fk1
  on damage_rent (id_rent)
;

create index rents_fk0
  on rents (id_user)
;

create index rents_fk1
  on rents (id_car)
;

create table user_role
(
  id_role int not null,
  id_user int not null,
  constraint user_role_fk0
  foreign key (id_role) references roles (id_role),
  constraint user_role_fk1
  foreign key (id_user) references users (id_user)
)
;

create index user_role_fk0
  on user_role (id_role)
;

create index user_role_fk1
  on user_role (id_user)
;

