
create table estado (
    id bigint not null auto_increment,
    nome varchar(255) not null,
    primary key (id)
);

create table cidade (
    id bigint not null auto_increment,
    nome varchar(255) not null,
    estado_id bigint not null,
    primary key (id),
    constraint fk_cidade_estado foreign key (estado_id) references estado (id)
);

create table cozinha (
    id bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
);

create table restaurante (
    id bigint not null auto_increment,
    nome varchar(255),
    taxa_frete decimal(19,2) not null,
    cozinha_id bigint not null,
    endereco_cep varchar(255),
    endereco_logradouro varchar(255),
    endereco_numero varchar(255),
    endereco_complemento varchar(255),
    endereco_bairro varchar(255),
    endereco_cidade_id bigint,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    primary key (id),
    constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha (id),
    constraint fk_restaurante_endereco_cidade foreign key (endereco_cidade_id) references cidade (id)
);

create table forma_pagamento (
    id bigint not null auto_increment,
    descricao varchar(255) not null,
    primary key (id)
);

create table restaurante_forma_pagamento (
    restaurante_id bigint not null,
    forma_pagamento_id bigint not null,
    primary key (restaurante_id, forma_pagamento_id),
    constraint fk_rest_fp_rest foreign key (restaurante_id) references restaurante (id),
    constraint fk_rest_fp_fp foreign key (forma_pagamento_id) references forma_pagamento (id)
);

create table produto (
    id bigint not null auto_increment,
    nome varchar(255) not null,
    descricao varchar(255) not null,
    preco decimal(19,2) not null,
    ativo boolean not null,
    restaurante_id bigint not null,
    primary key (id),
    constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante (id)
);

create table permissao (
    id bigint not null auto_increment,
    nome varchar(255) not null,
    descricao varchar(255) not null,
    primary key (id)
);

create table grupo (
    id bigint not null auto_increment,
    nome varchar(255) not null,
    primary key (id)
);

create table grupo_permissao (
    grupo_id bigint not null,
    permissao_id bigint not null,
    primary key (grupo_id, permissao_id),
    constraint fk_grupo_perm_grupo foreign key (grupo_id) references grupo (id),
    constraint fk_grupo_perm_perm foreign key (permissao_id) references permissao (id)
);

create table usuario (
    id bigint not null auto_increment,
    nome varchar(255) not null,
    email varchar(255) not null,
    senha varchar(255) not null,
    data_cadastro datetime not null,
    primary key (id)
);

create table usuario_grupo (
    usuario_id bigint not null,
    grupo_id bigint not null,
    primary key (usuario_id, grupo_id),
    constraint fk_usuario_grupo_user foreign key (usuario_id) references usuario (id),
    constraint fk_usuario_grupo_grupo foreign key (grupo_id) references grupo (id)
);
