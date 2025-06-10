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
delete from restaurante_usuario_responsavel;
delete from pedido;
delete from item_pedido;

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
INSERT IGNORE INTO grupo (id,nome) VALUES (4, 'Vendedor'), (5, 'Secretária'), (6, 'Cadastrador');


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
(1, 'Augusto Iseppe', 'augusto@algafood.com', '123456', utc_timestamp);

insert into usuario (id, nome, email, senha, data_cadastro) values
(2, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(3, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(4, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(5, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values
(6, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp);

-- Usuário x Grupo
INSERT IGNORE INTO usuario_grupo (usuario_id, grupo_id) VALUES (1, 1), (1, 2), (2, 2);

-- Restaurante
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true, true);

-- Restaurante x Forma Pagamento
INSERT IGNORE INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT IGNORE INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 4);
INSERT IGNORE INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 2);

-- Produtos
INSERT IGNORE INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES
(1, 'Picanha no Alho', 'Deliciosa picanha fatiada com alho frito', 59.90, TRUE, 1),
(2, 'Linguiça Artesanal', 'Linguiça de pernil com ervas finas', 34.90, TRUE, 1);
INSERT IGNORE INTO produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES
(3, 'Pad Thai', 'Tradicional macarrão tailandês com camarões', 45.00, TRUE, 2),
(4, 'Massaman Curry', 'Curry indiano com carne e batatas', 49.90, TRUE, 2);

INSERT INTO restaurante_usuario_responsavel (restaurante_id, usuario_id) VALUES (1, 5), (3, 5);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep,
    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
    status, data_criacao, subtotal, taxa_frete, valor_total)
values (1, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 2, 2, 110, 220, 'Menos picante, por favor');


