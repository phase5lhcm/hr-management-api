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
    department_id integer primary key not null,
    emplid integer not null,
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


