create table reports(
	id serial primary key,
	report_name text,
	executor text,
	terms date
);
insert into reports(report_name, executor, terms) values('balance', 'Ivanov', '2023-05-10');
select * from reports;
update reports set report_name = 'balance', executor = 'Petrov', terms = '2023-07-15';
select * from reports;
delete from reports;
select * from reports;
