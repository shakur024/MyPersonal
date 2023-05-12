drop database if exists Hero;

create database Hero;

use Hero;

create table Hero(
heroId int not null auto_increment primary key,
heroes varchar(30) not null,
description varchar(40),
Powers varchar(35));

create table org (
orgId int not null auto_increment  primary key,
orgName varchar(50) not null,
description varchar(50) not null,
addressInfo varchar(35));

create table loc (
locId int not null auto_increment  primary key,
LocName varchar(35),
description varchar(35),
addressInfo varchar(35),
latitude decimal(11,7),
longitude decimal(11,7));


create table sight(
sightingId int not null auto_increment primary key,
locationId int,
sightDate date,
FOREIGN KEY (locationId) REFERENCES loc(locId));


create table superHeroSighting(
superHeroId int,
sightingId int, 
FOREIGN KEY (superHeroId) REFERENCES Hero(heroId),
FOREIGN KEY (sightId) REFERENCES sighting(sightId));

create table superHeroOrganization (
superHeroId int,
organizationId int,
FOREIGN KEY (superHeroId) REFERENCES Hero(heroId),
FOREIGN KEY (organizationId) REFERENCES org(orgId));












