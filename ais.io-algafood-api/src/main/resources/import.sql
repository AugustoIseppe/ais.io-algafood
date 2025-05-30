insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Thai Gourmet 1', 10.00, 1, utc_timestamp, utc_timestamp, 3, '38400-000', 'Rua das Flores', '123', 'Centro');
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery 2', 15.00, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Tuk Tuk Comida 3', 20.00, 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Java Steakhouse', 25.00, 3, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Picanha do Chef', 30.00, 4, utc_timestamp, utc_timestamp, 1, '38400-000', 'Avenida Brasil', '456', 'Jardim');
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Bar do Chico', 20.00, 4, utc_timestamp, utc_timestamp, 1, '38400-000', 'Avenida Brasil', '456', 'Jardim');

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (4, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (5, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (5, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (6, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (6, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Pad Thai', 'Macarrão tailandês com camarões', 45.00, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Massala de Frango', 'Frango com especiarias indianas', 50.00, true, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife de Chorizo', 'Corte argentino de carne', 60.00, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Picanha na Brasa', 'Picanha grelhada com tempero especial', 70.00, true, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Feijoada Completa', 'Feijoada tradicional brasileira', 55.00, true, 6);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Curry Verde', 'Curry tailandês com frango', 48.00, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Curry de Lentilhas', 'Lentilhas com especiarias indianas', 42.00, true, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Empanadas de Carne', 'Empanadas argentinas recheadas com carne', 38.00, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Arroz à Grega', 'Arroz com legumes e especiarias', 30.00, true, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada Tropical', 'Salada fresca com frutas tropicais', 25.00, true, 6);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sopa Tom Yum', 'Sopa tailandesa picante com camarões', 40.00, true, 1);

