create table company(
	id serial primary key,
	company_name varchar(255)
);

create table worker(
	id serial primary key,
	name varchar(255),
	birthday date,
	company_id int references company(id)
);

create table skill(
	id serial primary key,
	skill_name varchar(255)
);

create table skill_worker(
	id serial primary key,
	skill_id int references skill(id),
	worker_id int references worker(id)
);

create table social_account(
	id serial primary key,
	social_account_name varchar(255)
);

create table social_account_worker(
	id serial primary key,
	social_account_id int references social_account(id) unique,
	worker_id int references worker(id) unique
);

insert into company(company_name) values('COmpany');
insert into worker(name, birthday, company_id) values('Ivanov II', '1972-11-13', 1);
insert into worker(name, birthday, company_id) values('Petrov PP', '1982-12-15', 1);
insert into skill(skill_name) values('woodmaster');
insert into skill(skill_name) values('steelmaster');
insert into skill_worker(skill_id, worker_id) values(1, 1);
insert into skill_worker(skill_id, worker_id) values(1, 2);
insert into skill_worker(skill_id, worker_id) values(2, 2);
insert into social_account(social_account_name) values('1-2 345678');
insert into social_account_worker(social_account_id, worker_id) values(1, 1);



