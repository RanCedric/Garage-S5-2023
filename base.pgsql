\c postgres;
drop database garage;
create database garage;
\c garage;

create sequence seq_admin_id;
create sequence seq_sexe_id;
create sequence seq_poste_id;
create sequence seq_employer_id;

create table admin(
    admin_id varchar(10) default 'ADM'||nextval('seq_admin_id'),
    admin_name varchar(20) not null,
    admin_mdp varchar(30) not null,
    primary key (admin_id)
);

insert into admin (admin_name,admin_mdp) values ('admin','admin');

create table sexe(
    sexe_id varchar(10) default 'SEX'||nextval('seq_sexe_id'),
    sexe_label varchar(10) not null,
    primary key (sexe_id)
);

insert into sexe (sexe_label) values ('homme'),('femme');

create table poste(
    poste_id varchar(10) default 'PST'||nextval('seq_poste_id'),
    poste_label varchar(20) not null,
    poste_karama double precision default 0,
    primary key (poste_id)
);

insert into poste (poste_label,poste_karama) values ('assistant',1000),('manasa',800);

create table employer(
    employer_id varchar(10) default 'EMP'||nextval('seq_employer_id'),
    employer_name varchar(20) not null,
    employer_forname varchar(20) not null,
    ref_sexe_id varchar(20) not null,
    employer_date date not null,
    employer_numero varchar(20),
    primary key (employer_id)
);

create table specialites(
    employer_id_spec varchar(10),
    poste_id_specialites varchar(10)
);

alter table employer
ADD FOREIGN KEY (ref_sexe_id) REFERENCES sexe(sexe_id);

alter table specialites
ADD FOREIGN KEY (employer_id_spec) REFERENCES employer(employer_id),
ADD FOREIGN KEY (poste_id_specialites) REFERENCES poste(poste_id);
