create table directories (
   id integer not null auto_increment,
   name varchar(255) not null,
   parent_directory_id integer,
   primary key (id),
   foreign key (parent_directory_id) references directories (id)
);

create table files (
   id integer not null auto_increment,
   name varchar(255) not null,
   directory_id integer not null,
   primary key (id),
   foreign key (directory_id) references directories (id)
);
