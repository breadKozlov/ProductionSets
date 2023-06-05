/* create database eurosets_db;
   create schema public;
 */

/*create table public.schedule_hours (
   id serial primary key,
   name_of_hour varchar(120) not null
);

create table public.brigades (
   id serial primary key,
   name_of_brigade varchar(120) not null,
   phone_number_of_foreman varchar(50)
);

create table public.schedule (
   id serial primary key,
   name_month varchar(50) not null,
   id_hour int references public.schedule_hours(id),
   id_brigade int references public.brigades(id)
);

create table public.workers (
   id serial primary key,
   name_worker varchar(33) not null,
   surname_worker varchar(50) not null,
   speciality varchar(100) not null,
   rank int,
   experience int,
   brigade_number int references public.brigades(id),
   email varchar(32) not null unique
);

create table public.sets_for_cars (
   id serial primary key,
   name_of_set varchar(45) not null,
   num_of_parts int not null,
   rate_of_set decimal not null
);

create table public.set_materials (
   id serial primary key,
   name_of_material varchar(10) not null,
   description varchar(230) not null
);

create table public.requirement (
  id serial primary key,
  id_set int,
  id_set_material int,
  unit_cost decimal not null,
  total_sets int not null,
  foreign key (id_set) references public.sets_for_cars(id),
  foreign key (id_set_material) references public.set_materials(id),
  unique(id_set,id_set_material)
);

create table public.production (
  id serial primary key,
  id_worker int references public.workers(id),
  id_set int references public.sets_for_cars(id),
  made_sets int not null,
  date_of_production timestamp not null
);

create table public.production_materials (
  id serial primary key,
  id_set_material int references public.set_materials(id),
  id_brigade int references public.brigades(id),
  quantity_of_raw_materials decimal not null,
  date_of_production timestamp not null
);

create table public.users (
  id serial primary key,
  name_user varchar(124) not null,
  birthday date not null,
  email varchar(124) not null unique,
  password varchar(32) not null,
  role varchar(32) not null,
  gender varchar(32) not null
);

create table public.workers_sets(
  id serial primary key,
  id_set int references public.sets_for_cars(id),
  id_worker int references public.workers(id),
  requirement int not null,
  unique (id_set,id_worker)
);

/*drop table public.plan_worker_set;
drop table production;
drop table requirement;
drop table sets_for_cars;
drop table worker;
drop table set_materials;*/

insert into public.brigades (name_of_brigade,phone_number_of_foreman)
values
('Brigade 1','+375291112233'),
('Brigade 2','+375293334455'),
('Brigade 3','+375295556677');

insert into public.schedule_hours (name_of_hour)
values
('8.00 - 20.00'),
('20.00 - 8.00'),
('8.00 - 17.00'),
('does not work');

insert into public.schedule (name_month, id_hour,id_brigade)
values
('January',1,1),
('January',2,2),
('January',3,3),
('February',2,1),
('February',1,2),
('February',3,3),
('March',1,1),
('March',2,2),
('March',3,3),
('April',2,1),
('April',1,2),
('April',3,3),
('May',1,1),
('May',2,2),
('May',3,3),
('June',2,1),
('June',1,2),
('June',3,3),
('July',1,1),
('July',2,2),
('July',3,3),
('August',2,1),
('August',1,2),
('August',3,3),
('September',1,1),
('September',2,2),
('September',3,3),
('October',4,1),
('October',1,2),
('October',3,3),
('November',1,1),
('November',4,2),
('November',3,3),
('December',2,1),
('December',1,2),
('December',4,3);

insert into public.workers (name_worker,surname_worker,speciality,rank,experience,brigade_number,email)
values
('Alexei','Bratchenya','extruder foreman',6,5,1,'nautilus@tut.by'),
('Alexander','Ovchinnikov','extruder operator',5,8,1,'holera11@gmail.com'),
('Alexander','Babey','extruder operator',4,8,1,'pipka@yandex.ru'),
('Igor','Dubin','extruder foreman',6,11,2,'brigadir222@mail.ru'),
('Leonid','Norov','extruder operator',4,12,2,'kola@gmail.com'),
('Eduard','Shuneiko','extruder operator',5,8,2,'killer@tut.by'),
('Victor','Sandulenko','extruder operator',2,3,3,'keramin2011@mail.ru'),
('Sergey','Alekseenko','extruder operator',4,12,3,'rupor@tut.by'),
('Igor','Kuznetsov','extruder foreman',6,12,3,'kik@yandex.ru');

