
create table IF NOT EXISTS schedule_hours (
                                       id serial primary key,
                                       name_of_hour varchar(120) not null
);

create table IF NOT EXISTS brigades (
                                 id serial primary key,
                                 name_of_brigade varchar(120) not null,
                                 phone_number_of_foreman varchar(50)
);

create table IF NOT EXISTS schedule (
                                 id serial primary key,
                                 name_month varchar(50) not null,
                                 id_hour int references schedule_hours(id),
                                 id_brigade int references brigades(id)
);

create table IF NOT EXISTS workers (
                                id serial primary key,
                                name_worker varchar(33) not null,
                                surname_worker varchar(50) not null,
                                speciality varchar(100) not null,
                                rank int,
                                experience int,
                                brigade_number int references brigades(id),
                                email varchar(32) not null unique
);

create table IF NOT EXISTS sets_for_cars (
                                      id serial primary key,
                                      name_of_set varchar(45) not null,
                                      num_of_parts int not null,
                                      rate_of_set float not null
);

create table IF NOT EXISTS set_materials (
                                      id serial primary key,
                                      name_of_material varchar(10) not null,
                                      description varchar(230) not null
);

create table IF NOT EXISTS requirement (
                                    id serial primary key,
                                    id_set int,
                                    id_set_material int,
                                    unit_cost float not null,
                                    total_sets int not null,
                                    foreign key (id_set) references sets_for_cars(id),
                                    foreign key (id_set_material) references set_materials(id),
                                    unique(id_set,id_set_material)
);

create table IF NOT EXISTS production (
                                   id serial primary key,
                                   id_worker int references workers(id),
                                   id_set int references sets_for_cars(id),
                                   made_sets int not null,
                                   date_of_production date not null
);

create table IF NOT EXISTS production_materials (
                                             id serial primary key,
                                             id_set_material int references set_materials(id),
                                             id_brigade int references brigades(id),
                                             quantity_of_raw_materials float not null,
                                             date_of_production date not null
);

create table IF NOT EXISTS users (
                              id serial primary key,
                              name_user varchar(124) not null,
                              birthday date not null,
                              email varchar(124) not null unique,
                              password varchar(32) not null,
                              role varchar(32) not null,
                              gender varchar(32) not null
);

create table IF NOT EXISTS workers_sets(
                                    id serial primary key,
                                    id_set int references public.sets_for_cars(id),
                                    id_worker int references public.workers(id),
                                    requirement int not null,
                                    unique (id_set,id_worker)
);