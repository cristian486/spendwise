CREATE TABLE conta(
    id binary(36) not null unique,
    usuario binary(50) not null,
    saldo_atual numeric(9, 2) not null,
    debito numeric(9, 2) not null,
    credito numeric(9, 2) not null,
    constraint contapk primary key(id)
);