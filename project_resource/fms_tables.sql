CREATE SCHEMA IF NOT EXISTS `file_management_sys` DEFAULT CHARACTER SET utf8 ;
USE `file_management_sys` ;

-- -----------------------------------------------------
-- Table `file_management_sys`.`fms_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `file_management_sys`.`fms_user` (
  `email` VARCHAR(45) NOT NULL COMMENT '主键，邮箱',
  `password` VARCHAR(32) NOT NULL COMMENT '密码',
  `gender` VARCHAR(10) NULL DEFAULT '\"male\"' COMMENT '性别',
  `domain` VARCHAR(40) NOT NULL COMMENT '涉及领域，以逗号分隔',
  `create_time` DATETIME(2) NOT NULL COMMENT '注册日期',
  `update_time` DATETIME(2) NOT NULL COMMENT '上一次更新个人信息日期',
  `membership` ENUM("Basic", "PREMIUM", "VIP") NOT NULL DEFAULT 'Basic' COMMENT '会员级别',
  `upload_counter` INT NULL COMMENT '上传文件次数',
  `download_counter` INT NULL COMMENT '下载文件次数',
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`, `username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `file_management_sys`.`fms_file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `file_management_sys`.`fms_file` (
  `fileID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(64) NOT NULL COMMENT '文件名由文件所属类别和唯一ID组成',
  `User_email` VARCHAR(45) NOT NULL,
  `User_username` VARCHAR(45) NOT NULL,
  `visited_times` INT NULL COMMENT '被访问次数',
  `create_time` DATETIME(2) NOT NULL,
  `size` SMALLINT(3) NULL COMMENT '单位：MB',
  PRIMARY KEY (`fileID`, `file_name`),
  CONSTRAINT `fk_File_User`
    FOREIGN KEY (`User_email` , `User_username`)
    REFERENCES `file_management_sys`.`fms_user` (`email` , `username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `file_management_sys`.`fms_download_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `file_management_sys`.`fms_download_history` (
  `fms_user_email` VARCHAR(45) NOT NULL,
  `fms_user_username` VARCHAR(45) NOT NULL,
  `histryID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fms_file_fileID` INT UNSIGNED NOT NULL,
  `fms_file_file_name` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`histryID`),
  CONSTRAINT `fk_fms_download_history_fms_user1`
    FOREIGN KEY (`fms_user_email` , `fms_user_username`)
    REFERENCES `file_management_sys`.`fms_user` (`email` , `username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fms_download_history_fms_file1`
    FOREIGN KEY (`fms_file_fileID` , `fms_file_file_name`)
    REFERENCES `file_management_sys`.`fms_file` (`fileID` , `file_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
