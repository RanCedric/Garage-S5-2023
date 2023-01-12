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
    primary key (poste_id)
);

insert into poste (poste_label) values ('assistant'),('manasa');

create table employer(
    employer_id varchar(10) default 'EMP'||nextval('seq_employer_id'),
    employer_name varchar(20) not null,
    employer_forname varchar(20) not null,
    ref_sexe_id varchar(20) not null,
    employer_date date not null,
    employer_numero varchar(20),
    ref_poste_id varchar(20) not null,
    primary key (employer_id)
);

create table salaire(
    ref_employer_id varchar(10),
    salaire_date date default current_timestamp,
    salaire_value int default 0
);

alter table employer
ADD FOREIGN KEY (ref_sexe_id) REFERENCES sexe(sexe_id),
ADD FOREIGN KEY (ref_poste_id) REFERENCES poste(poste_id);

alter table salaire
ADD FOREIGN KEY (ref_employer_id) REFERENCES employer(employer_id);

