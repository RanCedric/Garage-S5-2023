\c postgres;
drop database garage;
create database garage1;
\c garage1;

create sequence seq_admin_id;
create sequence seq_sexe_id;
create sequence seq_specialite_id;
create sequence seq_employer_id;
create sequence seq_produit_id;
create sequence seq_prixpo_id;
create sequence seq_poste_id;

create sequence seq_materiel_id;
create sequence seq_service_id;

create sequence seq_client_id;

create sequence seq_facturation_id;
create sequence seq_facturation_service_id;
create sequence seq_facturation_materiel_id; 

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

create table specialite(
    specialite_id varchar(10) default 'PST'||nextval('seq_specialite_id'),
    specialite_label varchar(20) not null,
    specialite_karama double precision default 0,
    primary key (specialite_id)
);

insert into specialite (specialite_label,specialite_karama) values ('assistant',1000),('manasa',800);
insert into specialite (specialite_label,specialite_karama) values ('Mecanicien',1000);


create table employer(
    employer_id varchar(10) default 'EMP'||nextval('seq_employer_id'),
    employer_name varchar(20) not null,
    employer_forname varchar(20) not null,
    ref_sexe_id varchar(20) not null,
    employer_date date not null,
    employer_numero varchar(20),
    ref_poste_id VARCHAR(50),
    primary key (employer_id)
);

create table specialEmp(
    employer_id_spec varchar(10),
    specialite_id_specialites varchar(10)
);

create table poste(
 poste_id varchar(50) default 'PID'||nextval('seq_poste_id'),
 poste_label VARCHAR(50),
 PRIMARY KEY(poste_id)
);

create table produits(
    produit_id varchar(10) default 'PRO' ||nextval('seq_produit_id'),
    produit_label varchar(50) unique,
    produit_pu double precision,
    primary key (produit_id)
);

create table materiel (
	materiel_id varchar(10) default 'MAT' ||nextval('seq_materiel_id'),
    	materiel_label varchar(50) unique,
    	materiel_pu double precision,
    	primary key (materiel_id)
);

create table service(
	service_id varchar(10) default 'SRV' ||nextval('seq_service_id'),
    service_nom varchar(50) unique,
    service_prix decimal(18,5),
    primary key (service_id)
);

create table service_materiel(
	service_id varchar(10),
    materiel_id varchar(50),
    FOREIGN key (service_id) REFERENCES service(service_id),
    FOREIGN key (materiel_id) REFERENCES materiel(materiel_id)
);

create table service_specialite(
	service_id varchar(10),
    specialite_id varchar(50),
    service_duree decimal(18,5),
    FOREIGN key (service_id) REFERENCES service(service_id),
    FOREIGN key (specialite_id) REFERENCES specialite(specialite_id)
);

-- create sequence seq_facturation_id;
-- create sequence seq_facturation_service_id;
-- create sequence seq_facturation_materiel_id; 

create table client (
	client_id varchar(10) default 'CLI' || nextval('seq_client_id'),
	client_name varchar(50),
	date_naissance date,
	primary key (client_id)
);

insert into client values (default, 'John', '1999-01-05');

create table facture(
	facture_id varchar(10) default 'FCT' ||nextval('seq_facturation_id'),
    facture_client varchar(50),
    client_id varchar(10),
    facture_date date,
    primary key (facture_id),
    foreign key (client_id) references Client(client_id)
);

create table factureDetails(
    factureDetails_id SERIAL,
    factureId_origine VARCHAR(30),
    dateFactureDetails DATE,
    PRIMARY KEY(factureDetails_id)
);

alter table factureDetails add foreign key (factureId_origine) references facture(facture_id);

create table facture_service(
    facture_id_service int,
    service_id varchar(50),
    quantite decimal(18,5),
    foreign key (facture_id_service) REFERENCES factureDetails (factureDetails_id),
    foreign key (service_id) REFERENCES service(service_id)
);

alter table facture_service add column remise DECIMAL(18,5) default 0;

