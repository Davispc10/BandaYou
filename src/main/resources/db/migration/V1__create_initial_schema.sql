create table banda (id_banda bigint not null auto_increment, nome varchar(255), email varchar(255), estilo varchar(255), primary key(id_banda));
create table musica (id_musica bigint not null auto_increment, id_banda bigint not null, nome varchar(255), compositor varchar(255),  primary key(id_musica), foreign key(id_banda) references banda(id_banda));
