create table pacientes (
                         id bigint not null auto_increment,
                         nome varchar(100) not null,
                         email varchar(100) not null,
                         telefone varchar(22) not null,
                         cpf varchar(11) not null,
                         logradouro varchar(100) not null,
                         numero varchar(100) not null,
                         bairro varchar(100) not null,
                         uf varchar(100) not null,
                         cidade varchar(100) not null,
                         cep varchar(8) not null,
                         complemento varchar(100) not null,
                         primary key (id)
);