insert into public.sets_for_cars (name_of_set,num_of_parts,rate_of_set)
values
('Set 6430-5000120',27,2.99),
('Set 5440-5000120',18,2.76),
('Set 6501-5000120',20,2.33),
('Set 54327-5000130',14,3.86),
('Set 4371-5000120',17,2.09),
('Set 555102-5000130',8,2.56),
('Set 6430C9-5000120',27,3.21),
('Gasket 6430-5112072',1,0.29),
('Gasket 6430-5112073',1,0.23),
('Gasket 6418-5112072',1,0.36),
('Gasket 6418-5112073',1,0.24),
('Gasket 6501-5112072',1,0.24),
('Gasket 6501-5112073',1,0.24),
('Gasket 4371-5112073',3,0.43),
('Gasket 5309DR-5112072',2,0.49),
('Gasket 5309DR-5112073',2,0.55),
('Overlay 6430-5112075',1,0.09),
('Overlay 6430-6102094',1,0.09),
('Overlay 6430-6102095',1,0.09),
('Overlay 6430-6102096',1,0.06),
('Overlay 6430-6102097',1,0.06),
('Overlay 64221-6102095',1,0.06),
('Overlay 64221-6102097',1,0.08);

insert into public.set_materials (name_of_material,description)
values
('K2 mm','Material - polyethylene foam; thickness - 2 mm; adhesive - on one side'),
('K4 mm','Material - polyethylene foam; thickness - 4 mm; adhesive - on one side'),
('K10 mm','Material - polyethylene foam; thickness - 10 mm; adhesive - on one side'),
('2K10 mm','Material - polyethylene foam; thickness - 10 mm; adhesive - on both sides'),
('10 mm','Material - polyethylene foam; thickness - 10 mm; no adhesive'),
('K20 mm','Material - polyethylene foam; thickness - 20 mm; adhesive - on one side');

insert into "public".requirement (id_set,id_set_material,unit_cost,total_sets)
values
(1,6,9.707,300),
(2,6,9.3,200),
(3,6,5.8,500),
(4,3,3.7,250),
(4,4,3.31,250),
(4,5,1.9,250),
(5,6,5.716,100),
(6,3,3.13,20),
(6,4,2.18,20),
(6,5,2.1,20),
(7,6,10.961,800),
(8,3,1.95,100),
(9,3,1.4,100),
(10,3,1.58,15),
(11,3,1.58,15),
(12,3,1.58,0),
(13,3,1.58,0),
(14,3,3.6,10),
(15,3,3.03,12),
(16,3,3.0,12),
(17,3,0.14,350),
(18,1,0.19,1000),
(19,1,0.19,1000),
(20,1,0.03,500),
(21,1,0.03,550),
(22,2,0.04,250),
(23,2,0.07,350);

insert into public.production (id_worker,id_set,made_sets,date_of_production)
values
(1,1,250,'2023-04-04'),
(1,4,100,'2023-04-05'),
(1,3,150,'2023-04-05'),
(2,4,100,'2023-05-22'),
(2,19,500,'2023-05-28'),
(2,18,250,'2023-05-29'),
(3,22,250,'2023-04-04'),
(3,23,100,'2023-04-25'),
(3,11,15,'2023-04-08'),
(4,7,250,'2023-03-01'),
(4,7,100,'2023-04-02'),
(4,2,50,'2023-05-07'),
(4,8,20,'2023-06-15'),
(4,8,20,'2023-06-16'),
(5,17,350,'2023-03-01'),
(5,15,12,'2023-04-04'),
(6,16,10,'2023-08-11'),
(6,12,10,'2023-06-21'),
(6,9,100,'2023-05-14'),
(7,11,23,'2023-07-17'),
(8,5,45,'2023-10-28'),
(5,7,11,'2023-11-25'),
(4,7,13,'2023-12-09'),
(3,14,20,'2023-11-11'),
(1,17,100,'2023-09-13'),
(2,23,140,'2023-04-24');

