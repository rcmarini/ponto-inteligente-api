INSERT INTO `empresa` (`id`, `cnpj`, `data_atualizacao`, `data_criacao`, `razao_social`) 
VALUES (NULL, '16982236000100', '2019-05-15 00:33:00', '2019-05-15 00:33:00', 'The Point IT');

INSERT INTO `funcionario` (`id`, `cpf`, `data_atualizacao`, `data_criacao`, `email`, `nome`,
	`perfil`, `qtd_horas_trabalho_dia`, `senha`, `valor_hora`, `empresa_id`) 
VALUES (NULL, '46751531001', '2019-05-15 00:33:00', '2019-05-15 00:33:00', 'adm.ponto@email.com', 'Ponto Administrador', 
	'ROLE_ADMIN', NULL, '$2a$10$fS.KDE0aIOXsRkGZJsw36OEICFKCvdPLlsfEpLbwAV55kRWPId0Gi', NULL, (SELECT `id` FROM `empresa` WHERE `cnpj` = '16982236000100'));
