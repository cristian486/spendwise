CREATE TABLE transacao(
    id binary(36) not null unique,
    usuario varchar(50) not null,
    descricao varchar(250),
    data datetime not null,
    valor numeric(9, 2) not null,
    categoria varchar(20) not null,
    tipo varchar(15) not null,
    constraint transacaopk primary key(id)
);