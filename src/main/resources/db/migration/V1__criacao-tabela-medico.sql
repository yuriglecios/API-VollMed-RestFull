create table medicos (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    telefone varchar(22) not null,
    crm varchar(6) not null,
    especialidade varchar(100) not null,
    logradouro varchar(100) not null,
    numero varchar(100) not null,
    bairro varchar(100) not null,
    uf varchar(100) not null,
    cidade varchar(100) not null,
    cep varchar(8) not null,
    complemento varchar(100) not null,
    constraint medico_pk primary key (id),
    primary key (id)
);