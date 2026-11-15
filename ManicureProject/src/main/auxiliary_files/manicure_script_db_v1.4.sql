  -- Desabilita temporariamente checagem de chaves para limpeza
CREATE DATABASE IF NOT EXISTS `manicure_db` DEFAULT CHARACTER SET utf8mb4;



USE `manicure_db`;
SET FOREIGN_KEY_CHECKS = 0;

-- Remove tabelas antigas para evitar conflito de versões anteriores
DROP TABLE IF EXISTS `ITEM_AGENDAMENTO`;
DROP TABLE IF EXISTS `SERVICO_INSUMOS`;
DROP TABLE IF EXISTS `PAGAMENTO`;
DROP TABLE IF EXISTS `AGENDAMENTO`;
DROP TABLE IF EXISTS `SERVICO`;
DROP TABLE IF EXISTS `INSUMOS`;
DROP TABLE IF EXISTS `PROFISSIONAL`;
DROP TABLE IF EXISTS `USUARIO`;

SET FOREIGN_KEY_CHECKS = 1;

-- =====================================================
-- 1. TABELA USUARIO
-- =====================================================
CREATE TABLE `USUARIO` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `nome` VARCHAR(100) NOT NULL,
  `endereco` VARCHAR(255) NULL,
  `data_nascimento` DATE NULL,
  `senha` VARCHAR(255) NOT NULL,
  `telefone` VARCHAR(20) NULL,
  `status` TINYINT DEFAULT 1,
  `tipo_perfil` ENUM('CLIENTE', 'PROFISSIONAL', 'ADMIN') NOT NULL DEFAULT 'CLIENTE',
  PRIMARY KEY (`idUsuario`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- =====================================================
-- 2. TABELA PROFISSIONAL
-- =====================================================
CREATE TABLE `PROFISSIONAL` (
  `idProfissional` INT NOT NULL AUTO_INCREMENT,
  `USUARIO_idUsuario` INT NOT NULL,
  `comissao_percentual` DECIMAL(5,2) NULL DEFAULT 0.00,
  `especialidades` VARCHAR(150) NULL,
  PRIMARY KEY (`idProfissional`),
  CONSTRAINT `fk_PROFISSIONAL_USUARIO`
    FOREIGN KEY (`USUARIO_idUsuario`)
    REFERENCES `USUARIO` (`idUsuario`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- =====================================================
-- 3. TABELA SERVICO
-- =====================================================
CREATE TABLE `SERVICO` (
  `idServico` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` TEXT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  `duracao` INT NOT NULL COMMENT 'Duração estimada em minutos',
  PRIMARY KEY (`idServico`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- =====================================================
-- 4. TABELA AGENDAMENTO
-- =====================================================
CREATE TABLE `AGENDAMENTO` (
  `idAgendamento` INT NOT NULL AUTO_INCREMENT,
  `USUARIO_idUsuario` INT NOT NULL COMMENT 'Cliente que solicitou',
  `PROFISSIONAL_idProfissional` INT NOT NULL COMMENT 'Manicure responsável',
  `data_hora_inicio` DATETIME NOT NULL,
  `data_hora_fim` DATETIME NOT NULL,
  `preferencias` TEXT NULL,
  `valor_total` DECIMAL(10,2) NOT NULL,
  `valor_sinal` DECIMAL(10,2) DEFAULT 0.00,
  `status` ENUM('PENDENTE', 'CONFIRMADO', 'CONCLUIDO', 'CANCELADO', 'NO_SHOW') NOT NULL DEFAULT 'PENDENTE',
  PRIMARY KEY (`idAgendamento`),
  CONSTRAINT `fk_AGENDAMENTO_USUARIO`
    FOREIGN KEY (`USUARIO_idUsuario`)
    REFERENCES `USUARIO` (`idUsuario`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_AGENDAMENTO_PROFISSIONAL`
    FOREIGN KEY (`PROFISSIONAL_idProfissional`)
    REFERENCES `PROFISSIONAL` (`idProfissional`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- =====================================================
-- 5. TABELA ITEM_AGENDAMENTO (Múltiplos Serviços por Agendamento)
-- =====================================================
CREATE TABLE `ITEM_AGENDAMENTO` (
  `id_item` INT NOT NULL AUTO_INCREMENT,
  `Agendamento_idAgendamento` INT NOT NULL,
  `SERVICO_idServico` INT NOT NULL,
  `preco_aplicado` DECIMAL(10,2) NOT NULL,
  `duracao_minutos` INT NOT NULL,
  PRIMARY KEY (`id_item`),
  CONSTRAINT `fk_ITEM_AGENDAMENTO_AGENDAMENTO`
    FOREIGN KEY (`Agendamento_idAgendamento`)
    REFERENCES `AGENDAMENTO` (`idAgendamento`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ITEM_AGENDAMENTO_SERVICO`
    FOREIGN KEY (`SERVICO_idServico`)
    REFERENCES `SERVICO` (`idServico`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- =====================================================
-- 6. TABELA PAGAMENTO
-- =====================================================
CREATE TABLE `PAGAMENTO` (
  `idPagamento` INT NOT NULL AUTO_INCREMENT,
  `Agendamento_idAgendamento` INT NOT NULL,
  `metodo_de_pagamento` ENUM('PIX', 'DINHEIRO', 'CARTAO_CREDITO', 'CARTAO_DEBITO') NOT NULL,
  `valor_pago` DECIMAL(10,2) NOT NULL,
  `data_pagamento` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` ENUM('Pendente', 'Pago', 'Cancelado') NOT NULL DEFAULT 'Pendente',
  PRIMARY KEY (`idPagamento`),
  CONSTRAINT `fk_PAGAMENTO_AGENDAMENTO`
    FOREIGN KEY (`Agendamento_idAgendamento`)
    REFERENCES `AGENDAMENTO` (`idAgendamento`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- =====================================================
-- 7. TABELA INSUMOS
-- =====================================================
CREATE TABLE `INSUMOS` (
  `idInsumos` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` TEXT NULL,
  `estoque` INT NOT NULL DEFAULT 0,
  `unidade_medida` VARCHAR(20) NOT NULL DEFAULT 'unidade',
  PRIMARY KEY (`idInsumos`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- =====================================================
-- 8. TABELA SERVICO_INSUMOS
-- =====================================================
CREATE TABLE `SERVICO_INSUMOS` (
  `SERVICO_idServico` INT NOT NULL,
  `INSUMOS_idInsumos` INT NOT NULL,
  `quantidade_utilizada` DECIMAL(10,2) NOT NULL DEFAULT 1.00,
  PRIMARY KEY (`SERVICO_idServico`, `INSUMOS_idInsumos`),
  CONSTRAINT `fk_SERVICO_INSUMOS_SERVICO`
    FOREIGN KEY (`SERVICO_idServico`)
    REFERENCES `SERVICO` (`idServico`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_SERVICO_INSUMOS_INSUMOS`
    FOREIGN KEY (`INSUMOS_idInsumos`)
    REFERENCES `INSUMOS` (`idInsumos`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;