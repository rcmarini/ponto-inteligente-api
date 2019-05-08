    create table `empresa` (
       `id` bigint(20) not null auto_increment,
        `cnpj` varchar(255) not null,
        `data_atualizacao` datetime not null,
        `data_criacao` datetime not null,
        `razao_social` varchar(255) not null,
        primary key (id)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

    create table `funcionario` (
       `id` bigint(20) not null auto_increment,
        `cpf` varchar(255) not null,
        `data_atualizacao` datetime not null,
        `data_criacao` datetime not null,
        `email` varchar(255) not null,
        `nome` varchar(255) not null,
        `perfil` varchar(255) not null,
        `qtd_horas_almoco` float DEFAULT NULL,
        `qtd_horas_trabalho_dia` float DEFAULT NULL,
        `senha` varchar(255) not null,
        `valor_hora` decimal(19,2) DEFAULT NULL,
        `empresa_id` bigint,
        primary key (id)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

    create table `lancamento` (
       `id` bigint(20) not null auto_increment,
        `data` datetime not null,
        `data_atualizacao` datetime not null,
        `data_criacao` datetime not null,
        `descricao` varchar(255) not null,
        `localizacao` varchar(255) not null,
        `tipo` varchar(255) not null,
        `funcionario_id` bigint,
        primary key (id)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

    alter table funcionario 
       add constraint fk_func_empresa 
       foreign key (empresa_id) 
       references empresa (id);
    
    alter table lancamento 
       add constraint fk_lanc_func
       foreign key (funcionario_id) 
       references funcionario (id);