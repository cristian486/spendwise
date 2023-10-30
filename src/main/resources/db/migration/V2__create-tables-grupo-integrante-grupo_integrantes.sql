CREATE TABLE grupo(
    id binary(36) not null unique,
    nome varchar(255) not null unique,
    dono varchar(255) not null,
    dono_id binary(36) not null,
    constraint grupopk primary key(id)
);

CREATE TABLE integrante(
    id binary(36) not null unique,
    grupo_id binary(36) not null,
    usuario binary(36) not null,
    constraint integrantepk primary key(id),
    constraint integrante_grupofk foreign key(grupo_id) references grupo(id),
    constraint integranteuk unique(grupo_id, usuario)
);
