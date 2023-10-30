/*
postgres is installed as the standalone version on my local and NOT via homebrew
postgres db can be accessed from postgres app via Applications directory
changes to db can be made from there

To access db from command line instead, ensure that you are in the project directory and run the following:
psql -U postgresql@14 --file hrmanagement_db.sql // runs all scripts in hrmanagement_db.sql file
OR

/Applications/Postgres.app/Contents/Versions/latest/bin/psql -h localhost -p 5432 -U your_username -c "SELECT usename FROM pg_user"

the superuser is cmaynard and the user with all privileges to this db is administrator

*/
DROP DATABASE IF EXISTS hrmanagementdb;
DROP user IF EXISTS administrator;
CREATE user administrator with password 'password';
CREATE DATABASE hrmanagementdb with template=template0 owner=administrator;
\connect hrmanagementdb;
alter default privileges grant all on tables to administrator;
alter default privileges grant all on sequences to administrator;

CREATE TABLE employee(
    emplid integer primary key not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    address varchar not null,
    email varchar(30) not null,
    password text not null
);

CREATE table department(
    emplid integer,
    department_id integer primary key not null,
    dept_head integer not null,
    title varchar(25) not null,
    description varchar(60) not null
);

alter table department add constraint dept_employee_fk
    foreign key (emplid) references employee(emplid);

create table empl_time_off(
    time_off_request_id integer primary key not null,
    department_id integer not null,
    emplid integer not null,
    days_off numeric(10) not null,
    request_date bigint not null,
    note varchar(60)
);

alter table empl_time_off add constraint empl_time_off_dept_fk
    foreign key (department_id) references department(department_id);
alter table empl_time_off add constraint empl_time_off_empl_fk
    foreign key (emplid) references employee(emplid);

create sequence employee_seq increment 1 start 1;
create sequence department_seq increment 1 start 1;
create sequence empl_time_off increment 1 start 1000;