create table facture_produit(
	facture_id_produit int,
    produit_id varchar(50),
    quantite decimal(18,5),
    foreign key (facture_id_produit) REFERENCES factureDetails (factureDetails_id),
    foreign key (produit_id) REFERENCES produits(produit_id)
);

-- 
insert into facture values (default, 'Jean', 'CLI1', '2023-01-01');
insert into facture values (default, 'Jeeanne', 'CLI1', '2023-01-01');

--insert into facture_service values ('FCT1', 'SRV1', 8, default);

--insert into facture_produit values ('FCT1', 'PRO1', 5);
--insert into facture_produit values ('FCT1', 'PRO2', 5);

-- 
SELECT *
FROM facture
LEFT JOIN facture_service as fs ON fs.facture_id = facture.facture_id
LEFT JOIN facture_produit as fp ON fp.facture_id = facture.facture_id
LEFT JOIN produits as p ON p.produit_id = fp.produit_id
LEFT JOIN service as s ON s.service_id = fs.service_id;

insert into materiel values (default, 'Huile', 3000),
(default, 'T', 200),
(default, 'G', 3500),
(default, 'I', 6000),
(default, 'O', 1000); 


insert into service values (default, 'Srvice1'),
(default, 'srv2'),
(default, 'srv3'),
(default, 'srv4'),
(default, 'srv5'); 

insert into service_materiel values ('SRV1', 'MAT1'), ('SRV1', 'MAT2'), ('SRV2', 'MAT3'), ('SRV2', 'MAT4'); 
insert into service_specialite values ('SRV1', 'PST1', 5), ('SRV1', 'PST2', 6), ('SRV2', 'PST3', 7); 

create view service_mat_montant as
SELECT service_materiel.service_id, SUM(materiel_pu) as montant
FROM service_materiel
JOIN Materiel ON materiel.materiel_id = service_materiel.materiel_id
GROUP BY service_materiel.service_id;

create view service_spec_montant as
SELECT service_specialite.service_id, SUM(specialite_karama * service_specialite.service_duree) as montant
FROM service_specialite
JOIN specialite ON specialite.specialite_id = service_specialite.specialite_id
GROUP BY service_specialite.service_id;

insert into produits (produit_label,produit_pu) values ('Batterie',1200);
insert into produits (produit_label,produit_pu) values ('Huile',800);

create table prixpo(
    prixpo_id varchar(10) default 'PPO' || nextval('seq_prixpo_id'),
    prixpo_min double precision,
    prixpo_max double precision,
    prixpo_pourcent double precision,
    primary key (prixpo_id),
    unique(prixpo_min,prixpo_max)
);



INSERT INTO prixpo(prixpo_min ,prixpo_max,prixpo_pourcent ) VALUES
(0,500,100),(501,2000,70),(2001,10000,50),(10001,100000,20),(100001,1000000,10);


INSERT INTO poste(poste_label) VALUES('poste1'),('poste2'),('poste3');

alter table employer
ADD FOREIGN KEY (ref_sexe_id) REFERENCES sexe(sexe_id),
ADD FOREIGN KEY (ref_poste_id) REFERENCES poste(poste_id);

alter table specialEmp
ADD FOREIGN KEY (employer_id_spec) REFERENCES employer(employer_id),
ADD FOREIGN KEY (specialite_id_specialites) REFERENCES specialite(specialite_id);

create table facture_payer(
    facture_id_payer VARCHAR(50),
    paye_date date,
    montant_paye double precision
);

alter table facture_payer
add foreign key (facture_id_payer) references facture(facture_id);

CREATE table depense(
    depense_label VARCHAR(100),
    depense_date date,
    depense_montant double precision
);

create table report(
	report_id serial primary key,
	montant_report DECIMAL(18,5),
	date_report(18,5)
);

create table remises(
	remise_id serial primary key,
	remise_nom VARCHAR(255),
	remise_montant DECIMAL(18,5)
);
