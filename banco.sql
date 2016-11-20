-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema gestao_nao_conformidade
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gestao_nao_conformidade
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestao_nao_conformidade` DEFAULT CHARACTER SET utf8 ;
USE `gestao_nao_conformidade` ;

-- -----------------------------------------------------
-- Table `gestao_nao_conformidade`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestao_nao_conformidade`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestao_nao_conformidade`.`nao_conformidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestao_nao_conformidade`.`nao_conformidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL,
  `descricao` TINYTEXT NULL,
  `prazo` DATE NULL,
  `prioridade` INT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_nao_conformidade_usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_nao_conformidade_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `gestao_nao_conformidade`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
