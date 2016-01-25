-- MySQL Script generated by MySQL Workbench
-- Tue Jan 19 15:22:25 2016
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Customer` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Email_Address` VARCHAR(45) NOT NULL,
  `Squad` VARCHAR(45) NOT NULL,
  `Name_On_Back` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Order` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Order1` VARCHAR(45) NOT NULL,
  `Order1Size` VARCHAR(45) NOT NULL,
  `Order1Number` VARCHAR(45) NOT NULL,
  `Order2` VARCHAR(45) NOT NULL,
  `Order2Size` VARCHAR(45) NOT NULL,
  `Order2Number` VARCHAR(45) NOT NULL,
  `Order3` VARCHAR(45) NOT NULL,
  `Order3Size` VARCHAR(45) NOT NULL,
  `Order3Number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Linking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Linking` (
  `idLinking` INT NOT NULL AUTO_INCREMENT,
  `Customer_ID` INT NOT NULL,
  `Order_ID` INT NOT NULL,
  PRIMARY KEY (`idLinking`, `Customer_ID`, `Order_ID`),
  INDEX `fk_Linking_Customer_idx` (`Customer_ID` ASC),
  INDEX `fk_Linking_Order1_idx` (`Order_ID` ASC),
  CONSTRAINT `fk_Linking_Customer`
    FOREIGN KEY (`Customer_ID`)
    REFERENCES `mydb`.`Customer` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Linking_Order1`
    FOREIGN KEY (`Order_ID`)
    REFERENCES `mydb`.`Order` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Items` (
  `idItems` INT NOT NULL AUTO_INCREMENT,
  `Item` VARCHAR(45) NULL,
  PRIMARY KEY (`idItems`),
  UNIQUE INDEX `idItems_UNIQUE` (`idItems` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;