insert into public.production_materials(id_set_material,id_brigade,quantity_of_raw_materials,date_of_production)
values
(6,1,650,'2023-03-26'),
(5,2,250,'2023-03-11'),
(4,3,100,'2023-03-02'),
(3,1,650,'2023-04-01'),
(2,1,1200,'2023-02-06'),
(1,2,300,'2023-05-11'),
(4,1,650,'2023-03-16'),
(2,3,650,'2023-01-03'),
(1,2,650,'2023-08-18'),
(6,3,650,'2023-10-22'),
(5,3,650,'2023-09-21'),
(4,3,650,'2023-03-07'),
(4,1,650,'2023-01-05');

insert into public.workers_sets (id_set,id_worker,requirement)
values (1,2,300),
(2,2,200),
(1,3,100),
(11,5,150),
(8,6,120),
(9,6,90),
(17,2,90),
(18,2,65),
(20,8,110),
(11,8,350),
(17,5,70),
(22,9,15),
(7,7,25),
(14,2,45),
(10,4,55),
(12,2,145);


/* required materials for sets*/
select id_set,sfc.name_of_set, sm.name_of_material, r.unit_cost, r.total_sets, (r.unit_cost * r.total_sets) as over_cost
from "public".requirement r
join public.sets_for_cars sfc on r.id_set = sfc.id
join public.set_materials sm on r.id_set_material = sm.id;

/* production of sets */
select pw.name_worker,pw.surname_worker,sfc.name_of_set,made_sets,sfc.rate_of_set,
made_sets * sfc.rate_of_set as over_price, date_of_production
from public.production pp
join public.sets_for_cars sfc on pp.id_set = sfc.id
join public.workers pw on pp.id_worker = pw.id;

/* production of materials for sets */
select b.name_of_brigade,sm.name_of_material, pm.quantity_of_raw_materials, pm.date_of_production
from public.production_materials pm
join public.set_materials sm on pm.id_set_material=sm.id
join public.brigades b on pm.id_brigade = b.id;

/* difference between materials required and released in production */
select table_req.name_of_material,material_required,material_released from
(select sm.name_of_material, sum(pm.quantity_of_raw_materials) as material_released
from public.production_materials as pm
join public.set_materials sm on pm.id_set_material = sm.id
group by sm.name_of_material,sm.id
order by sm.id) as table_rel, (select sm.name_of_material,sum(r.total_sets * r.unit_cost) as material_required
from public.requirement r
join public.set_materials sm on r.id_set_material=sm.id
group by sm.name_of_material,sm.id
order by sm.id) as table_req
where table_rel.name_of_material = table_req.name_of_material;

/* over released sets */
select sfc.name_of_set as name,sum(made_sets) as over_released
from public.production pp
join public.sets_for_cars sfc on pp.id_set = sfc.id
group by name;

/* over required sets */
select sfc.name_of_set as name, avg(total_sets) as requirement from "public".requirement as r
join public.sets_for_cars sfc on r.id_set = sfc.id
group by name,sfc.id
order by sfc.id;

/* remainder between required and released sets */
select table1.name, table1.total_req as total_required,
coalesce (table2.over_released,0) as total_released,
(table1.total_req - coalesce (table2.over_released,0)) as remainder
from (select sfc.name_of_set as name, avg(total_sets) as total_req from "public".requirement as r
join public.sets_for_cars sfc on r.id_set = sfc.id
group by name,sfc.id
order by sfc.id) as table1
left join (select sfc.name_of_set as name,sum(made_sets) as over_released
from public.production pp
join public.sets_for_cars sfc on pp.id_set = sfc.id
group by name) as table2 on table1.name = table2.name;

--get schedule of all brigades in all month--
select sch.name_month,b.name_of_brigade,h.name_of_hour from public.schedule as sch
join public.schedule_hours as h on sch.id_hour = h.id
join public.brigades as b on sch.id_brigade = b.id;

--get schedule of brigades in January
select b.name_of_brigade,h.name_of_hour from public.schedule as sch
join public.schedule_hours as h on sch.id_hour = h.id
join public.brigades as b on sch.id_brigade = b.id
where sch.name_month = 'January';

--get schedule of one brigades in all month--
select sch.name_month, h.name_of_hour from public.schedule as sch
join public.schedule_hours as h on sch.id_hour = h.id
join public.brigades as b on sch.id_brigade = b.id
where b.name_of_brigade = 'Brigade 2';