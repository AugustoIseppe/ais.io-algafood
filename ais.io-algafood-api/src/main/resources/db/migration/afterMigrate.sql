set foreign_key_checks = 0;

delete from usuario_grupo;
delete from usuario;
delete from grupo_permissao;
delete from grupo;
delete from permissao;
delete from restaurante_forma_pagamento;
delete from produto;
delete from restaurante;
delete from cidade;
delete from forma_pagamento;
delete from estado;
delete from cozinha;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table grupo_permissao auto_increment = 1;
alter table permissao auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table restaurante_forma_pagamento auto_increment = 1;
alter table produto auto_increment = 1;
alter table usuario auto_increment = 1;


insert ignore into cozinha (id, nome) values (1, 'Tailandesa');
insert ignore into cozinha (id, nome) values (2, 'Indiana');
insert ignore into cozinha (id, nome) values (3, 'Argentina');
insert ignore into cozinha (id, nome) values (4, 'Brasileira');

-- Estados
INSERT IGNORE INTO estado (id, nome) VALUES (1, 'São Paulo');
INSERT IGNORE INTO estado (id, nome) VALUES (2, 'Rio de Janeiro');

-- Cidades
INSERT IGNORE INTO cidade (id, nome, estado_id) VALUES (1, 'Pirassununga', 1);
INSERT IGNORE INTO cidade (id, nome, estado_id) VALUES (2, 'Campinas', 1);
INSERT IGNORE INTO cidade (id, nome, estado_id) VALUES (3, 'Rio de Janeiro', 2);

-- Formas de Pagamento
INSERT IGNORE INTO forma_pagamento (id, descricao) VALUES (1, 'Dinheiro');
INSERT IGNORE INTO forma_pagamento (id, descricao) VALUES (2, 'Cartão de Crédito');
INSERT IGNORE INTO forma_pagamento (id, descricao) VALUES (3, 'Cartão de Débito');
INSERT IGNORE INTO forma_pagamento (id, descricao) VALUES (4, 'PIX');

-- Grupos
INSERT IGNORE INTO grupo (id, nome) VALUES (1, 'Administrador');
INSERT IGNORE INTO grupo (id, nome) VALUES (2, 'Gerente');
INSERT IGNORE INTO grupo (id, nome) VALUES (3, 'Cliente');

-- Permissões
INSERT IGNORE INTO permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_USUARIOS', 'Pode consultar os usuários');
INSERT IGNORE INTO permissao (id, nome, descricao) VALUES (2, 'EDITAR_USUARIOS', 'Pode editar os usuários');
INSERT IGNORE INTO permissao (id, nome, descricao) VALUES (3, 'GERENCIAR_RESTAURANTES', 'Pode gerenciar restaurantes');

-- Grupo x Permissão
INSERT IGNORE INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 1);
INSERT IGNORE INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 2);
INSERT IGNORE INTO grupo_permissao (grupo_id, permissao_id) VALUES (1, 3);
INSERT IGNORE INTO grupo_permissao (grupo_id, permissao_id) VALUES (2, 1);
INSERT IGNORE INTO grupo_permissao (grupo_id, permissao_id) VALUES (2, 3);

-- Usuário
INSERT IGNORE INTO usuario (id, nome, email, senha, data_cadastro) VALUES
(1, 'Augusto Iseppe', 'augusto@algafood.com', '123456', UTC_TIMESTAMP);

-- Usuário x Grupo
INSERT IGNORE INTO usuario_grupo (usuario_id, grupo_id) VALUES (1, 1);

-- Restaurante
INSERT IGNORE INTO restaurante (
    id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero,
    endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo
) VALUES (
    1, 'Churrascaria Gaúcha', 12.50, 3, '13630-000', 'Av. Brasil', '1000',
    'Sala 1', 'Centro', 1, UTC_TIMESTAMP, UTC_TIMESTAMP, true
);

INSERT IGNORE INTO restaurante (
    id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero,
    endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo
) VALUES (
    2, 'Restaurante Tailandês', 15.00, 1, '13630-001', 'Rua das Flores', '200',
    'Apto 101', 'Jardim', 1, UTC_TIMESTAMP, UTC_TIMESTAMP, true
);

-- Restaurante x Forma Pagamento
INSERT IGNORE INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT IGNORE INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 4);

-- Produtos
INSERT IGNORE INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES
(1, 'Picanha no Alho', 'Deliciosa picanha fatiada com alho frito', 59.90, TRUE, 1),
(2, 'Linguiça Artesanal', 'Linguiça de pernil com ervas finas', 34.90, TRUE, 1);
