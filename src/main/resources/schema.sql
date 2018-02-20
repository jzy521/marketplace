create table user
(
   id integer auto_increment not null,
   name varchar(255) not null,
   email varchar(255) not null,
   password varchar(255) not null,
   primary key(id)
);

create table project
(
	id integer auto_increment not null,
	name varchar(255) not null,
	userId integer not null,
	open boolean not null,
	budget decimal(20, 2) not null,
	description varchar,
	endDate timestamp,
    bidNum integer,
    winningPrice decimal(20, 2),	
    currentPrice decimal(20, 2),
	primary key(id),
	foreign key (userId) references user(id)
);	

create table bid
(
	id integer auto_increment not null,
	projectId  integer not null,
	userId integer not null,
	bidTime timestamp not null,
	bidAmount decimal(20, 2) not null,
	bidPrice decimal(20, 2) not null,	
	primary key (id),
	foreign key (projectId) references project(id),
	foreign key (userId) references user(id)
);
