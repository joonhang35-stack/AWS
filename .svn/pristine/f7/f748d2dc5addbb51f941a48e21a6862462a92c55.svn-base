# 1.0.1
# Create db schemas
# By HS
#
# Drop tables
#
DROP TABLE IF EXISTS `bterpdb`.`invoice_eo_link`;
DROP TABLE IF EXISTS `bterpdb`.`ex_order_bill_pmnt`;
DROP TABLE IF EXISTS `bterpdb`.`ex_order_bill`;
DROP TABLE IF EXISTS `bterpdb`.`ex_order_hotel`;
DROP TABLE IF EXISTS `bterpdb`.`ex_order_airline`;
DROP TABLE IF EXISTS `bterpdb`.`ex_order_pax`;
DROP TABLE IF EXISTS `bterpdb`.`ex_order_item`;
DROP TABLE IF EXISTS `bterpdb`.`ex_order`;
DROP TABLE IF EXISTS `bterpdb`.`invoice_pmnt`;
DROP TABLE IF EXISTS `bterpdb`.`invoice_pax`;
DROP TABLE IF EXISTS `bterpdb`.`invoice_item`;
DROP TABLE IF EXISTS `bterpdb`.`invoice`;
DROP TABLE IF EXISTS `bterpdb`.`tour_booking_pax`;
DROP TABLE IF EXISTS `bterpdb`.`tour_booking_item`;
DROP TABLE IF EXISTS `bterpdb`.`tour_booking`;
DROP TABLE IF EXISTS `bterpdb`.`tour_hotel`;
DROP TABLE IF EXISTS `bterpdb`.`tour_itinery`;
DROP TABLE IF EXISTS `bterpdb`.`tour_dep`;
DROP TABLE IF EXISTS `bterpdb`.`tour_pkg`;
DROP TABLE IF EXISTS `bterpdb`.`tour_theme`;
DROP TABLE IF EXISTS `bterpdb`.`tour_cat`;
DROP TABLE IF EXISTS `bterpdb`.`tour_operator`;
DROP TABLE IF EXISTS `bterpdb`.`cash_book`;
DROP TABLE IF EXISTS `bterpdb`.`bank_contact`;
DROP TABLE IF EXISTS `bterpdb`.`bank_address`;
DROP TABLE IF EXISTS `bterpdb`.`bank`;
DROP TABLE IF EXISTS `bterpdb`.`account_trans`;
DROP TABLE IF EXISTS `bterpdb`.`account_bal`;
DROP TABLE IF EXISTS `bterpdb`.`account`;
DROP TABLE IF EXISTS `bterpdb`.`account_sub_cat`;
DROP TABLE IF EXISTS `bterpdb`.`account_cat`;
DROP TABLE IF EXISTS `bterpdb`.`hotel_charges`;
DROP TABLE IF EXISTS `bterpdb`.`hotel_item_charge`;
DROP TABLE IF EXISTS `bterpdb`.`hotel_item`;
DROP TABLE IF EXISTS `bterpdb`.`hotel_room`;
DROP TABLE IF EXISTS `bterpdb`.`hotel_reservation`;
DROP TABLE IF EXISTS `bterpdb`.`hotel_contact`;
DROP TABLE IF EXISTS `bterpdb`.`hotel_address`;
DROP TABLE IF EXISTS `bterpdb`.`hotel`;
DROP TABLE IF EXISTS `bterpdb`.`airline_schedule_item`;
DROP TABLE IF EXISTS `bterpdb`.`airline_schedule`;
DROP TABLE IF EXISTS `bterpdb`.`airline_remarks`;
DROP TABLE IF EXISTS `bterpdb`.`airline_charges`;
DROP TABLE IF EXISTS `bterpdb`.`airline_item_charge`;
DROP TABLE IF EXISTS `bterpdb`.`airline_item`;
DROP TABLE IF EXISTS `bterpdb`.`airline`;
DROP TABLE IF EXISTS `bterpdb`.`supplier`;
DROP TABLE IF EXISTS `bterpdb`.`customer`;
DROP TABLE IF EXISTS `bterpdb`.`corporate_contact`;
DROP TABLE IF EXISTS `bterpdb`.`corporate_address`;
DROP TABLE IF EXISTS `bterpdb`.`corporate`;
DROP TABLE IF EXISTS `bterpdb`.`person_complication`;
DROP TABLE IF EXISTS `bterpdb`.`person_meal`;
DROP TABLE IF EXISTS `bterpdb`.`person_lang`;
DROP TABLE IF EXISTS `bterpdb`.`person_identity_detail`;
DROP TABLE IF EXISTS `bterpdb`.`person_identity`;
DROP TABLE IF EXISTS `bterpdb`.`person_contact`;
DROP TABLE IF EXISTS `bterpdb`.`person_address`;
DROP TABLE IF EXISTS `bterpdb`.`person`;
DROP TABLE IF EXISTS `bterpdb`.`employee_contact`;
DROP TABLE IF EXISTS `bterpdb`.`employee_address`;
DROP TABLE IF EXISTS `bterpdb`.`company_contact`;
DROP TABLE IF EXISTS `bterpdb`.`company_address`;
DROP TABLE IF EXISTS `bterpdb`.`employee`;
DROP TABLE IF EXISTS `bterpdb`.`company`;
DROP TABLE IF EXISTS `bterpdb`.`country`;
DROP TABLE IF EXISTS `bterpdb`.`sub_region`;
DROP TABLE IF EXISTS `bterpdb`.`region`;
DROP TABLE IF EXISTS `bterpdb`.`lookup_item`;
DROP TABLE IF EXISTS `bterpdb`.`lookup_cat`;
DROP TABLE IF EXISTS `bterpdb`.`sys_num_conf`;

#
# Table - Maintenance
#
CREATE  TABLE `bterpdb`.`region` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(10) NOT NULL COMMENT 'region code' ,
  `name` VARCHAR(255) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Region maintenance';
  
CREATE  TABLE `bterpdb`.`sub_region` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_region` BIGINT NOT NULL ,
  `code` VARCHAR(10) NOT NULL COMMENT'sub region code' ,
  `name` VARCHAR(255) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code`) ,
  INDEX `fk_sub_region_id_region` (`id_region` ASC) ,
  CONSTRAINT `fk_sub_region_id_region`
    FOREIGN KEY (`id_region` )
    REFERENCES `bterpdb`.`region` (`id` )
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Sub region maintenance';
  
CREATE TABLE `bterpdb`.`country` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_sub_region` BIGINT NOT NULL ,
  `code` VARCHAR(10) NOT NULL COMMENT 'country code' ,
  `name` VARCHAR(255) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code`) ,
  INDEX `fk_country_id_sub_region` (`id_sub_region` ASC) ,
  CONSTRAINT `fk_country_id_sub_region`
    FOREIGN KEY (`id_sub_region` )
    REFERENCES `bterpdb`.`sub_region` (`id` )
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Country maintenance';

DROP TABLE IF EXISTS `bterpdb`.`inv_eo_item`;
CREATE TABLE `bterpdb`.`inv_eo_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `description` varchar(255) NOT NULL,
  `amount` float(10,2) NOT NULL DEFAULT '0.00',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Invoice/Exchange Order item maintenance';

CREATE TABLE `bterpdb`.`lookup_cat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL COMMENT 'unique code for lookup category',
  `description` varchar(255) NOT NULL COMMENT 'lookup category name',
  `remarks` varchar(255) DEFAULT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Lookup categories which preset some configuration';

CREATE TABLE `bterpdb`.`lookup_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lookup_cat_cd` varchar(10) NOT NULL COMMENT 'inherit to lookup_cat code',
  `code` varchar(10) NOT NULL COMMENT 'lookup item code',
  `description` varchar(255) NOT NULL COMMENT 'description name',
  `remarks` varchar(255) DEFAULT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lookup_item_lookup_cat_cd` (`lookup_cat_cd`),
  CONSTRAINT `fk_lookup_item_lookup_cat_cd` FOREIGN KEY (`lookup_cat_cd`) REFERENCES `bterpdb`.`lookup_cat` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE  TABLE `bterpdb`.`sys_num_conf` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(10) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `next_no` BIGINT NOT NULL DEFAULT 0 COMMENT 'next running number' ,
  `prefix_id` VARCHAR(10) NULL COMMENT 'prefix value (e.g. Bank Payment is BP) which set to in front of document number' ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT = 'System number configuration';

DROP TABLE IF EXISTS `bterpdb`.`tmp_msg`;
CREATE  TABLE `bterpdb`.`tmp_msg` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(10) NOT NULL ,
  `description` TEXT NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  `version` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Template message (e.g. terms & conditions)';

#
# Table - Company
#
CREATE  TABLE `bterpdb`.`company` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `reg_no` VARCHAR(20) NULL ,
  `email` VARCHAR(255) NULL ,
  `slogan` VARCHAR(255) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'User company info';

CREATE  TABLE `bterpdb`.`company_address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_company` BIGINT NOT NULL ,
  `id_country` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**OFF/BILL' ,
  `addr_1` VARCHAR(255) NOT NULL ,
  `addr_2` VARCHAR(255) NULL ,
  `addr_3` VARCHAR(255) NULL ,
  `city` VARCHAR(255) NULL ,
  `state` VARCHAR(255) NULL ,
  `postcode` VARCHAR(10) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_company_address_id_company` (`id_company`) ,
  INDEX `fk_company_address_id_country` (`id_country`) ,
  CONSTRAINT `fk_company_address_id_company`
    FOREIGN KEY (`id_company` )
    REFERENCES `bterpdb`.`company` (`id` ),
  CONSTRAINT `fk_company_address_id_country`
    FOREIGN KEY (`id_country` )
    REFERENCES `bterpdb`.`country` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple addresses for specified company';

CREATE  TABLE `bterpdb`.`company_contact` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_company_addr` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**TEL/FAX' ,
  `number` VARCHAR(20) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_company_contact_id_company_addr` (`id_company_addr`) ,
  CONSTRAINT `fk_company_contact_id_company_addr`
    FOREIGN KEY (`id_company_addr` )
    REFERENCES `bterpdb`.`company_address` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple contacts for specified company';

CREATE  TABLE `bterpdb`.`employee` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_company` BIGINT NOT NULL ,
  `u_sec_user` VARCHAR(50) NOT NULL ,
  `department` VARCHAR(100) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_employee_id_company` (`id_company`) ,
  INDEX `fk_employee_u_sec_user` (`u_sec_user`) ,
  CONSTRAINT `fk_employee_id_company`
    FOREIGN KEY (`id_company` )
    REFERENCES `bterpdb`.`company` (`id` ),
  CONSTRAINT `fk_employee_u_sec_user`
    FOREIGN KEY (`u_sec_user`)
    REFERENCES `bterpdb`.`SEC_USER` (`uuid`))
COMMENT = 'Company employees maintenance';

CREATE  TABLE `bterpdb`.`employee_address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_employee` BIGINT NOT NULL ,
  `id_country` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**OFF/BILL' ,
  `addr_1` VARCHAR(255) NOT NULL ,
  `addr_2` VARCHAR(255) NULL ,
  `addr_3` VARCHAR(255) NULL ,
  `city` VARCHAR(255) NULL ,
  `state` VARCHAR(255) NULL ,
  `postcode` VARCHAR(10) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_employee_address_id_employee` (`id_employee`) ,
  INDEX `fk_employee_address_id_country` (`id_country`) ,
  CONSTRAINT `fk_employee_address_id_employee`
    FOREIGN KEY (`id_employee` )
    REFERENCES `bterpdb`.`employee` (`id` ),
  CONSTRAINT `fk_employee_address_id_country`
    FOREIGN KEY (`id_country` )
    REFERENCES `bterpdb`.`country` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple addresses for specified employee';

CREATE  TABLE `bterpdb`.`employee_contact` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_employee_addr` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**TEL/FAX' ,
  `number` VARCHAR(20) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_employee_contact_id_employee_addr` (`id_employee_addr`) ,
  CONSTRAINT `fk_employee_contact_id_employee_addr`
    FOREIGN KEY (`id_employee_addr` )
    REFERENCES `bterpdb`.`employee_address` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple contacts for specified employee';

#
# Table - Person
#
CREATE  TABLE `bterpdb`.`person` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**P = Personal / C = Corporate' ,
  `salutaion_cd` VARCHAR(10) NOT NULL COMMENT '**MR/MRS/MS/etc.' ,
  `first_name` VARCHAR(50) NOT NULL ,
  `middle_name` VARCHAR(50) NULL ,
  `last_name` VARCHAR(50) NOT NULL ,
  `nick_name` VARCHAR(50) NULL ,
  `sex_cd` VARCHAR(10) NOT NULL COMMENT '**M = Male / F = Female' ,
  `marriage_cd` VARCHAR(10) NULL COMMENT '**MAR = Married / SGL = Single / etc.' ,
  `class_cd` VARCHAR(10) NOT NULL COMMENT '**VIP / BL = Black Listed / etc.' ,
  `dt_birth` DATETIME NOT NULL ,
  `age` INT NOT NULL DEFAULT 0 ,
  `is_ins_bought` TINYINT NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `email` VARCHAR(255) NULL ,
  `remarks` TEXT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) )
COMMENT = 'Person info';

CREATE  TABLE `bterpdb`.`person_address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_person` BIGINT NOT NULL ,
  `id_country` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**OFF/BILL' ,
  `addr_1` VARCHAR(255) NOT NULL ,
  `addr_2` VARCHAR(255) NULL ,
  `addr_3` VARCHAR(255) NULL ,
  `city` VARCHAR(255) NULL ,
  `state` VARCHAR(255) NULL ,
  `postcode` VARCHAR(10) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_person_address_id_person` (`id_person`) ,
  INDEX `fk_person_address_id_country` (`id_country`) ,
  CONSTRAINT `fk_person_address_id_person`
    FOREIGN KEY (`id_person` )
    REFERENCES `bterpdb`.`person` (`id` ),
  CONSTRAINT `fk_person_address_id_country`
    FOREIGN KEY (`id_country` )
    REFERENCES `bterpdb`.`country` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple addresses for specified person';

CREATE  TABLE `bterpdb`.`person_contact` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_person_addr` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**TEL/FAX' ,
  `number` VARCHAR(20) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_person_contact_id_person_addr` (`id_person_addr`) ,
  CONSTRAINT `fk_person_contact_id_person_addr`
    FOREIGN KEY (`id_person_addr` )
    REFERENCES `bterpdb`.`person_address` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple contacts for specified person';

CREATE  TABLE `bterpdb`.`person_identity` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_person` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**NRIC/VISA/PASS' ,
  `number` VARCHAR(50) NOT NULL ,
  `dt_issued` DATETIME NOT NULL ,
  `dt_expired` DATETIME NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_person_identity_id_person` (`id_person`) ,
  CONSTRAINT `fk_person_identity_id_person`
    FOREIGN KEY (`id_person` )
    REFERENCES `bterpdb`.`person` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple identities for specified person (e.g. NRIC, Visa, Passport and etc.)';

CREATE  TABLE `bterpdb`.`person_identity_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_person_identity` BIGINT NOT NULL ,
  `id_country` BIGINT NOT NULL COMMENT 'issued country' ,
  `type_cd` VARCHAR(10) NULL COMMENT '**BUS = Business / SOC = Social /etc.' ,
  `entry_cd` VARCHAR(10) NULL COMMENT '**SGL = Single / MUL = Multiple /etc.' ,
  `scanned_path` VARCHAR(255) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_person_identity_detail_id_person_identity` (`id_person_identity`) ,
  INDEX `fk_person_identity_detail_id_counrty` (`id_country`) ,
  CONSTRAINT `fk_person_identity_detail_id_person_identity`
    FOREIGN KEY (`id_person_identity` )
    REFERENCES `bterpdb`.`person_identity` (`id` ),
  CONSTRAINT `fk_person_identity_detail_id_counrty`
    FOREIGN KEY (`id_country` )
    REFERENCES `bterpdb`.`country` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Details for specified identity';

CREATE  TABLE `bterpdb`.`person_lang` (
  `id_person` BIGINT NOT NULL ,
  `lang_cd` VARCHAR(10) NOT NULL COMMENT '**EN/ZH' ,
  INDEX `fk_person_lang_id_person` (`id_person`) ,
  CONSTRAINT `fk_person_lang_id_person`
    FOREIGN KEY (`id_person` )
    REFERENCES `bterpdb`.`person` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple languages for specified person';

CREATE  TABLE `bterpdb`.`person_meal` (
  `id_person` BIGINT NOT NULL ,
  `meal_cd` VARCHAR(10) NOT NULL COMMENT '**HALAL/BEEF/OTHER/etc.' ,
  `remarks` TEXT NULL ,
  INDEX `fk_person_meal_id_person` (`id_person`) ,
  CONSTRAINT `fk_person_meal_id_person`
    FOREIGN KEY (`id_person` )
    REFERENCES `bterpdb`.`person` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple meal preferences for specified person';

CREATE  TABLE `bterpdb`.`person_complication` (
  `id_person` BIGINT NOT NULL ,
  `complication_cd` VARCHAR(10) NOT NULL COMMENT '**DISABLE / HA = Heart Attack / etc.' ,
  `remarks` TEXT NULL ,
  INDEX `fk_person_complication_id_person` (`id_person`) ,
  CONSTRAINT `fk_person_complication_id_person`
    FOREIGN KEY (`id_person` )
    REFERENCES `bterpdb`.`person` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple illnesses for specified person';

CREATE  TABLE `bterpdb`.`corporate` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_person` BIGINT NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `reg_no` VARCHAR(20) NULL ,
  `description` VARCHAR(255) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_corporate_id_person` (`id_person`) ,
  CONSTRAINT `fk_corporate_id_person`
    FOREIGN KEY (`id_person` )
    REFERENCES `bterpdb`.`person` (`id` ))
COMMENT = 'Corporate info which attach with person';

CREATE  TABLE `bterpdb`.`corporate_address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_corporate` BIGINT NOT NULL ,
  `id_country` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**OFF/BILL' ,
  `addr_1` VARCHAR(255) NOT NULL ,
  `addr_2` VARCHAR(255) NULL ,
  `addr_3` VARCHAR(255) NULL ,
  `city` VARCHAR(255) NULL ,
  `state` VARCHAR(255) NULL ,
  `postcode` VARCHAR(10) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_corporate_address_id_corporate` (`id_corporate`) ,
  INDEX `fk_corporate_address_id_country` (`id_country`) ,
  CONSTRAINT `fk_corporate_address_id_corporate`
    FOREIGN KEY (`id_corporate` )
    REFERENCES `bterpdb`.`corporate` (`id` ),
  CONSTRAINT `fk_corporate_address_id_country`
    FOREIGN KEY (`id_country` )
    REFERENCES `bterpdb`.`country` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple addresses for specified corporate';

CREATE  TABLE `bterpdb`.`corporate_contact` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_corporate_addr` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**TEL/FAX' ,
  `number` VARCHAR(20) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_corporate_contact_id_corporate_addr` (`id_corporate_addr`) ,
  CONSTRAINT `fk_corporate_contact_id_corporate_addr`
    FOREIGN KEY (`id_corporate_addr` )
    REFERENCES `bterpdb`.`corporate_address` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple contacts for specified corporate';

CREATE  TABLE `bterpdb`.`customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_person` BIGINT NOT NULL ,
  `code` VARCHAR(20) NOT NULL ,
  `status_cd` VARCHAR(10) NOT NULL COMMENT '**A = Active / I = Inactive' ,
  `remarks` TEXT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_customer_id_person` (`id_person`) ,
  CONSTRAINT `fk_customer_id_person`
    FOREIGN KEY (`id_person` )
    REFERENCES `bterpdb`.`person` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Customer (debtor) info which attach with person';

CREATE  TABLE `bterpdb`.`supplier` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_person` BIGINT NOT NULL ,
  `code` VARCHAR(20) NOT NULL ,
  `status_cd` VARCHAR(10) NOT NULL COMMENT '**A = Active / I = Inactive' ,
  `type_cd` VARCHAR(10) NULL COMMENT '**SUNDRY/TRADE' ,
  `pmnt_type_cd` VARCHAR(10) NULL COMMENT '**CHQ/CASH/etc.' ,
  `acct_ref` VARCHAR(255) NULL COMMENT 'e.g. bank account reference' ,
  `credit_limit` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `remarks` TEXT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_supplier_id_person` (`id_person`) ,
  CONSTRAINT `fk_supplier_id_person`
    FOREIGN KEY (`id_person` )
    REFERENCES `bterpdb`.`person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Supplier (creditor) info which attach with person';

#
# Table - Airline
#
CREATE  TABLE `bterpdb`.`airline` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  `tkt_validity` SMALLINT(3) NOT NULL DEFAULT 0 COMMENT 'number of day for ticket validity' ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Airline info';

CREATE  TABLE `bterpdb`.`airline_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `remarks` VARCHAR(255) NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**ADT/CHD/BOTH/NA' ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Airline item configuration';

CREATE  TABLE `bterpdb`.`airline_item_charge` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_airline_item` BIGINT NOT NULL ,
  `amount` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_airline_item_charge_id_airline_item` (`id_airline_item`) ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  CONSTRAINT `fk_airline_item_charge_id_airline_item`
    FOREIGN KEY (`id_airline_item` )
    REFERENCES `bterpdb`.`airline_item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Airline item charges';

CREATE  TABLE `bterpdb`.`airline_charges` (
  `id_airline` BIGINT NOT NULL ,
  `id_airline_item_charge` BIGINT NOT NULL ,
  INDEX `fk_airline_charges_id_airline` (`id_airline` ASC) ,
  INDEX `fk_airline_charges_id_airline_item_charge` (`id_airline_item_charge`) ,
  CONSTRAINT `fk_airline_charges_id_airline`
    FOREIGN KEY (`id_airline` )
    REFERENCES `bterpdb`.`airline` (`id` ),
  CONSTRAINT `fk_airline_charges_id_airline_item_charge`
    FOREIGN KEY (`id_airline_item_charge` )
    REFERENCES `bterpdb`.`airline_item_charge` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Charges of airline';

CREATE  TABLE `bterpdb`.`airline_remarks` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_airline` BIGINT NOT NULL ,
  `remarks` VARCHAR(255) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_airline_remarks_id_airline` (`id_airline`) ,
  CONSTRAINT `fk_airline_remarks_id_airline`
    FOREIGN KEY (`id_airline` )
    REFERENCES `bterpdb`.`airline` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple remarks purpose for specified airline';

CREATE  TABLE `bterpdb`.`airline_schedule` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_airline` BIGINT NOT NULL ,
  `description` VARCHAR(100) NOT NULL COMMENT 'schedule name' ,
  `remarks` VARCHAR(255) NULL ,
  `dt_schedule` DATETIME NOT NULL COMMENT 'schedule date' ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_airline_schedule_id_airline` (`id_airline`) ,
  CONSTRAINT `fk_airline_schedule_id_airline`
    FOREIGN KEY (`id_airline` )
    REFERENCES `bterpdb`.`airline` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Schedule theme for specified airline (e.g. schedule 1, schedule 2 and etc.)';

CREATE  TABLE `bterpdb`.`airline_schedule_item` (
  `id` BIGINT NOT NULL ,
  `id_airline_schedule` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**DEP/TRAN/DOM/RTN' ,
  `airport_cd` VARCHAR(10) NOT NULL COMMENT '**KUL/TWN/etc.' ,
  `flight_cd` VARCHAR(20) NOT NULL ,
  `etd` VARCHAR(4) NOT NULL COMMENT 'Estimated time departure - HHmm' ,
  `eta` VARCHAR(4) NOT NULL COMMENT 'Estimated time arrival - HHmm' ,
  `is_next_day` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'next day arrival' ,
  `remarks` VARCHAR(255) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_airline_schedule_item_id_airline_schedule` (`id_airline_schedule`) ,
  CONSTRAINT `fk_airline_schedule_item_id_airline_schedule`
    FOREIGN KEY (`id_airline_schedule` )
    REFERENCES `bterpdb`.`airline_schedule` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple details for specified airline schedule';

#
# Tables - Hotel
#
CREATE  TABLE `bterpdb`.`hotel` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `star_cd` VARCHAR(10) NOT NULL COMMENT '**ONE/TWO/THREE/FOUR/FIVE' ,
  `name` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255) NULL ,
  `website` VARCHAR(255) NULL ,
  `remarks` TEXT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Hotel info';

CREATE  TABLE `bterpdb`.`hotel_address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_hotel` BIGINT NOT NULL ,
  `id_country` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**OFF/BILL' ,
  `addr_1` VARCHAR(255) NOT NULL ,
  `addr_2` VARCHAR(255) NULL ,
  `addr_3` VARCHAR(255) NULL ,
  `city` VARCHAR(255) NULL ,
  `state` VARCHAR(255) NULL ,
  `postcode` VARCHAR(10) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_hotel_address_id_hotel` (`id_hotel`) ,
  INDEX `fk_hotel_address_id_country` (`id_country`) ,
  CONSTRAINT `fk_hotel_address_id_hotel`
    FOREIGN KEY (`id_hotel` )
    REFERENCES `bterpdb`.`hotel` (`id` ),
  CONSTRAINT `fk_hotel_address_id_country`
    FOREIGN KEY (`id_country` )
    REFERENCES `bterpdb`.`country` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple addresses for specified hotel';

CREATE  TABLE `bterpdb`.`hotel_contact` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_hotel_addr` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**TEL/FAX' ,
  `number` VARCHAR(20) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_hotel_contact_id_hotel_addr` (`id_hotel_addr`) ,
  CONSTRAINT `fk_hotel_contact_id_hotel_addr`
    FOREIGN KEY (`id_hotel_addr` )
    REFERENCES `bterpdb`.`hotel_address` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple contacts for specified hotel';

CREATE  TABLE `bterpdb`.`hotel_reservation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_hotel` BIGINT NOT NULL ,
  `dt_checkin` DATETIME NOT NULL ,
  `dt_checkout` DATETIME NOT NULL ,
  `arrival_flight_cd` VARCHAR(20) NULL ,
  `dep_flight_cd` VARCHAR(20) NULL ,
  `total_night` SMALLINT(3) NOT NULL DEFAULT 0 ,
  `total_adt` SMALLINT(3) NOT NULL DEFAULT 0 COMMENT 'total adult' ,
  `total_chd` SMALLINT(3) NOT NULL DEFAULT 0 COMMENT 'total children' ,
  `room_type` VARCHAR(255) NULL ,
  `meal_provide` VARCHAR(100) NULL ,
  `usage` VARCHAR(100) NULL COMMENT 'hotel usage' ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_hotel_reservation_id_hotel` (`id_hotel`) ,
  CONSTRAINT `fk_hotel_reservation_id_hotel`
    FOREIGN KEY (`id_hotel` )
    REFERENCES `bterpdb`.`hotel` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Hotel reservation info';

CREATE  TABLE `bterpdb`.`hotel_room` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_hotel_reservation` BIGINT NOT NULL ,
  `room_type_cd` VARCHAR(10) NOT NULL COMMENT '**SGL/TWN/etc.' ,
  `quantity` SMALLINT(3) NOT NULL DEFAULT 0 ,
  `remarks` VARCHAR(255) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_hotel_room_id_hotel_reservation` (`id_hotel_reservation`) ,
  CONSTRAINT `fk_hotel_room_id_hotel_reservation`
    FOREIGN KEY (`id_hotel_reservation` )
    REFERENCES `bterpdb`.`hotel_reservation` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Hotel rooms reservation info';

CREATE  TABLE `bterpdb`.`hotel_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `remarks` VARCHAR(255) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Hotel item configuration';

CREATE  TABLE `bterpdb`.`hotel_item_charge` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_hotel_item` BIGINT NOT NULL ,
  `amount` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_hotel_item_charge_id_hotel_item` (`id_hotel_item`) ,
  CONSTRAINT `fk_hotel_item_charge_id_hotel_item`
    FOREIGN KEY (`id_hotel_item` )
    REFERENCES `bterpdb`.`hotel_item` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Hotel item charge';

CREATE  TABLE `bterpdb`.`hotel_charges` (
  `id_hotel` BIGINT NOT NULL ,
  `id_hotel_item_charge` BIGINT NOT NULL ,
  INDEX `fk_hotel_charges_id_hotel` (`id_hotel`) ,
  INDEX `fk_hotel_charges_id_hotel_item_charge` (`id_hotel_item_charge`) ,
  CONSTRAINT `fk_hotel_charges_id_hotel`
    FOREIGN KEY (`id_hotel` )
    REFERENCES `bterpdb`.`hotel` (`id` ),
  CONSTRAINT `fk_hotel_charges_id_hotel_item_charge`
    FOREIGN KEY (`id_hotel_item_charge` )
    REFERENCES `bterpdb`.`hotel_item_charge` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Charges for specified hotel';

#
# Tables - Account
#
CREATE  TABLE `bterpdb`.`account_cat` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(50) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Account categories (e.g. Asset, Liability, Income and etc.)';

CREATE  TABLE `bterpdb`.`account_sub_cat` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_acct_cat` BIGINT NOT NULL ,
  `code` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(50) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_account_sub_cat_id_acct_cat` (`id_acct_cat` ASC) ,
  CONSTRAINT `fk_account_sub_cat_id_acct_cat`
    FOREIGN KEY (`id_acct_cat` )
    REFERENCES `bterpdb`.`account_cat` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Sub account categories for GL purpose (e.g. Fixed Assets, Current Liabilities and etc.)';

CREATE  TABLE `bterpdb`.`account` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_company` BIGINT NOT NULL ,
  `id_acct_cat` BIGINT NOT NULL ,
  `id_acct_sub_cat` BIGINT NOT NULL ,
  `code` VARCHAR(20) NOT NULL ,
  `sub_code` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `remarks` VARCHAR(255) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`code`, `sub_code`) ,
  UNIQUE INDEX `id_UNIQUE` (`id`) ,
  INDEX `fk_account_id_company` (`id_company`) ,
  INDEX `fk_account_id_acct_cat` (`id_acct_cat`) ,
  INDEX `fk_account_id_acct_sub_cat` (`id_acct_sub_cat`) ,
  CONSTRAINT `fk_account_id_company`
    FOREIGN KEY (`id_company` )
    REFERENCES `bterpdb`.`company` (`id` ),
  CONSTRAINT `fk_account_id_acct_cat`
    FOREIGN KEY (`id_acct_cat` )
    REFERENCES `bterpdb`.`account_cat` (`id` ),
  CONSTRAINT `fk_account_id_acct_sub_cat`
    FOREIGN KEY (`id_acct_sub_cat` )
    REFERENCES `bterpdb`.`account_sub_cat` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Chart of accounts';

CREATE  TABLE `bterpdb`.`account_bal` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_acct` BIGINT NOT NULL ,
  `dt_begin_bal` DATETIME NOT NULL ,
  `amt_begin_bal` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_current_bal` DATETIME NOT NULL ,
  `amt_current_bal` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_close_bal` DATETIME NOT NULL ,
  `amt_close_bal` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_account_bal_id_acct` (`id_acct`) ,
  CONSTRAINT `fk_account_bal_id_acct`
    FOREIGN KEY (`id_acct` )
    REFERENCES `bterpdb`.`account` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Types of balance for specified account';

CREATE  TABLE `bterpdb`.`account_trans` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_company` BIGINT NOT NULL ,
  `id_acct` BIGINT NOT NULL ,
  `dt_trans` DATETIME NOT NULL ,
  `sys_no` VARCHAR(20) NOT NULL COMMENT 'Invoice no / bill payment no / etc.' ,
  `ref_no` VARCHAR(20) NOT NULL COMMENT 'cheque no / etc.' ,
  `description` VARCHAR(255) NOT NULL ,
  `debit` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `credit` FLOAT(10,2) NOT NULL ,
  `type_cd` VARCHAR(10) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_account_trans_id_company` (`id_company`) ,
  INDEX `fk_account_trans_id_acct` (`id_acct`) ,
  CONSTRAINT `fk_account_trans_id_company`
    FOREIGN KEY (`id_company` )
    REFERENCES `bterpdb`.`company` (`id` ),
  CONSTRAINT `fk_account_trans_id_acct`
    FOREIGN KEY (`id_acct` )
    REFERENCES `bterpdb`.`account` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Account transactions';

CREATE  TABLE `bterpdb`.`bank` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_company` BIGINT NOT NULL ,
  `id_acct` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL ,
  `acct_no` VARCHAR(20) NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `dt_last_recon` DATETIME NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_bank_id_company` (`id_company`) ,
  INDEX `fk_bank_id_acct` (`id_acct`) ,
  CONSTRAINT `fk_bank_id_company`
    FOREIGN KEY (`id_company` )
    REFERENCES `bterpdb`.`company` (`id` ),
  CONSTRAINT `fk_bank_id_acct`
    FOREIGN KEY (`id_acct` )
    REFERENCES `bterpdb`.`account` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Bank info';

CREATE  TABLE `bterpdb`.`bank_address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_bank` BIGINT NOT NULL ,
  `id_country` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**OFF/BILL' ,
  `addr_1` VARCHAR(255) NOT NULL ,
  `addr_2` VARCHAR(255) NULL ,
  `addr_3` VARCHAR(255) NULL ,
  `city` VARCHAR(255) NULL ,
  `state` VARCHAR(255) NULL ,
  `postcode` VARCHAR(10) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_bank_address_id_bank` (`id_bank`) ,
  INDEX `fk_bank_address_id_country` (`id_country`) ,
  CONSTRAINT `fk_bank_address_id_bank`
    FOREIGN KEY (`id_bank` )
    REFERENCES `bterpdb`.`bank` (`id` ),
  CONSTRAINT `fk_bank_address_id_country`
    FOREIGN KEY (`id_country` )
    REFERENCES `bterpdb`.`country` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple addresses for specified bank';

CREATE  TABLE `bterpdb`.`bank_contact` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_bank_addr` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**TEL/FAX' ,
  `number` VARCHAR(20) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_bank_contact_id_bank_addr` (`id_bank_addr`) ,
  CONSTRAINT `fk_bank_contact_id_bank_addr`
    FOREIGN KEY (`id_bank_addr` )
    REFERENCES `bterpdb`.`bank_address` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple contacts for specified bank';

CREATE  TABLE `bterpdb`.`cash_book` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_bank` BIGINT NOT NULL ,
  `dt_trans` DATETIME NOT NULL ,
  `sys_no` VARCHAR(20) NOT NULL COMMENT 'Invoice no / bill payment no / etc.' ,
  `ref_no` VARCHAR(10) NOT NULL COMMENT 'cheque no. / etc.' ,
  `trans_type_cd` VARCHAR(10) NOT NULL COMMENT '**CHQ/DEPOSIT/VOID' ,
  `payee` VARCHAR(255) NOT NULL ,
  `remarks` VARCHAR(255) NOT NULL ,
  `debit` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `credit` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `is_clear` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `is_mark` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `type_cd` VARCHAR(10) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_cash_book_id_bank` (`id_bank`) ,
  CONSTRAINT `fk_cash_book_id_bank`
    FOREIGN KEY (`id_bank` )
    REFERENCES `bterpdb`.`bank` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'To record bank transaction';

#
# Tables - Tour
#
CREATE  TABLE `bterpdb`.`tour_operator` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_employee` BIGINT NOT NULL COMMENT 'in charge person' ,
  `name` VARCHAR(255) NOT NULL ,
  `contact` VARCHAR(255) NULL ,
  `tour_rate` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `pmnt_type_cd` VARCHAR(10) NULL COMMENT '**CHQ/ATM/CASH/etc.' ,
  `dt_due_pax_detail` DATETIME NOT NULL ,
  `dt_due_pmnt` DATETIME NOT NULL ,
  `dt_due_tkt_issuance` DATETIME NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_tour_operator_id_employee` (`id_employee`) ,
  CONSTRAINT `fk_tour_operator_id_employee`
    FOREIGN KEY (`id_employee` )
    REFERENCES `bterpdb`.`employee` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Tour operator info';

CREATE  TABLE `bterpdb`.`tour_cat` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_acct` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**T = Tour / FNE = Free & easy' ,
  `description` VARCHAR(100) NOT NULL ,
  `remarks` VARCHAR(255) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_tour_cat_id_acct` (`id_acct`) ,
  CONSTRAINT `fk_tour_cat_id_acct`
    FOREIGN KEY (`id_acct` )
    REFERENCES `bterpdb`.`account` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Tour categories';

CREATE  TABLE `bterpdb`.`tour_theme` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_tour_cat` BIGINT NOT NULL ,
  `id_country` BIGINT NULL ,
  `id_parent` BIGINT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `remarks` VARCHAR(255) NULL ,
  `img_path` VARCHAR(255) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_tour_theme_id_tour_cat` (`id_tour_cat` ASC) ,
  CONSTRAINT `fk_tour_theme_id_tour_cat`
    FOREIGN KEY (`id_tour_cat` )
    REFERENCES `bterpdb`.`tour_cat` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple tour themes for specified tour category';

CREATE  TABLE `bterpdb`.`tour_pkg` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_tour_theme` BIGINT NOT NULL ,
  `id_acct` BIGINT NOT NULL ,
  `id_parent` BIGINT NULL COMMENT 'level control - for free & easy tour, when is_optional is true' ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**T = Tour / FNE = Free & easy' ,
  `num_days` SMALLINT(3) NOT NULL DEFAULT 0 ,
  `num_nights` SMALLINT(3) NOT NULL DEFAULT 0 ,
  `name_en` VARCHAR(255) NOT NULL ,
  `name_zh` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL ,
  `name_other` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL ,
  `is_muslim_pkg` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `is_theme_toue` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `is_optional` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `is_new` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `is_promo` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `year` VARCHAR(4) NOT NULL COMMENT 'Year of theme - yyyy' ,
  `deposit` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `bag_deduction` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `tfair_discount` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `price_diff_sgl` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `price_diff_ctw` FLOAT(10,2) NOT NULL DEFAULT 0 COMMENT 'Child with twin' ,
  `price_diff_cwb` FLOAT(10,2) NOT NULL DEFAULT 0 COMMENT 'Child with bed' ,
  `price_diff_cnb` FLOAT(10,2) NOT NULL DEFAULT 0 COMMENT 'Child no bed' ,
  `grnd_sgl` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `grnd_ctw` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `grnd_cwb` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `grnd_cnb` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `cna_adt_ac` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `cna_adt_sl` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `cpa_adt_ac` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `cpa_adt_sl` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `price_from` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `high_light` TEXT NULL ,
  `dt_book_start` DATETIME NULL ,
  `dt_book_end` DATETIME NULL ,
  `dt_travel_start` DATETIME NULL ,
  `dt_travel_end` DATETIME NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  `version` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_tour_pkg_id_tour_theme` (`id_tour_theme`) ,
  INDEX `fk_tour_pkg_id_acct` (`id_acct`) ,
  CONSTRAINT `fk_tour_pkg_id_tour_theme`
    FOREIGN KEY (`id_tour_theme` )
    REFERENCES `bterpdb`.`tour_theme` (`id` ),
  CONSTRAINT `fk_tour_pkg_id_acct`
    FOREIGN KEY (`id_acct` )
    REFERENCES `bterpdb`.`account` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple packages for specified tour theme';

CREATE  TABLE `bterpdb`.`tour_dep` (
  `id` BIGINT NOT NULL ,
  `id_tour_pkg` BIGINT NOT NULL ,
  `id_airline_schedule` BIGINT NOT NULL ,
  `id_tour_operator` BIGINT NOT NULL ,
  `dt_dep` DATETIME NOT NULL ,
  `code` VARCHAR(20) NOT NULL COMMENT '*for free & easy will generate automatically, e.g. F000000001' ,
  `description` VARCHAR(255) NOT NULL ,
  `full_twn` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `full_sgl` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `full_ctw` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `full_ceb` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `full_cnb` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `grnd_twn` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `grnd_sgl` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `grnd_ctw` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `grnd_ceb` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `grnd_cnb` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `tour_mgr_cost` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `cna_adt_ac` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `cna_adt_sl` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `cpa_adt_ac` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `cpa_adt_sl` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `full_remarks` TEXT NULL ,
  `grnd_remarks` TEXT NULL ,
  `prn` VARCHAR(10) NULL ,
  `seat_allotment` SMALLINT(3) NOT NULL DEFAULT 0 ,
  `travel_ins_policy_s` VARCHAR(255) NULL ,
  `travel_ins_policy_f` VARCHAR(255) NULL ,
  `is_issued_s` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `is_issued_f` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `is_deposit_paid` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `is_push` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `is_hot_deal` TINYINT(1) NOT NULL DEFAULT 0 ,
  `status_cd` VARCHAR(10) NOT NULL COMMENT '**A = Available / F = Full / C = Close / L = Limited / NO = Not Operating / I = Issued / R = Refer' ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code`) ,
  INDEX `fk_tour_dep_id_tour_pkg` (`id_tour_pkg`) ,
  INDEX `fk_tour_dep_id_airline_schedule` (`id_airline_schedule`) ,
  INDEX `fk_tour_dep_id_tour_operator` (`id_tour_operator`) ,
  CONSTRAINT `fk_tour_dep_id_tour_pkg`
    FOREIGN KEY (`id_tour_pkg` )
    REFERENCES `bterpdb`.`tour_pkg` (`id` ),
  CONSTRAINT `fk_tour_dep_id_airline_schedule`
    FOREIGN KEY (`id_airline_schedule` )
    REFERENCES `bterpdb`.`airline_schedule` (`id` ),
  CONSTRAINT `fk_tour_dep_id_tour_operator`
    FOREIGN KEY (`id_tour_operator` )
    REFERENCES `bterpdb`.`tour_operator` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple depatures for specified tour package';

CREATE  TABLE `bterpdb`.`tour_itinery` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_tour_dep` BIGINT NOT NULL ,
  `itinery_path` VARCHAR(255) NOT NULL ,
  `lang_cd` VARCHAR(10) NOT NULL COMMENT '**EN/ZH/OTHER' ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**CUST/AGENT' ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_tour_itinery_id_tour_dep` (`id_tour_dep`) ,
  CONSTRAINT `fk_tour_itinery_id_tour_dep`
    FOREIGN KEY (`id_tour_dep` )
    REFERENCES `bterpdb`.`tour_dep` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple itineraries for specified tour departure';

CREATE  TABLE `bterpdb`.`tour_hotel` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_tour_dep` BIGINT NOT NULL ,
  `id_hotel` BIGINT NOT NULL ,
  `night_num` SMALLINT(3) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_tour_hotel_id_tour_dep` (`id_tour_dep`) ,
  INDEX `fk_tour_hotel_id_hotel` (`id_hotel`) ,
  CONSTRAINT `fk_tour_hotel_id_tour_dep`
    FOREIGN KEY (`id_tour_dep` )
    REFERENCES `bterpdb`.`tour_dep` (`id` ),
  CONSTRAINT `fk_tour_hotel_id_hotel`
    FOREIGN KEY (`id_hotel` )
    REFERENCES `bterpdb`.`hotel` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple hotel for specified tour departure';

CREATE  TABLE `bterpdb`.`tour_booking` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_tour_dep` BIGINT NOT NULL ,
  `id_person` BIGINT NOT NULL ,
  `order_type_cd` VARCHAR(10) NOT NULL COMMENT '**TFAIR/AGT/INHSE' ,
  `quantity` SMALLINT(3) NOT NULL DEFAULT 0 ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_tour_booking_id_tour_dep` (`id_tour_dep`) ,
  INDEX `fk_tour_booking_id_person` (`id_person`) ,
  CONSTRAINT `fk_tour_booking_id_tour_dep`
    FOREIGN KEY (`id_tour_dep` )
    REFERENCES `bterpdb`.`tour_dep` (`id` ),
  CONSTRAINT `fk_tour_booking_id_person`
    FOREIGN KEY (`id_person` )
    REFERENCES `bterpdb`.`person` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple booking for specified tour departure';

CREATE  TABLE `bterpdb`.`tour_booking_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_tour_booking` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NOT NULL COMMENT '**FTPAX/FTCHILD/FTINFANT/GAPAX/GACHILD/GAINFANT' ,
  `quantity` SMALLINT(3) NOT NULL DEFAULT 0 ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_tour_booking_item_id_tour_booking` (`id_tour_booking`) ,
  CONSTRAINT `fk_tour_booking_item_id_tour_booking`
    FOREIGN KEY (`id_tour_booking` )
    REFERENCES `bterpdb`.`tour_booking` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple booking item for specified booking';

CREATE  TABLE `bterpdb`.`tour_booking_pax` (
  `id_person` BIGINT NOT NULL ,
  `id_tour_booking` BIGINT NOT NULL ,
  INDEX `fk_tour_booking_pax_id_person` (`id_person`) ,
  INDEX `fk_tour_booking_pax_id_tour_booking` (`id_tour_booking`) ,
  CONSTRAINT `fk_tour_booking_pax_id_person`
    FOREIGN KEY (`id_person` )
    REFERENCES `bterpdb`.`person` (`id` ),
  CONSTRAINT `fk_tour_booking_pax_id_tour_booking`
    FOREIGN KEY (`id_tour_booking` )
    REFERENCES `bterpdb`.`tour_booking` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'The pax list of booking';

#
# Tables - Sales
#
CREATE  TABLE `bterpdb`.`invoice` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_company` BIGINT NOT NULL ,
  `id_customer` BIGINT NOT NULL ,
  `id_acct` BIGINT NOT NULL ,
  `dt_inv` DATETIME NOT NULL ,
  `code` VARCHAR(20) NOT NULL ,
  `doc_type_cd` VARCHAR(10) NOT NULL COMMENT '**INV/DN' ,
  `type_cd` VARCHAR(10) NULL COMMENT '**GENERAL/TOUR/etc.' ,
  `attn_to` VARCHAR(255) NOT NULL COMMENT 'customer nam' ,
  `id_saler` BIGINT NOT NULL ,
  `id_tour_dep` BIGINT NOT NULL ,
  `id_issuer` BIGINT NOT NULL ,
  `id_eo_ref` BIGINT NULL ,
  `cat_cd` VARCHAR(10) NULL COMMENT '**FAIR/CRUISES/etc.' ,
  `order_cd` VARCHAR(10) NULL COMMENT '**FAIR/ADS/etc.' ,
  `delivery_cd` VARCHAR(10) NULL COMMENT '**HAND/POST/etc.' ,
  `amount` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `is_inv_paid` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_invoice_id_company` (`id_company`) ,
  INDEX `fk_invoice_id_customer` (`id_customer`) ,
  INDEX `fk_invoice_id_acct` (`id_acct`) ,
  INDEX `fk_invoice_id_saler` (`id_saler`) ,
  INDEX `fk_invoice_id_tour_dep` (`id_tour_dep`) ,
  INDEX `fk_invoice_id_issuer` (`id_issuer`) ,
  CONSTRAINT `fk_invoice_id_company`
    FOREIGN KEY (`id_company` )
    REFERENCES `bterpdb`.`company` (`id` ),
  CONSTRAINT `fk_invoice_id_customer`
    FOREIGN KEY (`id_customer` )
    REFERENCES `bterpdb`.`customer` (`id` ),
  CONSTRAINT `fk_invoice_id_acct`
    FOREIGN KEY (`id_acct` )
    REFERENCES `bterpdb`.`account` (`id` ),
  CONSTRAINT `fk_invoice_id_saler`
    FOREIGN KEY (`id_saler` )
    REFERENCES `bterpdb`.`employee` (`id` ),
  CONSTRAINT `fk_invoice_id_tour_dep`
    FOREIGN KEY (`id_tour_dep` )
    REFERENCES `bterpdb`.`tour_dep` (`id` ),
  CONSTRAINT `fk_invoice_id_issuer`
    FOREIGN KEY (`id_issuer` )
    REFERENCES `bterpdb`.`employee` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Invoice info';

CREATE  TABLE `bterpdb`.`invoice_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_inv` BIGINT NOT NULL ,
  `id_acct` BIGINT NOT NULL ,
  `id_inv_eo_item` BIGINT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `quantity` SMALLINT(3) NOT NULL DEFAULT 0 ,
  `unit_price` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `amount` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_invoice_item_id_inv` (`id_inv`) ,
  INDEX `fk_invoice_item_id_acct` (`id_acct`) ,
  CONSTRAINT `fk_invoice_item_id_inv`
    FOREIGN KEY (`id_inv` )
    REFERENCES `bterpdb`.`invoice` (`id` ),
  CONSTRAINT `fk_invoice_item_id_acct`
    FOREIGN KEY (`id_acct` )
    REFERENCES `bterpdb`.`account` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple items for specified invoice';

CREATE  TABLE `bterpdb`.`invoice_pax` (
  `id_inv` BIGINT NOT NULL ,
  `id_tour_booking` BIGINT NOT NULL ,
  INDEX `fk_invoice_pax_id_inv` (`id_inv`) ,
  INDEX `fk_invoice_pax_id_tour_booking` (`id_tour_booking`) ,
  CONSTRAINT `fk_invoice_pax_id_inv`
    FOREIGN KEY (`id_inv` )
    REFERENCES `bterpdb`.`invoice` (`id` ),
  CONSTRAINT `fk_invoice_pax_id_tour_booking`
    FOREIGN KEY (`id_tour_booking` )
    REFERENCES `bterpdb`.`tour_booking` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Invoice pax maintenance';

CREATE  TABLE `bterpdb`.`invoice_pmnt` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_inv` BIGINT NOT NULL ,
  `id_issuer` BIGINT NULL ,
  `id_bank` BIGINT NULL ,
  `dt_pmnt` DATETIME NOT NULL ,
  `code` VARCHAR(20) NOT NULL ,
  `pmnt_type_cd` VARCHAR(10) NULL COMMENT '**CHQ/CASH/etc.' ,
  `ref_no` VARCHAR(20) NOT NULL COMMENT 'cheque no./bank' ,
  `received_from` VARCHAR(255) NOT NULL ,
  `amount` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `remarks` TEXT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_invoice_pmnt_id_inv` (`id_inv`) ,
  INDEX `fk_invoice_pmnt_id_issuer` (`id_issuer`) ,
  CONSTRAINT `fk_invoice_pmnt_id_inv`
    FOREIGN KEY (`id_inv` )
    REFERENCES `bterpdb`.`invoice` (`id` ),
  CONSTRAINT `fk_invoice_pmnt_id_issuer`
    FOREIGN KEY (`id_issuer` )
    REFERENCES `bterpdb`.`employee` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Invoice payment maintenance';

#
# Tables - Exchange Order
#
CREATE  TABLE `bterpdb`.`ex_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_company` BIGINT NOT NULL ,
  `id_supplier` BIGINT NOT NULL ,
  `id_acct` BIGINT NOT NULL ,
  `dt_eo` DATETIME NOT NULL ,
  `code` VARCHAR(20) NOT NULL ,
  `doc_type_cd` VARCHAR(10) NOT NULL COMMENT '**INV/DN' ,
  `type_cd` VARCHAR(10) NULL COMMENT '**GENERAL/TOUR/etc.' ,
  `attn_to` VARCHAR(255) NOT NULL COMMENT 'customer nam' ,
  `id_orderer` BIGINT NOT NULL ,
  `id_tour_dep` BIGINT NOT NULL ,
  `id_approver` BIGINT NOT NULL ,
  `id_inv_ref` BIGINT NULL ,
  `pmnt_type_cd` VARCHAR(10) NULL COMMENT '**CHQ/CASH/etc.' ,
  `cat_cd` VARCHAR(10) NULL COMMENT '**FAIR/CRUISES/etc.' ,
  `delivery_cd` VARCHAR(10) NULL COMMENT '**HAND/POST/etc.' ,
  `amount` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `is_eo_paid` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' ,
  `dt_supp_inv` DATETIME NULL ,
  `dt_due` DATETIME NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ex_order_id_company` (`id_company`) ,
  INDEX `fk_ex_order_id_supplier` (`id_supplier`) ,
  INDEX `fk_ex_order_id_acct` (`id_acct`) ,
  INDEX `fk_ex_order_id_orderer` (`id_orderer`) ,
  INDEX `fk_ex_order_id_tour_dep` (`id_tour_dep`) ,
  INDEX `fk_ex_order_id_approver` (`id_approver`) ,
  CONSTRAINT `fk_ex_order_id_company`
    FOREIGN KEY (`id_company` )
    REFERENCES `bterpdb`.`company` (`id` ),
  CONSTRAINT `fk_ex_order_id_supplier`
    FOREIGN KEY (`id_supplier` )
    REFERENCES `bterpdb`.`supplier` (`id` ),
  CONSTRAINT `fk_ex_order_id_acct`
    FOREIGN KEY (`id_acct` )
    REFERENCES `bterpdb`.`account` (`id` ),
  CONSTRAINT `fk_ex_order_id_orderer`
    FOREIGN KEY (`id_orderer` )
    REFERENCES `bterpdb`.`employee` (`id` ),
  CONSTRAINT `fk_ex_order_id_tour_dep`
    FOREIGN KEY (`id_tour_dep` )
    REFERENCES `bterpdb`.`tour_dep` (`id` ),
  CONSTRAINT `fk_ex_order_id_approver`
    FOREIGN KEY (`id_approver` )
    REFERENCES `bterpdb`.`employee` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Exchange order info';

CREATE  TABLE `bterpdb`.`ex_order_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_eo` BIGINT NOT NULL ,
  `id_inv_eo_item` BIGINT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `quantity` SMALLINT(3) NOT NULL DEFAULT 0 ,
  `unit_price` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `amount` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ex_order_item_id_eo` (`id_eo`) ,
  CONSTRAINT `fk_ex_order_item_id_eo`
    FOREIGN KEY (`id_eo` )
    REFERENCES `bterpdb`.`ex_order` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Multiple items for specified exchange order';

CREATE  TABLE `bterpdb`.`ex_order_pax` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_eo` BIGINT NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `pnr` VARCHAR(20) NULL ,
  `air_cd` VARCHAR(20) NULL ,
  `tkt_no` VARCHAR(20) NULL ,
  `payable` VARCHAR(20) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ex_order_pax_id_eo` (`id_eo`) ,
  CONSTRAINT `fk_ex_order_pax_id_eo`
    FOREIGN KEY (`id_eo` )
    REFERENCES `bterpdb`.`ex_order` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Exchange order pax maintenance';

CREATE  TABLE `bterpdb`.`ex_order_airline` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_eo` BIGINT NOT NULL ,
  `from` VARCHAR(255) NOT NULL ,
  `flight_no` VARCHAR(20) NOT NULL ,
  `dt_flight` DATETIME NOT NULL ,
  `time` VARCHAR(50) NOT NULL ,
  `class` VARCHAR(20) NOT NULL ,
  `status` VARCHAR(20) NOT NULL ,
  `pnr` VARCHAR(20) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ex_order_airline_id_eo` (`id_eo`) ,
  CONSTRAINT `fk_ex_order_airline_id_eo`
    FOREIGN KEY (`id_eo` )
    REFERENCES `bterpdb`.`ex_order` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Exchange order airline maintenance';

CREATE  TABLE `bterpdb`.`ex_order_hotel` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_eo` BIGINT NOT NULL ,
  `dt_chk_in` DATETIME NOT NULL ,
  `dt_chk_out` DATETIME NOT NULL ,
  `total_night` SMALLINT(3) NOT NULL DEFAULT 0 ,
  `flight_arrival` VARCHAR(255) NULL ,
  `flight_dep` VARCHAR(255) NULL ,
  `pax_no` VARCHAR(255) NULL ,
  `room_type` VARCHAR(255) NULL ,
  `meal_provide` VARCHAR(255) NULL ,
  `usage` VARCHAR(255) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ex_order_hotel_id_eo` (`id_eo`) ,
  CONSTRAINT `fk_ex_order_hotel_id_eo`
    FOREIGN KEY (`id_eo` )
    REFERENCES `bterpdb`.`ex_order` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Exchange order hotel maintenance';

CREATE  TABLE `bterpdb`.`ex_order_bill` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_eo` BIGINT NOT NULL ,
  `dt_bill` DATETIME NOT NULL ,
  `code` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `remarks` TEXT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ex_order_bill_id_eo` (`id_eo`) ,
  CONSTRAINT `fk_ex_order_bill_id_eo`
    FOREIGN KEY (`id_eo` )
    REFERENCES `bterpdb`.`ex_order` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Exchange order bill maintenance';

CREATE  TABLE `bterpdb`.`ex_order_bill_pmnt` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_eo_bill` BIGINT NOT NULL ,
  `id_bank` BIGINT NOT NULL ,
  `dt_pmnt` DATETIME NOT NULL ,
  `code` VARCHAR(20) NOT NULL ,
  `ref_no` VARCHAR(20) NOT NULL ,
  `descripton` VARCHAR(255) NOT NULL ,
  `remarks` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ex_order_bill_pmnt_id_eo_bill` (`id_eo_bill`) ,
  INDEX `fk_ex_order_bill_pmnt_id_bank` (`id_bank`) ,
  CONSTRAINT `fk_ex_order_bill_pmnt_id_eo_bill`
    FOREIGN KEY (`id_eo_bill` )
    REFERENCES `bterpdb`.`ex_order_bill` (`id` ),
  CONSTRAINT `fk_ex_order_bill_pmnt_id_bank`
    FOREIGN KEY (`id_bank` )
    REFERENCES `bterpdb`.`bank` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Exchange order bill payment maintenance';

CREATE  TABLE `bterpdb`.`invoice_eo_link` (
  `id_inv` BIGINT NOT NULL ,
  `id_eo` BIGINT NOT NULL ,
  INDEX `fk_invoice_eo_link_id_inv` (`id_inv`) ,
  INDEX `fk_invoice_eo_link_id_eo` (`id_eo`) ,
  CONSTRAINT `fk_invoice_eo_link_id_inv`
    FOREIGN KEY (`id_inv` )
    REFERENCES `bterpdb`.`invoice` (`id` ),
  CONSTRAINT `fk_invoice_eo_link_id_eo`
    FOREIGN KEY (`id_eo` )
    REFERENCES `bterpdb`.`ex_order` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Invoice/exchange order link';

# 1.0.2
# Tables changes
# By HS
#
drop table `bterpdb`.`employee_contact`;
drop table `bterpdb`.`employee_address`;

ALTER TABLE `bterpdb`.`employee` ADD COLUMN `id_person` BIGINT NOT NULL  AFTER `id_company` , 
  ADD CONSTRAINT `fk_employee_id_person`
  FOREIGN KEY (`id_person` )
  REFERENCES `bterpdb`.`person` (`id` )
, ADD INDEX `fk_employee_id_person` (`id_person`) ;

ALTER TABLE `bterpdb`.`bank_contact` DROP FOREIGN KEY `fk_bank_contact_id_bank_addr` ;
ALTER TABLE `bterpdb`.`bank_contact` CHANGE COLUMN `id_bank_addr` `id_bank` BIGINT(20) NOT NULL  , 
  ADD CONSTRAINT `fk_bank_contact_id_bank`
  FOREIGN KEY (`id_bank` )
  REFERENCES `bterpdb`.`bank` (`id` );

ALTER TABLE `bterpdb`.`company_contact` DROP FOREIGN KEY `fk_company_contact_id_company_addr` ;
ALTER TABLE `bterpdb`.`company_contact` CHANGE COLUMN `id_company_addr` `id_company` BIGINT(20) NOT NULL  , 
  ADD CONSTRAINT `fk_company_contact_id_company`
  FOREIGN KEY (`id_company` )
  REFERENCES `bterpdb`.`company` (`id` );

ALTER TABLE `bterpdb`.`corporate_contact` DROP FOREIGN KEY `fk_corporate_contact_id_corporate_addr` ;
ALTER TABLE `bterpdb`.`corporate_contact` CHANGE COLUMN `id_corporate_addr` `id_corporate` BIGINT(20) NOT NULL  , 
  ADD CONSTRAINT `fk_corporate_contact_id_corporate`
  FOREIGN KEY (`id_corporate` )
  REFERENCES `bterpdb`.`corporate` (`id` );

ALTER TABLE `bterpdb`.`hotel_contact` DROP FOREIGN KEY `fk_hotel_contact_id_hotel_addr` ;
ALTER TABLE `bterpdb`.`hotel_contact` CHANGE COLUMN `id_hotel_addr` `id_hotel` BIGINT(20) NOT NULL  , 
  ADD CONSTRAINT `fk_hotel_contact_id_hotel`
  FOREIGN KEY (`id_hotel` )
  REFERENCES `bterpdb`.`hotel` (`id` );

ALTER TABLE `bterpdb`.`person_contact` DROP FOREIGN KEY `fk_person_contact_id_person_addr` ;
ALTER TABLE `bterpdb`.`person_contact` CHANGE COLUMN `id_person_addr` `id_person` BIGINT(20) NOT NULL  , 
  ADD CONSTRAINT `fk_person_contact_id_person`
  FOREIGN KEY (`id_person` )
  REFERENCES `bterpdb`.`person` (`id` );

ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `balance` FLOAT(10,2) NOT NULL DEFAULT 0  AFTER `amount` ;
ALTER TABLE `bterpdb`.`ex_order` ADD COLUMN `balance` FLOAT(10,2) NOT NULL DEFAULT 0  AFTER `amount` ;

# 1.0.3
# Functions changes
# By HS
#
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_SALES_CUST_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_SALES_CUST_SEARCH';
UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/sales/cust' WHERE `ID`='U_SALES_CUST';

UPDATE `bterpdb`.`SEC_FUNC` SET `LEVEL_NO`=3 WHERE `UUID`='U_SALES_INV_PRINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_SALES_INV_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/sales/creditnote' WHERE `UUID`='U_SALES_CREDITNOTE';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_SALES_CREDITNOTE_ISSUANCE';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_SALES_CREDITNOTE_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_NAME`='Tour Packages', `URI_ENTRY`='/app/product/tour' WHERE `UUID`='U_PRODUCT_SERIES';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_PRODUCT_SERIES_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_PRODUCT_SERIES_SEARCH';
UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_NAME`='Free & Easy', `URI_ENTRY`='/app/product/fne' WHERE `UUID`='U_PRODUCT_MASTER';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_PRODUCT_MASTER_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_PRODUCT_MASTER_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `UUID`='U_PRODUCT_TOUR', `FUNC_CD`='PRODUCT_TOUR' WHERE `UUID`='U_PRODUCT_SERIES';
UPDATE `bterpdb`.`SEC_FUNC` SET `UUID`='U_PRODUCT_FNE', `FUNC_CD`='PRODUCT_FNE' WHERE `UUID`='U_PRODUCT_MASTER';
UPDATE `bterpdb`.`SEC_FUNC` SET `SEQ_NO`=2 WHERE `UUID`='U_PRODUCT_FNE';

INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_PRODUCT_TICKETING', 'PRODUCT_TICKETING', 'Ticketing', 'L', '/app/product/ticketing', '', '1', 2, 3, 'U_PRODUCT', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);
INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_PRODUCT_HOTEL', 'PRODUCT_HOTEL', 'Hotel', 'L', '/app/product/hotel', '', '1', 2, 4, 'U_PRODUCT', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);
INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_PRODUCT_FLIGHT', 'PRODUCT_FLIGHT', 'Flight', 'L', '/app/product/flight', '', '1', 2, 5, 'U_PRODUCT', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/purchase/supplier' WHERE `UUID`='U_PURCHASE_SUPPLIER';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_PURCHASE_SUPPLIER_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_PURCHASE_SUPPLIER_SEARCH';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_PURCHASE_SUPPLIER_SEARCHBAL';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/purchase/exorder' WHERE `UUID`='U_PURCHASE_EXORDER';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_PURCHASE_EXORDER_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_PURCHASE_EXORDER_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `UUID`='U_PURCHASE_BILL_MULTI_PMNT', `FUNC_CD`='PURCHASE_BILL_MULTI_PMNT', `FUNC_NAME`='Multi Bills Payment', `URI_ENTRY`='/app/purchase/bill/multibills' WHERE `UUID`='U_PURCHASE_BILL_PMNT';
UPDATE `bterpdb`.`SEC_FUNC` SET `UUID`='U_PURCHASE_BILL_SGL_PMNT', `FUNC_CD`='PURCHASE_BILL_SGL_PMNT', `FUNC_NAME`='Single Bills Payment', `URI_ENTRY`='/app/purchase/bill/pmnt' WHERE `UUID`='U_PURCHASE_BILL_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/acct/chart' WHERE `UUID`='U_ACCT_CHART';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_ACCT_CHART_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_ACCT_CHART_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/acct/openbalance' WHERE `UUID`='U_ACCT_OPENBALANCE';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_ACCT_OPENBALANCE_MAINT';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/acct/financial' WHERE `UUID`='U_ACCT_FINANCIAL';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_ACCT_FINANCIAL_MAINT';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/acct/journal' WHERE `UUID`='U_ACCT_JOURNAL';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_ACCT_JOURNAL_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_ACCT_JOURNAL_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/acct/ledger' WHERE `UUID`='U_ACCT_LEDGER';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_ACCT_LEDGER_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_ACCT_LEDGER_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `UUID`='U_BANK_ACCT', `FUNC_CD`='BANK_ACCT', `FUNC_NAME`='Bank Account', `URI_ENTRY`='/app/bank/acct' WHERE `UUID`='U_BANK_BANKCASH';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_BANK_BANKCASH_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_BANK_BANKCASH_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/bank/rcpt', `SEQ_NO`=3 WHERE `UUID`='U_BANK_RCPT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_BANK_RCPT_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_BANK_RCPT_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/bank/adjustment', `SEQ_NO`=4 WHERE `UUID`='U_BANK_ADJUSTMENT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_BANK_ADJUSTMENT_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_BANK_ADJUSTMENT_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/bank/cashbook', `SEQ_NO`=6 WHERE `UUID`='U_BANK_CASHBOOK';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_BANK_CASHBOOK_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_BANK_CASHBOOK_SEARCH';

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/bank/recon' WHERE `UUID`='U_BANK_RECON';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_BANK_RECON_MAINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_BANK_RECON_SEARCH';

INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_BANK_PMNT', 'BANK_PMNT', 'Payment', 'L', '/app/bank/pmnt', '', '1', 2, 2, 'U_BANK', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

# 1.0.4
# Alter tbl
# By HS
#
ALTER TABLE `bterpdb`.`tmp_msg` ADD COLUMN `type_cd` VARCHAR(10) NOT NULL COMMENT 'type of message template'  AFTER `id` ;
ALTER TABLE `bterpdb`.`account_cat` ADD UNIQUE INDEX `code_UNIQUE` (`code`) ;

ALTER TABLE `bterpdb`.`account` ADD COLUMN `sub_description` VARCHAR(255) NULL COMMENT 'Sub account description'  AFTER `description` ;
ALTER TABLE `bterpdb`.`account` DROP PRIMARY KEY , ADD PRIMARY KEY (`id_company`, `code`, `sub_code`) ;

# 1.0.5
# Alter tbl, Create New Table for supplier remarks
# By Keith
#
ALTER TABLE `bterpdb`.`person` CHANGE COLUMN `salutaion_cd` `salutation_cd` VARCHAR(10) NOT NULL COMMENT '**MR/MRS/MS/etc.'  ;

CREATE  TABLE `bterpdb`.`supplier_remarks` (
  `id` BIGINT(20) NOT NULL ,
  `id_person_corp` BIGINT(20) NOT NULL ,
  `remarks` TEXT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` TIMESTAMP NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) );

# 1.0.6
# Alter tbl
# By Keith
#
ALTER TABLE `bterpdb`.`person` CHANGE COLUMN `sex_cd` `sex_cd` VARCHAR(10) NULL COMMENT '**M = Male / F = Female'  , CHANGE COLUMN `class_cd` `class_cd` VARCHAR(10) NULL COMMENT '**VIP / BL = Black Listed / etc.'  , CHANGE COLUMN `dt_birth` `dt_birth` DATETIME NULL  , CHANGE COLUMN `age` `age` INT(11) NULL DEFAULT '0'  , CHANGE COLUMN `is_ins_bought` `is_ins_bought` TINYINT(4) NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes'  ;

# 1.0.7
# Alter tbl
#By Keith
#
ALTER TABLE `bterpdb`.`supplier` CHANGE COLUMN `code` `code` VARCHAR(20) NULL  , CHANGE COLUMN `status_cd` `status_cd` VARCHAR(10) NULL COMMENT '**A = Active / I = Inactive'  ;
ALTER TABLE `bterpdb`.`supplier` DROP COLUMN `remarks` ;
ALTER TABLE `bterpdb`.`person` DROP COLUMN `remarks` ;
ALTER TABLE `bterpdb`.`person_identity` CHANGE COLUMN `dt_issued` `dt_issued` DATETIME NULL  , CHANGE COLUMN `dt_expired` `dt_expired` DATETIME NULL  ;
ALTER TABLE `bterpdb`.`person_address` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(10) NULL COMMENT '**OFF/BILL'  ;
ALTER TABLE `bterpdb`.`supplier` CHANGE COLUMN `pmnt_type_cd` `pmnt_type_cd` VARCHAR(20) NULL DEFAULT NULL COMMENT '**CHQ/CASH/etc.'  ;
ALTER TABLE `bterpdb`.`supplier_remarks` ADD COLUMN `time_stamp` DATETIME NOT NULL  AFTER `remarks` ;
ALTER TABLE `bterpdb`.`supplier_remarks` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT  ;
ALTER TABLE `bterpdb`.`supplier_remarks` CHANGE COLUMN `created_by` `created_by` VARCHAR(50) NOT NULL DEFAULT '0000-00-00 00:00:00'  ;
ALTER TABLE `bterpdb`.`account_sub_cat` CHANGE COLUMN `code` `code` VARCHAR(20) NULL  , ADD COLUMN `gl_number` VARCHAR(45) NOT NULL  AFTER `description` ;
ALTER TABLE `bterpdb`.`bank` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(10) NULL  ;

# 1.0.8
# Alter tbl
# By HS
#
ALTER TABLE `bterpdb`.`tmp_msg` CHANGE COLUMN `code` `title` VARCHAR(255) NOT NULL  , CHANGE COLUMN `description` `message` TEXT NOT NULL  ;
ALTER TABLE `bterpdb`.`tmp_msg` CHANGE COLUMN `type_cd` `id_lookup_item` BIGINT(20) NOT NULL COMMENT 'type of message template'  , 
  ADD CONSTRAINT `fk_tmp_msg_id_lookup_item`
  FOREIGN KEY (`id_lookup_item` )
  REFERENCES `bterpdb`.`lookup_item` (`id` )
, ADD INDEX `fk_tmp_msg_id_lookup_item` (`id_lookup_item`) ;

UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/acct/beginbal' WHERE `ID`='U_ACCT_OPENBALANCE';

# new tbl financial period
CREATE  TABLE `bterpdb`.`financial_period` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_company` BIGINT NOT NULL ,
  `year` VARCHAR(4) NOT NULL ,
  `seq_no` INT NOT NULL ,
  `description` VARCHAR(20) NOT NULL ,
  `dt_start` DATETIME NOT NULL ,
  `dt_end` DATETIME NOT NULL ,
  `status` TINYINT(1) NOT NULL COMMENT '0 = close, 1= open' ,
  `period_type` TINYINT(1) NOT NULL COMMENT '0 = monthly, 1= quaterly' ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_financial_period_id_company` (`id_company`) ,
  CONSTRAINT `fk_financial_period_id_company`
    FOREIGN KEY (`id_company` )
    REFERENCES `bterpdb`.`company` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Account financial period';

ALTER TABLE `bterpdb`.`account_bal` ADD COLUMN `credit_begin_bal` FLOAT(10,2) NOT NULL DEFAULT 0  AFTER `debit_begin_bal` , ADD COLUMN `credit_current_bal` FLOAT(10,2) NOT NULL DEFAULT 0  AFTER `debit_current_bal` , ADD COLUMN `credit_close_bal` FLOAT(10,2) NOT NULL DEFAULT 0  AFTER `debit_close_bal` , CHANGE COLUMN `amt_begin_bal` `debit_begin_bal` FLOAT(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `amt_current_bal` `debit_current_bal` FLOAT(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `amt_close_bal` `debit_close_bal` FLOAT(10,2) NOT NULL DEFAULT '0.00'  ;
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_ACCT_OPENBALANCE';

# 1.0.9
# Alter tbl
# By Ravi
#
ALTER TABLE `bterpdb`.`sys_num_conf` CHANGE COLUMN `code` `code` VARCHAR(20) NOT NULL  ;

# 1.0.10
# Alter tbl
# By HS
#
ALTER TABLE `bterpdb`.`bank` ADD COLUMN `contact_name` VARCHAR(255) NULL  AFTER `name` ;

ALTER TABLE `bterpdb`.`lookup_item` DROP FOREIGN KEY `fk_lookup_item_lookup_cat_cd` ;
ALTER TABLE `bterpdb`.`lookup_item` CHANGE COLUMN `lookup_cat_cd` `id_lookup_cat` BIGINT NOT NULL COMMENT 'inherit to lookup_cat code'  , 
  ADD CONSTRAINT `fk_lookup_item_id_lookup_cat`
  FOREIGN KEY (`id_lookup_cat` )
  REFERENCES `bterpdb`.`lookup_cat` (`id` );

INSERT INTO `bterpdb`.`sec_func` (`UUID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `STATUS_CD`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
	VALUES ('U_MAINTENANCE', 'MAINTENANCE', 'Maintenance', 'T', '#', '1', '1', '15', 'A', '2013-01-04 15:07:03', 'SYSTEM', '2013-01-09 18:20:02', 'Super User', '3');
INSERT INTO `bterpdb`.`sec_func` (`UUID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
	VALUES ('U_TEMPLATE_MAINTENANCE', 'Template_Maintenance', 'Message Template ', 'L', '/app/maintenance/templatemaintenance', '1', '2', '1', 'U_MAINTENANCE', 'A', '2013-01-04 15:07:03', 'SYSTEM', '2013-01-09 18:20:02', 'Super User', '3');
INSERT INTO `bterpdb`.`sec_func` (`UUID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
	VALUES ('U_LOOKUP', 'Look_Up', 'Lookup', 'L', '/app/maintenance/lookup', '1', '3', '2', 'U_MAINTENANCE', 'A', '2013-01-04 15:07:03', 'SYSTEM', '2013-01-09 18:20:02', 'Super User', '3');

# 1.0.11
# Alter tbl
# By Keith
#
ALTER TABLE `bterpdb`.`supplier` ADD COLUMN `bank_name` VARCHAR(45) NULL  AFTER `upd_by` , ADD COLUMN `bank_no` VARCHAR(45) NULL  AFTER `bank_name` ;
ALTER TABLE `bterpdb`.`person_identity` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(20) NOT NULL COMMENT '**NRIC/VISA/PASS'  ;
ALTER TABLE `bterpdb`.`supplier` CHANGE COLUMN `bank_name` `bank_name` VARCHAR(45) NULL DEFAULT NULL  AFTER `credit_limit` , CHANGE COLUMN `bank_no` `bank_no` VARCHAR(45) NULL DEFAULT NULL  AFTER `bank_name` ;
ALTER TABLE `bterpdb`.`account_sub_cat` DROP COLUMN `gl_number` ;

# 1.0.12
# Alter tbl -- change corporate address type to null
# By Keith
#
ALTER TABLE `bterpdb`.`corporate_address` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(10) NULL COMMENT '**OFF/BILL'  ;

# 1.0.13
# Add tour categories function - sub menu
# Update product sequen number - the sequen of sub menus
# By Hs
#
INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_PRODUCT_CAT', 'PRODUCT_CAT', 'Tour Categories', 'L', '/app/product/cat', '', '1', 2, 1, 'U_PRODUCT', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

UPDATE `bterpdb`.`SEC_FUNC` SET `SEQ_NO`=2 WHERE `UUID`='U_PRODUCT_TOUR';
UPDATE `bterpdb`.`SEC_FUNC` SET `SEQ_NO`=3 WHERE `UUID`='U_PRODUCT_FNE';
UPDATE `bterpdb`.`SEC_FUNC` SET `SEQ_NO`=4 WHERE `UUID`='U_PRODUCT_TICKETING';
UPDATE `bterpdb`.`SEC_FUNC` SET `SEQ_NO`=5 WHERE `UUID`='U_PRODUCT_HOTEL';
UPDATE `bterpdb`.`SEC_FUNC` SET `SEQ_NO`=6 WHERE `UUID`='U_PRODUCT_FLIGHT';

# correct the naming
ALTER TABLE `bterpdb`.`tour_pkg` CHANGE COLUMN `is_theme_toue` `is_theme_tour` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes'  ;

# 1.0.14
# Add new-field to ExOrder 
# By Keith
#

ALTER TABLE `bterpdb`.`ex_order` 
ADD COLUMN `is_draft` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes'  AFTER `balance` , 
ADD COLUMN `is_pending` TINYINT(1) NOT NULL DEFAULT '0'  AFTER `is_draft` , 
ADD COLUMN `is_billed` TINYINT(1) NOT NULL DEFAULT '0'  AFTER `is_pending` , 
ADD COLUMN `is_cancelled` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes'  AFTER `is_eo_paid` ;

# 1.0.15
# Add  code field to Company
# By Ravi
#


ALTER TABLE `bterpdb`.`company` ADD COLUMN `code` VARCHAR(45) NOT NULL  AFTER `upd_by` ;


# 1.0.16
# Add new record for table com_sys_param
# By Kent
#
INSERT INTO com_sys_param(UUID, APP_ID, CAT_CD, PARAM_CD, PARAM_VALUE, PARAM_DESC, DT_CREATED, CREATED_BY, DT_UPD, UPD_BY, VERSION)
VALUES('U_MAX_LOGIN_INVALID_COUNT', '*', 'SECURITY', 'MAX_LOGIN_INVALID_COUNT', '3', 'Maximum login invalid count', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', 1);

# 1.0.17
# Alter code field position of Company
# By Ravi
#
ALTER TABLE `bterpdb`.`company` CHANGE COLUMN `code` `code` VARCHAR(20) NULL DEFAULT NULL  AFTER `name` ;

# 1.0.18
# Alter table
# By Keith
# Completed Add function In exchange Order
ALTER TABLE `bterpdb`.`ex_order` DROP FOREIGN KEY `fk_ex_order_id_tour_dep` , DROP FOREIGN KEY `fk_ex_order_id_acct` ;
ALTER TABLE `bterpdb`.`ex_order` DROP COLUMN `id_tour_dep` , DROP COLUMN `id_acct` 
, DROP INDEX `fk_ex_order_id_tour_dep` 
, DROP INDEX `fk_ex_order_id_acct` ;
ALTER TABLE `bterpdb`.`ex_order` ADD COLUMN `dt_start` DATETIME NULL DEFAULT NULL  AFTER `dt_supp_inv` ;
ALTER TABLE `bterpdb`.`ex_order` ADD COLUMN `remarks` TEXT NULL DEFAULT NULL  AFTER `dt_due` ;
ALTER TABLE `bterpdb`.`ex_order` DROP COLUMN `balance` , DROP COLUMN `amount` ;
ALTER TABLE `bterpdb`.`ex_order` ADD COLUMN `cn_no` VARCHAR(20) NULL DEFAULT NULL  AFTER `pmnt_type_cd` ;
ALTER TABLE `bterpdb`.`ex_order` DROP COLUMN `code` ;
ALTER TABLE `bterpdb`.`ex_order` CHANGE COLUMN `doc_type_cd` `doc_type_cd` VARCHAR(10) NULL COMMENT '**INV/DN'  ;
ALTER TABLE `bterpdb`.`ex_order_item` DROP COLUMN `id_inv_eo_item` , 
ADD COLUMN `applicable_fare` FLOAT(10,2) NOT NULL DEFAULT '0.00'  AFTER `amount` , 
ADD COLUMN `net_fare` FLOAT(10,2) NOT NULL DEFAULT '0.00'  AFTER `applicable_fare` ;
ALTER TABLE `bterpdb`.`ex_order_airline` CHANGE COLUMN `from` `point` VARCHAR(255) NOT NULL  ;
ALTER TABLE `bterpdb`.`ex_order_hotel` CHANGE COLUMN `usage` `hotel_usage` VARCHAR(255) NULL DEFAULT NULL  ;

# Change Type code From person to supplier
ALTER TABLE `bterpdb`.`person` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(10) NULL COMMENT '**P = Personal / C = Corporate'  ;
ALTER TABLE `bterpdb`.`person` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(10) NULL DEFAULT NULL  ;
ALTER TABLE `bterpdb`.`supplier` CHANGE COLUMN `code` `code` VARCHAR(20) NULL DEFAULT NULL COMMENT '**P = Personal / C = Corporate'  ;

# 1.0.19
# Alter table
# By Ravi
# Added image to country

ALTER TABLE `bterpdb`.`country` ADD COLUMN `image` BLOB NOT NULL  AFTER `upd_by` ;

# 1.0.20
# Alter table
# By Ravi
# Added image to country after name

ALTER TABLE `bterpdb`.`country` CHANGE COLUMN `image` `image` BLOB NOT NULL  AFTER `name` ;

# 1.0.21
# Insert table
# By Ravi
# Inserting sec_fun of Invoice Exchange Order Item

INSERT INTO `bterpdb`.`sec_func` (`ID`, `UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES
('81', 'U_PRODUCT_INV_EO_ITEM', '*', 'PRODUCT_INV_EO_ITEM', 'Invoice And Exchange Order', 'L', '/app/product/InvoiceAndExchangeOrderDetailsItem', '1', '2', '6', 'U_PRODUCT', 'A', '2013-01-04 15:07:03', 'SYSTEM', '2013-01-09 18:20:02', 'Super User', '1');

# 1.0.22
# Alter table
# By Keith
# Drop net fare and applicable face, change exchange order type from not null to null

ALTER TABLE `bterpdb`.`ex_order_item` DROP COLUMN `net_fare` , DROP COLUMN `applicable_fare` ;
ALTER TABLE `bterpdb`.`ex_order` CHANGE COLUMN `pmnt_type_cd` `pmnt_type_cd` VARCHAR(20) NULL DEFAULT NULL COMMENT '**CHQ/CASH/etc.'  ;
ALTER TABLE `bterpdb`.`ex_order` CHANGE COLUMN `is_draft` `is_draft` TINYINT(1) NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes'  , CHANGE COLUMN `is_pending` `is_pending` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `is_billed` `is_billed` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `is_eo_paid` `is_eo_paid` TINYINT(1) NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes'  , CHANGE COLUMN `is_cancelled` `is_cancelled` TINYINT(1) NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes'  ;

# 1.0.23
# By HS
#
-- Alter tbl: missing column in tour package table
ALTER TABLE `bterpdb`.`tour_pkg` ADD COLUMN `session_cd` VARCHAR(10) NOT NULL  AFTER `is_promo` ;

-- Alter tbl: remove account from tour package and move it to tour theme
ALTER TABLE `bterpdb`.`tour_pkg` DROP FOREIGN KEY `fk_tour_pkg_id_acct` ;
ALTER TABLE `bterpdb`.`tour_pkg` DROP COLUMN `id_acct` 
, DROP INDEX `fk_tour_pkg_id_acct` ;

ALTER TABLE `bterpdb`.`tour_theme` ADD COLUMN `id_acct` BIGINT NOT NULL  AFTER `id_tour_cat` , 
  ADD CONSTRAINT `fk_tour_theme_id_acct`
  FOREIGN KEY (`id_acct` )
  REFERENCES `bterpdb`.`account` (`id` )
, ADD INDEX `fk_tour_theme_id_acct` (`id_acct`) ;

# 1.0.24
# By HS
#
-- Alter tbl: rename the column name
ALTER TABLE `bterpdb`.`tour_pkg` CHANGE COLUMN `session_cd` `season_cd` VARCHAR(10) NOT NULL  ;

# 1.0.25
# By Kent
#
-- Create person class table
DROP TABLE IF EXISTS `bterpdb`.`person_class`;
CREATE TABLE  `bterpdb`.`person_class` (
  `id_person` bigint(20) NOT NULL,
  `class_cd` varchar(10) NOT NULL COMMENT '**Blacklisted / VIP / Normal / Staff / Family Members',
  `remarks` text,
  KEY `fk_person_class_id_person` (`id_person`),
  CONSTRAINT `fk_person_class_id_person` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Multiple class for specified person';

-- Create customer remarks table
DROP TABLE IF EXISTS `bterpdb`.`customer_remarks`;
CREATE TABLE  `bterpdb`.`customer_remarks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_cust_corp` bigint(20) NOT NULL,
  `remarks` text,
  `time_stamp` datetime NOT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Add id for table person_lang
DROP TABLE IF EXISTS `bterpdb`.`person_lang`;
CREATE TABLE  `bterpdb`.`person_lang` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_person` bigint(20) NOT NULL,
  `lang_cd` varchar(10) NOT NULL COMMENT '**EN/ZH',
  PRIMARY KEY (`id`),
  KEY `fk_person_lang_id_person` (`id_person`),
  CONSTRAINT `fk_person_lang_id_person` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Multiple languages for specified person';

-- Add id for table person_meal
DROP TABLE IF EXISTS `bterpdb`.`person_meal`;
CREATE TABLE  `bterpdb`.`person_meal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_person` bigint(20) NOT NULL,
  `meal_cd` varchar(10) NOT NULL COMMENT '**HALAL/BEEF/OTHER/etc.',
  `remarks` text,
  PRIMARY KEY (`id`),
  KEY `fk_person_meal_id_person` (`id_person`),
  CONSTRAINT `fk_person_meal_id_person` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Multiple meal preferences for specified person';

-- Add id for table person_class
DROP TABLE IF EXISTS `bterpdb`.`person_class`;
CREATE TABLE  `bterpdb`.`person_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_person` bigint(20) NOT NULL,
  `class_cd` varchar(10) NOT NULL COMMENT '**Blacklisted / VIP / Normal / Staff / Family Members',
  `remarks` text,
  PRIMARY KEY (`id`),
  KEY `fk_person_class_id_person` (`id_person`),
  CONSTRAINT `fk_person_class_id_person` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Multiple class for specified person';

-- Add id for table person_complication
DROP TABLE IF EXISTS `bterpdb`.`person_complication`;
CREATE TABLE  `bterpdb`.`person_complication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_person` bigint(20) NOT NULL,
  `complication_cd` varchar(10) NOT NULL COMMENT '**DISABLE / HA = Heart Attack / etc.',
  `remarks` text,
  PRIMARY KEY (`id`),
  KEY `fk_person_complication_id_person` (`id_person`),
  CONSTRAINT `fk_person_complication_id_person` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Multiple illnesses for specified person';

-- Change corporate_address.type_cd to null
ALTER TABLE `bterpdb`.`corporate_address` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(10) NULL COMMENT '**OFF/BILL'  ;

# 1.0.26
# By HS
#
-- Alter tbl: added status column to differentiate active / inactive
ALTER TABLE `bterpdb`.`airline` ADD COLUMN `status` TINYINT NOT NULL DEFAULT 0 COMMENT '0 = inactive / 1 = active'  AFTER `tkt_validity` ;
ALTER TABLE `bterpdb`.`airline_schedule` ADD COLUMN `status` TINYINT NOT NULL DEFAULT 0 COMMENT '0 = inactive / 1 = active'  AFTER `dt_schedule` ;

-- tour_dep: remove tour operator foreign key from 
ALTER TABLE `bterpdb`.`tour_dep` DROP FOREIGN KEY `fk_tour_dep_id_tour_operator` ;
ALTER TABLE `bterpdb`.`tour_dep` 
DROP INDEX `fk_tour_dep_id_tour_operator` ;
ALTER TABLE `bterpdb`.`tour_dep` CHANGE COLUMN `id_tour_operator` `id_tour_operator` BIGINT(20) NULL  ;

-- tour_dep: remove tour airline schedule foreign key
ALTER TABLE `bterpdb`.`tour_dep` DROP FOREIGN KEY `fk_tour_dep_id_airline_schedule` ;
ALTER TABLE `bterpdb`.`tour_dep` 
DROP INDEX `fk_tour_dep_id_airline_schedule` ;
ALTER TABLE `bterpdb`.`tour_dep` CHANGE COLUMN `id_airline_schedule` `id_airline_schedule` BIGINT(20) NULL  ;

-- tour_dep: add auto increment to id
ALTER TABLE `bterpdb`.`tour_dep` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT  ;

-- tour_pkg: set season_cd and year can be null
ALTER TABLE `bterpdb`.`tour_pkg` CHANGE COLUMN `season_cd` `season_cd` VARCHAR(10) NULL  , CHANGE COLUMN `year` `year` VARCHAR(4) NULL COMMENT 'Year of theme - yyyy'  ;
-- tour_pkg: add columns reserved 1,2,3 for some extra purpose
ALTER TABLE `bterpdb`.`tour_pkg` ADD COLUMN `reserved_1` VARCHAR(255) NULL  AFTER `dt_travel_end` , ADD COLUMN `reserved_2` VARCHAR(255) NULL  AFTER `reserved_1` , ADD COLUMN `reserved_3` VARCHAR(255) NULL  AFTER `reserved_2` ;

-- Add source and destination columns for general ledger purpose
ALTER TABLE `bterpdb`.`account_trans` ADD COLUMN `source` VARCHAR(255) NOT NULL  AFTER `ref_no` , ADD COLUMN `destination` VARCHAR(255) NOT NULL  AFTER `source` , CHANGE COLUMN `description` `description` VARCHAR(255) NULL  ;

-- Country image column can be null
ALTER TABLE `bterpdb`.`country` CHANGE COLUMN `image` `image` VARCHAR(255) NULL  ;

-- tour_itinery: seperated file name and path
ALTER TABLE `bterpdb`.`tour_itinery` ADD COLUMN `path` VARCHAR(255) NOT NULL  AFTER `name` , CHANGE COLUMN `itinery_path` `name` VARCHAR(255) NOT NULL  ;

# 1.0.27
# By Kent
#
-- move dt_issued and dt_expired from person_identity to person_identity_detail
ALTER TABLE `bterpdb`.`person_identity` DROP COLUMN `dt_issued`, DROP COLUMN `dt_expired`;
ALTER TABLE `bterpdb`.`person_identity_detail` ADD COLUMN `dt_expired` DATETIME NOT NULL AFTER `entry_cd`;
ALTER TABLE `bterpdb`.`person_identity_detail` ADD COLUMN `dt_issued` DATETIME NOT NULL AFTER `entry_cd`;

# 1.0.28
# By HS
# Airline module
#
-- remove columns status and tkt_validity
ALTER TABLE `bterpdb`.`airline` DROP COLUMN `status` , DROP COLUMN `tkt_validity` ;

# 1.0.29
# By HS
#
-- add region id to tour category
ALTER TABLE `bterpdb`.`tour_cat` ADD COLUMN `id_region` BIGINT NULL  AFTER `id_acct` ;

# 1.0.30
# By Keith
#
-- modified purchase bill table
ALTER TABLE `bterpdb`.`ex_order_bill` CHANGE COLUMN `remarks` `comments` TEXT NULL DEFAULT NULL  , 
ADD COLUMN `id_supplier` BIGINT(20) NOT NULL  AFTER `id_eo` , 
ADD COLUMN `id_tour_cat` BIGINT(20) NULL  AFTER `id_supplier` , 
ADD COLUMN `dt_due` DATETIME NOT NULL  AFTER `dt_bill` , ADD COLUMN `ex_reason` TEXT NULL  AFTER `comments` , 
  ADD CONSTRAINT `fk_ex_order_bill_id_supplier`
  FOREIGN KEY (`id_supplier` )
  REFERENCES `bterpdb`.`supplier` (`id` )
  ON DELETE RESTRICT
  ON UPDATE RESTRICT
, ADD INDEX `fk_ex_order_bill_id_supplier_idx` (`id_supplier` ASC) ;

ALTER TABLE `bterpdb`.`ex_order_bill` CHANGE COLUMN `code` `code` VARCHAR(20) NULL  , 
CHANGE COLUMN `description` `description` VARCHAR(255) NULL  ;
ALTER TABLE `bterpdb`.`account_trans` CHANGE COLUMN `dt_trans` `dt_trans` DATETIME NULL  ;

#1.0.31
# By HS
#
-- set schedule date time can be null
ALTER TABLE `bterpdb`.`airline_schedule` CHANGE COLUMN `dt_schedule` `dt_schedule` DATETIME NULL COMMENT 'schedule date'  ;

-- changed airport_cd to from_airport_cd and added to_airport_cd
ALTER TABLE `bterpdb`.`airline_schedule_item` ADD COLUMN `to_airport_cd` VARCHAR(10) NOT NULL  AFTER `from_airport_cd` , CHANGE COLUMN `airport_cd` `from_airport_cd` VARCHAR(10) NOT NULL COMMENT '**KUL/TWN/etc.'  ;

-- add character set to hotel name to allow to insert in other language
ALTER TABLE `bterpdb`.`hotel` CHANGE COLUMN `name` `name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL  ;

-- create table airline schedule item charge for record each schdule item charges
CREATE  TABLE `bterpdb`.`airline_schedule_item_charge` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_airline_schedule_item` BIGINT NOT NULL ,
  `type_cd` VARCHAR(10) NULL ,
  `type_desc` VARCHAR(255) NULL ,
  `amount` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_airline_schedule_item_charge_id_airline_schedule_item` (`id_airline_schedule_item`) ,
  CONSTRAINT `fk_airline_schedule_item_charge_id_airline_schedule_item`
    FOREIGN KEY (`id_airline_schedule_item` )
    REFERENCES `bterpdb`.`airline_schedule_item` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COMMENT = 'Airline schedule item charges';
-- add ticket validity in number of days
ALTER TABLE `bterpdb`.`airline_schedule_item` ADD COLUMN `tkt_validity` SMALLINT(3) NOT NULL DEFAULT 0  AFTER `is_next_day` ;

-- add columns for trade debtor account, trade creditor and sundry creditor
ALTER TABLE `bterpdb`.`company` ADD COLUMN `id_acct_sales` BIGINT DEFAULT NULL  AFTER `slogan` , ADD COLUMN `id_acct_purchase_trade` BIGINT DEFAULT NULL  AFTER `id_acct_sales` , ADD COLUMN `id_acct_purchase_sundry` BIGINT DEFAULT NULL  AFTER `id_acct_purchase_trade` ;

-- add default company flag to employee table to control the user company access
ALTER TABLE `bterpdb`.`employee` ADD COLUMN `is_default_comp` TINYINT(1) NOT NULL DEFAULT 0  AFTER `department`;

-- added id auto increment
ALTER TABLE `bterpdb`.`airline_schedule_item` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT;

-- remove person foreign key from employee
ALTER TABLE `bterpdb`.`employee` DROP FOREIGN KEY `fk_employee_id_person`;
ALTER TABLE `bterpdb`.`employee` CHANGE COLUMN `u_sec_user` `u_sec_user` VARCHAR(50) NOT NULL AFTER `id_company`, CHANGE COLUMN `id_person` `id_person` BIGINT(20) NULL;

-- create global configuration table
CREATE  TABLE `bterpdb`.`global_config` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `value` VARCHAR(255) NOT NULL ,
  `remarks` VARCHAR(255) NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  `version` INT NOT NULL ,
  PRIMARY KEY (`id`) )
COMMENT = 'Global configuration';

-- insert superman into employee table
INSERT INTO `bterpdb`.`employee` (`id`, `id_company`, `u_sec_user`, `department`, `is_default_comp`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES (1, 1, 'superman', 'Management', 1, NOW(), 'SYSTEM', NOW(), 'SYSTEM');

# 1.0.32
# By Keith
#
-- added bill amount into purchase bill table
ALTER TABLE `bterpdb`.`ex_order_bill` ADD COLUMN `bill_amt` FLOAT(10,2) NOT NULL DEFAULT 0.00  AFTER `description` ;
ALTER TABLE `bterpdb`.`account_trans` ADD COLUMN `code` VARCHAR(20) NULL  AFTER `destination` ;

# 1.0.33
# By HS
#
-- added function into sec_func - company
INSERT INTO `bterpdb`.`SEC_FUNC` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `STATUS_CD`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_COMPANY', '*', 'COMPANY', 'Company', 'T', '/app/company', '1', 1, 9, 'A', '2013-01-04 15:07:03', 'SYSTEM', '2013-01-04 15:07:03', 'SYSTEM', 1);

# 1.0.34
# By Keith
#
-- changed remarks to reference
ALTER TABLE `bterpdb`.`ex_order` CHANGE COLUMN `remarks` `reference` TEXT NULL DEFAULT NULL  ;



# 1.0.35
# By Ravi
#
--alter the size of code in sys_num_conf
ALTER TABLE `bterpdb`.`sys_num_conf` CHANGE COLUMN `code` `code` VARCHAR(40) NOT NULL  ;
# 1.0.36
# By Ravi
#
--alter the size of code in sys_num_conf
ALTER TABLE `bterpdb`.`sys_num_conf` CHANGE COLUMN `code` `code` VARCHAR(100) NOT NULL  ;

# 1.0.37
# By HS
#
-- delete data from lookup_item table and lookup_cat table
DELETE FROM `bterpdb`.`lookup_item`;
DELETE FROM `bterpdb`.`lookup_cat`;

-- change id_lookup_cat to lookup_cat_cd
ALTER TABLE `bterpdb`.`lookup_item` DROP FOREIGN KEY `fk_lookup_item_id_lookup_cat` ;
ALTER TABLE `bterpdb`.`lookup_item` CHANGE COLUMN `id_lookup_cat` `lookup_cat_cd` VARCHAR(10) NOT NULL COMMENT 'inherit to lookup_cat code'  
, DROP INDEX `fk_lookup_item_lookup_cat_cd` ;

-- add foreign key to lookup_item table
ALTER TABLE `bterpdb`.`lookup_item` 
  ADD CONSTRAINT `fk_lookup_item_lookup_cat_cd`
  FOREIGN KEY (`lookup_cat_cd` )
  REFERENCES `bterpdb`.`lookup_cat` (`code` )
, ADD INDEX `fk_lookup_item_lookup_cat_cd` (`lookup_cat_cd`) ;

-- change code to primary key
ALTER TABLE `bterpdb`.`lookup_cat` 
DROP PRIMARY KEY 
, ADD PRIMARY KEY (`code`) 
, ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC) 
, DROP INDEX `code_UNIQUE` ;

-- add default categories
INSERT INTO `lookup_cat` (`id`, `code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	(1,'id_type','Identification Types','Identification Types','2013-01-23 16:15:26','Super User','2013-03-01 12:18:22','brandon chin'),
	(2,'pymt_type','Payment Types','Payment Types','2013-01-23 16:23:13','Super User','2013-01-23 16:23:13','Super User'),
	(3,'room_type','Room Type','Room Type','2013-01-25 14:25:16','Super User','2013-03-01 12:18:09','brandon chin'),
	(4,'cust_class','Customer Class','Customer Class','2013-02-21 12:00:00','brandon chin','2013-02-21 12:00:00','brandon chin'),
	(5,'bank_tran','Bank Transaction Types','Bank Transaction Types','2013-03-01 12:20:11','brandon chin','2013-03-01 14:50:11','brandon chin'),
	(6,'delv_mthd','Delivery Methods','Delivery Methods','2013-03-01 12:20:41','brandon chin','2013-03-01 12:20:41','brandon chin'),
	(7,'ordr_sorc','Order Source','Order Source','2013-03-01 12:21:18','brandon chin','2013-03-01 12:21:18','brandon chin'),
	(8,'inv_eo_cat','Invoice and EO Categories','Invoice and EO Categories','2013-03-01 12:21:50','brandon chin','2013-03-01 12:21:50','brandon chin'),
	(9,'salutatn','Salutations','Salutations','2013-03-01 12:22:32','brandon chin','2013-03-01 12:22:32','brandon chin'),
	(10,'meal_pref','Meal Preference','Meal Preference','2013-03-01 12:22:52','brandon chin','2013-03-01 12:22:52','brandon chin'),
	(11,'cust_cmpl','Customer Complications','Customer Complications','2013-03-01 12:23:28','brandon chin','2013-03-01 12:23:28','brandon chin'),
	(12,'lang_dilc','Language and Dialects','Language and Dialects','2013-03-01 12:25:14','brandon chin','2013-03-01 12:25:14','brandon chin'),
	(14,'bank_list','Bank Lists','Bank Lists','2013-03-01 12:31:13','brandon chin','2013-03-01 12:31:13','brandon chin'),
	(15,'acct_type','Account Types','Account Types','2013-03-01 12:31:44','brandon chin','2013-03-01 12:31:44','brandon chin'),
	(16,'deln_stat','Delinquency Statuses','Delinquency Statuses','2013-03-01 12:33:04','brandon chin','2013-03-01 12:33:04','brandon chin'),
	(17,'cntc_type','Contact Types','Contact Types','2013-03-01 12:34:28','brandon chin','2013-03-01 12:34:28','brandon chin'),
	(18,'msia_stat','Malaysian States','Malaysian States','2013-03-01 12:35:10','brandon chin','2013-03-01 12:35:10','brandon chin'),
	(19,'arpt_code','Airport Codes','Airport Codes','2013-03-01 12:39:55','brandon chin','2013-03-01 12:39:55','brandon chin'),
	(21,'supl_class','Supplier Class','Supplier Class','2013-03-01 12:47:37','brandon chin','2013-03-01 12:47:37','brandon chin');

-- add default items
INSERT INTO `lookup_item` (`id`, `lookup_cat_cd`, `code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	(1,'id_type','nric','NRIC/Work Permit No','NRIC/Work Permit No','2013-01-23 16:15:48','Super User','2013-01-23 16:22:27','Super User'),
	(2,'id_type','pss_prt','Passport','Passport','2013-01-23 16:16:12','Super User','2013-01-23 16:24:26','Super User'),
	(3,'pymt_type','cash','Cash','Cash','2013-01-23 16:23:49','Super User','2013-01-23 16:23:49','Super User'),
	(4,'pymt_type','crd_card','Credit Card','Credit Card','2013-01-23 16:24:09','Super User','2013-01-23 16:24:09','Super User'),
	(5,'room_type','SGL','Single','Single','2013-01-25 14:25:31','Super User','2013-01-25 14:25:31','Super User'),
	(6,'room_type','DBL','Double','Double','2013-01-25 14:25:46','Super User','2013-01-25 14:25:46','Super User'),
	(7,'cust_class','vip','VIP','VIP','2013-02-21 12:00:31','brandon chin','2013-02-21 12:00:31','brandon chin'),
	(8,'id_type','visa','Visa','Visa','2013-03-01 14:43:36','brandon chin','2013-03-01 14:43:36','brandon chin'),
	(9,'pymt_type','cheq','Cheque','Cheque','2013-03-01 14:44:22','brandon chin','2013-03-01 14:44:22','brandon chin'),
	(10,'bank_tran','cash_deps','Cash Deposit','Cash Deposit','2013-03-01 14:53:12','brandon chin','2013-03-01 14:53:12','brandon chin'),
	(11,'bank_tran','cash_with','Cash Withdrawal','Cash Withdrawal','2013-03-01 14:53:35','brandon chin','2013-03-01 14:53:35','brandon chin');

# 1.0.38
# By Keith
#
--- added status in exchange order bill
ALTER TABLE `bterpdb`.`ex_order_bill` ADD COLUMN `is_pending` TINYINT(1) NULL DEFAULT '0'  
AFTER `description` , ADD COLUMN `is_paid` TINYINT(1) NULL DEFAULT '0'  
AFTER `is_pending` , ADD COLUMN `is_amended` TINYINT(1) NULL DEFAULT '0'  
AFTER `is_paid` , ADD COLUMN `is_cancelled` TINYINT(1) NULL DEFAULT '0'  
AFTER `is_amended` ;

--- remove exchange order foreign key from exchange order bill
ALTER TABLE `bterpdb`.`ex_order_bill` DROP FOREIGN KEY `fk_ex_order_bill_id_eo` ;
ALTER TABLE `bterpdb`.`ex_order_bill` CHANGE COLUMN `id_eo` `id_eo` BIGINT(20) NULL  , 
  ADD CONSTRAINT `fk_ex_order_bill_id_eo`
  FOREIGN KEY (`id_eo` )
  REFERENCES `bterpdb`.`ex_order` (`id` );

# 1.0.39
# By HS
#
-- change all code length to 50
ALTER TABLE `bterpdb`.`account` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  ;
ALTER TABLE `bterpdb`.`account_cat` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  ;
ALTER TABLE `bterpdb`.`account_sub_cat` CHANGE COLUMN `code` `code` VARCHAR(50) NULL DEFAULT NULL  ;
ALTER TABLE `bterpdb`.`airline` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  ;
ALTER TABLE `bterpdb`.`airline_item` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL, CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**ADT/CHD/BOTH/NA'  ;
ALTER TABLE `bterpdb`.`airline_schedule_item` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**DEP/TRAN/DOM/RTN'  , CHANGE COLUMN `from_airport_cd` `from_airport_cd` VARCHAR(50) NOT NULL COMMENT '**KUL/TWN/etc.'  , CHANGE COLUMN `to_airport_cd` `to_airport_cd` VARCHAR(50) NOT NULL  , CHANGE COLUMN `flight_cd` `flight_cd` VARCHAR(50) NOT NULL  ;
ALTER TABLE `bterpdb`.`airline_schedule_item_charge` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NULL DEFAULT NULL  ;
ALTER TABLE `bterpdb`.`bank_address` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(10) NOT NULL COMMENT '**OFF/BILL'  ;
ALTER TABLE `bterpdb`.`bank_contact` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**TEL/FAX'  ;
ALTER TABLE `bterpdb`.`cash_book` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NULL DEFAULT NULL  ;
ALTER TABLE `bterpdb`.`company` CHANGE COLUMN `code` `code` VARCHAR(50) NULL DEFAULT NULL  ;
ALTER TABLE `bterpdb`.`company_address` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**OFF/BILL'  ;
ALTER TABLE `bterpdb`.`company_contact` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**TEL/FAX'  ;
ALTER TABLE `bterpdb`.`corporate_address` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**OFF/BILL'  ;
ALTER TABLE `bterpdb`.`corporate_contact` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**TEL/FAX'  ;
ALTER TABLE `bterpdb`.`country` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL COMMENT 'country code'  ;
ALTER TABLE `bterpdb`.`customer` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  , CHANGE COLUMN `status_cd` `status_cd` VARCHAR(50) NOT NULL COMMENT '**A = Active / I = Inactive'  ;
ALTER TABLE `bterpdb`.`ex_order` CHANGE COLUMN `doc_type_cd` `doc_type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**INV/DN'  , CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**GENERAL/TOUR/etc.'  , CHANGE COLUMN `pmnt_type_cd` `pmnt_type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**CHQ/CASH/etc.'  , CHANGE COLUMN `cat_cd` `cat_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**FAIR/CRUISES/etc.'  , CHANGE COLUMN `delivery_cd` `delivery_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**HAND/POST/etc.'  ;
ALTER TABLE `bterpdb`.`ex_order_bill` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  ;
ALTER TABLE `bterpdb`.`ex_order_bill_pmnt` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  ;
ALTER TABLE `bterpdb`.`ex_order_pax` CHANGE COLUMN `air_cd` `air_cd` VARCHAR(50) NULL DEFAULT NULL  ;
ALTER TABLE `bterpdb`.`global_config` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  ;
ALTER TABLE `bterpdb`.`hotel` CHANGE COLUMN `star_cd` `star_cd` VARCHAR(50) NOT NULL COMMENT '**ONE/TWO/THREE/FOUR/FIVE'  ;
ALTER TABLE `bterpdb`.`hotel_address` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**OFF/BILL'  ;
ALTER TABLE `bterpdb`.`hotel_contact` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**TEL/FAX'  ;
ALTER TABLE `bterpdb`.`hotel_item` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  ;
ALTER TABLE `bterpdb`.`hotel_reservation` CHANGE COLUMN `arrival_flight_cd` `arrival_flight_cd` VARCHAR(50) NULL DEFAULT NULL  , CHANGE COLUMN `dep_flight_cd` `dep_flight_cd` VARCHAR(50) NULL DEFAULT NULL  ;
ALTER TABLE `bterpdb`.`hotel_room` CHANGE COLUMN `room_type_cd` `room_type_cd` VARCHAR(50) NOT NULL COMMENT '**SGL/TWN/etc.'  ;
ALTER TABLE `bterpdb`.`inv_eo_item` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  ;
ALTER TABLE `bterpdb`.`invoice` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  , CHANGE COLUMN `doc_type_cd` `doc_type_cd` VARCHAR(50) NOT NULL COMMENT '**INV/DN'  , CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**GENERAL/TOUR/etc.'  , CHANGE COLUMN `cat_cd` `cat_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**FAIR/CRUISES/etc.'  , CHANGE COLUMN `order_cd` `order_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**FAIR/ADS/etc.'  , CHANGE COLUMN `delivery_cd` `delivery_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**HAND/POST/etc.'  ;
ALTER TABLE `bterpdb`.`invoice_pmnt` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  , CHANGE COLUMN `pmnt_type_cd` `pmnt_type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**CHQ/CASH/etc.'  ;
ALTER TABLE `bterpdb`.`lookup_cat` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL COMMENT 'unique code for lookup category'  ;
ALTER TABLE `bterpdb`.`lookup_item` DROP FOREIGN KEY `fk_lookup_item_lookup_cat_cd` ;
ALTER TABLE `bterpdb`.`lookup_item` CHANGE COLUMN `lookup_cat_cd` `lookup_cat_cd` VARCHAR(50) NOT NULL COMMENT 'inherit to lookup_cat code'  , CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL COMMENT 'lookup item code'  , 
  ADD CONSTRAINT `fk_lookup_item_lookup_cat_cd`
  FOREIGN KEY (`lookup_cat_cd` )
  REFERENCES `bterpdb`.`lookup_cat` (`code` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE `bterpdb`.`person` CHANGE COLUMN `salutation_cd` `salutation_cd` VARCHAR(50) NOT NULL COMMENT '**MR/MRS/MS/etc.'  , CHANGE COLUMN `sex_cd` `sex_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**M = Male / F = Female'  , CHANGE COLUMN `marriage_cd` `marriage_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**MAR = Married / SGL = Single / etc.'  , CHANGE COLUMN `class_cd` `class_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**VIP / BL = Black Listed / etc.'  ;
ALTER TABLE `bterpdb`.`person_address` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**OFF/BILL'  ;
ALTER TABLE `bterpdb`.`person_complication` CHANGE COLUMN `complication_cd` `complication_cd` VARCHAR(50) NOT NULL COMMENT '**DISABLE / HA = Heart Attack / etc.'  ;
ALTER TABLE `bterpdb`.`person_contact` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**TEL/FAX'  ;
ALTER TABLE `bterpdb`.`person_identity` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**NRIC/VISA/PASS'  ;
ALTER TABLE `bterpdb`.`person_identity_detail` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**BUS = Business / SOC = Social /etc.'  , CHANGE COLUMN `entry_cd` `entry_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**SGL = Single / MUL = Multiple /etc.'  ;
ALTER TABLE `bterpdb`.`person_lang` CHANGE COLUMN `lang_cd` `lang_cd` VARCHAR(50) NOT NULL COMMENT '**EN/ZH'  ;
ALTER TABLE `bterpdb`.`person_meal` CHANGE COLUMN `meal_cd` `meal_cd` VARCHAR(50) NOT NULL COMMENT '**HALAL/BEEF/OTHER/etc.'  ;
ALTER TABLE `bterpdb`.`region` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL COMMENT 'region code'  ;
ALTER TABLE `bterpdb`.`sub_region` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL COMMENT 'sub region code'  ;
ALTER TABLE `bterpdb`.`supplier` CHANGE COLUMN `code` `code` VARCHAR(50) NULL DEFAULT NULL COMMENT '**P = Personal / C = Corporate'  , CHANGE COLUMN `status_cd` `status_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**A = Active / I = Inactive'  , CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**SUNDRY/TRADE'  , CHANGE COLUMN `pmnt_type_cd` `pmnt_type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**CHQ/CASH/etc.'  ;
ALTER TABLE `bterpdb`.`sys_num_conf` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL  ;
ALTER TABLE `bterpdb`.`tour_booking` CHANGE COLUMN `order_type_cd` `order_type_cd` VARCHAR(50) NOT NULL COMMENT '**TFAIR/AGT/INHSE'  ;
ALTER TABLE `bterpdb`.`tour_booking_item` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**FTPAX/FTCHILD/FTINFANT/GAPAX/GACHILD/GAINFANT'  ;
ALTER TABLE `bterpdb`.`tour_cat` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**T = Tour / FNE = Free & easy'  ;
ALTER TABLE `bterpdb`.`tour_dep` CHANGE COLUMN `code` `code` VARCHAR(50) NOT NULL COMMENT '*for free & easy will generate automatically, e.g. F000000001'  , CHANGE COLUMN `status_cd` `status_cd` VARCHAR(50) NOT NULL COMMENT '**A = Available / F = Full / C = Close / L = Limited / NO = Not Operating / I = Issued / R = Refer'  ;
ALTER TABLE `bterpdb`.`tour_itinery` CHANGE COLUMN `lang_cd` `lang_cd` VARCHAR(50) NOT NULL COMMENT '**EN/ZH/OTHER'  , CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**CUST/AGENT'  ;
ALTER TABLE `bterpdb`.`tour_operator` CHANGE COLUMN `pmnt_type_cd` `pmnt_type_cd` VARCHAR(50) NULL DEFAULT NULL COMMENT '**CHQ/ATM/CASH/etc.'  ;
ALTER TABLE `bterpdb`.`tour_pkg` CHANGE COLUMN `type_cd` `type_cd` VARCHAR(50) NOT NULL COMMENT '**T = Tour / FNE = Free & easy'  , CHANGE COLUMN `season_cd` `season_cd` VARCHAR(50) NULL DEFAULT NULL  ;

-- insert default seson ref data
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_SEASON_ALL', '*', 'SEASON', 'ALL', 'All Season', 'All season', 1, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_SEASON_SPRING', '*', 'SEASON', 'SPRING', 'Spring', 'Spring', 2, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_SEASON_SUMMER', '*', 'SEASON', 'SUMMER', 'Summer', 'Summer', 3, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_SEASON_AUTUMN', '*', 'SEASON', 'AUTUMN', 'Autumn', 'Autumn', 4, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_SEASON_WINTER', '*', 'SEASON', 'WINTER', 'Winter', 'Winter', 5, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_HOTEL_STAR_ZERO', '*', 'HOTEL_STAR', 'ZERO', 'No', 'No star', 1, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_HOTEL_STAR_ONE', '*', 'HOTEL_STAR', 'ONE', '1', '1 star', 2, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_HOTEL_STAR_TWO', '*', 'HOTEL_STAR', 'TWO', '2', '2 star', 3, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_HOTEL_STAR_THREE', '*', 'HOTEL_STAR', 'THREE', '3', '3 star', 4, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_HOTEL_STAR_FOUR', '*', 'HOTEL_STAR', 'FOUR', '4', '4 star', 5, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_HOTEL_STAR_FIVE', '*', 'HOTEL_STAR', 'FIVE', '5', '5 star', 6, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM', 1);

# 1.0.40
# By Keith
#
--- Added PurchaseBillNumber in System Number Generation
INSERT INTO `bterpdb`.`sys_num_conf` (`id`, `code`, `description`, `next_no`, `prefix_id`, 
`dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('', 'PurchaseBill', 'PurchaseBillNumber', 
'50016', 'PB', '2013-01-23 18:42:02', 'Super User', '2013-01-23 18:42:02', 'Super User');

--- add sys_prefix in account trans table
ALTER TABLE `bterpdb`.`account_trans` ADD COLUMN `sys_prefix` VARCHAR(10) NULL DEFAULT NULL  AFTER `dt_trans` ;

# 1.0.41
# By Kent
#
ALTER TABLE `bterpdb`.`customer_remarks` CHANGE COLUMN `id_cust_corp` `id_cust` BIGINT(20) NOT NULL, ROW_FORMAT = DYNAMIC;
ALTER TABLE `bterpdb`.`customer` CHANGE COLUMN `id_person` `id_pc` BIGINT(20) NOT NULL,
 CHANGE COLUMN `code` `pc_type_cd` VARCHAR(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
 DROP INDEX `fk_customer_id_person`,
 ADD INDEX `fk_customer_id_person` USING BTREE(`id_pc`),
 DROP FOREIGN KEY `fk_customer_id_person`
, ROW_FORMAT = DYNAMIC;
ALTER TABLE `bterpdb`.`customer` DROP INDEX `fk_customer_id_person`
, ROW_FORMAT = DYNAMIC;

# 1.0.42
# By HS
#
-- add airline id to tour pkg
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `id_airline` BIGINT NULL  AFTER `id_tour_pkg` ;

--add tour status cat
INSERT INTO `bterpdb`.`lookup_cat` (`code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`) VALUES ('tour_status', 'Tour Status', 'Tour Status', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- add tour status items
INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	('tour_status','A','Available','Available',NOW(),'SYSTEM',NOW(),'SYSTEM'),
	('tour_status','F','Full','Full',NOW(),'SYSTEM',NOW(),'SYSTEM'),
	('tour_status','C','Close','Close',NOW(),'SYSTEM',NOW(),'SYSTEM'),
	('tour_status','L','Limited','Limited',NOW(),'SYSTEM',NOW(),'SYSTEM'),
	('tour_status','N','Not Operating','Not Operating',NOW(),'SYSTEM',NOW(),'SYSTEM'),
	('tour_status','I','Issued','Issued',NOW(),'SYSTEM',NOW(),'SYSTEM'),
	('tour_status','R','Refer','Refer',NOW(),'SYSTEM',NOW(),'SYSTEM');

-- add language items
INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	('lang_dilc','EN','English','English',NOW(),'SYSTEM',NOW(),'SYSTEM'),
	('lang_dilc','ZH','Chinese','Chinese',NOW(),'SYSTEM',NOW(),'SYSTEM'),
	('lang_dilc','MY','Malay','Malay',NOW(),'SYSTEM',NOW(),'SYSTEM');

-- add column staff incentive to tour_pkg
ALTER TABLE `bterpdb`.`tour_pkg` ADD COLUMN `csi_adt_ac` FLOAT(10,2) NOT NULL DEFAULT 0  AFTER `cpa_adt_si` , ADD COLUMN `csi_adt_si` FLOAT(10,2) NOT NULL DEFAULT 0  AFTER `csi_adt_ac` , CHANGE COLUMN `cna_adt_sl` `cna_adt_si` FLOAT(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cpa_adt_sl` `cpa_adt_si` FLOAT(10,2) NOT NULL DEFAULT '0.00'  ;
-- add column staff incentive to tour_dep
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `csi_adt_ac` FLOAT(10,2) NOT NULL DEFAULT 0  AFTER `cpa_adt_si` , ADD COLUMN `csi_adt_si` FLOAT(10,2) NOT NULL DEFAULT 0  AFTER `csi_adt_ac` , CHANGE COLUMN `cna_adt_sl` `cna_adt_si` FLOAT(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cpa_adt_sl` `cpa_adt_si` FLOAT(10,2) NOT NULL DEFAULT '0.00'  ;

# 1.0.43
# By Ravi
#
--add group_no coloumn to cash_book table

ALTER TABLE `bterpdb`.`cash_book` ADD COLUMN `group_no` INT NOT NULL  AFTER `upd_by` ;

ALTER TABLE `bterpdb`.`cash_book` CHANGE COLUMN `group_no` `group_no` INT(11) NOT NULL  AFTER `type_cd` ;

ALTER TABLE `bterpdb`.`cash_book` CHANGE COLUMN `group_no` `group_no` INT(11) NULL DEFAULT NULL  ;

# 1.0.44
# By Keith
#
--- changed descripton and code to null
ALTER TABLE `bterpdb`.`ex_order_bill_pmnt` CHANGE COLUMN `descripton` `description` VARCHAR(255) NOT NULL  ;
ALTER TABLE `bterpdb`.`ex_order_bill_pmnt` CHANGE COLUMN `code` `code` VARCHAR(50) NULL  ;
ALTER TABLE `bterpdb`.`ex_order_bill_pmnt` CHANGE COLUMN `description` `description` VARCHAR(255) NULL  ;

--- added Exchange Order Number
INSERT INTO `bterpdb`.`sys_num_conf` (`id`, `code`, `description`, `next_no`, `prefix_id`, 
`dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('', 'ExchangeOrder', 'PurchaseExchangeOrder', 
'70716', 'EO', '2013-01-23 18:42:02', 'Super User', '2013-01-23 18:42:02', 'Super User');

# 1.0.45
# By HS
#
CREATE  TABLE `bterpdb`.`db_tracking` (
  `version` VARCHAR(255) NOT NULL ,
  `dt_created` DATETIME NULL ,
  `dt_upd` DATETIME NULL ,
  PRIMARY KEY (`version`) )
COMMENT = 'for db script version tracking';

INSERT INTO `bterpdb`.`db_tracking` VALUES ('1.0.45', NOW(), NOW());

# 1.0.46
# By HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.46', dt_upd = NOW();

-- add flight fee and charges
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `misc_adt` FLOAT(10,2) NOT NULL DEFAULT 0 COMMENT 'total flight fee and charges for adult'  AFTER `csi_adt_si` , ADD COLUMN `misc_chd` FLOAT(10,2) NOT NULL DEFAULT 0 COMMENT 'total flight fee and charges for child'  AFTER `misc_adt` ;
-- add tfair discount
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `tfair_discount` FLOAT(10,2) NOT NULL DEFAULT 0  AFTER `tour_mgr_cost` ;

# 1.0.47
# By Ravi
#

-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.47', dt_upd = NOW();

---add refund to sec_fun and add bank deposit and refund to system number configuration
INSERT INTO `bterpdb`.`sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_BANK_REFUND', '*', 'BANK_REFUND', 'Refund', 'L', '/app/bank/refund', '1', '2', '7', 'U_BANK', 'A', '2013-01-04 15:07:03', 'SYSTEM', '2013-01-04 15:07:03', 'SYSTEM', '1');

INSERT INTO `bterpdb`.`sys_num_conf` (`code`, `description`, `next_no`, `prefix_id`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('Refund Application', 'Refund Application', '1', 'RA', '2013-01-23 18:42:02', 'ravi', '2013-03-16 16:35:40', 'Super User');

INSERT INTO `bterpdb`.`sys_num_conf` (`code`, `description`, `next_no`, `prefix_id`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('BankDepositDetails', 'BankDeposit', '148472', 'BD', '2013-03-08 18:03:40', 'ravi', '2013-03-16 15:06:10', 'Super User');

# 1.0.48
# By Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.48', dt_upd = NOW();

-- added amount paid in exchange order bill
ALTER TABLE `bterpdb`.`ex_order_bill` ADD COLUMN `amt_paid` FLOAT(10,2) NULL DEFAULT '0.00'  AFTER `upd_by` ;
ALTER TABLE `bterpdb`.`ex_order_bill` CHANGE COLUMN `amt_paid` `amt_paid` FLOAT(10,2) NULL DEFAULT '0.00'  AFTER `bill_amt` ;

# 1.0.49
# By HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.49', dt_upd = NOW();

-- insert booking function
INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_SALES_BOOKING', 'SALES_BOOKING', 'Booking', 'L', '/app/sales/booking', '', '1', 2, 5, 'U_SALES', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

-- change id_person to id_cust
ALTER TABLE `bterpdb`.`tour_booking` DROP FOREIGN KEY `fk_tour_booking_id_person` ;
ALTER TABLE `bterpdb`.`tour_booking` CHANGE COLUMN `id_person` `id_cust` BIGINT(20) NOT NULL  , 
  ADD CONSTRAINT `fk_tour_booking_id_cust`
  FOREIGN KEY (`id_cust` )
  REFERENCES `bterpdb`.`customer` (`id` );

-- add id_employee
ALTER TABLE `bterpdb`.`tour_booking` ADD COLUMN `id_employee` BIGINT NOT NULL  AFTER `id_tour_dep` , 
  ADD CONSTRAINT `fk_tour_booking_id_employee`
  FOREIGN KEY (`id_employee` )
  REFERENCES `bterpdb`.`employee` (`id` )
, ADD INDEX `fk_tour_booking_id_employee` (`id_employee`) ;

-- add booking type to lookup
INSERT INTO `bterpdb`.`lookup_cat` (`code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('booking_type', 'Booking Type', 'Booking Type', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('booking_type', 'W', 'Walk-In', 'Walk-In', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('booking_type', 'T', 'TFair', 'TFair', 'SYSTEM', NOW(), 'SYSTEM');

-- add contact type to lookup
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('cntc_type', 'mobile', 'Mobile No', 'Mobile No', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('cntc_type', 'office', 'Office No', 'Office No', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('cntc_type', 'home', 'Home No', 'Home No', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('cntc_type', 'fax', 'Fax No', 'Fax No', 'SYSTEM', NOW(), 'SYSTEM');

# 1.0.50
# By Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.50', dt_upd = NOW();

-- added status
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_STATUS_PENDING', '*', 'STATUS', 'PE', 'Pending', 'Pending status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_STATUS_BILLED', '*', 'STATUS', 'B', 'Billed', 'Billed status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_STATUS_PAID', '*', 'STATUS', 'PD', 'Paid', 'Paid status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_STATUS_CANCELLED', '*', 'STATUS', 'CC', 'Cancelled', 'Cancelled status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_STATUS_VOID', '*', 'STATUS', 'V', 'Void', 'Void status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_STATUS_CONFIRMED', '*', 'STATUS', 'CF', 'Confirmed', 'Confirmed status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_BILL_STATUS_PAID', '*', 'BILL_STATUS', 'PD', 'Paid', 'Paid status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
UPDATE `bterpdb`.`COM_REF_DATA` SET `UUID`='U_EO_STATUS_BILLED', `CAT_CD`='EO_STATUS', `REF_CD`='BL' WHERE `ID`='23';
UPDATE `bterpdb`.`COM_REF_DATA` SET `UUID`='U_BILL_STATUS_BILLED', `CAT_CD`='BILL_STATUS', `REF_CD`='BL', `REF_VALUE`='Billed', `REF_DESC`='Billed status' WHERE `ID`='26';
UPDATE `bterpdb`.`COM_REF_DATA` SET `UUID`='U_EO_STATUS_PENDING', `CAT_CD`='EO_STATUS' WHERE `ID`='22';
UPDATE `bterpdb`.`COM_REF_DATA` SET `UUID`='U_EO_STATUS_VOID', `CAT_CD`='EO_STATUS', `REF_CD`='VD', `REF_VALUE`='Void', `REF_DESC`='Void status' WHERE `ID`='24';
UPDATE `bterpdb`.`COM_REF_DATA` SET `UUID`='U_EO_STATUS_CANCELLED', `CAT_CD`='EO_STATUS' WHERE `ID`='25';
UPDATE `bterpdb`.`COM_REF_DATA` SET `UUID`='U_BILL_STATUS_CANCELLED', `CAT_CD`='BILL_STATUS', `REF_CD`='CC', `REF_VALUE`='Cancelled', `REF_DESC`='Cancelled status' WHERE `ID`='27';
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_BILL_STATUS_VOID', '*', 'BILL_STATUS', 'VD', 'Void', 'Void status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_INV_STATUS_BILLED', '*', 'INV_STATUS', 'BL', 'Billed', 'Billed status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_INV_STATUS_CANCELLED', '*', 'INV_STATUS', 'CC', 'Cancelled', 'Cancelled status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_INV_STATUS_PAID', '*', 'INV_STATUS', 'PD', 'Paid', 'Paid status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_INV_STATUS_VOID', '*', 'INV_STATUS', 'VD', 'Void', 'Void status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_BOOK_STATUS_BOOKED', '*', 'BOOK_STATUS', 'BK', 'Booked', 'Booked status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_BOOK_STATUS_CANCELLED', '*', 'BOOK_STATUS', 'CC', 'Cancelled', 'Cancelled status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_BOOK_STATUS_CONFIRMED', '*', 'BOOK_STATUS', 'CF', 'Confirmed', 'Confirmed status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_BOOK_STATUS_VOID', '*', 'BOOK_STATUS', 'VD', 'Void', 'Void status', '1', 'A', 'N', 'Y', '2013-02-26 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');

-- updated is_draft to status and deleted the rest
ALTER TABLE `bterpdb`.`ex_order` DROP COLUMN `is_cancelled` , DROP COLUMN `is_eo_paid` , DROP COLUMN `is_billed` , 
DROP COLUMN `is_pending` , CHANGE COLUMN `is_draft` `status` VARCHAR(10) NULL DEFAULT NULL  ;

-- updated is_pending to status and deleted the rest
ALTER TABLE `bterpdb`.`ex_order_bill` DROP COLUMN `is_cancelled` , DROP COLUMN `is_amended` , DROP COLUMN `is_paid` , 
CHANGE COLUMN `is_pending` `status` VARCHAR(10) NULL DEFAULT NULL  ;

# 1.0.51
# By Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.51', dt_upd = NOW();

-- drop foreign key
ALTER TABLE `bterpdb`.`ex_order_bill` DROP FOREIGN KEY `fk_ex_order_bill_id_eo` ;

-- change status to status_cd
ALTER TABLE `bterpdb`.`ex_order` CHANGE COLUMN `status` `status_cd` VARCHAR(10) NULL DEFAULT NULL  ;
ALTER TABLE `bterpdb`.`ex_order_bill` CHANGE COLUMN `status` `status_cd` VARCHAR(10) NULL DEFAULT NULL  ;

# 1.0.52
# By Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.52', dt_upd = NOW();

ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `pmnt_type_cd` VARCHAR(20) NULL DEFAULT NULL COMMENT '**CHQ/CASH/etc.' AFTER `attn_to`;
ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `dt_departure` datetime NOT NULL AFTER `id_tour_dep`;
ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `subj_line` VARCHAR(255) NULL DEFAULT NULL AFTER `is_inv_paid`;
ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `remarks` text AFTER `subj_line`;
ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `status_cd` VARCHAR(50) NULL DEFAULT NULL AFTER `balance`;


INSERT INTO `bterpdb`.`lookup_cat` (`code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	('inv_cat','Invoice Category','Invoice Category',SYSDATE(),'Super User',SYSDATE(),'Super User');
	
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	('inv_cat','ge','General','General',SYSDATE(),'Super User',SYSDATE(),'Super User'),
	('inv_cat','ti','Ticketing','Ticketing',SYSDATE(),'Super User',SYSDATE(),'Super User'),
	('inv_cat','to','Tour','Tour',SYSDATE(),'Super User',SYSDATE(),'Super User'),
	('inv_cat','in','Inbound','Inbound',SYSDATE(),'Super User',SYSDATE(),'Super User');


INSERT INTO `bterpdb`.`com_ref_data` (`UUID`,`APP_ID`,`CAT_CD`,`REF_CD`,`REF_VALUE`,`REF_DESC`,`SEQ_NO`,`STATUS_CD`,`IS_DEFAULT`,`IS_DISPLAY`,`DT_CREATED`,`CREATED_BY`,`DT_UPD`,`UPD_BY`,`VERSION`)
VALUES
  ('U_DOC_TYPE_INVOICE','*','DOC_TYPE','I','Invoice','Document type for invoice',1,'A','N','Y',SYSDATE(),'SYSTEM',SYSDATE(),'SYSTEM',1),
  ('U_DOC_TYPE_DEBIT_NOTE','*','DOC_TYPE','D','Debit Note','Document type for debit note',2,'A','N','Y',SYSDATE(),'SYSTEM',SYSDATE(),'SYSTEM',1);


ALTER TABLE `bterpdb`.`invoice` MODIFY COLUMN `code` VARCHAR(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL
, ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `bterpdb`.`invoice_pax`;
CREATE TABLE  `bterpdb`.`invoice_pax` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_inv` bigint(20) NOT NULL,
  `id_cust` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_pax_id_inv` (`id_inv`),
  KEY `fk_invoice_pax_id_cust` (`id_cust`),
  CONSTRAINT `fk_invoice_pax_id_cust` FOREIGN KEY (`id_cust`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_invoice_pax_id_inv` FOREIGN KEY (`id_inv`) REFERENCES `invoice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COMMENT='';


INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) 
VALUES
('meal_pref', 'vege', 'Vegetarian', 'Vegetarian', 'SYSTEM', NOW(), 'SYSTEM'),
('meal_pref', 'nonbeef', 'Non-beef', 'Non-beef', 'SYSTEM', NOW(), 'SYSTEM'),
('meal_pref', 'muslim', 'Muslim', 'Muslim', 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) 
VALUES
('cust_cmpl', 'bbt', 'Diabetes', 'Diabetes', 'SYSTEM', NOW(), 'SYSTEM'),
('cust_cmpl', 'hbp', 'High Blood Pressure', 'Diabetes', 'SYSTEM', NOW(), 'SYSTEM'),
('cust_cmpl', 'hdc', 'Handicapped', 'Handicapped', 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) 
VALUES
('cust_class', 'bl', 'Blacklisted', 'Blacklisted', 'SYSTEM', NOW(), 'SYSTEM'),
('cust_class', 'vip', 'VIP', 'VIP', 'SYSTEM', NOW(), 'SYSTEM'),
('cust_class', 'nor', 'Normal', 'Normal', 'SYSTEM', NOW(), 'SYSTEM'),
('cust_class', 'stf', 'Staff', 'Staff', 'SYSTEM', NOW(), 'SYSTEM'),
('cust_class', 'fm', 'Family Members', 'Family Members', 'SYSTEM', NOW(), 'SYSTEM');

# 1.0.53
# By Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.53', dt_upd = NOW();

INSERT INTO `bterpdb`.`sys_num_conf` (`code`, `description`, `next_no`, `prefix_id`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('BankAdjustment', 'Bank Adjustment', '9', 'BA', '2013-01-23 18:42:02', 'ravi', '2013-03-18 13:32:18', 'Super User');

-- add sys_prefix column to cash_book table
ALTER TABLE `bterpdb`.`cash_book` ADD COLUMN `sys_prefix` VARCHAR(10) NULL DEFAULT NULL  AFTER `dt_trans` ;

# 1.0.54
# By HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.54', dt_upd = NOW();

-- add payment status to tour booking
ALTER TABLE `bterpdb`.`tour_booking` ADD COLUMN `pmnt_status_cd` VARCHAR(50) NOT NULL DEFAULT 'KIV'  AFTER `quantity` ;

-- add book pax type in lookup
--INSERT INTO `bterpdb`.`lookup_cat` (`code`, `description`, `remarks`, `dt_created`, `dt_upd`, `upd_by`) VALUES ('book_pax_type', 'Booking Pax Type', 'Booking Pax Type', 'SYSTEM', NOW(), 'SYSTEM');
--INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('book_pax_type', 'ft_adt', 'FT Adult', 'FT Adult', 'SYSTEM', NOW(), 'SYSTEM');
--INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('book_pax_type', 'ft_chd', 'FT Child', 'FT Child', 'SYSTEM', NOW(), 'SYSTEM');
--INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('book_pax_type', 'ft_inft', 'FT Infant', 'FT Infant', 'SYSTEM', NOW(), 'SYSTEM');
--INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('book_pax_type', 'ga_adt', 'GA Adult', 'GA Adult', 'SYSTEM', NOW(), 'SYSTEM');
--INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('book_pax_type', 'ga_chd', 'GA Child', 'GA Child', 'SYSTEM', NOW(), 'SYSTEM');
--INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `created_by`, `dt_upd`, `upd_by`) VALUES ('book_pax_type', 'ga_inft', 'GA Infant', 'GA Infant', 'SYSTEM', NOW(), 'SYSTEM');

-- add status code to booking table to differenciate the status
ALTER TABLE `bterpdb`.`tour_booking` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL  AFTER `pmnt_status_cd` ;

-- lookup item change walk-in to in-house
UPDATE `bterpdb`.`lookup_item` SET `description`='In-House' WHERE `lookup_cat_cd`='booking_type' AND `code`='I';

-- add booking id to invoice table
ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `id_tour_booking` BIGINT NULL  AFTER `id_acct` ;

-- add booking paid status to ref data
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_PMNT_STATUS_FULL', '*', 'BOOK_PMNT_STATUS', 'FULL', 'Full', 'Full paid', 1, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_PMNT_STATUS_DEP', '*', 'BOOK_PMNT_STATUS', 'DEP', 'Deposit', 'Deposit only', 2, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_PMNT_STATUS_KIV', '*', 'BOOK_PMNT_STATUS', 'KIV', 'KIV', 'Not make any payment', 3, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_PAX_FT_ADT', '*', 'BOOK_PAX_TYPE', 'FT_ADT', 'FT Adult', 'Full tour adult', 1, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_PAX_FT_CHD', '*', 'BOOK_PAX_TYPE', 'FT_CHD', 'FT Child', 'Full tour child', 2, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_PAX_FT_INFT', '*', 'BOOK_PAX_TYPE', 'FT_INFT', 'FT Infant', 'Full tour infant', 3, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_PAX_GA_ADT', '*', 'BOOK_PAX_TYPE', 'GA_ADT', 'GA Adult', 'Ground only adult', 4, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_PAX_GA_CHD', '*', 'BOOK_PAX_TYPE', 'GA_CHD', 'GA Child', 'Ground only child', 5, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_PAX_GA_INFT', '*', 'BOOK_PAX_TYPE', 'GA_INFT', 'GA Infant', 'Ground only infant', 6, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');

-- add sequance column
ALTER TABLE `bterpdb`.`global_config` ADD COLUMN `seq` SMALLINT NOT NULL DEFAULT 1  AFTER `remarks` ;

-- add global info - kiv and paid expiry
INSERT INTO `bterpdb`.`global_config` (`code`, `description`, `value`, `remarks`, `seq`, `created_by`, `dt_upd`, `upd_by`) VALUES ('KIV_EXP', 'KIV Expiry', '3', '3 days', 1, 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`global_config` (`code`, `description`, `value`, `remarks`, `seq`, `created_by`, `dt_upd`, `upd_by`) VALUES ('PAID_EXP', 'Paid Expiry', '30', '30 days before the tour start', 2, 'SYSTEM', NOW(), 'SYSTEM');

# 1.0.55
# By Keith
#

-- combine multiple bills and single bill payment
UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_NAME`='Bills Payment', `URI_ENTRY`='/app/purchase/bill/pmnt' WHERE `ID`='39';
UPDATE `bterpdb`.`SEC_FUNC` SET `STATUS_CD`='S' WHERE `ID`='40';
UPDATE `bterpdb`.`SEC_FUNC` SET `STATUS_CD`='S' WHERE `ID`='41';

# 1.0.56
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.56', dt_upd = NOW();

-- agent booking type
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('booking_type', 'A', 'Agent', 'Agent', '2013-03-21 11:23:59', 'SYSTEM', '2013-03-21 11:23:59', 'SYSTEM');

-- delete booking type from lookup item
DELETE FROM `bterpdb`.`lookup_item` WHERE `lookup_cat_cd`='booking_type' AND `code`='T';
DELETE FROM `bterpdb`.`lookup_item` WHERE `lookup_cat_cd`='booking_type' AND `code`='I';
DELETE FROM `bterpdb`.`lookup_item` WHERE `lookup_cat_cd`='booking_type' AND `code`='W';
DELETE FROM `bterpdb`.`lookup_item` WHERE `lookup_cat_cd`='booking_type' AND `code`='A';

INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_TYPE_INHSE', '*', 'BOOK_TYPE', 'I', 'In-House', 'In house', 1, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_TYPE_TFAIR', '*', 'BOOK_TYPE', 'T', 'TFair', 'TFair', 2, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_BOOK_TYPE_AGENT', '*', 'BOOK_TYPE', 'A', 'Agent', 'Agent', 3, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');

# 1.0.57
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.57', dt_upd = NOW();

-- re-dump lookup item
DELETE FROM `bterpdb`.`tmp_msg`;
DELETE FROM `bterpdb`.`lookup_item`;
DELETE FROM `bterpdb`.`lookup_cat`;

INSERT INTO `bterpdb`.`lookup_cat` (`id`, `code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	(19,'arpt_code','Airport Codes','Airport Codes','2013-03-01 12:39:55','brandon chin','2013-03-01 12:39:55','brandon chin'),
	(15,'bank_acct_type','Bank Account Types','Bank Account Types','2013-03-01 12:31:44','brandon chin','2013-03-25 11:10:22','brandon chin'),
	(14,'bank_list','Bank Lists','Bank Lists','2013-03-01 12:31:13','brandon chin','2013-03-01 12:31:13','brandon chin'),
	(5,'bank_tran','Bank Transaction Types','Bank Transaction Types','2013-03-01 12:20:11','brandon chin','2013-03-01 14:50:11','brandon chin'),
	(24,'booking_type','Booking Type','Booking Type','2013-03-20 15:02:50','SYSTEM','2013-03-20 15:02:50','SYSTEM'),
	(26,'book_pax_type','Booking Pax Type','Booking Pax Type','0000-00-00 00:00:00','','2013-03-26 11:01:43','SYSTEM'),
	(22,'cb_tt','Cash Book Transaction Type','Cash Book Transaction Type','2013-03-13 17:19:28','brandon chin','2013-03-13 17:19:28','brandon chin'),
	(17,'cntc_type','Contact Types','Contact Types','2013-03-01 12:34:28','brandon chin','2013-03-01 12:34:28','brandon chin'),
	(4,'cust_class','Customer Class','Customer Class','2013-02-21 12:00:00','brandon chin','2013-02-21 12:00:00','brandon chin'),
	(11,'cust_cmpl','Customer Complications','Customer Complications','2013-03-01 12:23:28','brandon chin','2013-03-01 12:23:28','brandon chin'),
	(16,'deln_stat','Delinquency Statuses','Delinquency Statuses','2013-03-01 12:33:04','brandon chin','2013-03-01 12:33:04','brandon chin'),
	(6,'delv_mthd','Delivery Method','Delivery Method','2013-03-01 12:20:41','brandon chin','2013-03-23 15:09:28','brandon chin'),
	(1,'id_type','Identification Types','Identification Types','2013-01-23 16:15:26','Super User','2013-03-01 12:18:22','brandon chin'),
	(25,'inv_cat','Invoice Category','Invoice Category','2013-03-22 17:06:29','Super User','2013-03-22 17:06:29','Super User'),
	(8,'inv_eo_cat','Invoice and EO Categories','Invoice and EO Categories','2013-03-01 12:21:50','brandon chin','2013-03-01 12:21:50','brandon chin'),
	(12,'lang_dilc','Language and Dialects','Language and Dialects','2013-03-01 12:25:14','brandon chin','2013-03-01 12:25:14','brandon chin'),
	(10,'meal_pref','Meal Preference','Meal Preference','2013-03-01 12:22:52','brandon chin','2013-03-01 12:22:52','brandon chin'),
	(18,'msia_stat','Malaysian States','Malaysian States','2013-03-01 12:35:10','brandon chin','2013-03-01 12:35:10','brandon chin'),
	(7,'ordr_sorc','Order Source','Order Source','2013-03-01 12:21:18','brandon chin','2013-03-01 12:21:18','brandon chin'),
	(2,'pymt_type','Payment Types','Payment Types','2013-01-23 16:23:13','Super User','2013-01-23 16:23:13','Super User'),
	(3,'room_type','Room Type','Room Type','2013-01-25 14:25:16','Super User','2013-03-01 12:18:09','brandon chin'),
	(9,'salutatn','Salutations','Salutations','2013-03-01 12:22:32','brandon chin','2013-03-01 12:22:32','brandon chin'),
	(21,'supl_class','Supplier Class','Supplier Class','2013-03-01 12:47:37','brandon chin','2013-03-01 12:47:37','brandon chin'),
	(23,'tour_status','Tour Status','Tour Status','2013-03-14 17:46:30','brandon chin','2013-03-01 12:47:37','brandon chin');


INSERT INTO `bterpdb`.`lookup_item` (`id`, `lookup_cat_cd`, `code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	(1,'id_type','nric','NRIC/Work Permit No','NRIC/Work Permit No','2013-01-23 16:15:48','Super User','2013-01-23 16:22:27','Super User'),
	(2,'id_type','pss_prt','Passport','Passport','2013-01-23 16:16:12','Super User','2013-01-23 16:24:26','Super User'),
	(3,'pymt_type','cash','Cash','Cash','2013-01-23 16:23:49','Super User','2013-01-23 16:23:49','Super User'),
	(4,'pymt_type','crd_card','Credit Card','Credit Card','2013-01-23 16:24:09','Super User','2013-01-23 16:24:09','Super User'),
	(5,'room_type','SGL','Single','Single','2013-01-25 14:25:31','Super User','2013-01-25 14:25:31','Super User'),
	(6,'room_type','DBL','Double','Double','2013-01-25 14:25:46','Super User','2013-01-25 14:25:46','Super User'),
	(7,'cust_class','vip','VIP','VIP','2013-02-21 12:00:31','brandon chin','2013-02-21 12:00:31','brandon chin'),
	(8,'id_type','visa','Visa','Visa','2013-03-01 14:43:36','brandon chin','2013-03-01 14:43:36','brandon chin'),
	(9,'pymt_type','cheq','Cheque','Cheque','2013-03-01 14:44:22','brandon chin','2013-03-01 14:44:22','brandon chin'),
	(10,'bank_tran','cash_deps','Cash Deposit','Cash Deposit','2013-03-01 14:53:12','brandon chin','2013-03-01 14:53:12','brandon chin'),
	(11,'bank_tran','cash_with','Cash Withdrawal','Cash Withdrawal','2013-03-01 14:53:35','brandon chin','2013-03-01 14:53:35','brandon chin'),
	(13,'cb_tt','gnrl_deps','General Deposit','General Deposit','2013-03-13 17:20:15','brandon chin','2013-03-13 17:20:15','brandon chin'),
	(15,'cb_tt','gnrl_pymt','General Payment','General Payment','2013-03-13 17:23:24','brandon chin','2013-03-13 17:23:24','brandon chin'),
	(16,'bank_tran','refund','Refund','Refund','2013-03-14 16:56:06','brandon chin','2013-03-14 16:56:06','brandon chin'),
	(17,'cb_tt','gnrl_rfnd','General Refund','General Refund','2013-03-14 16:56:44','brandon chin','2013-03-14 16:56:44','brandon chin'),
	(39,'tour_status','A','Available','Available','2013-03-14 17:48:50','SYSTEM','2013-03-14 17:48:50','SYSTEM'),
	(40,'tour_status','F','Full','Full','2013-03-14 17:48:50','SYSTEM','2013-03-14 17:48:50','SYSTEM'),
	(41,'tour_status','C','Close','Close','2013-03-14 17:48:50','SYSTEM','2013-03-14 17:48:50','SYSTEM'),
	(42,'tour_status','L','Limited','Limited','2013-03-14 17:48:50','SYSTEM','2013-03-14 17:48:50','SYSTEM'),
	(43,'tour_status','N','Not Operating','Not Operating','2013-03-14 17:48:50','SYSTEM','2013-03-14 17:48:50','SYSTEM'),
	(44,'tour_status','I','Issued','Issued','2013-03-14 17:48:50','SYSTEM','2013-03-14 17:48:50','SYSTEM'),
	(45,'tour_status','R','Refer','Refer','2013-03-14 17:48:50','SYSTEM','2013-03-14 17:48:50','SYSTEM'),
	(46,'lang_dilc','EN','English','English','2013-03-14 17:49:10','SYSTEM','2013-03-14 17:49:10','SYSTEM'),
	(47,'lang_dilc','ZH','Chinese','Chinese','2013-03-14 17:49:10','SYSTEM','2013-03-14 17:49:10','SYSTEM'),
	(48,'lang_dilc','MY','Malay','Malay','2013-03-14 17:49:10','SYSTEM','2013-03-14 17:49:10','SYSTEM'),
	(49,'salutatn','mr','Mr.','Mr.','2013-03-15 13:00:36','brandon chin','2013-03-15 13:00:36','brandon chin'),
	(52,'salutatn','ms','Ms.','Ms.','2013-03-15 13:02:09','brandon chin','2013-03-15 13:02:09','brandon chin'),
	(53,'salutatn','mrs','Mrs.','Mrs.','2013-03-15 13:02:19','brandon chin','2013-03-15 13:02:19','brandon chin'),
	(56,'cntc_type','mobile','Mobile No','Mobile No','2013-03-20 15:02:50','SYSTEM','2013-03-20 15:02:50','SYSTEM'),
	(57,'cntc_type','office','Office No','Office No','2013-03-20 15:02:50','SYSTEM','2013-03-20 15:02:50','SYSTEM'),
	(58,'cntc_type','home','Home No','Home No','2013-03-20 15:02:50','SYSTEM','2013-03-20 15:02:50','SYSTEM'),
	(59,'cntc_type','fax','Fax No','Fax No','2013-03-20 15:02:50','SYSTEM','2013-03-20 15:02:50','SYSTEM'),
	(60,'inv_cat','ge','General','General','2013-03-22 17:06:29','Super User','2013-03-22 17:06:29','Super User'),
	(61,'inv_cat','ti','Ticketing','Ticketing','2013-03-22 17:06:29','Super User','2013-03-22 17:06:29','Super User'),
	(62,'inv_cat','to','Tour','Tour','2013-03-22 17:06:29','Super User','2013-03-22 17:06:29','Super User'),
	(63,'inv_cat','in','Inbound','Inbound','2013-03-22 17:06:29','Super User','2013-03-22 17:06:29','Super User'),
	(64,'meal_pref','vege','Vegetarian','Vegetarian','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(65,'meal_pref','nonbeef','Non-beef','Non-beef','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(66,'meal_pref','muslim','Muslim','Muslim','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(67,'cust_cmpl','bbt','Diabetes','Diabetes','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(68,'cust_cmpl','hbp','High Blood Pressure','Diabetes','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(69,'cust_cmpl','hdc','Handicapped','Handicapped','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(70,'cust_class','bl','Blacklisted','Blacklisted','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(71,'cust_class','vip','VIP','VIP','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(72,'cust_class','nor','Normal','Normal','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(73,'cust_class','stf','Staff','Staff','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(74,'cust_class','fm','Family Members','Family Members','2013-03-22 17:06:29','SYSTEM','2013-03-22 17:06:29','SYSTEM'),
	(75,'ordr_sorc','in_house','In House','In House','2013-03-23 15:06:51','brandon chin','2013-03-23 15:06:51','brandon chin'),
	(76,'delv_mthd','by_hand','By Hand','By Hand','2013-03-23 15:10:47','brandon chin','2013-03-23 15:10:47','brandon chin'),
	(77,'bank_acct_type','save_acct','Savings Account','Savings Account','2013-03-25 11:11:11','brandon chin','2013-03-25 11:13:51','brandon chin'),
	(78,'bank_acct_type','crnt_acct','Current Account','Current Account','2013-03-25 11:12:05','brandon chin','2013-03-25 11:13:57','brandon chin'),
	(79,'bank_acct_type','ptty_cash','Petty Cash','Petty Cash','2013-03-25 11:12:20','brandon chin','2013-03-25 11:14:01','brandon chin'),
	(80,'book_pax_type','ft_adt','FT Adult','FT Adult','2013-03-26 11:01:43','SYSTEM','2013-03-26 11:01:43','SYSTEM'),
	(81,'book_pax_type','ft_chd','FT Child','FT Child','2013-03-26 11:01:43','SYSTEM','2013-03-26 11:01:43','SYSTEM'),
	(82,'book_pax_type','ft_inft','FT Infant','FT Infant','2013-03-26 11:01:43','SYSTEM','2013-03-26 11:01:43','SYSTEM'),
	(83,'book_pax_type','ga_adt','GA Adult','GA Adult','2013-03-26 11:01:43','SYSTEM','2013-03-26 11:01:43','SYSTEM'),
	(84,'book_pax_type','ga_chd','GA Child','GA Child','2013-03-26 11:01:43','SYSTEM','2013-03-26 11:01:43','SYSTEM'),
	(85,'book_pax_type','ga_inft','GA Infant','GA Infant','2013-03-26 11:01:43','SYSTEM','2013-03-26 11:01:43','SYSTEM');

-- bank add status code column
ALTER TABLE `bterpdb`.`bank` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `dt_last_recon` ;

-- change commission column in tour pkg
ALTER TABLE `bterpdb`.`tour_pkg` DROP COLUMN `csi_adt_si` , DROP COLUMN `cpa_adt_si` , DROP COLUMN `cna_adt_si` , CHANGE COLUMN `cna_adt_ac` `cna_adt` FLOAT(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cpa_adt_ac` `cpa_adt` FLOAT(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `csi_adt_ac` `csi_adt` FLOAT(10,2) NOT NULL DEFAULT '0.00'  ;

-- change commission column in tour dep
ALTER TABLE `bterpdb`.`tour_dep` DROP COLUMN `csi_adt_si` , DROP COLUMN `cpa_adt_si` , DROP COLUMN `cna_adt_si` , CHANGE COLUMN `cna_adt_ac` `cna_adt` FLOAT(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cpa_adt_ac` `cpa_adt` FLOAT(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `csi_adt_ac` `csi_adt` FLOAT(10,2) NOT NULL DEFAULT '0.00'  ;

# 1.0.58
# Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.58', dt_upd = NOW();

-- create journal table
CREATE  TABLE `bterpdb`.`journal` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `dt_journal` DATETIME NOT NULL ,
  `total_amt` FLOAT(10,2) NOT NULL DEFAULT '0.00' ,
  `reason` TEXT NULL DEFAULT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) );
  
# 1.0.59
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.59', dt_upd = NOW();


INSERT INTO `bterpdb`.`sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_GLOBAL_CONFIG', '*', 'Global_Config', 'Global Config', 'L', '/app/maintenance/globalConfig', '1', '7', '6', 'U_MAINTENANCE', 'A', 'SYSTEM', NOW(), 'SYSTEM');


ALTER TABLE `bterpdb`.`global_config` CHANGE COLUMN `version` `version` INT(11) NOT NULL DEFAULT 1  ;
ALTER TABLE `bterpdb`.`global_config` ADD UNIQUE INDEX `code_UNIQUE` (`code` ASC) ;
DELETE FROM `bterpdb`.`global_config`;

INSERT INTO `bterpdb`.`global_config`(`code`,`description`,`value`,`created_by`,`dt_upd`,`upd_by`) VALUES ('KIV_EXP','KIV EXP','3','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`global_config`(`code`,`description`,`value`,`created_by`,`dt_upd`,`upd_by`) VALUES ('PAID_EXP','PAID EXP','30','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`global_config`(`code`,`description`,`value`,`created_by`,`dt_upd`,`upd_by`) VALUES ('PATH_COUNTRY','Country Logo','1','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`global_config`(`code`,`description`,`value`,`created_by`,`dt_upd`,`upd_by`) VALUES ('PATH_PASS','Passport','2','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`global_config`(`code`,`description`,`value`,`created_by`,`dt_upd`,`upd_by`) VALUES ('PATH_VISA','Visa','3','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`global_config`(`code`,`description`,`value`,`created_by`,`dt_upd`,`upd_by`) VALUES ('PATH_TOUR_ITINERY','Tour Itinery','4','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`global_config`(`code`,`description`,`value`,`created_by`,`dt_upd`,`upd_by`) VALUES ('PATH_F&E_ITINERY','Free & Easy Itinery','5','Super User', NOW(),'Super User');

# 1.0.60
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.60', dt_upd = NOW();

--change sub region id column to region id in country table 

ALTER TABLE `bterpdb`.`country` DROP FOREIGN KEY `fk_country_id_sub_region` ;
ALTER TABLE `bterpdb`.`country` CHANGE COLUMN `id_sub_region` `id_region` BIGINT(20) NOT NULL  , 
  ADD CONSTRAINT `fk_country_id_region`
  FOREIGN KEY (`id_region` )
  REFERENCES `bterpdb`.`region` (`id` );
  
# 1.0.61
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.61', dt_upd = NOW();
  
ALTER TABLE `bterpdb`.`sys_num_conf` ADD COLUMN `id_company` BIGINT NOT NULL DEFAULT 1  AFTER `id` , 
  ADD CONSTRAINT `fk_sys_num_conf_id_company`
  FOREIGN KEY (`id_company` )
  REFERENCES `bterpdb`.`company` (`id` )
, ADD INDEX `fk_sys_num_conf_id_company` (`id_company`) ;

ALTER TABLE `bterpdb`.`sys_num_conf` ADD COLUMN `is_default` TINYINT(1) NOT NULL DEFAULT '0'  AFTER `prefix_id` ;

# 1.0.62
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.62', dt_upd = NOW();

ALTER TABLE `bterpdb`.`lookup_item` ADD COLUMN `is_editable` TINYINT(1) NOT NULL DEFAULT 0  AFTER `remarks` ;
ALTER TABLE `bterpdb`.`lookup_item` ADD COLUMN `seq_no` SMALLINT NOT NULL DEFAULT 1  AFTER `is_editable` ;

# 1.0.63
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.63', dt_upd = NOW();

-- add status code to tour cat and tour theme
ALTER TABLE `bterpdb`.`tour_cat` ADD COLUMN `status_cd` VARCHAR(45) NOT NULL DEFAULT 'A' COMMENT 'A = Active / I = Inactive' AFTER `remarks` ;
ALTER TABLE `bterpdb`.`tour_theme` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A' COMMENT 'A = Active / I = Inactive' AFTER `img_path` ;

-- add status code to account trans
ALTER TABLE `bterpdb`.`account_trans` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A' COMMENT 'A = Active / I = Inactive' AFTER `type_cd` ;

-- remove id account foreign key from tour cat
ALTER TABLE `bterpdb`.`tour_cat` DROP FOREIGN KEY `fk_tour_cat_id_acct` ;
ALTER TABLE `bterpdb`.`tour_cat` CHANGE COLUMN `id_acct` `id_acct` BIGINT(20) NULL  ;
-- remove id account foreign key from tour theme
ALTER TABLE `bterpdb`.`tour_theme` DROP FOREIGN KEY `fk_tour_theme_id_acct` ;
ALTER TABLE `bterpdb`.`tour_theme` CHANGE COLUMN `id_acct` `id_acct` BIGINT(20) NULL  ;

# 1.0.64
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.64', dt_upd = NOW();

-- change primary key to id_acct and dt_begin_bal in table account_bal
ALTER TABLE `bterpdb`.`account_bal` 
ADD UNIQUE INDEX `id_UNIQUE` (`id`) 
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`id_acct`, `dt_begin_bal`) ;

# 1.0.65
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.65', dt_upd = NOW();

-- add sys_code for account_trans
ALTER TABLE `bterpdb`.`account_trans` ADD COLUMN `sys_cd` VARCHAR(50) NOT NULL  AFTER `dt_trans` ;

# 1.0.66
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.66', dt_upd = NOW();

INSERT INTO `bterpdb`.`sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`,`CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_FOREIGN_EXCHANGE_RATE', '*', 'Foreign_Ex_Rate', 'Foreign Exchange Rate', 'L', '/app/maintenance/foreignExRate', '1', '8', '7', 'U_MAINTENANCE', 'A','SYSTEM', NOW(), 'SYSTEM');

CREATE  TABLE `bterpdb`.`currency_ex` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_country` BIGINT NOT NULL ,
  `code` VARCHAR(50) NOT NULL ,
  `ex_rate` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  `version` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code`) ,
  INDEX `fk_currency_ex_id_country` (`id_country`) ,
  CONSTRAINT `fk_currency_ex_id_country`
    FOREIGN KEY (`id_country` )
    REFERENCES `bterpdb`.`country` (`id` ));
    
    
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('1', 'AFN', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('2', 'EUR', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('3', 'ALL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('4', 'DZD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('5', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('6', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('7', 'AOA', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('8', 'XCD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('9', 'XCD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('10','ARS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('11','AMD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('12','AWG', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('13','AUD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('14','EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('15','AZN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('16','BSD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('17','BHD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('18','BDT', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('19','BBD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('20','BYR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('21','EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('22','BZD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('23','XOF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('24','BMD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('25','BTN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('26','BOB', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('27','BAM', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('28','BWP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('29','NOK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('30','BRL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('31','USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('32','BND', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('33','BGN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('34','XOF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('35','BIF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('36','KHR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('37','XAF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('38', 'CAD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('39', 'CVE', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('40', 'KYD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('41', 'XAF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('42', 'XAF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('43', 'CLP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('44', 'CNY', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('46', 'AUD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('47', 'AUD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('48', 'COP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('49', 'KMF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('50', 'XAF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('51', 'CDF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('52', 'NZD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('53', 'CRC', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('54', 'XOF', '1.00','Super User', NOW(),'Super User');


INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('55', 'HRK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('56', 'CUP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('57', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('58', 'CZK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('59', 'DKK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('60', 'DJF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('61', 'XCD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('62', 'DOP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('63', 'ECS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('64', 'EGP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('65', 'SVC', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('66', 'XAF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('67', 'ERN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('68', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('69', 'ETB', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('70', 'FKP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('71', 'DKK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('72', 'FJD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('73', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('74', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('75', 'EUR', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('76', 'XPF', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('77', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('78', 'XAF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('79', 'GMD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('80', 'GEL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('81', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('82', 'GHS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('83', 'GIP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('84', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('85', 'DKK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('86', 'XCD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('87', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('88', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('89', 'QTQ', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('90', 'GNF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('91', 'GWP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('92', 'GYD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('93', 'HTG', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('94', 'AUD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('95', 'HNL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('96', 'HKD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('97', 'HUF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('98', 'ISK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('99', 'INR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('100', 'IDR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('101', 'IRR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('102', 'IQD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('103', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('104', 'ILS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('105', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('106', 'JMD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('107', 'JPY', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('108', 'JOD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('109', 'KZT', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('110', 'KES', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('111', 'AUD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('112', 'KPW', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('113', 'KRW', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('114', 'KWD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('115', 'KGS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('116', 'LAK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('117', 'LVL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('118', 'LBP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('119', 'LSL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('120', 'LRD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('121', 'LYD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('122', 'CHF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('123', 'LTL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('124', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('125', 'MOP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('126', 'MKD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('127', 'MGF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('128', 'MWK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('129', 'MYR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('130', 'MVR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('131', 'XOF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('132', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('133', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('134', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('135', 'MRO', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('136', 'MUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('137', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('138', 'MXN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('139', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('140', 'MDL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('141', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('142', 'MNT', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('143', 'XCD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('144', 'MAD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('145', 'MZN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('146', 'MMK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('147', 'NAD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('148', 'AUD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('149', 'NPR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('150', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('151', 'ANG', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('152', 'XPF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('153', 'NZD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('154', 'NIO', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('155', 'XOF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('156', 'NGN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('157', 'NZD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('158', 'AUD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('159', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('160', 'NOK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('161', 'OMR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('162', 'PKR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('163', 'USD', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('164', 'USD', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('165', 'PAB', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('166', 'PGK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('167', 'PYG', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('168', 'PEN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('169', 'PHP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('170', 'NZD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('171', 'PLN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('172', 'XPF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('173', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('174', 'QAR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('175', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('176', 'RON', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('178', 'RUB', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('179', 'RUB', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('180', 'RWF', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('181', 'XCD', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('182', 'XCD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('183', 'XCD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('184', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('185', 'XCD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('186', 'WST', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('187', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('188', 'STD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('189', 'SAR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('190', 'XOF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('191', 'RSD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('192', 'SCR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('193', 'SLL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('194', 'SGD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('195', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('196', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('197', 'SBD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('198', 'SOS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('199', 'ZAR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('200', 'GBP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('201', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('202', 'LKR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('203', 'SDG', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('204', 'SRD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('205', 'NOK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('206', 'SZL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('207', 'SEK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('208', 'CHF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('209', 'SYP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('210', 'TWD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('211', 'TJS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('212', 'TZS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('213', 'THB', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('214', 'USD', '1.00','Super User', NOW(),'Super User');

INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('215', 'XOF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('216', 'NZD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('217', 'TOP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('218', 'TTD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('219', 'TND', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('220', 'TRY', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('221', 'TMT', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('222', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('223', 'AUD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('224', 'UGX', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('225', 'UAH', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('226', 'AED', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('227', 'GBP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('228', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('229', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('230', 'UYU', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('231', 'UZS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('232', 'VUV', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('233', 'EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('234', 'VEF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('235', 'VND', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('236', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('237', 'USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('238', 'XPF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('239', 'MAD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('240', 'YER', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('241', 'ZMW', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` (`id_country`, `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('242', 'ZWD', '1.00','Super User', NOW(),'Super User');

# 1.0.67
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.67', dt_upd = NOW();

DROP TABLE `bterpdb`.`currency_ex`;

CREATE  TABLE `bterpdb`.`currency_ex` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(50) NOT NULL ,
  `ex_rate` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  `version` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code`));
  
  
  
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AFN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('EUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ALL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('DZD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('USD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AOA', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'XCD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ARS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AMD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AWG', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AUD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AZN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BSD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BHD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BDT', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BBD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BYR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BZD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('XOF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BMD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BTN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BOB', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BAM', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BWP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('NOK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BRL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BND', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BGN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BIF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KHR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('XAF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'CAD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'CVE', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'KYD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('CLP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('CNY', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'COP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KMF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('CDF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'NZD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'CRC', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('HRK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'CUP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'CZK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('DKK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'DJF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'DOP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ECS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('EGP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SVC', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'ERN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'ETB', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('FKP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'FJD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('XPF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GMD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GEL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('GHS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GIP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'QTQ', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GNF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GWP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GYD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'HTG', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'HNL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'HKD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'HUF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'ISK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'INR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'IDR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('IRR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('IQD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ILS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('JMD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('JPY', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('JOD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KZT', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KES', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KPW', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KRW', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KWD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KGS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LAK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LVL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LBP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LSL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LRD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LYD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('CHF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LTL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MOP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MKD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MGF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MWK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MYR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MVR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MRO', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MUR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MXN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MDL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MNT', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MAD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MZN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MMK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('NAD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('NPR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ANG', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('NIO', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('NGN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('OMR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PKR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PAB', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PGK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PYG', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PEN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PHP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PLN', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('QAR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('RON', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('RUB', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('RWF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('WST', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('STD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SAR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('RSD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SCR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SLL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SGD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SBD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SOS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ZAR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('GBP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LKR', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SDG', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SRD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SZL', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SEK', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SYP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TWD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TJS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TZS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('THB', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TOP', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TTD', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TND', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TRY', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TMT', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('UGX', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('UAH', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AED', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('UYU', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('UZS', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('VUV', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'VEF', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ('VND', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'YER', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'ZMW', '1.00','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'ZWD', '1.00','Super User', NOW(),'Super User');

# 1.0.68
# Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.68', dt_upd = NOW();

ALTER TABLE `bterpdb`.`ex_order_bill` CHANGE COLUMN `id_tour_cat` `id_tour_dep` BIGINT(20) NULL DEFAULT NULL  ;

# 1.0.69
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.69', dt_upd = NOW();

DROP TABLE IF EXISTS `bterpdb`.`currency_ex`;

---added description to currency_ex table

CREATE  TABLE `bterpdb`.`currency_ex` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(50) NOT NULL ,
  `ex_rate` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `description` VARCHAR(50) NOT NULL,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  `version` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code`));
  
  
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AFN', '1.00','Afghani','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('EUR', '1.00','Euro','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ALL', '1.00','Leck','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('DZD', '1.00','Algerian Dinar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('USD', '1.00','US Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AOA', '1.00','Kwanza','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'XCD', '1.00','East Carribbean Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ARS', '1.00','Argentine Peso','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AMD', '1.00','Armenian Dram','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AWG', '1.00','Aruban Guilder','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AUD', '1.00','Australian Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AZM', '1.00','Azerbaijanian Manat','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BSD', '1.00','Bahamian Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BHD', '1.00','Bahraini Dinar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BDT', '1.00','Taka','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BBD', '1.00','Barbados Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BYR', '1.00','Belarussian Ruble','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BZD', '1.00','Belize Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('XOF', '1.00','CFA Franc BCEAO','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BMD', '1.00','Bermudian Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BTN', '1.00','Ngultrum','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BOB', '1.00','Boliviano','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BAM', '1.00','Convertible Marks','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BWP', '1.00','Pula','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('NOK', '1.00','Norvegian Krone','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BRL', '1.00','Brazilian Real','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BND', '1.00','Brunei Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BGN', '1.00','Bulgarian Lev','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('BIF', '1.00','Burundi Franc','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KHR', '1.00','Riel','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('XAF', '1.00','CFA Franc BEAC','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'CAD', '1.00','Canadian Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'CVE', '1.00','Cape Verde Escudo','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'KYD', '1.00','Cayman Islands Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('CLP', '1.00','Chilean Peso','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('CNY', '1.00','Yuan Renminbi','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'COP', '1.00','Colombian Peso','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KMF', '1.00','Comoro Franc','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('CDF', '1.00','Franc Congolais','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'NZD', '1.00','New Zealand Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'CRC', '1.00','Costa Rican Colon','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('HRK', '1.00','Croatian kuna','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'CUP', '1.00','Cuban Peso','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'CZK', '1.00','Czech Koruna','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('DKK', '1.00','Danish Krone','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'DJF', '1.00','','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'DOP', '1.00','Djibouti Franc','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ECS', '1.00','Sucre','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('EGP', '1.00','Egyptian Pound','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SVC', '1.00','El Salvador Colon','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'ERN', '1.00','Nakfa','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'ETB', '1.00','Ethiopian Birr','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('FKP', '1.00','Falkland Islands Pound','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'FJD', '1.00','Fiji Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('XPF', '1.00','CFP Franc','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GMD', '1.00','Dalasi','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GEL', '1.00','Lari','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('GHC', '1.00','Cedi','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GIP', '1.00','Gibraltar Pound','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GTQ', '1.00','Quetzal','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GNF', '1.00','Guinea Franc','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GWP', '1.00','Guinea-Bissau Peso','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'GYD', '1.00','Guyana Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'HTG', '1.00','Gourde','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'HNL', '1.00','Lempira','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'HKD', '1.00','Hong Kong Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'HUF', '1.00','Forint','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'ISK', '1.00','Iceland Krona','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'INR', '1.00','Indian Rupee','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'IDR', '1.00','Rupiah','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('IRR', '1.00','Iranian Rial','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('IQD', '1.00','Iraqi Dinar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ILS', '1.00','New Israeli Sheqel','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('JMD', '1.00','Jamaican Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('JPY', '1.00','Yen','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('JOD', '1.00','Jordanian Dinar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KZT', '1.00','Tenge','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KES', '1.00','Kenyan Shilling','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KPW', '1.00','North Korean Won','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KRW', '1.00','Won','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KWD', '1.00','Kuwaiti Dinar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('KGS', '1.00','Som','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LAK', '1.00','Kip','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LVL', '1.00','Latvian Lats','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LBP', '1.00','Lebanese Pound','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LSL', '1.00','Loti','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LRD', '1.00','Liberian Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LYD', '1.00','Lybian Dinar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('CHF', '1.00','Swiss Franc','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LTL', '1.00','Lithuanian Litas','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MOP', '1.00','Pataca','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MKD', '1.00','Denar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MGF', '1.00','Malagasy Franc','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MWK', '1.00','Kwacha','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MYR', '1.00','Malaysian Ringgit','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MVR', '1.00','Rufiyaa','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MRO', '1.00','Ouguiya','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MUR', '1.00','Mauritius Rupee','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MXN', '1.00','Mexican Peso','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MDL', '1.00','Moldovan Leu','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MNT', '1.00','Tugrik','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MAD', '1.00','Moroccan Dirham','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MZM', '1.00','Metical','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('MMK', '1.00','Kyat','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('NAD', '1.00','Namibia Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('NPR', '1.00','Nepalese Rupee','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ANG', '1.00','Netherlands Antillan Guilder','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('NIO', '1.00','Cordoba Oro','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('NGN', '1.00','Naira','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('OMR', '1.00','Rial Omani','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PKR', '1.00','Pakistan Rupee','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PAB', '1.00','Balboa','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PGK', '1.00','Kina','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PYG', '1.00','Guarani','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PEN', '1.00','Nuevo Sol','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PHP', '1.00','Philippine Peso','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('PLN', '1.00','Zloty','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('QAR', '1.00','Qatari Rial','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ROL', '1.00','Leu','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('RUB', '1.00','Russian Ruble','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('RWF', '1.00','Rwanda Franc','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('WST', '1.00','Tala','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('STD', '1.00','Dobra','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SAR', '1.00','Saudi Riyal','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('RSD', '1.00','Dinar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SCR', '1.00','Seychelles Rupee','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SLL', '1.00','Leone','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SGD', '1.00','Singapore Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SBD', '1.00','Solomon Islands Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SOS', '1.00','Somali Shilling','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('ZAR', '1.00','Rand','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('GBP', '1.00','Pound Sterling','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('LKR', '1.00','Sri Lanka Rupee','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SDD', '1.00','Sudanese Dinar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SRD', '1.00','Suriname Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SZL', '1.00','Lilangeni','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SEK', '1.00','Swedish Krona','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('SYP', '1.00','Syrian Pound','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TWD', '1.00','New Taiwan Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TJS', '1.00','Somoni','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TZS', '1.00','Tanzanian Shilling','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('THB', '1.00','Baht','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TOP', '1.00','PaAnga','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TTD', '1.00','Trinidad and Tobago Dollar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TND', '1.00','Tunisian Dinar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TRL', '1.00','Turkish Lira','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('TMM', '1.00','Manat','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('UGX', '1.00','Uganda Shilling','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('UAH', '1.00','Hryvnia','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('AED', '1.00','UAE Dirham','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('UYU', '1.00','Peso Uruguayo','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('UZS', '1.00','Uzbekistan Sum','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('VUV', '1.00','Vatu','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'VEB', '1.00','Bolivar','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ('VND', '1.00','Dong','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'YER', '1.00','Yemeni Rial','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'ZMK', '1.00','Kwacha','Super User', NOW(),'Super User');
INSERT INTO `bterpdb`.`currency_ex` ( `code`, `ex_rate`,`description`,`created_by`, `dt_upd`, `upd_by`) VALUES ( 'ZWD', '1.00','Zimbabwe Dollar','Super User', NOW(),'Super User');

# 1.0.70
# Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.70', dt_upd = NOW();

-- added code in exchange order
ALTER TABLE `bterpdb`.`ex_order` ADD COLUMN `code` VARCHAR(50) NOT NULL  AFTER `id_supplier` ;

-- added company id in exchange order bill
ALTER TABLE `bterpdb`.`ex_order_bill` ADD COLUMN `id_company` BIGINT NOT NULL DEFAULT 1  AFTER `id` , 
  ADD CONSTRAINT `fk_ex_order_bill_id_company`
  FOREIGN KEY (`id_company` )
  REFERENCES `bterpdb`.`company` (`id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `fk_ex_order_bill_id_company_idx` (`id_company` ASC) ;
ALTER TABLE `bterpdb`.`ex_order_bill` ADD COLUMN `cn_no` VARCHAR(20) NULL  AFTER `code` , ADD COLUMN `dt_inv` DATETIME NULL  AFTER `cn_no` , ADD COLUMN `dt_dep` DATETIME NULL  AFTER `dt_inv` ;

# 1.0.71
# Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.71', dt_upd = NOW();

-- added currency
ALTER TABLE `bterpdb`.`ex_order_item` ADD COLUMN `id_currency` BIGINT NOT NULL DEFAULT 95  AFTER `id_eo` , ADD COLUMN `ex_rate` FLOAT(10,2) NULL DEFAULT '0.00'  AFTER `amount` , ADD COLUMN `fore_cur_amt` FLOAT(10,2) NULL DEFAULT '0.00'  AFTER `ex_rate` , 
  ADD CONSTRAINT `fk_ex_order_item_id_currency`
  FOREIGN KEY (`id_currency` )
  REFERENCES `bterpdb`.`currency_ex` (`id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `fk_ex_order_item_id_currency_idx` (`id_currency` ASC) ;

# 1.0.72
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.72', dt_upd = NOW();

-- added company id in customer table
ALTER TABLE `bterpdb`.`customer` ADD COLUMN `id_company` BIGINT NOT NULL DEFAULT 1 AFTER `status_cd`;
ALTER TABLE `bterpdb`.`customer` ADD CONSTRAINT `fk_customer_id_company` FOREIGN KEY `fk_customer_id_company` (`id_company`)
    REFERENCES `company` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

# 1.0.73
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.73', dt_upd = NOW();

-- update and add booking functions
UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='#' WHERE `UUID`='U_SALES_BOOKING';
INSERT INTO `bterpdb`.`SEC_FUNC` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES
	('U_SALES_BOOKING_RESERVE', '*', 'SALES_BOOKING_RESERVE', 'Make Booking', 'L', '/app/sales/booking/reserve', '1', 3, 1, 'U_SALES_BOOKING', 'A', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_SALES_BOOKING_LIST', '*', 'SALES_BOOKING_LIST', 'Booking List', 'L', '/app/sales/booking/list', '1', 3, 2, 'U_SALES_BOOKING', 'A', 'SYSTEM', NOW(), 'SYSTEM');

-- add tour type to ref data
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES
	('U_TOUR_TYPE_ALL', '*', 'TOUR_TYPE', 'ALL', 'All', 'All', 1, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_TYPE_TOUR', '*', 'TOUR_TYPE', 'TOUR', 'Tour', 'Tour', 2, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_TYPE_FNE', '*', 'TOUR_TYPE', 'FNE', 'Free & Easy', 'Free & Easy', 3, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');

-- add booking payment status to ref data
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES
	('U_BOOK_PMNT_STATUS_KIVEXP', '*', 'BOOK_PMNT_STATUS', 'KIVEXP', 'KIV Expired', 'KIV Expired', 4, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
UPDATE `bterpdb`.`COM_REF_DATA` SET `SEQ_NO`=3 WHERE `UUID`='U_BOOK_PMNT_STATUS_FULL';
UPDATE `bterpdb`.`COM_REF_DATA` SET `SEQ_NO`=2 WHERE `UUID`='U_BOOK_PMNT_STATUS_DEP';
UPDATE `bterpdb`.`COM_REF_DATA` SET `SEQ_NO`=1 WHERE `UUID`='U_BOOK_PMNT_STATUS_KIV';

-- create tour_dep_item tbl
CREATE  TABLE `bterpdb`.`tour_dep_item` (
  `id` BIGINT NOT NULL ,
  `id_tour_dep` BIGINT NOT NULL ,
  `id_inv_eo_item` BIGINT NULL ,
  `code` VARCHAR(50) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `amount` FLOAT(10,2) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_tour_dep_item_id_tour_dep` (`id_tour_dep`) ,
  CONSTRAINT `fk_tour_dep_item_id_tour_dep`
    FOREIGN KEY (`id_tour_dep` )
    REFERENCES `bterpdb`.`tour_dep` (`id` ))
COMMENT = 'For adding the item price';

-- add quantity
ALTER TABLE `bterpdb`.`tour_dep_item` ADD COLUMN `quantity` INT NOT NULL DEFAULT 0  AFTER `description` ;

-- tour booking add company
ALTER TABLE `bterpdb`.`tour_booking` ADD COLUMN `id_company` BIGINT NOT NULL DEFAULT 1 AFTER `id` , 
  ADD CONSTRAINT `fk_tour_booking_id_company`
  FOREIGN KEY (`id_company` )
  REFERENCES `bterpdb`.`company` (`id` )
, ADD INDEX `fk_tour_booking_id_company` (`id_company`) ;


# 1.0.74
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.74', dt_upd = NOW();

-- added sys_cd to cash_book table
ALTER TABLE `bterpdb`.`cash_book` ADD COLUMN `sys_cd` VARCHAR(50) NOT NULL  AFTER `dt_trans` ;
-- updated the invAndExcOrder function 
UPDATE `bterpdb`.`sec_func` SET `URI_ENTRY`='/app/product/invAndExcOrder' WHERE `FUNC_CD`='PRODUCT_INV_EO_ITEM';

# 1.0.75
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.75', dt_upd = NOW();

-- remove invoice issuance and print function
DELETE FROM `bterpdb`.`SEC_ROLE_FUNC` WHERE `U_FUNC`='U_SALES_INV_ISSUANCE';
DELETE FROM `bterpdb`.`SEC_ROLE_FUNC` WHERE `U_FUNC`='U_SALES_INV_PRINT';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_SALES_INV_ISSUANCE';
DELETE FROM `bterpdb`.`SEC_FUNC` WHERE `UUID`='U_SALES_INV_PRINT';
UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/sales/inv' WHERE `UUID`='U_SALES_INV';

# 1.0.76
# Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.76', dt_upd = NOW();

-- add company id
ALTER TABLE `bterpdb`.`supplier` ADD COLUMN `id_company` BIGINT(20) NOT NULL DEFAULT '1'  AFTER `id_person` , ADD COLUMN `sys_no` VARCHAR(45) NOT NULL  AFTER `id_company` , 
  ADD CONSTRAINT `fk_supplier_id_company`
  FOREIGN KEY (`id_company` )
  REFERENCES `bterpdb`.`company` (`id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `fk_supplier_id_company_idx` (`id_company` ASC) ;

-- add beneficiary name
ALTER TABLE `bterpdb`.`supplier` ADD COLUMN `beneficiary_name` VARCHAR(45) NULL  AFTER `bank_no` ;

-- add designation
ALTER TABLE `bterpdb`.`person` ADD COLUMN `designation` VARCHAR(45) NULL  AFTER `type_cd` ;

-- move bill payment to accounting
UPDATE `bterpdb`.`SEC_FUNC` SET `UUID`='U_ACCT_BILL', `FUNC_CD`='ACCT_BILL', `URI_ENTRY`='/app/acct/bill/pmnt', `SEQ_NO`='5', `U_PARENT_FUNC`='U_ACCT' WHERE `ID`='39';

# 1.0.77
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.77', dt_upd = NOW();

-- add company id and account id to tbl tour_dep_item
ALTER TABLE `bterpdb`.`tour_dep_item` ADD COLUMN `id_company` BIGINT NOT NULL  AFTER `id` , ADD COLUMN `id_acct` BIGINT NOT NULL  AFTER `id_company` , 
  ADD CONSTRAINT `fk_tour_dep_item_id_company`
  FOREIGN KEY (`id_company` )
  REFERENCES `bterpdb`.`company` (`id` ), 
  ADD CONSTRAINT `fk_tour_dp_item_id_acct`
  FOREIGN KEY (`id_acct` )
  REFERENCES `bterpdb`.`account` (`id` )
, ADD INDEX `fk_tour_dep_item_id_company` (`id_company`) 
, ADD INDEX `fk_tour_dp_item_id_acct` (`id_acct`) ;

-- add auto increment to tour departure item
ALTER TABLE `bterpdb`.`tour_dep_item` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT  ;

-- add invoice remarks
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `inv_remarks` TEXT NULL  AFTER `is_hot_deal` ;

# 1.0.78
# Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.78', dt_upd = NOW();

-- added company id and reference column
ALTER TABLE `bterpdb`.`journal` ADD COLUMN `id_company` BIGINT(20) NOT NULL DEFAULT '1'  AFTER `id` , ADD COLUMN `reference` TEXT NULL  AFTER `reason` , 
  ADD CONSTRAINT `fk_journal_id_company`
  FOREIGN KEY (`id_company` )
  REFERENCES `bterpdb`.`company` (`id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `fk_supplier_id_company_idx` (`id_company` ASC) ;

-- added system number column
ALTER TABLE `bterpdb`.`journal` ADD COLUMN `sys_no` VARCHAR(45) NOT NULL  AFTER `dt_journal` ;

# 1.0.79
# LF
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.79', dt_upd = NOW();

-- add new column company id to table inv_eo_item
ALTER TABLE `bterpdb`.`inv_eo_item` ADD COLUMN `id_company` bigint(20) NOT NULL DEFAULT 1 AFTER `id`;

-- add foreign key from id_company of inv_eo_item to company table
ALTER TABLE `bterpdb`.`inv_eo_item` ADD FOREIGN KEY (`id_company`) REFERENCES `company` (`id`);

# 1.0.80
# Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.80', dt_upd = NOW();

-- add category column
ALTER TABLE `bterpdb`.`supplier` ADD COLUMN `cat_cd` VARCHAR(45) NOT NULL  AFTER `type_cd` ;

# 1.0.81
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.81', dt_upd = NOW();

-- add new column country id to table person
ALTER TABLE `bterpdb`.`person` ADD COLUMN `id_country` bigint NULL AFTER `nick_name`;

# 1.0.82
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.82', dt_upd = NOW();

-- create tbl financial lock period
CREATE  TABLE `bterpdb`.`financial_period_lock` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `dt_start` DATETIME NOT NULL ,
  `dt_end` DATETIME NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  `version` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) )
COMMENT = 'Lock financial period';

-- add company id
ALTER TABLE `bterpdb`.`financial_period_lock` ADD COLUMN `id_company` BIGINT NOT NULL  AFTER `id` , 
  ADD CONSTRAINT `fk_financial_period_lock_id_company`
  FOREIGN KEY (`id_company` )
  REFERENCES `bterpdb`.`company` (`id` )
, ADD INDEX `fk_financial_period_lock_id_company` (`id_company`) ;

# 1.0.83
# Keith
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.83', dt_upd = NOW();

-- added tour id in exchange order table
ALTER TABLE `bterpdb`.`ex_order` ADD COLUMN `id_tour` BIGINT(20) NULL  AFTER `id_supplier` ;


# 1.0.84
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.84', dt_upd = NOW();

-- update functions code
UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_CD`='MAINT_TEMPLATE_MAINT' WHERE `UUID`='U_TEMPLATE_MAINTENANCE';
UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_CD`='MAINT_LOOKUP' WHERE `UUID`='U_LOOKUP';
UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_CD`='MAINT_SYSTEM_NUMBER_GEN' WHERE `UUID`='b8eb5b9e-8a4c-4826-a05f-a96c0b7160c2';
UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_CD`='MAINT_COMPANY_PROFILE' WHERE `UUID`='11a35a20-c4cf-4713-ae74-4380e6a30d45';
UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_CD`='MAINT_REGION_COUNTRY' WHERE `UUID`='268cac1d-8021-4b0f-a972-e6a2abaa513a';
UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_CD`='MAINT_GLOBAL_CONFIG' WHERE `UUID`='U_GLOBAL_CONFIG';

-- add status code to tour package
ALTER TABLE `bterpdb`.`tour_pkg` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `reserved_3` ;

-- create tour_pkg_history
CREATE TABLE `tour_pkg_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_hist` bigint(20) NOT NULL,
  `id_tour_theme` bigint(20) NOT NULL,
  `id_parent` bigint(20) DEFAULT NULL COMMENT 'level control - for free & easy tour, when is_optional is true',
  `type_cd` varchar(50) NOT NULL COMMENT '**T = Tour / FNE = Free & easy',
  `num_days` smallint(3) NOT NULL DEFAULT '0',
  `num_nights` smallint(3) NOT NULL DEFAULT '0',
  `name_en` varchar(255) NOT NULL,
  `name_zh` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_other` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_muslim_pkg` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_theme_tour` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_optional` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_new` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_promo` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `season_cd` varchar(50) DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL COMMENT 'Year of theme - yyyy',
  `deposit` float(10,2) NOT NULL DEFAULT '0.00',
  `bag_deduction` float(10,2) NOT NULL DEFAULT '0.00',
  `tfair_discount` float(10,2) NOT NULL DEFAULT '0.00',
  `price_diff_sgl` float(10,2) NOT NULL DEFAULT '0.00',
  `price_diff_ctw` float(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Child with twin',
  `price_diff_cwb` float(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Child with bed',
  `price_diff_cnb` float(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Child no bed',
  `grnd_sgl` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_ctw` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_cwb` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_cnb` float(10,2) NOT NULL DEFAULT '0.00',
  `cna_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `cpa_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `csi_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `price_from` float(10,2) NOT NULL DEFAULT '0.00',
  `high_light` text,
  `dt_book_start` datetime DEFAULT NULL,
  `dt_book_end` datetime DEFAULT NULL,
  `dt_travel_start` datetime DEFAULT NULL,
  `dt_travel_end` datetime DEFAULT NULL,
  `reserved_1` varchar(255) DEFAULT NULL,
  `reserved_2` varchar(255) DEFAULT NULL,
  `reserved_3` varchar(255) DEFAULT NULL,
  `reason` text NOT NULL,
  `action_cd` varchar(50) NOT NULL,
  `status_cd` varchar(50) NOT NULL DEFAULT 'A',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tour packages audit trial history';

# 1.0.85
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.85', dt_upd = NOW();

-- add tour status code to tour_dep
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `tour_status_cd` VARCHAR(50) NOT NULL COMMENT '**A = Available / F = Full / C = Close / L = Limited / NO = Not Operating / I = Issued / R = Refer'  AFTER `inv_remarks` ;
-- add version to tour dep
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `version` INT NOT NULL DEFAULT 1  AFTER `upd_by` ;

-- create tour_dep_history
CREATE TABLE `tour_dep_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_hist` bigint(20) NOT NULL,
  `id_tour_pkg` bigint(20) NOT NULL,
  `id_airline` bigint(20) DEFAULT NULL,
  `id_airline_schedule` bigint(20) DEFAULT NULL,
  `id_tour_operator` bigint(20) DEFAULT NULL,
  `dt_dep` datetime NOT NULL,
  `code` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `full_twn` float(10,2) NOT NULL DEFAULT '0.00',
  `full_sgl` float(10,2) NOT NULL DEFAULT '0.00',
  `full_ctw` float(10,2) NOT NULL DEFAULT '0.00',
  `full_ceb` float(10,2) NOT NULL DEFAULT '0.00',
  `full_cnb` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_twn` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_sgl` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_ctw` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_ceb` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_cnb` float(10,2) NOT NULL DEFAULT '0.00',
  `tour_mgr_cost` float(10,2) NOT NULL DEFAULT '0.00',
  `tfair_discount` float(10,2) NOT NULL DEFAULT '0.00',
  `cna_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `cpa_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `csi_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `misc_adt` float(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total flight fee and charges for adult',
  `misc_chd` float(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total flight fee and charges for child',
  `full_remarks` text,
  `grnd_remarks` text,
  `prn` varchar(10) DEFAULT NULL,
  `seat_allotment` smallint(3) NOT NULL DEFAULT '0',
  `travel_ins_policy_s` varchar(255) DEFAULT NULL,
  `travel_ins_policy_f` varchar(255) DEFAULT NULL,
  `is_issued_s` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_issued_f` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_deposit_paid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_push` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_hot_deal` tinyint(1) NOT NULL DEFAULT '0',
  `inv_remarks` text,
  `status_cd` varchar(50) NOT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Multiple depatures history';

# 1.0.86
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.86', dt_upd = NOW();

ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `room_type_cd` VARCHAR(10) NOT NULL COMMENT '**SGL/TWN/etc.' AFTER `id_cust`;
ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `room_pairing_no` SMALLINT NOT NULL AFTER `room_type_cd`;
ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `travel_ins_policy_s` VARCHAR(255) NULL AFTER `room_pairing_no`;
ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `travel_ins_policy_f` VARCHAR(255) NULL AFTER `travel_ins_policy_s`;
ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `is_issued_s` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' AFTER `travel_ins_policy_f`;
ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `is_issued_f` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = No / 1 = Yes' AFTER `is_issued_s`;
ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `ticket_no` VARCHAR(255) NULL AFTER `is_issued_f`;
ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `special_request` TEXT NULL AFTER `ticket_no`;
ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `lang_cd` VARCHAR(50) NULL AFTER `special_request`;

# 1.0.87
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.87', dt_upd = NOW();

-- add gds_booking_ref to invoice
ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `gds_booking_ref` VARCHAR(255) NULL COMMENT 'GDS Booking Referance Field' AFTER `delivery_cd`;

# 1.0.88
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.88', dt_upd = NOW();

-- add reason and action_cd in tour_dep_history
ALTER TABLE `bterpdb`.`tour_dep_history` ADD COLUMN `reason` TEXT NOT NULL  AFTER `inv_remarks` , ADD COLUMN `action_cd` VARCHAR(50) NOT NULL  AFTER `reason` ;

-- add history function
INSERT INTO `bterpdb`.`SEC_FUNC` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`)
VALUES ('U_HISTORY', '*', 'HISTORY', 'Audit Trail', 'T', '/app/history', '1', 1, 16, 'A', 'SYSTEM', NOW(), 'SYSTEM');

-- view tour package current history
CREATE VIEW vw_tour_pkg_current_history AS
SELECT T.description AS 'theme_desc', H.*
FROM tour_pkg_history H LEFT JOIN tour_theme T
ON T.id = H.id_tour_theme
WHERE H.id = (SELECT H2.id FROM tour_pkg_history H2 ORDER BY H2.id_hist LIMIT 1)
ORDER BY H.id DESC;

-- view tour package all history
CREATE VIEW vw_tour_pkg_all_history AS
SELECT T.description AS 'theme_desc', H.*
FROM tour_pkg_history H LEFT JOIN tour_theme T
ON T.id = H.id_tour_theme
ORDER BY H.id DESC;

# 1.0.89
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.89', dt_upd = NOW();

-- add reserved_seat and is_show_airline columns
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `reserved_seat` SMALLINT NOT NULL DEFAULT 0  AFTER `seat_allotment` , ADD COLUMN `is_show_airline` TINYINT(1) NOT NULL DEFAULT 0  AFTER `travel_ins_policy_f` ;

# 1.0.90
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.90', dt_upd = NOW();

-- add version to invoice
ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `version` INT NOT NULL DEFAULT 1  AFTER `upd_by` ;

CREATE TABLE `bterpdb`.`invoice_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_hist` bigint(20) NOT NULL,
  `id_company` bigint(20) NOT NULL,
  `id_customer` bigint(20) NOT NULL,
  `id_acct` bigint(20) NOT NULL,
  `id_tour_booking` bigint(20) DEFAULT NULL,
  `dt_inv` datetime NOT NULL,
  `code` varchar(20) DEFAULT NULL,
  `doc_type_cd` varchar(50) NOT NULL COMMENT '**INV/DN',
  `type_cd` varchar(50) DEFAULT NULL COMMENT '**GENERAL/TOUR/etc.',
  `attn_to` varchar(255) NOT NULL COMMENT 'customer nam',
  `pmnt_type_cd` varchar(20) DEFAULT NULL COMMENT '**CHQ/CASH/etc.',
  `id_saler` bigint(20) NOT NULL,
  `id_tour_dep` bigint(20) NOT NULL,
  `dt_departure` datetime NOT NULL,
  `id_issuer` bigint(20) NOT NULL,
  `id_eo_ref` bigint(20) DEFAULT NULL,
  `cat_cd` varchar(50) DEFAULT NULL COMMENT '**FAIR/CRUISES/etc.',
  `order_cd` varchar(50) DEFAULT NULL COMMENT '**FAIR/ADS/etc.',
  `delivery_cd` varchar(50) DEFAULT NULL COMMENT '**HAND/POST/etc.',
  `gds_booking_ref` varchar(255) DEFAULT NULL COMMENT 'GDS Booking Referance Field',
  `amount` float(10,2) NOT NULL DEFAULT '0.00',
  `balance` float(10,2) NOT NULL DEFAULT '0.00',
  `reason` text NOT NULL,
  `action_cd` varchar(50) NOT NULL,
  `status_cd` varchar(50) DEFAULT NULL,
  `is_inv_paid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `subj_line` varchar(255) DEFAULT NULL,
  `remarks` text,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='Invoice history';

CREATE TABLE `bterpdb`.`invoice_item_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_ref` bigint(20) NOT NULL,
  `id_hist` bigint(20) NOT NULL,
  `id_inv` bigint(20) NOT NULL,
  `id_acct` bigint(20) NOT NULL,
  `id_inv_eo_item` bigint(20) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `quantity` smallint(3) NOT NULL DEFAULT '0',
  `unit_price` float(10,2) NOT NULL DEFAULT '0.00',
  `amount` float(10,2) NOT NULL DEFAULT '0.00',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Invoice items history';

CREATE TABLE `bterpdb`.`invoice_pax_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_ref` bigint(20) NOT NULL,
  `id_hist` bigint(20) NOT NULL,
  `id_inv` bigint(20) NOT NULL,
  `id_cust` bigint(20) NOT NULL,
  `room_type_cd` varchar(10) NOT NULL COMMENT '**SGL/TWN/etc.',
  `room_pairing_no` smallint(6) NOT NULL,
  `travel_ins_policy_s` varchar(255) DEFAULT NULL,
  `travel_ins_policy_f` varchar(255) DEFAULT NULL,
  `is_issued_s` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_issued_f` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `ticket_no` varchar(255) DEFAULT NULL,
  `special_request` text,
  `lang_cd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Invoice paxs history';

CREATE TABLE  `bterpdb`.`invoice_pmnt_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_ref` bigint(20) NOT NULL,
  `id_hist` bigint(20) NOT NULL,
  `id_inv` bigint(20) NOT NULL,
  `id_issuer` bigint(20) DEFAULT NULL,
  `id_bank` bigint(20) DEFAULT NULL,
  `dt_pmnt` datetime NOT NULL,
  `code` varchar(50) NOT NULL,
  `pmnt_type_cd` varchar(50) DEFAULT NULL COMMENT '**CHQ/CASH/etc.',
  `ref_no` varchar(20) NOT NULL COMMENT 'cheque no./bank',
  `received_from` varchar(255) NOT NULL,
  `amount` float(10,2) NOT NULL DEFAULT '0.00',
  `remarks` text,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Invoice payments history';

# 1.0.91
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.91', dt_upd = NOW();

-- create bank recon tbl
CREATE  TABLE `bterpdb`.`bank_recon` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_bank` BIGINT NOT NULL ,
  `dt_start` DATETIME NOT NULL ,
  `dt_end` DATETIME NOT NULL ,
  `month` VARCHAR(3) NOT NULL ,
  `year` VARCHAR(4) NOT NULL ,
  `credit` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `debit` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `balance` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  `version` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_bank_recon_id_bank` (`id_bank`) ,
  CONSTRAINT `fk_bank_recon_id_bank`
    FOREIGN KEY (`id_bank` )
    REFERENCES `bterpdb`.`bank` (`id` ))
COMMENT = 'Bank Reconciliation';

-- drop view
drop view `bterpdb`.`vw_tour_pkg_current_history`;
-- recreate view
CREATE VIEW vw_tour_pkg_current_history AS
SELECT T.description AS 'theme_desc', H.*
FROM tour_pkg_history H LEFT JOIN tour_theme T
ON T.id = H.id_tour_theme
WHERE H.id IN (SELECT MAX(H2.id) FROM tour_pkg_history H2 GROUP BY H2.id_hist)
GROUP BY H.id_hist
ORDER BY H.id DESC;

# 1.0.92
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.92', dt_upd = NOW();

-- add home function
INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, IS_SECURE, LEVEL_NO, SEQ_NO, STATUS_CD, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_HOME', 'HOME', 'Home', 'T', '/app/home', '1', 1, 1, 'A', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

-- add reconciliation function
INSERT INTO sec_func(UUID, APP_ID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_BANK_RECON', '*', 'BANK_RECON', 'Reconciliation', 'L', '/app/bank/recon', '', '1', 2, 5, 'U_BANK', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

# 1.0.93
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.93', dt_upd = NOW();

-- drop and create tbl tour_dep_history
drop table if exists `bterpdb`.`tour_dep_history`;
CREATE TABLE `tour_dep_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_hist` bigint(20) NOT NULL,
  `id_tour_pkg` bigint(20) NOT NULL,
  `id_airline` bigint(20) DEFAULT NULL,
  `id_airline_schedule` bigint(20) DEFAULT NULL,
  `id_tour_operator` bigint(20) DEFAULT NULL,
  `dt_dep` datetime NOT NULL,
  `code` varchar(50) NOT NULL COMMENT '*for free & easy will generate automatically, e.g. F000000001',
  `description` varchar(255) NOT NULL,
  `full_twn` float(10,2) NOT NULL DEFAULT '0.00',
  `full_sgl` float(10,2) NOT NULL DEFAULT '0.00',
  `full_ctw` float(10,2) NOT NULL DEFAULT '0.00',
  `full_ceb` float(10,2) NOT NULL DEFAULT '0.00',
  `full_cnb` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_twn` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_sgl` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_ctw` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_ceb` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_cnb` float(10,2) NOT NULL DEFAULT '0.00',
  `tour_mgr_cost` float(10,2) NOT NULL DEFAULT '0.00',
  `tfair_discount` float(10,2) NOT NULL DEFAULT '0.00',
  `cna_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `cpa_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `csi_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `misc_adt` float(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total flight fee and charges for adult',
  `misc_chd` float(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total flight fee and charges for child',
  `full_remarks` text,
  `grnd_remarks` text,
  `prn` varchar(10) DEFAULT NULL,
  `seat_allotment` smallint(3) NOT NULL DEFAULT '0',
  `reserved_seat` smallint(6) NOT NULL DEFAULT '0',
  `travel_ins_policy_s` varchar(255) DEFAULT NULL,
  `travel_ins_policy_f` varchar(255) DEFAULT NULL,
  `is_show_airline` tinyint(1) NOT NULL DEFAULT '0',
  `is_issued_s` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_issued_f` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_deposit_paid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_push` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_hot_deal` tinyint(1) NOT NULL DEFAULT '0',
  `inv_remarks` text,
  `tour_status_cd` varchar(50) NOT NULL,
  `reason` text NOT NULL,
  `action_cd` varchar(50) NOT NULL,
  `status_cd` varchar(50) NOT NULL COMMENT '**A = Available / F = Full / C = Close / L = Limited / NO = Not Operating / I = Issued / R = Refer',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COMMENT='Multiple depatures history';

-- create view tour_dep_history
CREATE VIEW vw_tour_dep_history AS
SELECT H.*, A.code AS 'airline_cd'
FROM tour_dep_history H LEFT JOIN airline A
ON H.id_airline = A.id
WHERE H.id IN (SELECT MAX(H2.id) FROM tour_dep_history H2 GROUP BY H2.id_hist)
GROUP BY H.id_hist
ORDER BY H.id DESC;

-- create view tour_dep_history all
CREATE VIEW vw_tour_dep_history_all AS
SELECT H.*, A.code AS 'airline_cd', P.num_days AS 'num_days', P.num_nights AS 'num_nights'
FROM tour_dep_history H
LEFT JOIN airline A ON H.id_airline = A.id
LEFT JOIN tour_pkg P ON H.id_tour_pkg = P.id
ORDER BY H.id DESC;


# 1.0.94
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.94', dt_upd = NOW();

-- create table tour_dep_history
CREATE TABLE tour_package_remarks LIKE customer_remarks;

-- update table tour_dep 
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `tour_mgr_pax` VARCHAR(45) NOT NULL  AFTER `version` ;

# 1.0.95
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.95', dt_upd = NOW();

-- add column id_tour_dep_hist to booking
ALTER TABLE `bterpdb`.`tour_booking` ADD COLUMN `id_tour_dep_hist` BIGINT NULL  AFTER `id_cust` ;

-- change tour mgr pax data type to smallint
UPDATE `bterpdb`.`tour_dep` SET `tour_mgr_pax` = '0';
ALTER TABLE `bterpdb`.`tour_dep` CHANGE COLUMN `tour_mgr_pax` `tour_mgr_pax` SMALLINT NOT NULL DEFAULT 0  AFTER `reserved_seat` ;

# 1.0.96
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.96', dt_upd = NOW();

ALTER TABLE `bterpdb`.`tour_pkg` ADD COLUMN `price_to` FLOAT(10,2) NOT NULL DEFAULT '0.00'  AFTER `version` ;

# 1.0.97
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.97', dt_upd = NOW();

-- drop and create tour_dep_history again
drop table if exists `bterpdb`.`tour_dep_history`;
CREATE TABLE `tour_dep_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_hist` bigint(20) NOT NULL,
  `id_tour_pkg` bigint(20) NOT NULL,
  `id_airline` bigint(20) DEFAULT NULL,
  `id_airline_schedule` bigint(20) DEFAULT NULL,
  `id_tour_operator` bigint(20) DEFAULT NULL,
  `dt_dep` datetime NOT NULL,
  `code` varchar(50) NOT NULL COMMENT '*for free & easy will generate automatically, e.g. F000000001',
  `description` varchar(255) NOT NULL,
  `full_twn` float(10,2) NOT NULL DEFAULT '0.00',
  `full_sgl` float(10,2) NOT NULL DEFAULT '0.00',
  `full_ctw` float(10,2) NOT NULL DEFAULT '0.00',
  `full_ceb` float(10,2) NOT NULL DEFAULT '0.00',
  `full_cnb` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_twn` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_sgl` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_ctw` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_ceb` float(10,2) NOT NULL DEFAULT '0.00',
  `grnd_cnb` float(10,2) NOT NULL DEFAULT '0.00',
  `tour_mgr_cost` float(10,2) NOT NULL DEFAULT '0.00',
  `tfair_discount` float(10,2) NOT NULL DEFAULT '0.00',
  `cna_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `cpa_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `csi_adt` float(10,2) NOT NULL DEFAULT '0.00',
  `misc_adt` float(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total flight fee and charges for adult',
  `misc_chd` float(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total flight fee and charges for child',
  `full_remarks` text,
  `grnd_remarks` text,
  `prn` varchar(10) DEFAULT NULL,
  `seat_allotment` smallint(3) NOT NULL DEFAULT '0',
  `reserved_seat` smallint(6) NOT NULL DEFAULT '0',
  `tour_mgr_pax` smallint(6) NOT NULL DEFAULT '0',
  `travel_ins_policy_s` varchar(255) DEFAULT NULL,
  `travel_ins_policy_f` varchar(255) DEFAULT NULL,
  `is_show_airline` tinyint(1) NOT NULL DEFAULT '0',
  `is_issued_s` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_issued_f` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_deposit_paid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_push` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `is_hot_deal` tinyint(1) NOT NULL DEFAULT '0',
  `inv_remarks` text,
  `tour_status_cd` varchar(50) NOT NULL,
  `reason` text NOT NULL,
  `action_cd` varchar(50) NOT NULL,
  `status_cd` varchar(50) NOT NULL COMMENT '**A = Available / F = Full / C = Close / L = Limited / NO = Not Operating / I = Issued / R = Refer',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COMMENT='Multiple depatures history';

-- drop views
drop view `bterpdb`.`vw_tour_dep_history`;
drop view `bterpdb`.`vw_tour_dep_history_all`;
-- create view tour_dep_history
CREATE VIEW vw_tour_dep_history AS
SELECT H.*, A.code AS 'airline_cd'
FROM tour_dep_history H LEFT JOIN airline A
ON H.id_airline = A.id
WHERE H.id IN (SELECT MAX(H2.id) FROM tour_dep_history H2 GROUP BY H2.id_hist)
GROUP BY H.id_hist
ORDER BY H.id DESC;
-- create view tour_dep_history all
CREATE VIEW vw_tour_dep_history_all AS
SELECT H.*, A.code AS 'airline_cd', P.num_days AS 'num_days', P.num_nights AS 'num_nights'
FROM tour_dep_history H
LEFT JOIN airline A ON H.id_airline = A.id
LEFT JOIN tour_pkg P ON H.id_tour_pkg = P.id
ORDER BY H.id DESC;


# 1.0.98
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.98', dt_upd = NOW();

-- new Add, Upd Del function role for invoice payment
INSERT INTO `SEC_ROLE` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `REMARKS`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES
  ('ROLE_INV_PMNT_ADD','*','INV_PMNT_ADD','Invoice Payment Add Function',1,'A','',CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP,'SYSTEM',1),
  ('ROLE_INV_PMNT_UPD','*','INV_PMNT_UPD','Invoice Payment Update Function',1,'A','',CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP,'SYSTEM',1),
  ('ROLE_INV_PMNT_DEL','*','INV_PMNT_DEL','Invoice Payment Delete Function',1,'A','',CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP,'SYSTEM',1);
  
  
# 1.0.99
# Lap Foong
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.99', dt_upd = NOW();

alter table airline_schedule_item add dt_dep datetime COMMENT 'Departure Date' after flight_cd;
alter table airline_schedule_item add dt_arr datetime COMMENT 'Arrival Date' after dt_dep;

-- remove airline schedule item charge
DROP TABLE IF EXISTS airline_schedule_item_charge;

-- create airline schedule charge
DROP TABLE IF EXISTS airline_schedule_charge;
CREATE TABLE `airline_schedule_charge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_airline_schedule` bigint(20) NOT NULL,
  `type_cd` varchar(50) DEFAULT NULL,
  `type_desc` varchar(255) DEFAULT NULL,
  `amount` float(10,2) NOT NULL DEFAULT '0.00',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_airline_schedule_charge_id_airline_schedule` (`id_airline_schedule`),
  CONSTRAINT `fk_airline_schedule_charge_id_airline_schedule` FOREIGN KEY (`id_airline_schedule`) REFERENCES `airline_schedule` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=latin1 COMMENT='Airline schedule charges';

-- set fix charges to airline schedule
alter table airline_schedule add apt_adt float(10,2) NOT NULL DEFAULT '0.00' after dt_schedule;
alter table airline_schedule add apt_chd float(10,2) NOT NULL DEFAULT '0.00' after dt_schedule;
alter table airline_schedule add fuel_adt float(10,2) NOT NULL DEFAULT '0.00' after dt_schedule;
alter table airline_schedule add fuel_chd float(10,2) NOT NULL DEFAULT '0.00' after dt_schedule;
alter table airline_schedule add trvl_ins float(10,2) NOT NULL DEFAULT '0.00' after dt_schedule;
alter table airline_schedule add visa float(10,2) NOT NULL DEFAULT '0.00' after dt_schedule;
alter table airline_schedule add ac float(10,2) NOT NULL DEFAULT '0.00' after dt_schedule;
alter table airline_schedule add tipping float(10,2) NOT NULL DEFAULT '0.00' after dt_schedule;

-- move tkt_validity from table airline_schedule_item to airline_schedule
alter table airline_schedule_item drop tkt_validity;
alter table airline_schedule add tkt_validity smallint(3) NOT NULL DEFAULT '0' after dt_schedule;

# 1.0.100
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.0.100', dt_upd = NOW();

INSERT INTO `global_config` (`code`, `description`, `value`, `remarks`, `seq`, `created_by`, `dt_upd`, `upd_by`, `version`)
VALUES
	('PATH_PDF_FILE_SIZE','Pdf File Size Limit','500',NULL,9,'SYSTEM',NOW(),'SYSTEM',1),
('PATH_VALIDITY_PUSH','Validity Push','30',NULL,10,'SYSTEM',NOW(),'SYSTEM',1);


# 1.1.0
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.0', dt_upd = NOW();

-- clear unique key from code column
ALTER TABLE `bterpdb`.`tour_dep` 
DROP INDEX `code_UNIQUE` ;

# 1.1.1
# Lap Foong
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.1', dt_upd = NOW();

-- add booking payment status to ref data
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES
	('U_BOOK_PMNT_STATUS_ALL', '*', 'BOOK_PMNT_STATUS', 'ALL', 'All', 'All', 1, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');

UPDATE `bterpdb`.`COM_REF_DATA` SET `SEQ_NO`=5 WHERE `UUID`='U_BOOK_PMNT_STATUS_KIVEXP';
UPDATE `bterpdb`.`COM_REF_DATA` SET `SEQ_NO`=4 WHERE `UUID`='U_BOOK_PMNT_STATUS_FULL';
UPDATE `bterpdb`.`COM_REF_DATA` SET `SEQ_NO`=3 WHERE `UUID`='U_BOOK_PMNT_STATUS_DEP';
UPDATE `bterpdb`.`COM_REF_DATA` SET `SEQ_NO`=2 WHERE `UUID`='U_BOOK_PMNT_STATUS_KIV';

# 1.1.2
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.2', dt_upd = NOW();

-- set decription to optional
ALTER TABLE `bterpdb`.`tour_dep` CHANGE COLUMN `description` `description` VARCHAR(255) NULL  ;


# 1.1.3
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.3', dt_upd = NOW(); 

CREATE TABLE `tour_package_room_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_pkg` bigint(20) NOT NULL,
  `room_name` text NOT NULL,
  `price_from` float(10,2) NOT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  `price_to` float(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;


CREATE TABLE `room_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Airline info';
 

# 1.1.4
# Lap Foong
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.4', dt_upd = NOW();

-- add reserved seat reason variable
alter table tour_dep add reserved_seat_reason TEXT NULL AFTER reserved_seat;

-- change booking payment status value
UPDATE COM_REF_DATA SET REF_VALUE='Expired' WHERE UUID='U_BOOK_PMNT_STATUS_KIVEXP';
UPDATE COM_REF_DATA SET REF_VALUE='Deposit Paid' WHERE UUID='U_BOOK_PMNT_STATUS_DEP';
UPDATE COM_REF_DATA SET REF_VALUE='Paid in Full' WHERE UUID='U_BOOK_PMNT_STATUS_FULL';


# 1.1.5
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.5', dt_upd = NOW();

-- set decription to optional
DROP TABLE `room_type` ; 

CREATE TABLE `room_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT, 
  `description` varchar(255) DEFAULT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
); 
 
INSERT INTO `bterpdb`.`room_type`(`description`,`dt_created`,`created_by`,`dt_upd`, `upd_by`)  VALUES('Adult Twin','2013-04-09 17:03:19','Super User','2013-04-09 17:03:19','Super User');
INSERT INTO `bterpdb`.`room_type`(`description`,`dt_created`,`created_by`,`dt_upd`, `upd_by`)  VALUES('Adult Single','2013-04-09 17:03:19','Super User','2013-04-09 17:03:19','Super User');
INSERT INTO `bterpdb`.`room_type`(`description`,`dt_created`,`created_by`,`dt_upd`, `upd_by`)  VALUES('Adult Extra Bed','2013-04-09 17:03:19','Super User','2013-04-09 17:03:19','Super User');
INSERT INTO `bterpdb`.`room_type`(`description`,`dt_created`,`created_by`,`dt_upd`, `upd_by`) VALUES('Child Share Twin','2013-04-09 17:03:19','Super User','2013-04-09 17:03:19','Super User');
INSERT INTO `bterpdb`.`room_type`(`description`,`dt_created`,`created_by`,`dt_upd`, `upd_by`)  VALUES('Child With Bed','2013-04-09 17:03:19','Super User','2013-04-09 17:03:19','Super User');
INSERT INTO `bterpdb`.`room_type`(`description`,`dt_created`,`created_by`,`dt_upd`, `upd_by`) VALUES('Child No Bed','2013-04-09 17:03:19','Super User','2013-04-09 17:03:19','Super User');

# 1.1.6
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.6', dt_upd = NOW();

INSERT INTO `bterpdb`.`lookup_cat`(`code`,`description`,`remarks`,`dt_created`,`created_by`,`dt_upd`,`upd_by`) VALUES ('pymt_term','Payment Terms','Payment Terms','2013-05-13 19:13:49','Super User','2013-05-13 19:13:49','Super User');
INSERT INTO `bterpdb`.`lookup_item`(`lookup_cat_cd`,`code`,`description`,`remarks`,`is_editable`,`seq_no`,`dt_created`,`created_by`,`dt_upd`,`upd_by`) VALUES ('pymt_term','cr_30','Credit 30 Days','30','1','1','2013-05-13 19:13:49','Super User','2013-05-13 19:13:49','Super User');
INSERT INTO `bterpdb`.`lookup_item`(`lookup_cat_cd`,`code`,`description`,`remarks`,`is_editable`,`seq_no`,`dt_created`,`created_by`,`dt_upd`,`upd_by`) VALUES ('pymt_term','cr_60','Credit 60 Days','60','1','2','2013-05-13 19:13:49','Super User','2013-05-13 19:13:49','Super User');
INSERT INTO `bterpdb`.`lookup_item`(`lookup_cat_cd`,`code`,`description`,`remarks`,`is_editable`,`seq_no`,`dt_created`,`created_by`,`dt_upd`,`upd_by`) VALUES ('pymt_term','cr_90','Credit 90 Days','90','1','3','2013-05-13 19:13:49','Super User','2013-05-13 19:13:49','Super User');


# 1.1.7
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.7', dt_upd = NOW();
-- add reserved seat reason variable
alter table ex_order_bill add payment_term FLOAT(10,2) NULL AFTER upd_by;

# 1.1.8
# Stevem
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.8', dt_upd = NOW();

-- add exchange order link to invoice
CREATE TABLE `ex_order_inv` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_eo` bigint(20) NOT NULL,
  `id_inv` text NOT NULL,
  `time_stamp` datetime NOT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);

# 1.1.9
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.9', dt_upd = NOW();

-- alter tbl tour_package_remarks to tour_dep_remarks
ALTER TABLE `bterpdb`.`tour_package_remarks` CHANGE COLUMN `id_cust` `id_tour_dep` BIGINT(20) NOT NULL  , 
  ADD CONSTRAINT `fk_tour_dep_remarks_id_tour_dep`
  FOREIGN KEY (`id_tour_dep` )
  REFERENCES `bterpdb`.`tour_dep` (`id` )
, ADD INDEX `fk_tour_dep_remarks_id_tour_dep` (`id_tour_dep`) , RENAME TO  `bterpdb`.`tour_dep_remarks` ;

# 1.1.10
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.10', dt_upd = NOW();

DROP TABLE `ex_order_inv`;
-- add exchange order link to invoice
CREATE TABLE `ex_order_inv` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_eo` bigint(20) NOT NULL,
  `id_inv` bigint(20) NOT NULL,
  `dt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) DEFAULT NULL,
  `dt_upd` datetime DEFAULT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `invid_idx` (`id_inv`),
  KEY `eoid_idx` (`id_eo`),
  CONSTRAINT `invid` FOREIGN KEY (`id_inv`) REFERENCES `invoice` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `eoid` FOREIGN KEY (`id_eo`) REFERENCES `ex_order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

# 1.1.11
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.11', dt_upd = NOW();

DELETE FROM `bterpdb`.`inv_eo_item`;

ALTER TABLE `bterpdb`.`inv_eo_item` ADD COLUMN `id_acct` BIGINT(20) NOT NULL  AFTER `description` ;

ALTER TABLE `bterpdb`.`inv_eo_item`  
  ADD CONSTRAINT `fk_inv_eo_item_acct` 
  FOREIGN KEY (`id_acct` ) 
  REFERENCES `bterpdb`.`account` (`id` ) 
  ON DELETE RESTRICT 
  ON UPDATE RESTRICT 
, ADD INDEX `fk_acct_id_acct_idx` (`id_acct` ASC) ;

# 1.1.12
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.12', dt_upd = NOW();

-- add bank global config
INSERT INTO `global_config` (`code`, `description`, `value`, `remarks`, `seq`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	('BANK_NAME','Bank','CIMB','Default bank name',11,'SYSTEM',NOW(),'SYSTEM'),
	('BANK_ACCT_NO','Account Number','1419-0011274-056','Default bank account number',12,'SYSTEM',NOW(),'SYSTEM'),
	('BANK_ACCT_NAME','Account Name','APPLE VACATIONS & CONVENTIONS SDN BHD','Default bank account name',13,'SYSTEM',NOW(),'SYSTEM'),
	('BANK_CC_CHARGES','Credit Card Surchanges','3%','Default bank credit card surcharges',14,'SYSTEM',NOW(),'SYSTEM');

-- add email tmp function with hidden
INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, IS_SECURE, LEVEL_NO, SEQ_NO, STATUS_CD, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_EMAIL_TMP', 'EMAIL_TMP', 'Email Template', 'H', '/app/emailtmp', '1', 0, 0, 'A', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

# 1.1.13
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.13', dt_upd = NOW();

ALTER TABLE `bterpdb`.`invoice_pax` CHANGE COLUMN `travel_ins_policy_s` `travel_ins_type` VARCHAR(50) DEFAULT NULL,
 CHANGE COLUMN `travel_ins_policy_f` `travel_ins_policy` VARCHAR(255) DEFAULT NULL,
 DROP COLUMN `is_issued_s`,
 DROP COLUMN `is_issued_f`;
 
ALTER TABLE `bterpdb`.`invoice_pax_history` CHANGE COLUMN `travel_ins_policy_s` `travel_ins_type` VARCHAR(50) DEFAULT NULL,
 CHANGE COLUMN `travel_ins_policy_f` `travel_ins_policy` VARCHAR(255) DEFAULT NULL,
 DROP COLUMN `is_issued_s`,
 DROP COLUMN `is_issued_f`;
 
INSERT INTO `lookup_cat` (`code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	('travel_Ins_type','Travel Insurance Type','Travel Insurance Type',NOW(),'SYSTEM',NOW(),'SYSTEM');

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	('travel_Ins_type','sgl','Single','Travel Insurance Policy (Single)',0,1,NOW(),'SYSTEM',NOW(),'SYSTEM'),
	('travel_Ins_type','fml','Family','Travel Insurance Policy (Family)',0,2,NOW(),'SYSTEM',NOW(),'SYSTEM'),
	('travel_Ins_type','own','Own','Travel Insurance Policy (Own)',0,3,NOW(),'SYSTEM',NOW(),'SYSTEM');
 
# 1.1.14
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.14', dt_upd = NOW();

INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_EO_STATUS_PAID', '*', 'EO_STATUS', 'PD', 'Paid', 'Paid Status', '1', 'A', 'N', 'Y', '2013-05-20 11:31:17', 'SYSTEM', '2013-05-20 11:31:17', 'SYSTEM', '1');

ALTER TABLE `bterpdb`.`tour_dep_item` DROP FOREIGN KEY `fk_tour_dp_item_id_acct` ;
ALTER TABLE `bterpdb`.`tour_dep_item` CHANGE COLUMN `id_acct` `id_acct` BIGINT(20) NULL  ;

ALTER TABLE `bterpdb`.`ex_order_item` DROP FOREIGN KEY `fk_ex_order_item_id_eo` ;
ALTER TABLE `bterpdb`.`ex_order_item` CHANGE COLUMN `id_eo` `id_eo` BIGINT(20) NULL  ;

# 1.1.15
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.15', dt_upd = NOW();

-- add airline charges code
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`)
VALUES
	('U_AIRLINE_ITM_CD_APT_ADT', '*', 'AIRLINE_ITM_CD', 'APT_ADT', 'Airport Tax - Adult', 'Airport Tax - Adult', 1, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_AIRLINE_ITM_CD_APT_CHD', '*', 'AIRLINE_ITM_CD', 'APT_CHD', 'Airport Tax - Children', 'Airport Tax - Children', 2, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_AIRLINE_ITM_CD_FUEL_ADT', '*', 'AIRLINE_ITM_CD', 'FUEL_ADT', 'YQ and Fuel Tax - Adult', 'YQ and Fuel Tax - Adult', 3, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_AIRLINE_ITM_CD_FUEL_CHD', '*', 'AIRLINE_ITM_CD', 'FUEL_CHD', 'YQ and Fuel Tax - Children', 'YQ and Fuel Tax - Children', 4, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_AIRLINE_ITM_CD_TRVL_INS', '*', 'AIRLINE_ITM_CD', 'TRVL_INS', 'Travel Insurance', 'Travel Insurance', 5, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_AIRLINE_ITM_CD_VISA', '*', 'AIRLINE_ITM_CD', 'VISA', 'Visa', 'Visa', 6, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_AIRLINE_ITM_CD_AC', '*', 'AIRLINE_ITM_CD', 'AC', 'Agent Commission', 'Agent Commission', 7, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_AIRLINE_ITM_CD_TIPPING', '*', 'AIRLINE_ITM_CD', 'TIPPING', 'Tipping', 'Tipping', 8, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');

-- add tour departure item charges code
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`)
VALUES
	('U_TOUR_DEP_ITM_CD_FT_SGL', '*', 'TOUR_DEP_ITM_CD', 'FT_SGL', 'Adult Single - Full Tour', 'Adult Single - Full Tour', 1, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_FT_TWN', '*', 'TOUR_DEP_ITM_CD', 'FT_TWN', 'Adult Twin - Full Tour', 'Adult Twin - Full Tour', 2, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_FT_CTW', '*', 'TOUR_DEP_ITM_CD', 'FT_CTW', 'Child with Twin - Full Tour', 'Child with Twin - Full Tour', 3, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_FT_CWB', '*', 'TOUR_DEP_ITM_CD', 'FT_CWB', 'Child with Bed - Full Tour', 'Child with Bed - Full Tour', 4, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_FT_CNB', '*', 'TOUR_DEP_ITM_CD', 'FT_CNB', 'Child No Bed - Full Tour', 'Child No Bed - Full Tour', 5, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_GA_SGL', '*', 'TOUR_DEP_ITM_CD', 'GA_SGL', 'Adult Single - Ground Only', 'Adult Single - Ground Only', 6, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_GA_TWN', '*', 'TOUR_DEP_ITM_CD', 'GA_TWN', 'Adult Twin - Ground Only', 'Adult Twin - Ground Only', 7, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_GA_CTW', '*', 'TOUR_DEP_ITM_CD', 'GA_CTW', 'Child with Twin - Ground Only', 'Child with Twin - Ground Only', 8, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_GA_CWB', '*', 'TOUR_DEP_ITM_CD', 'GA_CWB', 'Child with Bed - Ground Only', 'Child with Bed - Ground Only', 9, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_GA_CNB', '*', 'TOUR_DEP_ITM_CD', 'GA_CNB', 'Child No Bed - Ground Only', 'Child No Bed - Ground Only', 10, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_DISC', '*', 'TOUR_DEP_ITM_CD', 'DISC', 'Discount', 'Discount', 11, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');

-- add is editable flag in tour_dep_item
ALTER TABLE `bterpdb`.`tour_dep_item` ADD COLUMN `is_editable` TINYINT(1) NOT NULL DEFAULT 0  AFTER `amount` ;

# 1.1.16
# Kent
	#
	-- update db_tracking version
	UPDATE `bterpdb`.`db_tracking` SET version = '1.1.16', dt_upd = NOW();
	
	ALTER TABLE `bterpdb`.`invoice_pmnt` ADD COLUMN `pmnt_for` VARCHAR(255) NULL AFTER `ref_no` ;

# 1.1.17
# LF
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.17', dt_upd = NOW();

-- add to and from date
ALTER TABLE `bterpdb`.`tour_hotel` ADD COLUMN `dt_to` datetime NOT NULL after night_num;
ALTER TABLE `bterpdb`.`tour_hotel` ADD COLUMN `dt_from` datetime NOT NULL after night_num;


# 1.1.18
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.18', dt_upd = NOW();

ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `status_acc_cd` VARCHAR(50) NOT NULL DEFAULT 'N'  AFTER `version` ;

INSERT INTO `bterpdb`.`lookup_cat` (`code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('acc_code_status', 'Account Code Status', 'Account Code Status', '2013-05-21 15:46:19', 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('acc_code_status', 'N', 'Pending', 'Pending', '1', '1', '2013-05-21 15:46:19', 'Super User', '2013-05-21 15:46:19', 'Super User');
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('acc_code_status', 'Y', 'Completed', 'Completed', '1', '2', '2013-05-21 15:46:19', 'Super User', '2013-05-21 15:46:19', 'Super User');

# 1.1.19
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.19', dt_upd = NOW();

INSERT INTO `bterpdb`.`sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_ACCOUNT_CODE', '*', 'MAINT_ACC_CODE', 'Account Code', 'L', '/app/maintenance/AccountCode', '1', '9', '8', 'U_MAINTENANCE', 'A', 'SYSTEM', NOW(), 'SYSTEM');

# 1.1.20
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.20', dt_upd = NOW();

INSERT INTO `bterpdb`.`sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_ACCOUNT_CODE', '*', 'MAINT_ACC_CODE', 'Account Code', 'L', '/app/maintenance/accountCode', '1', '9', '8', 'U_MAINTENANCE', 'A', 'SYSTEM', NOW(), 'SYSTEM');

# 1.1.21
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.21', dt_upd = NOW();

delete FROM bterpdb.SEC_FUNC where UUID = 'U_ACCOUNT_CODE';

INSERT INTO `bterpdb`.`sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_ACCOUNT_CODE', '*', 'MAINT_ACC_CODE', 'Account Code', 'L', '/app/maintenance/accountCode', '1', '9', '8', 'U_MAINTENANCE', 'A', 'SYSTEM', NOW(), 'SYSTEM');

# 1.1.22
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.22', dt_upd = NOW();

-- create tour_booking_charge_item
CREATE TABLE `tour_booking_charge_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_tour_booking` bigint(20) NOT NULL,
  `id_acct` bigint(20) DEFAULT NULL,
  `id_inv_eo_item` bigint(20) DEFAULT NULL,
  `code` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `amount` float(10,2) NOT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tour_booking_charge_item_id_tour_booking` (`id_tour_booking`),
  CONSTRAINT `fk_tour_booking_charge_item_id_tour_booking` FOREIGN KEY (`id_tour_booking`) REFERENCES `tour_booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='To manage tour booking item charges';

-- add infant
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`)
VALUES
	('U_TOUR_DEP_ITM_CD_FT_INFT', '*', 'TOUR_DEP_ITM_CD', 'FT_INFT', 'Infant - Full Tour', 'Infant - Full Tour', 12, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM'),
	('U_TOUR_DEP_ITM_CD_GA_INFT', '*', 'TOUR_DEP_ITM_CD', 'GA_INFT', 'Infant - Ground Only', 'Infant - Ground Only', 13, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');


# 1.1.23
# LF
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.23', dt_upd = NOW();

-- change night_num equals null as night number no longer used
ALTER TABLE `bterpdb`.`tour_hotel` MODIFY `night_num` smallint(3) DEFAULT NULL;


# 1.1.24
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.24', dt_upd = NOW();

-- add gds_booking_ref to invoice
ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `inv_due` datetime NOT NULL COMMENT 'Invoice due date' AFTER `delivery_cd`;
UPDATE `bterpdb`.`invoice` SET inv_due = NOW();


# 1.1.25
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.25', dt_upd = NOW();

-- remove status_acc_cd from tour_dep table
ALTER TABLE `bterpdb`.`tour_dep` DROP COLUMN `status_acc_cd` ;

# 1.1.26
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.26', dt_upd = NOW();

-- remove status_acc_cd from tour_dep table
ALTER TABLE `bterpdb`.`ex_order` ADD COLUMN `comments` TEXT NULL  AFTER `upd_by` ;


# 1.1.27
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.27', dt_upd = NOW();

-- move account code to place under account menu
UPDATE `bterpdb`.`SEC_FUNC` SET `URI_ENTRY`='/app/acct/accountCode', `LEVEL_NO`='2', `SEQ_NO`='6', `U_PARENT_FUNC`='U_ACCT'
 WHERE `UUID`='U_ACCOUNT_CODE';

 
# 1.1.28
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.28', dt_upd = NOW();

UPDATE `bterpdb`.`sec_func` SET SEQ_NO = '7' WHERE UUID ='U_ACCT_LEDGER';

INSERT INTO `bterpdb`.`sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `URI_PROCESS`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) 
VALUES ('U_ACCT_CREDIT_NOTE', '*', 'ACCT_CREDIT_NOTE', 'Credit Note', 'L', '/app/acct/creditnote', '', '1', '2', '6', 'U_ACCT', 'A', '', 'SYSTEM', NOW(), 'SYSTEM');
UPDATE `bterpdb`.`invoice` SET inv_due = NOW();

ALTER TABLE `bterpdb`.`invoice` MODIFY COLUMN `subj_line` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;

ALTER TABLE `bterpdb`.`account_trans` MODIFY COLUMN `code` VARCHAR(50) DEFAULT NULL;

# 1.1.29
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.29', dt_upd = NOW();

ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `act_room_type_cd` VARCHAR(10) NULL,
ADD COLUMN `act_room_pairing_no` SMALLINT(6) NULL ;

# 1.1.30
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.30', dt_upd = NOW();

INSERT INTO `bterpdb`.`SEC_FUNC` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `URI_PROCESS`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES ('U_ROOMING_LIST', '*', 'PRODUCT_ROOMING_LIST', 'Rooming List', 'L', '/app/product/roomingList', '', '1', '2', '7', 'U_PRODUCT', 'A', '2013-05-28 13:37:12', 'SYSTEM', '2013-05-28 13:37:12', 'SYSTEM');

# 1.1.31
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.31', dt_upd = NOW();

-- add airline schedule items
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `airline_schedule_items` VARCHAR(20) NULL COMMENT 'to keep airline schedule items'  AFTER `inv_remarks` ;

-- add type code to item
ALTER TABLE `bterpdb`.`tour_booking_charge_item` ADD COLUMN `type_cd` VARCHAR(50) NOT NULL DEFAULT 'T' COMMENT 'T = tour sep / A = airline'  AFTER `amount` ;

-- add type code to item
ALTER TABLE `bterpdb`.`tour_dep_item` ADD COLUMN `type_cd` VARCHAR(50) NOT NULL DEFAULT 'T'  AFTER `is_editable` ;

# 1.1.32
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.32', dt_upd = NOW();

ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `cn_inv_no` varchar(50) NULL COMMENT 'Credit Note Invoice #' AFTER `attn_to`;

ALTER TABLE `bterpdb`.`invoice` MODIFY COLUMN `id_tour_dep` BIGINT(20) DEFAULT NULL,
 DROP FOREIGN KEY `fk_invoice_id_tour_dep`;

ALTER TABLE `bterpdb`.`invoice_pmnt` MODIFY COLUMN `ref_no` VARCHAR(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT 'cheque no./bank';


# 1.1.33
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.33', dt_upd = NOW();

ALTER TABLE `bterpdb`.`cash_book` CHANGE COLUMN `remarks` `remarks` VARCHAR(10000) NOT NULL  ;

ALTER TABLE `bterpdb`.`account_trans` CHANGE COLUMN `destination` `destination` VARCHAR(10000) NOT NULL  ;

ALTER TABLE `bterpdb`.`account_trans` CHANGE COLUMN `source` `source` VARCHAR(10000) NOT NULL  ;

# 1.1.34
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.34', dt_upd = NOW();

CREATE  TABLE `bterpdb`.`rooming_list` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `id_tour_dep` BIGINT(20) NOT NULL ,
  `category` VARCHAR(45) NOT NULL ,
  `fk_id` BIGINT(20) NULL ,
  `description` TEXT NULL  ,
 `dt_created` TIMESTAMP NOT NULL  , `created_by` VARCHAR(50) NOT NULL  , `dt_upd` DATETIME NOT NULL  , `upd_by` VARCHAR(50) NOT NULL , `description2` TEXT NULL ,

  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  PRIMARY KEY (`id`) );

# 1.1.35
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.35', dt_upd = NOW();

-- to start event scheduler
SET GLOBAL event_scheduler = ON;
SET @@global.event_scheduler = ON;
SET GLOBAL event_scheduler = 1;
SET @@global.event_scheduler = 1;

-- create event scheduler for daily job - update KIV expired status
DELIMITER $$
CREATE EVENT `bterp_daily_event_schedule` ON SCHEDULE EVERY 1 DAY STARTS '2013-05-30 00:00:00'
    ON COMPLETION NOT PRESERVE
    ENABLE
    COMMENT 'Do daily event (Update KIV expired status)'
    DO BEGIN
    UPDATE bterpdb.tour_booking SET pmnt_status_cd = 'KIVEXP', status_cd = 'CC', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE pmnt_status_cd = 'KIV' AND DATE(DATE_ADD(dt_created, INTERVAL (SELECT value FROM bterpdb.global_config WHERE code = 'KIV_EXP') DAY)) < DATE(NOW());
END $$
DELIMITER ;

# 1.1.36
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.36', dt_upd = NOW();

ALTER TABLE `bterpdb`.`cash_book` CHANGE COLUMN `remarks` `remarks` TEXT NOT NULL  ;
ALTER TABLE `bterpdb`.`account_trans` CHANGE COLUMN `source` `source` TEXT NOT NULL  , CHANGE COLUMN `destination` `destination` TEXT NOT NULL  ;

# 1.1.37
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.37', dt_upd = NOW();

-- drop event scheduler
DROP EVENT IF EXISTS bterp_daily_event_schedule;

-- create event scheduler for daily job - update KIV expired status, update tour departure expired status
DELIMITER $$
CREATE EVENT `bterp_daily_event_schedule` ON SCHEDULE EVERY 1 DAY_HOUR
    ON COMPLETION NOT PRESERVE
    ENABLE
    COMMENT 'Do daily event (Update KIV expired status)'
    DO BEGIN
    UPDATE bterpdb.tour_dep SET status_cd = 'IA', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE dt_dep < NOW();
    UPDATE bterpdb.tour_booking SET pmnt_status_cd = 'KIVEXP', status_cd = 'CC', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE pmnt_status_cd = 'KIV' AND DATE(DATE_ADD(dt_created, INTERVAL (SELECT value FROM bterpdb.global_config WHERE code = 'KIV_EXP') DAY)) < DATE(NOW());
END $$
DELIMITER ;

# 1.1.38
# LF
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.38', dt_upd = NOW();

-- drop event scheduler
DROP EVENT IF EXISTS bterp_daily_event_schedule;

-- create event scheduler for daily job - update KIV expired status, update tour departure expired status - changed to not update KIVEXP status_cd to 'CC'
DELIMITER $$
CREATE EVENT `bterp_daily_event_schedule` ON SCHEDULE EVERY 1 DAY_HOUR
    ON COMPLETION NOT PRESERVE
    ENABLE
    COMMENT 'Do daily event (Update KIV expired status)'
    DO BEGIN
    UPDATE bterpdb.tour_dep SET status_cd = 'IA', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE dt_dep < NOW();
    UPDATE bterpdb.tour_booking SET pmnt_status_cd = 'KIVEXP', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE pmnt_status_cd = 'KIV' AND DATE(DATE_ADD(dt_created, INTERVAL (SELECT value FROM bterpdb.global_config WHERE code = 'KIV_EXP') DAY)) < DATE(NOW());
END $$
DELIMITER ;

# 1.1.39
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.39', dt_upd = NOW();

-- create event scheduler for daily job - update KIV expired status, update tour departure expired status - changed to not update KIVEXP status_cd to 'CC'
ALTER TABLE `bterpdb`.`rooming_list` CHANGE COLUMN `description` `description` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL COMMENT 'cheque no./bank'  , CHANGE COLUMN `description2` `description2` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL  ;

# 1.1.40
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.40', dt_upd = NOW();
-- update tour status code
UPDATE `bterpdb`.`lookup_item` SET `code`='MF' WHERE `lookup_cat_cd`='tour_status' AND `code` = 'F';

# 1.1.41
# LF
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.41', dt_upd = NOW();

-- tour departure audit trail
DELIMITER $$
DROP PROCEDURE IF EXISTS prc_tour_dep $$
CREATE PROCEDURE `prc_tour_dep`(IN p_id bigint(20), IN p_action varchar(50), IN p_reason text)
BEGIN

    INSERT INTO tour_dep_history(id_hist,id_tour_pkg,id_airline,id_airline_schedule,id_tour_operator,dt_dep,code,description,full_twn,full_sgl,full_ctw,full_ceb,full_cnb,grnd_twn,grnd_sgl,grnd_ctw,grnd_ceb,grnd_cnb,tour_mgr_cost,tfair_discount,cna_adt,cpa_adt,csi_adt,misc_adt,misc_chd,full_remarks,grnd_remarks,prn,seat_allotment,reserved_seat,tour_mgr_pax,travel_ins_policy_s,travel_ins_policy_f,is_show_airline,is_issued_s,is_issued_f,is_deposit_paid,is_push,is_hot_deal,inv_remarks,tour_status_cd,reason,action_cd,status_cd,dt_created,created_by,dt_upd,upd_by)
    (SELECT 
p_id,id_tour_pkg,id_airline,id_airline_schedule,id_tour_operator,dt_dep,code,description,full_twn,full_sgl,full_ctw,full_ceb,full_cnb,grnd_twn,grnd_sgl,grnd_ctw,grnd_ceb,grnd_cnb,tour_mgr_cost,tfair_discount,cna_adt,cpa_adt,csi_adt,misc_adt,misc_chd,full_remarks,grnd_remarks,prn,seat_allotment,reserved_seat,tour_mgr_pax,travel_ins_policy_s,travel_ins_policy_f,is_show_airline,is_issued_s,is_issued_f,is_deposit_paid,is_push,is_hot_deal,inv_remarks,tour_status_cd,p_reason,p_action,status_cd,dt_created,created_by,dt_upd,upd_by
    FROM tour_dep
    WHERE id = p_id);
END$$
DELIMITER ;

# 1.1.42
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.42', dt_upd = NOW();

DROP TABLE IF EXISTS `bterpdb`.`invoice_history`;
CREATE TABLE  `bterpdb`.`invoice_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_hist` bigint(20) NOT NULL,
  `id_company` bigint(20) NOT NULL,
  `id_customer` bigint(20) NOT NULL,
  `id_acct` bigint(20) NOT NULL,
  `id_tour_booking` bigint(20) DEFAULT NULL,
  `dt_inv` datetime NOT NULL,
  `code` varchar(20) DEFAULT NULL,
  `doc_type_cd` varchar(50) NOT NULL COMMENT '**INV/DN',
  `type_cd` varchar(50) DEFAULT NULL COMMENT '**GENERAL/TOUR/etc.',
  `attn_to` varchar(255) NOT NULL COMMENT 'customer nam',
  `cn_inv_no` varchar(50) DEFAULT NULL COMMENT 'Credit Note Invoice #',
  `pmnt_type_cd` varchar(20) DEFAULT NULL COMMENT '**CHQ/CASH/etc.',
  `id_saler` bigint(20) NOT NULL,
  `id_tour_dep` bigint(20) DEFAULT NULL,
  `dt_departure` datetime NOT NULL,
  `id_issuer` bigint(20) NOT NULL,
  `id_eo_ref` bigint(20) DEFAULT NULL,
  `cat_cd` varchar(50) DEFAULT NULL COMMENT '**FAIR/CRUISES/etc.',
  `order_cd` varchar(50) DEFAULT NULL COMMENT '**FAIR/ADS/etc.',
  `delivery_cd` varchar(50) DEFAULT NULL COMMENT '**HAND/POST/etc.',
  `inv_due` datetime NOT NULL COMMENT 'Invoice due date',
  `gds_booking_ref` varchar(255) DEFAULT NULL COMMENT 'GDS Booking Referance Field',
  `amount` float(10,2) NOT NULL DEFAULT '0.00',
  `balance` float(10,2) NOT NULL DEFAULT '0.00',
  `reason` text NOT NULL,
  `action_cd` varchar(50) NOT NULL,
  `status_cd` varchar(50) DEFAULT NULL,
  `is_inv_paid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes',
  `subj_line` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `remarks` text,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Invoice history';

DROP TABLE IF EXISTS `bterpdb`.`invoice_item_history`;
CREATE TABLE  `bterpdb`.`invoice_item_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_ref` bigint(20) NOT NULL,
  `id_hist` bigint(20) NOT NULL,
  `id_inv` bigint(20) NOT NULL,
  `id_acct` bigint(20) NOT NULL,
  `id_inv_eo_item` bigint(20) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `quantity` smallint(3) NOT NULL DEFAULT '0',
  `unit_price` float(10,2) NOT NULL DEFAULT '0.00',
  `amount` float(10,2) NOT NULL DEFAULT '0.00',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Invoice items history';

DROP TABLE IF EXISTS `bterpdb`.`invoice_pax_history`;
CREATE TABLE  `bterpdb`.`invoice_pax_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_ref` bigint(20) NOT NULL,
  `id_hist` bigint(20) NOT NULL,
  `id_inv` bigint(20) NOT NULL,
  `id_cust` bigint(20) NOT NULL,
  `room_type_cd` varchar(10) NOT NULL COMMENT '**SGL/TWN/etc.',
  `room_pairing_no` smallint(6) NOT NULL,
  `travel_ins_type` varchar(50) DEFAULT NULL,
  `travel_ins_policy` varchar(255) DEFAULT NULL,
  `ticket_no` varchar(255) DEFAULT NULL,
  `special_request` text,
  `lang_cd` varchar(50) DEFAULT NULL,
  `act_room_type_cd` varchar(10) DEFAULT NULL,
  `act_room_pairing_no` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Invoice paxs history';

DROP TABLE IF EXISTS `bterpdb`.`invoice_pmnt_history`;
CREATE TABLE  `bterpdb`.`invoice_pmnt_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_ref` bigint(20) NOT NULL,
  `id_hist` bigint(20) NOT NULL,
  `id_inv` bigint(20) NOT NULL,
  `id_issuer` bigint(20) DEFAULT NULL,
  `id_bank` bigint(20) DEFAULT NULL,
  `dt_pmnt` datetime NOT NULL,
  `code` varchar(50) NOT NULL,
  `pmnt_type_cd` varchar(50) DEFAULT NULL COMMENT '**CHQ/CASH/etc.',
  `ref_no` varchar(20) DEFAULT NULL COMMENT 'cheque no./bank',
  `pmnt_for` varchar(255) DEFAULT NULL,
  `received_from` varchar(255) NOT NULL,
  `amount` float(10,2) NOT NULL DEFAULT '0.00',
  `remarks` text,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Invoice payment history';

DELIMITER $$

DROP PROCEDURE IF EXISTS `prc_invoice` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_invoice`(IN p_id bigint(20), IN p_action varchar(50), IN p_reason text)
BEGIN
  DECLARE v_inv_id bigint(20);
  DECLARE v_inv_item_id bigint(20);
  DECLARE v_inv_pax_id bigint(20);
  DECLARE v_inv_pmnt_id bigint(20);

  /***** Invoice History *****/
  INSERT INTO invoice_history (
  `id_hist`,
  `id_company`,
  `id_customer`,
  `id_acct`,
  `id_tour_booking`,
  `dt_inv`,
  `code`,
  `doc_type_cd`,
  `type_cd`,
  `attn_to`,
  `cn_inv_no`,
  `pmnt_type_cd`,
  `id_saler`,
  `id_tour_dep`,
  `dt_departure`,
  `id_issuer`,
  `id_eo_ref`,
  `cat_cd`,
  `order_cd`,
  `delivery_cd`,
  `inv_due`,
  `gds_booking_ref`,
  `amount`,
  `balance`,
  `reason`,
  `action_cd`,
  `status_cd`,
  `is_inv_paid`,
  `subj_line`,
  `remarks`,
  `dt_created`,
  `created_by`,
  `dt_upd`,
  `upd_by`)
  (SELECT
  `id`,
  `id_company`,
  `id_customer`,
  `id_acct`,
  `id_tour_booking`,
  `dt_inv`,
  `code`,
  `doc_type_cd`,
  `type_cd`,
  `attn_to`,
  `cn_inv_no`,
  `pmnt_type_cd`,
  `id_saler`,
  `id_tour_dep`,
  `dt_departure`,
  `id_issuer`,
  `id_eo_ref`,
  `cat_cd`,
  `order_cd`,
  `delivery_cd`,
  `inv_due`,
  `gds_booking_ref`,
  `amount`,
  `balance`,
  p_reason,
  p_action,
  `status_cd`,
  `is_inv_paid`,
  `subj_line`,
  `remarks`,
  `dt_created`,
  `created_by`,
  `dt_upd`,
  `upd_by`
  FROM invoice
  WHERE id = p_id);
  SET v_inv_id := LAST_INSERT_ID();

  /***** Invoice Item History *****/
  INSERT INTO invoice_item_history (
  `id_ref`,
  `id_hist`,
  `id_inv`,
  `id_acct`,
  `id_inv_eo_item`,
  `description`,
  `quantity`,
  `unit_price`,
  `amount`,
  `dt_created`,
  `created_by`,
  `dt_upd`,
  `upd_by`)
  (SELECT
  v_inv_id,
  `id`,
  `id_inv`,
  `id_acct`,
  `id_inv_eo_item`,
  `description`,
  `quantity`,
  `unit_price`,
  `amount`,
  `dt_created`,
  `created_by`,
  `dt_upd`,
  `upd_by`
  FROM invoice_item
  WHERE id_inv = p_id);
  SET v_inv_item_id := LAST_INSERT_ID();

  /***** Invoice Pax History *****/
  INSERT INTO invoice_pax_history (
  `id_ref`,
  `id_hist`,
  `id_inv`,
  `id_cust`,
  `room_type_cd`,
  `room_pairing_no`,
  `travel_ins_type`,
  `travel_ins_policy`,
  `ticket_no`,
  `special_request`,
  `lang_cd`,
  `act_room_type_cd`,
  `act_room_pairing_no`)
  (SELECT
  v_inv_id,
  `id`,
  `id_inv`,
  `id_cust`,
  `room_type_cd`,
  `room_pairing_no`,
  `travel_ins_type`,
  `travel_ins_policy`,
  `ticket_no`,
  `special_request`,
  `lang_cd`,
  `act_room_type_cd`,
  `act_room_pairing_no`
  FROM invoice_pax
  WHERE id_inv = p_id);
  SET v_inv_pax_id := LAST_INSERT_ID();

  /***** Invoice Payment History *****/
  INSERT INTO invoice_pmnt_history (
  `id_ref`,
  `id_hist`,
  `id_inv`,
  `id_issuer`,
  `id_bank`,
  `dt_pmnt`,
  `code`,
  `pmnt_type_cd`,
  `ref_no`,
  `received_from`,
  `amount`,
  `remarks`,
  `dt_created`,
  `created_by`,
  `dt_upd`,
  `upd_by`)
  (SELECT
  v_inv_id,
  `id`,
  `id_inv`,
  `id_issuer`,
  `id_bank`,
  `dt_pmnt`,
  `code`,
  `pmnt_type_cd`,
  `ref_no`,
  `received_from`,
  `amount`,
  `remarks`,
  `dt_created`,
  `created_by`,
  `dt_upd`,
  `upd_by`
  FROM invoice_pmnt
  WHERE id_inv = p_id);
  SET v_inv_pmnt_id := LAST_INSERT_ID();

select v_inv_id,v_inv_item_id, v_inv_pax_id, v_inv_pmnt_id, LAST_INSERT_ID();

END $$

DELIMITER ;

# 1.1.43
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.43', dt_upd = NOW();

ALTER TABLE `bterpdb`.`ex_order` CHANGE COLUMN `reference` `reference` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL  ;

# 1.1.44
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.44', dt_upd = NOW();

-- remove security for email tmp function
UPDATE `bterpdb`.`SEC_FUNC` SET `IS_SECURE`='0' WHERE `UUID`='U_EMAIL_TMP';

-- add number days and nights columns
ALTER TABLE `bterpdb`.`tour_dep` ADD COLUMN `num_days` SMALLINT(3) NOT NULL DEFAULT 0  AFTER `id_tour_operator` , ADD COLUMN `num_nights` SMALLINT(3) NOT NULL DEFAULT 0  AFTER `num_days` ;

# 1.1.45
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.45', dt_upd = NOW();

-- update tour status code
UPDATE `bterpdb`.`lookup_item` SET `code`='F' WHERE `lookup_cat_cd`='tour_status' AND `code` = 'MF';
-- delete tour status which is F
DELETE FROM `bterpdb`.`lookup_item` WHERE `lookup_cat_cd`='tour_status' AND `code` = 'F';

# 1.1.46
# Ravi
#

-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.46', dt_upd = NOW();

-- update sec_fun 
UPDATE `bterpdb`.`sec_func` SET `FUNC_NAME`='Invoice Item', `URI_ENTRY`='/app/acct/invAndExcOrder', `SEQ_NO`='8', `U_PARENT_FUNC`='U_ACCT' WHERE `FUNC_CD`='PRODUCT_INV_EO_ITEM';

# 1.1.47
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.47', dt_upd = NOW();
-- update tour dep records
update tour_dep set num_days = (select p.num_days from tour_pkg p where id_tour_pkg = p.id),
	num_nights = (select p.num_nights from tour_pkg p where id_tour_pkg = p.id);

# 1.1.48
# LF
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.48', dt_upd = NOW();
-- total amount and amount due in exchange order
ALTER TABLE ex_order ADD COLUMN tot_amt float(10,2) NOT NULL DEFAULT '0.00' AFTER reference;
ALTER TABLE ex_order ADD COLUMN amt_due float(10,2) NOT NULL DEFAULT '0.00' AFTER reference;


# 1.1.49
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.49', dt_upd = NOW();

INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_INV_STATUS_INVOICED', '*', 'INV_STATUS', 'IN', 'Invoiced', 'Invoiced status', '1', 'A', 'N', 'Y', '2013-06-06 10:25:17', 'SYSTEM', '2013-02-26 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_INV_STATUS_DEPOSIT_PAID', '*', 'INV_STATUS', 'DP', 'Deposit Paid', 'Deposit Paid Status', '1', 'A', 'N', 'Y', '2013-06-06 10:25:17', 'SYSTEM', '2013-06-06 10:25:17', 'SYSTEM', '1');
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('U_INV_STATUS_FULL_PAYMENT', '*', 'INV_STATUS', 'FP', 'Full Payment', 'Full Payment Status', '1', 'A', 'N', 'Y', '2013-06-06 10:25:17', 'SYSTEM', '2013-06-06 10:25:17', 'SYSTEM', '1');

# 1.1.50
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.50', dt_upd = NOW();

-- add airline charges code - deviation charges
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`)
VALUES
	('U_AIRLINE_ITM_CD_DEVIATION', '*', 'AIRLINE_ITM_CD', 'DEVIATION', 'Deviation Charges', 'Deviation Charges', 9, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');
-- update description
UPDATE `bterpdb`.`COM_REF_DATA` SET `REF_VALUE`='Agent Collection Fees', `REF_DESC`='Agent Collection Fees' WHERE `UUID`='U_AIRLINE_ITM_CD_AC';
-- add deviation charges in airline schedule
ALTER TABLE `bterpdb`.`airline_schedule` ADD COLUMN `deviation` FLOAT(10,2) NOT NULL DEFAULT 0 COMMENT 'deviation charges'  AFTER `trvl_ins` ;

# 1.1.51
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.51', dt_upd = NOW();

INSERT INTO `bterpdb`.`sec_role` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('ROLE_BANK_PMNT_DEL', '*', 'BANK_PMNT_DEL', 'Bank Payment Delete Function', '1', 'A', 'SYSTEM', NOW(), 'SYSTEM', '1');

INSERT INTO `bterpdb`.`sec_role` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('ROLE_BANK_DEPOSIT_DEL', '*', 'BANK_DEPOSIT_DEL', ' Bank Deposit Delete Function', '1', 'A', 'SYSTEM', NOW(), 'SYSTEM', '1');

INSERT INTO `bterpdb`.`sec_role` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ( 'ROLE_BANK_REFUND_DEL', '*', 'BANK_REFUND_DEL', ' Bank Refund Delete Function', '1', 'A',  'SYSTEM', NOW(), 'SYSTEM', '1');

INSERT INTO `bterpdb`.`sec_role` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ( 'ROLE_BANK_ADJUST_DEL', '*', 'BANK_ADJUST_DEL', ' Bank Adjust Delete Function', '1', 'A',  'SYSTEM', NOW(), 'SYSTEM', '1');


# 1.1.52
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.52', dt_upd = NOW();

INSERT INTO `lookup_item` (  `lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	(  'pymt_type', 'credit_note', 'Credit Note', 'Credit Note', 0, 5, '2013-06-07 11:23:36', 'brandon chin', '2013-06-07 11:23:36', 'brandon chin');

INSERT INTO `bterpdb`.`SEC_ROLE` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`) VALUES ('ROLE_BILL_PMNT_DEL', '*', 'BILL_PMNT_DEL', 'Bill Payment Delete', '1', 'A', '2013-06-07 00:43:23', 'SYSTEM', '2013-06-07 00:43:23', 'SYSTEM', '1');

ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `travel_warrant` FLOAT(10,2) NULL  AFTER `version` ;

# 1.1.53
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.53', dt_upd = NOW();

ALTER TABLE `bterpdb`.`invoice` ADD COLUMN `travel_warrant_chk` TINYINT(1) NULL DEFAULT 0  AFTER `travel_warrant` ;
ALTER TABLE `bterpdb`.`invoice` DROP COLUMN `travel_warrant` ;

# 1.1.54
# LF
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.54', dt_upd = NOW();
ALTER TABLE tour_dep ADD COLUMN airline_schdl_itms_w_seq varchar(20) DEFAULT NULL COMMENT 'to keep airline schedule items';

# 1.1.55
# LF
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.55', dt_upd = NOW();
-- add exchange order category items
INSERT INTO `bterpdb`.`lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	('eo_cat','ge','General','General',1,SYSDATE(),'Super User',SYSDATE(),'Super User'),
	('eo_cat','ti','Ticketing','Ticketing',2,SYSDATE(),'Super User',SYSDATE(),'Super User'),
	('eo_cat','to','Tour','Tour',3,SYSDATE(),'Super User',SYSDATE(),'Super User'),
	('eo_cat','other','Others','Others',4,SYSDATE(),'Super User',SYSDATE(),'Super User');
	
	
# 1.1.56
# LF
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.56', dt_upd = NOW();

-- changed Income to Revenue and Expenditure to Expenses
UPDATE account_cat SET description = 'Revenue' WHERE id = 4;
UPDATE account_cat SET description = 'Expenses' WHERE id = 5;

-- add Purchased and Other Income in account category
INSERT INTO `account_cat` (`code`, `description`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('P', 'Purchased', NOW(), 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `account_cat` (`code`, `description`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) VALUES ('OI', 'Other Income', NOW(), 'SYSTEM', NOW(), 'SYSTEM');


# 1.1.57
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.57', dt_upd = NOW();

ALTER TABLE `bterpdb`.`account_trans` CHANGE COLUMN `ref_no` `ref_no` VARCHAR(30) NOT NULL COMMENT 'cheque no / etc.'  ;

ALTER TABLE `bterpdb`.`cash_book` CHANGE COLUMN `ref_no` `ref_no` VARCHAR(30) NOT NULL DEFAULT '' COMMENT 'cheque no. / etc.';


# 1.1.58
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.58', dt_upd = NOW();

ALTER TABLE `bterpdb`.`cash_book` CHANGE COLUMN `ref_no` `ref_no` VARCHAR(30) NOT NULL DEFAULT '' COMMENT 'cheque no. / etc.';


# 1.1.59
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.59', dt_upd = NOW();

INSERT INTO `bterpdb`.`sec_role` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('ROLE_USE_TRVL_WRRNT', '*', 'USE_TRVL_WRRNT', 'Use Travel Warrant', '1', 'A', 'SYSTEM', NOW(), 'SYSTEM', '1');

# 1.1.60
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.60', dt_upd = NOW();

-- testing function for testing purpose
INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, IS_SECURE, LEVEL_NO, SEQ_NO, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_TESTING', 'TESTING', 'Testing', 'H', '/app/testing', '', '0', 0, 0, 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

# 1.1.61
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.61', dt_upd = NOW();

INSERT INTO `bterpdb`.`sec_role` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('ROLE_INV_SALES_PERSON', '*', 'INV_SALES_PERSON', 'Invoice Sales Person', '1', 'A', 'SYSTEM', NOW(), 'SYSTEM', '1');

# 1.1.62
# LF
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.62', dt_upd = NOW();

-- Allow +2 for Arrival Date
ALTER TABLE airline_schedule_item MODIFY eta VARCHAR(6) NOT NULL COMMENT 'Estimated time arrival - HHmm';

-- hide overnight
alter table airline_schedule_item modify is_next_day tinyint(1) DEFAULT '0' COMMENT 'next day arrival';

# 1.1.63
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.63', dt_upd = NOW();

-- add mice function
INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
	VALUES('U_PRODUCT_MICE', 'PRODUCT_MICE', 'MICE Tours', 'L', '/app/product/mice', '', '1', 2, 3, 'U_PRODUCT', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

-- add tour type to ref data
INSERT INTO `bterpdb`.`COM_REF_DATA` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `CREATED_BY`, `DT_UPD`, `UPD_BY`) VALUES
	('U_TOUR_TYPE_MICE', '*', 'TOUR_TYPE', 'MICE', 'MICE', 'MICE', 4, 'A', 'N', 'Y', 'SYSTEM', NOW(), 'SYSTEM');

-- add id_tour_cat column for mice purpose
ALTER TABLE `bterpdb`.`tour_pkg` ADD COLUMN `id_tour_cat` BIGINT NULL  AFTER `id_tour_theme` ;
ALTER TABLE `bterpdb`.`tour_pkg` ADD COLUMN `id_region` BIGINT NULL  AFTER `id_tour_cat` , ADD COLUMN `id_country` BIGINT NULL  AFTER `id_region` ;

# 1.1.64
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.64', dt_upd = NOW();

-- delete tour type all
DELETE FROM `bterpdb`.`COM_REF_DATA` WHERE `UUID`='U_TOUR_TYPE_ALL';

# 1.1.65
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.65', dt_upd = NOW();

-- add isOnline column for show online flag purpose
ALTER TABLE `bterpdb`.`country` ADD COLUMN `isOnline` TINYINT(1) NOT NULL DEFAULT 1  AFTER `upd_by` ;

# 1.1.66
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.66', dt_upd = NOW();

-- add status code to acct sub cat for logical delete purpose
ALTER TABLE `bterpdb`.`account_sub_cat` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A' AFTER `description` ;
-- add status code to account for logical delete purpose
ALTER TABLE `bterpdb`.`account` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A' AFTER `remarks` ;
-- add company id to account sub cat
ALTER TABLE `bterpdb`.`account_sub_cat` ADD COLUMN `id_company` BIGINT NOT NULL DEFAULT 1  AFTER `id` , 
  ADD CONSTRAINT `fk_acctount_sub_cat_id_company`
  FOREIGN KEY (`id_company` )
  REFERENCES `bterpdb`.`company` (`id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `fk_acctount_sub_cat_id_company` (`id_company` ASC) ;

# 1.1.67
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.67', dt_upd = NOW();

-- create ticketing info table
CREATE  TABLE `bterpdb`.`ticketing` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_airline` BIGINT NOT NULL ,
  `destination` VARCHAR(255) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `price_from` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `price_to` FLOAT(10,2) NOT NULL DEFAULT 0 ,
  `dt_book_start` DATETIME NOT NULL ,
  `dt_book_end` DATETIME NOT NULL ,
  `dt_travel_start` DATETIME NOT NULL ,
  `dt_travel_end` DATETIME NOT NULL ,
  `is_promo` TINYINT(1) NOT NULL DEFAULT 0 ,
  `remarks` TEXT NULL ,
  `status_cd` VARCHAR(50) NOT NULL ,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) )
COMMENT = 'Ticketing information for website display purpose';

# 1.1.68
# Kent
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.68', dt_upd = NOW();

ALTER TABLE `bterpdb`.`invoice_item` ADD COLUMN `id_airline` bigint(20) DEFAULT NULL AFTER `description`;
ALTER TABLE `bterpdb`.`invoice_item` ADD COLUMN `net_price` float(10,2) NULL DEFAULT '0.00' AFTER `unit_price`;

-- add default items
INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	('room_type','na','Not Applicable','Not Applicable Item', 1, CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM');

update lookup_item set seq_no = 2 where lookup_cat_cd = 'room_type' and code = 'sgl';
update lookup_item set seq_no = 3 where lookup_cat_cd = 'room_type' and code = 'dbl';
update lookup_item set seq_no = 4 where lookup_cat_cd = 'room_type' and code = 'twn';
update lookup_item set seq_no = 5 where lookup_cat_cd = 'room_type' and code = 'extra_bed';
update lookup_item set seq_no = 6 where lookup_cat_cd = 'room_type' and code = 'ctw';
update lookup_item set seq_no = 7 where lookup_cat_cd = 'room_type' and code = 'cnb';
update lookup_item set seq_no = 8 where lookup_cat_cd = 'room_type' and code = 'cwb';

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES
	('travel_Ins_type','na','Not Applicable','Not Applicable Item', 1, CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM');

update lookup_item set seq_no = 2 where lookup_cat_cd = 'travel_Ins_type' and code = 'sgl';
update lookup_item set seq_no = 3 where lookup_cat_cd = 'travel_Ins_type' and code = 'fml';
update lookup_item set seq_no = 4 where lookup_cat_cd = 'travel_Ins_type' and code = 'own';


# 1.1.69
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.69', dt_upd = NOW();
ALTER TABLE `bterpdb`.`cash_book` ADD COLUMN `id_supplier` BIGINT(20) NULL DEFAULT NULL  AFTER `upd_by` , ADD COLUMN `id_customer` BIGINT(20) NULL DEFAULT NULL  AFTER `id_supplier` ;

# 1.1.70
# Ravi
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.70', dt_upd = NOW();

ALTER TABLE `bterpdb`.`cash_book` CHANGE COLUMN `id_supplier` `id_supplier` BIGINT(20) NULL DEFAULT NULL  AFTER `upd_by` , CHANGE COLUMN `id_customer` `id_customer` BIGINT(20) NULL DEFAULT NULL  AFTER `id_supplier` ;

# 1.1.71
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.71', dt_upd = NOW();

-- add expiry date
ALTER TABLE `bterpdb`.`tour_booking` ADD COLUMN `dt_exp` DATETIME NOT NULL COMMENT 'booking expiry date'  AFTER `status_cd` ;
-- add kiv exp whole sales for global config
INSERT INTO `bterpdb`.`global_config`(`code`,`description`,`value`, `seq`, `created_by`,`dt_upd`,`upd_by`) VALUES ('KIV_EXP_WHOLE_SALES','KIV EXP - Whole Sales','5', 2, 'Super User', NOW(),'Super User');
-- update tour booking expiry date
update `bterpdb`.`tour_booking` set dt_exp = date_add(dt_created, interval 3 day);

-- drop event scheduler
DROP EVENT IF EXISTS bterp_daily_event_schedule;

-- create event scheduler for daily job - update KIV expired status, update tour departure expired status - changed to not update KIVEXP status_cd to 'CC'
DELIMITER $$
CREATE EVENT `bterp_daily_event_schedule` ON SCHEDULE EVERY 1 DAY_HOUR
    ON COMPLETION NOT PRESERVE
    ENABLE
    COMMENT 'Do daily event (Update KIV expired status)'
    DO BEGIN
    -- update tour departure to inactive (tour, mice, fne) where departure date expiried
	UPDATE bterpdb.tour_dep d, bterpdb.tour_pkg p SET d.status_cd = 'IA', d.dt_upd = NOW(), d.upd_by = 'SYSTEM' WHERE d.id_tour_pkg = p.id AND p.type_cd IN ('TOUR', 'MICE', 'FNE') AND DATE(d.dt_dep) <= DATE(NOW());
    -- update tour package to inactive (fne) where travel end date expiried
	UPDATE bterpdb.tour_pkg SET status_cd = 'IA', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE type_cd = 'FNE' AND DATE(dt_travel_end) <= DATE(NOW());
    -- update tour package to inactive (mice) where departure date expiried
	UPDATE bterpdb.tour_pkg p, bterpdb.tour_dep d SET p.status_cd = 'IA', p.dt_upd = NOW(), p.upd_by = 'SYSTEM' WHERE p.id = d.id_tour_pkg AND p.type_cd = 'MICE' AND DATE(d.dt_dep) <= DATE(NOW());
    -- update tour booking to inactive where KIV expiried
	UPDATE bterpdb.tour_booking SET pmnt_status_cd = 'KIVEXP', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE pmnt_status_cd = 'KIV' AND DATE(dt_exp) < DATE(NOW());
END $$
DELIMITER ;

# 1.1.72
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.72', dt_upd = NOW();

-- add indicator, remarks and remarks flag for room pairing
ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `act_room_indicator` VARCHAR(45) NULL  AFTER `act_room_pairing_no` , ADD COLUMN `act_room_remarks` TEXT NULL  AFTER `act_room_indicator` ;
ALTER TABLE `bterpdb`.`invoice_pax` ADD COLUMN `act_room_remarks_f` TINYINT(1) NULL DEFAULT 0  AFTER `act_room_remarks` ;

# 1.1.73
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.73', dt_upd = NOW();

-- update function name
UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_NAME`='MICE/Muslim Tours' WHERE `UUID`='U_PRODUCT_MICE';
UPDATE `bterpdb`.`SEC_FUNC` SET `FUNC_NAME`='Ticketing/Muslim Free & Easy' WHERE `UUID`='U_PRODUCT_TICKETING';

# 1.1.74
# Steven
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.74', dt_upd = NOW();

-- update new field for bill payment
ALTER TABLE `bterpdb`.`ex_order_bill_pmnt` ADD COLUMN `pmnt_amount` FLOAT(10,2) NOT NULL DEFAULT 0.00  AFTER `remarks` ;

# 1.1.75
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.75', dt_upd = NOW();
-- change column region and country
ALTER TABLE `bterpdb`.`tour_pkg` CHANGE COLUMN `price_to` `price_to` FLOAT(10,2) NOT NULL DEFAULT '0.00'  AFTER `price_from` , CHANGE COLUMN `id_region` `regions` VARCHAR(50) NULL DEFAULT NULL  AFTER `dt_travel_end` , CHANGE COLUMN `id_country` `countries` VARCHAR(255) NULL DEFAULT NULL  AFTER `regions` ;

# 1.1.76
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.76', dt_upd = NOW();
-- add status to employee
ALTER TABLE `bterpdb`.`employee` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `is_default_comp` ;

# 1.1.77
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.77', dt_upd = NOW();
-- update tour cat status code
UPDATE `bterpdb`.`tour_cat` SET status_cd = 'AC';

# 1.1.78
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.78', dt_upd = NOW();
-- update data length
ALTER TABLE `bterpdb`.`tour_dep` CHANGE COLUMN `prn` `prn` VARCHAR(100) NULL DEFAULT NULL  ;

# 1.1.79
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.79', dt_upd = NOW();
-- update data length
ALTER TABLE `bterpdb`.`tour_dep` CHANGE COLUMN `airline_schedule_items` `airline_schedule_items` VARCHAR(100) NULL DEFAULT NULL COMMENT 'to keep airline schedule items'  , CHANGE COLUMN `airline_schdl_itms_w_seq` `airline_schdl_itms_w_seq` VARCHAR(100) NULL DEFAULT NULL COMMENT 'to keep airline schedule items'  ;

# 1.1.80
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.80', dt_upd = NOW();
-- increase data length of beneficiary_name
ALTER TABLE `bterpdb`.`supplier` CHANGE COLUMN `beneficiary_name` `beneficiary_name` VARCHAR(255) NULL DEFAULT NULL  ;

# 1.1.81
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.81', dt_upd = NOW();
-- update description to uppercase
UPDATE inv_eo_item SET description = UPPER(description);

# 1.1.82
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.82', dt_upd = NOW();
-- update description to uppercase
update tour_dep_item set description = upper(description);
update tour_booking_charge_item set description = upper(description);
update com_ref_data set ref_value = upper(ref_value), ref_desc = upper(ref_desc) where uuid in ('U_AIRLINE_ITM_CD_APT_ADT', 'U_AIRLINE_ITM_CD_APT_CHD', 'U_AIRLINE_ITM_CD_FUEL_ADT', 'U_AIRLINE_ITM_CD_FUEL_CHD', 'U_AIRLINE_ITM_CD_TRVL_INS', 'U_AIRLINE_ITM_CD_VISA', 'U_AIRLINE_ITM_CD_AC', 'U_AIRLINE_ITM_CD_TIPPING', 'U_AIRLINE_ITM_CD_DEVIATION', 'U_TOUR_DEP_ITM_CD_FT_SGL', 'U_TOUR_DEP_ITM_CD_FT_TWN', 'U_TOUR_DEP_ITM_CD_FT_CTW', 'U_TOUR_DEP_ITM_CD_FT_CWB', 'U_TOUR_DEP_ITM_CD_FT_CNB', 'U_TOUR_DEP_ITM_CD_GA_SGL', 'U_TOUR_DEP_ITM_CD_GA_TWN', 'U_TOUR_DEP_ITM_CD_GA_CTW', 'U_TOUR_DEP_ITM_CD_GA_CWB', 'U_TOUR_DEP_ITM_CD_GA_CNB', 'U_TOUR_DEP_ITM_CD_DISC', 'U_TOUR_DEP_ITM_CD_FT_INFT', 'U_TOUR_DEP_ITM_CD_GA_INFT');
update invoice_item set description = upper(description);

# 1.1.83
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.83', dt_upd = NOW();
-- change description
UPDATE `bterpdb`.`COM_REF_DATA` SET `REF_VALUE`='AIRPORT TAX - ADULT (SUBJECT TO CHANGE)', `REF_DESC`='AIRPORT TAX - ADULT (SUBJECT TO CHANGE)' WHERE `UUID`='U_AIRLINE_ITM_CD_APT_ADT';
UPDATE `bterpdb`.`COM_REF_DATA` SET `REF_VALUE`='AIRPORT TAX - CHILDREN (SUBJECT TO CHANGE)', `REF_DESC`='AIRPORT TAX - CHILDREN (SUBJECT TO CHANGE)' WHERE `UUID`='U_AIRLINE_ITM_CD_APT_CHD';
UPDATE `bterpdb`.`COM_REF_DATA` SET `REF_VALUE`='YQ AND FUEL TAX - ADULT (SUBJECT TO CHANGE)', `REF_DESC`='YQ AND FUEL TAX - ADULT (SUBJECT TO CHANGE)' WHERE `UUID`='U_AIRLINE_ITM_CD_FUEL_ADT';
UPDATE `bterpdb`.`COM_REF_DATA` SET `REF_VALUE`='YQ AND FUEL TAX - CHILDREN (SUBJECT TO CHANGE)', `REF_DESC`='YQ AND FUEL TAX - CHILDREN (SUBJECT TO CHANGE)' WHERE `UUID`='U_AIRLINE_ITM_CD_FUEL_CHD';
-- update uppercase
update account_trans set description = upper(description) where code in ('APT_ADT', 'APT_CHD', 'FUEL_ADT', 'FUEL_CHD', 'TRVL_INS', 'VISA', 'AC', 'TIPPING', 'DEVIATION', 'FT_SGL', 'FT_TWN', 'FT_CTW', 'FT_CWB', 'FT_CNB', 'GA_SGL', 'GA_TWN', 'GA_CTW', 'GA_CWB', 'GA_CNB', 'DISC', 'FT_INFT', 'GA_INFT');
-- change description
update tour_dep_item set description = concat(description, ' (SUBJECT TO CHANGE)') where description in ('AIRPORT TAX - ADULT', 'AIRPORT TAX - CHILDREN', 'YQ AND FUEL TAX - ADULT', 'YQ AND FUEL TAX - CHILDREN');
update tour_booking_charge_item set description = concat(description, ' (SUBJECT TO CHANGE)') where description in ('AIRPORT TAX - ADULT', 'AIRPORT TAX - CHILDREN', 'YQ AND FUEL TAX - ADULT', 'YQ AND FUEL TAX - CHILDREN');
update invoice_item set description = concat(description, ' (SUBJECT TO CHANGE)') where description in ('AIRPORT TAX - ADULT', 'AIRPORT TAX - CHILDREN', 'YQ AND FUEL TAX - ADULT', 'YQ AND FUEL TAX - CHILDREN');
update account_trans set description = concat(description, ' (SUBJECT TO CHANGE)') where description in ('AIRPORT TAX - ADULT', 'AIRPORT TAX - CHILDREN', 'YQ AND FUEL TAX - ADULT', 'YQ AND FUEL TAX - CHILDREN');

# 1.1.84
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.84', dt_upd = NOW();
-- change description
update com_ref_data set ref_value = concat(ref_value, ' (1~10 Days)'), ref_desc = concat(ref_desc, ' (1~10 Days)') where uuid = 'U_AIRLINE_ITM_CD_TRVL_INS';
update tour_dep_item set description = concat(description, ' (1~10 Days)') where description = 'TRAVEL INSURANCE';
update tour_booking_charge_item set description = concat(description, ' (1~10 Days)') where description = 'TRAVEL INSURANCE';
update invoice_item set description = concat(description, ' (1~10 Days)') where description = 'TRAVEL INSURANCE';
update account_trans set description = concat(description, ' (1~10 Days)') where description = 'TRAVEL INSURANCE';

# 1.1.85
# HS
#
-- update db_tracking version
UPDATE `bterpdb`.`db_tracking` SET version = '1.1.85', dt_upd = NOW();
-- change primary key
ALTER TABLE `bterpdb`.`account` 
DROP PRIMARY KEY 
, ADD PRIMARY KEY (`id`) ;

# 1.1.86
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.86', dt_upd = NOW();
-- add id_corporate column
ALTER TABLE `customer` ADD COLUMN `id_corporate` BIGINT NULL  AFTER `id_pc` ;
-- add corporate name
ALTER TABLE `bterpdb`.`customer` ADD COLUMN `corporate_name` VARCHAR(255) NULL  AFTER `id_corporate` ;
-- update customer record
update customer set id_corporate = id_pc where pc_type_cd = 'C';
update customer c set id_pc = (select id_person from corporate where id = c.id_corporate) where pc_type_cd = 'C';
update customer c set c.corporate_name = (select name from corporate where id = c.id_corporate) where c.pc_type_cd = 'C';

# 1.1.87
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.87', dt_upd = NOW();
-- update column datatype float to double
ALTER TABLE `bterpdb`.`account_bal` CHANGE COLUMN `debit_begin_bal` `debit_begin_bal` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `credit_begin_bal` `credit_begin_bal` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `debit_current_bal` `debit_current_bal` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `credit_current_bal` `credit_current_bal` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `debit_close_bal` `debit_close_bal` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `credit_close_bal` `credit_close_bal` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`account_trans` CHANGE COLUMN `debit` `debit` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `credit` `credit` DOUBLE(10,2) NOT NULL  ;
ALTER TABLE `bterpdb`.`airline_item_charge` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`airline_schedule` CHANGE COLUMN `ac` `ac` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `visa` `visa` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `trvl_ins` `trvl_ins` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `deviation` `deviation` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'deviation charges'  , CHANGE COLUMN `fuel_chd` `fuel_chd` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `fuel_adt` `fuel_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `apt_chd` `apt_chd` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `apt_adt` `apt_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`airline_schedule_charge` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`airline_schedule_item_charge` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`bank_recon` CHANGE COLUMN `credit` `credit` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `debit` `debit` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `balance` `balance` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`cash_book` CHANGE COLUMN `debit` `debit` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `credit` `credit` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`ex_order` CHANGE COLUMN `amt_due` `amt_due` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `tot_amt` `tot_amt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`currency_ex` CHANGE COLUMN `ex_rate` `ex_rate` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`ex_order_bill` CHANGE COLUMN `bill_amt` `bill_amt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `amt_paid` `amt_paid` DOUBLE(10,2) NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`ex_order_item` CHANGE COLUMN `unit_price` `unit_price` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `ex_rate` `ex_rate` DOUBLE(10,2) NULL DEFAULT '0.00'  , CHANGE COLUMN `fore_cur_amt` `fore_cur_amt` DOUBLE(10,2) NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`hotel_item_charge` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`inv_eo_item` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`invoice` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `balance` `balance` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`invoice_history` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `balance` `balance` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`invoice_item` CHANGE COLUMN `unit_price` `unit_price` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `net_price` `net_price` DOUBLE(10,2) NULL DEFAULT '0.00'  , CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`invoice_item_history` CHANGE COLUMN `unit_price` `unit_price` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`invoice_pmnt` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`invoice_pmnt_history` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`journal` CHANGE COLUMN `total_amt` `total_amt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`tour_booking_charge_item` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL  ;
ALTER TABLE `bterpdb`.`ticketing` CHANGE COLUMN `price_from` `price_from` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `price_to` `price_to` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`tour_dep` CHANGE COLUMN `full_twn` `full_twn` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `full_sgl` `full_sgl` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `full_ctw` `full_ctw` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `full_ceb` `full_ceb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `full_cnb` `full_cnb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_twn` `grnd_twn` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_sgl` `grnd_sgl` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_ctw` `grnd_ctw` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_ceb` `grnd_ceb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_cnb` `grnd_cnb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `tour_mgr_cost` `tour_mgr_cost` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `tfair_discount` `tfair_discount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cna_adt` `cna_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cpa_adt` `cpa_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `csi_adt` `csi_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `misc_adt` `misc_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total flight fee and charges for adult'  , CHANGE COLUMN `misc_chd` `misc_chd` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total flight fee and charges for child'  ;
ALTER TABLE `bterpdb`.`tour_dep_history` CHANGE COLUMN `full_twn` `full_twn` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `full_sgl` `full_sgl` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `full_ctw` `full_ctw` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `full_ceb` `full_ceb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `full_cnb` `full_cnb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_twn` `grnd_twn` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_sgl` `grnd_sgl` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_ctw` `grnd_ctw` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_ceb` `grnd_ceb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_cnb` `grnd_cnb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `tour_mgr_cost` `tour_mgr_cost` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `tfair_discount` `tfair_discount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cna_adt` `cna_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cpa_adt` `cpa_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `csi_adt` `csi_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `misc_adt` `misc_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total flight fee and charges for adult'  , CHANGE COLUMN `misc_chd` `misc_chd` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total flight fee and charges for child'  ;
ALTER TABLE `bterpdb`.`tour_dep_item` CHANGE COLUMN `amount` `amount` DOUBLE(10,2) NOT NULL  ;
ALTER TABLE `bterpdb`.`tour_operator` CHANGE COLUMN `tour_rate` `tour_rate` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`tour_package_room_price` CHANGE COLUMN `price_from` `price_from` DOUBLE(10,2) NOT NULL  ;
ALTER TABLE `bterpdb`.`tour_pkg` CHANGE COLUMN `deposit` `deposit` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `bag_deduction` `bag_deduction` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `tfair_discount` `tfair_discount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `price_diff_sgl` `price_diff_sgl` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `price_diff_ctw` `price_diff_ctw` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Child with twin'  , CHANGE COLUMN `price_diff_cwb` `price_diff_cwb` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Child with bed'  , CHANGE COLUMN `price_diff_cnb` `price_diff_cnb` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Child no bed'  , CHANGE COLUMN `grnd_sgl` `grnd_sgl` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_ctw` `grnd_ctw` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_cwb` `grnd_cwb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_cnb` `grnd_cnb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cna_adt` `cna_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cpa_adt` `cpa_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `csi_adt` `csi_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `price_from` `price_from` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `price_to` `price_to` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;
ALTER TABLE `bterpdb`.`tour_pkg_history` CHANGE COLUMN `deposit` `deposit` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `bag_deduction` `bag_deduction` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `tfair_discount` `tfair_discount` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `price_diff_sgl` `price_diff_sgl` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `price_diff_ctw` `price_diff_ctw` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Child with twin'  , CHANGE COLUMN `price_diff_cwb` `price_diff_cwb` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Child with bed'  , CHANGE COLUMN `price_diff_cnb` `price_diff_cnb` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Child no bed'  , CHANGE COLUMN `grnd_sgl` `grnd_sgl` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_ctw` `grnd_ctw` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_cwb` `grnd_cwb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `grnd_cnb` `grnd_cnb` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cna_adt` `cna_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `cpa_adt` `cpa_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `csi_adt` `csi_adt` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `price_from` `price_from` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;

# 1.1.88
# Steven
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.88', dt_upd = NOW();
ALTER TABLE `bterpdb`.`invoice_pmnt` ADD COLUMN `id_inv_pmnt` VARCHAR(3) NULL DEFAULT '0'  AFTER `upd_by` ;
ALTER TABLE `bterpdb`.`invoice_pmnt` ADD COLUMN `id_cashbook` BIGINT(20) NULL  AFTER `id_inv_pmnt` ;

# 1.1.89
#HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.88', dt_upd = NOW();
-- add cancel to booking
INSERT INTO `bterpdb`.`com_ref_data` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`)
VALUES ('U_BOOK_PMNT_STATUS_CC', '*', 'BOOK_PMNT_STATUS', 'CC', 'Cancelled Booking', 'Cancelled Booking', 6, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

# 1.1.90
# Ravi
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.90', dt_upd = NOW();
ALTER TABLE `bterpdb`.`cash_book` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `group_no` ;

# 1.1.91
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.91', dt_upd = NOW();
-- add bank transaction type to lookup
INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `created_by`, `dt_upd`, `upd_by`)
VALUES ( 'bank_tran', 'bill_pymt', 'Withdrawal', 'Withdrawal', '0', '4', 'SYSTEM', NOW(), 'SYSTEM');

# 1.1.92
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.92', dt_upd = NOW();
-- update status to cancel
update tour_dep set status_cd = 'CC' where date(dt_upd) < date(dt_dep) and status_cd = 'IA';

# 1.1.93
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.93', dt_upd = NOW();
-- add column id_ref
ALTER TABLE `account_trans` ADD COLUMN `id_ref` BIGINT NULL  AFTER `id_acct` ;
-- patch account transaction info for PB
update account_trans a1, account_trans a2 set a1.id_ref = a2.id where a1.credit = 0 and a2.credit <> 0 and a1.sys_prefix = 'PB' and a2.sys_prefix = 'PB' and a1.sys_no = a2.sys_no;

ALTER TABLE `ex_order_bill_pmnt` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `pmnt_amount` , ADD COLUMN `dt_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  AFTER `status_cd` , ADD COLUMN `created_by` VARCHAR(50) NOT NULL DEFAULT 'SYSTEM'  AFTER `dt_created` , ADD COLUMN `dt_upd` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  AFTER `created_by` , ADD COLUMN `upd_by` VARCHAR(50) NOT NULL DEFAULT 'SYSTEM'  AFTER `dt_upd` ;
update ex_order_bill_pmnt set dt_created = now(), dt_upd = now();

ALTER TABLE `ex_order_bill_pmnt` ADD COLUMN `payee` VARCHAR(255) NOT NULL  AFTER `ref_no` ;
update ex_order_bill_pmnt p, cash_book c set p.payee = c.payee where p.code = c.sys_no and c.sys_prefix = 'BP';
-- add account id to table
ALTER TABLE `ex_order_bill` ADD COLUMN `id_acct` BIGINT NOT NULL  AFTER `id_supplier` , CHANGE COLUMN `payment_term` `payment_term` FLOAT(10,2) NULL DEFAULT NULL  AFTER `amt_paid` ;
-- update account id
update ex_order_bill b, account_trans a set b.id_acct = a.id_acct where a.sys_prefix = 'PB' and a.id_acct in (4,5) and b.id = a.sys_no;

-- add cheque amount column to table
ALTER TABLE `ex_order_bill_pmnt` ADD COLUMN `chq_amount` DOUBLE(10,2) NOT NULL DEFAULT 0  AFTER `remarks` , CHANGE COLUMN `pmnt_amount` `pmnt_amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00';
-- patch data to cheque amount column
update ex_order_bill_pmnt p, cash_book c set p.chq_amount = c.credit where c.sys_prefix = 'BP' and p.code = c.sys_no;

-- change group no data type
ALTER TABLE `cash_book` CHANGE COLUMN `id_supplier` `id_supplier` BIGINT(20) NULL DEFAULT NULL  AFTER `id_bank` , CHANGE COLUMN `id_customer` `id_customer` BIGINT(20) NULL DEFAULT NULL  AFTER `id_supplier` , CHANGE COLUMN `group_no` `group_no` VARCHAR(100) NULL DEFAULT NULL  ;
update cash_book set group_no = null where group_no = 0;

# 1.1.94
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.94', dt_upd = NOW();
-- add payement type to table
ALTER TABLE `ex_order_bill_pmnt` ADD COLUMN `pmnt_type` VARCHAR(5) NOT NULL DEFAULT 'S' COMMENT 'S = single payment / M = multiple payment'  AFTER `pmnt_amount` ;
-- update multiple payment
update ex_order_bill_pmnt a, ex_order_bill_pmnt b set a.pmnt_type = 'M' where a.id_eo_bill <> b.id_eo_bill and a.code = b.code;
-- data patch for source in account transaction
update account_trans a, ex_order_bill b set a.source = replace(a.source, concat('SB-', b.code), concat('SB-', a.sys_no)) where a.sys_no = b.id and sys_cd = 'purc_bill';
-- data patch for ex order bill
update ex_order_bill set code = id;

-- data patch for misc in tour departure
update tour_dep d set misc_adt = (select sum(i.amount) from tour_dep_item i where i.id_tour_dep = d.id and i.id_company = 19 and i.code not in ('FT_SGL', 'FT_TWN', 'FT_CTW', 'FT_CWB', 'FT_CNB', 'FT_INFT', 'GA_SGL', 'GA_TWN', 'GA_CTW', 'GA_CWB', 'GA_CNB', 'GA_INFT', 'DEVIATION', 'DISC', 'FUEL_CHD', 'APT_CHD')), misc_chd = (select sum(i.amount) from tour_dep_item i where i.id_tour_dep = d.id and i.id_company = 19 and i.code not in ('FT_SGL', 'FT_TWN', 'FT_CTW', 'FT_CWB', 'FT_CNB', 'FT_INFT', 'GA_SGL', 'GA_TWN', 'GA_CTW', 'GA_CWB', 'GA_CNB', 'GA_INFT', 'DEVIATION', 'DISC', 'FUEL_ADT', 'APT_ADT')) where d.id in (select id_tour_dep from tour_dep_item where id_company = 19 group by id_tour_dep);
-- data patch for account transaction to terminate voided invoice transactions
update account_trans a, invoice i set a.status_cd = 'T' where a.sys_prefix = 'INV' and a.sys_no = i.code and i.status_cd = 'VD' and i.upd_by = 'SYSTEM';

# 1.1.95
# Kent
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.95', dt_upd = NOW();
ALTER TABLE `customer` ADD COLUMN `code` VARCHAR(50) NULL AFTER `corporate_name`;
update `customer` set `code` = `id`;

# 1.1.96
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '1.1.96', dt_upd = NOW();
-- patch negative transaction
update account_trans set debit = -credit, credit = 0 where credit < 0 and sys_cd = 'purc_bill';
update account_trans set credit = -debit, debit = 0 where debit < 0 and sys_cd = 'purc_bill';

# NO DO TRACKING UPDATE FOR THIS
# KS
# Patch Back data for Journal : Update the Destination data prefix with Reason and Source data prefix with Journal code.

UPDATE account_trans acct_trans INNER JOIN journal jour 
	ON acct_trans.sys_no = jour.id AND acct_trans.ref_no = jour.id
SET source = concat(sys_prefix, '-', acct_trans.sys_no),
	destination = CASE WHEN IFNULL(jour.reference, '') = '' THEN jour.reason ELSE concat(jour.reason, '-', jour.reference) END 
WHERE acct_trans.sys_cd = 'jrnl' and acct_trans.status_cd = 'A' and acct_trans.sys_prefix = 'JE';

# Debug 1.0.1
# HS
-- Data patch for exorder due to amt due in exorder not tally with bill payment
select * from ex_order_bill eob, ex_order eo where eob.bill_amt = eob.amt_paid and eob.id_eo is not null and eob.id_eo = eo.id and eo.amt_due = eob.amt_paid and eo.status_cd in ('BL', 'PD') and eob.status_cd != 'CC';

update ex_order_bill eob, ex_order eo set eo.amt_due = 0, eo.status_cd = 'PD', eob.status_cd = 'PD'
where eob.bill_amt = eob.amt_paid and eob.id_eo is not null and eob.id_eo = eo.id and eo.amt_due = eob.amt_paid and eo.status_cd in ('BL', 'PD') and eob.status_cd != 'CC';

select * from ex_order_bill eob, ex_order eo where eob.bill_amt = eob.amt_paid and eob.id_eo is not null and eob.id_eo = eo.id and eo.amt_due != 0 and eo.amt_due != eob.amt_paid and eo.status_cd in ('BL', 'PD') and eob.status_cd != 'CC';

update ex_order_bill eob, ex_order eo set eo.amt_due = eo.tot_amt - eob.amt_paid, eo.status_cd = 'PD', eob.status_cd = 'PD'
where eob.bill_amt = eob.amt_paid and eob.id_eo is not null and eob.id_eo = eo.id and eo.amt_due != 0 and eo.amt_due != eob.amt_paid and eo.status_cd in ('BL', 'PD') and eob.status_cd != 'CC';

-- allow corporate name to key in chinese word
ALTER TABLE `corporate` CHANGE COLUMN `name` `name` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL  ;
ALTER TABLE `customer` CHANGE COLUMN `corporate_name` `corporate_name` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL  ;

# Patch 1.0.2
# Kent
-- Added new column for keep tracking patch/debug
ALTER TABLE `db_tracking` ADD COLUMN `debug_version` varchar(255) NOT NULL AFTER `version`;

UPDATE `db_tracking` SET debug_version = '1.0.2', dt_upd = NOW();

-- Update Credit Note - invoice no. replacement eg.: INV 100001 to 100001 and Prefix(INV) from sys_num_conf table
update invoice set cn_inv_no = replace(cn_inv_no, 'INV ', '') where doc_type_cd = 'C';

# Debug 1.0.3 @2014-06-03 17:00
# HS
UPDATE `db_tracking` SET debug_version = '1.0.2', dt_upd = NOW();

-- Data patch for CN
update account_trans set credit = -credit where sys_cd = 'crdt_note' and credit < 0;
update account_trans set debit = -debit where sys_cd = 'crdt_note' and debit < 0;

select eo1.eoId, eo1.eoCode, eo1.billCode, t.id, t.sys_cd, t.sys_no, t.source, t.destination from account_trans t
left join (
	select eo.id as 'eoId', eo.code as 'eoCode', eobill.code as 'billCode' from ex_order eo, ex_order_bill eobill where eo.id = eobill.id_eo
) eo1 on t.source like concat('% EO-', eo1.eoId)
where sys_cd = 'purc_bill' and eo1.billCode = t.sys_no and t.source like '% EO-%' and t.source not like '% EO-';

update account_trans t
left join (
	select eo.id as 'eoId', eo.code as 'eoCode', eobill.code as 'billCode' from ex_order eo, ex_order_bill eobill where eo.id = eobill.id_eo
) eo1 on t.source like concat('% EO-', eo1.eoId)
set t.source = replace(t.source, concat('EO-', eo1.eoId), concat('EO-', eo1.eoCode))
where sys_cd = 'purc_bill' and eo1.billCode = t.sys_no and t.source like '% EO-%' and t.source not like '% EO-';

/*
select * from account_trans where destination like 'Supp-%';
select * from account_trans where destination like 'Memo-%';
select * from account_trans where destination like 'Withdrawal Adj-%';
select * from account_trans where destination like 'Debit Adj-%';
select * from account_trans where source like '%Bank Name-%';
select * from account_trans where source like '%Memo-%';
select * from account_trans where source like '%EO-';
*/

update account_trans set destination = replace(destination, 'Supp-' , '') where destination like 'Supp-%';
update account_trans set destination = replace(destination, 'Memo-' , '') where destination like 'Memo-%';
update account_trans set destination = replace(destination, 'Withdrawal Adj-' , '') where destination like 'Withdrawal Adj-%';
update account_trans set destination = replace(destination, 'Debit Adj-' , '') where destination like 'Debit Adj-%';
update account_trans set source = replace(destination, 'Bank Name-' , '') where source like '%Bank Name-%';
update account_trans set source = replace(destination, 'Memo-' , '') where source like '%Memo-%';
update account_trans set source = replace(destination, 'EO-' , '') where source like '%EO-';

# Debug 1.0.3 20140612
# KS Update Invoice related table for Credit Note negative amount patch
UPDATE `db_tracking` SET debug_version = '1.0.3', dt_upd = NOW();

UPDATE invoice inv 
SET amount = amount * -1
WHERE inv.doc_type_cd = 'C' AND inv.amount < 0;

UPDATE invoice_item invItem INNER JOIN invoice inv 
	ON inv.id = invItem.id_inv AND inv.doc_type_cd = 'C' 
SET invItem.unit_price = invItem.unit_price * -1, invItem.amount = invItem.amount * -1
WHERE invItem.unit_price < 0;

UPDATE invoice_pmnt invPayment INNER JOIN invoice inv 
	ON inv.id = invPayment.id_inv AND inv.doc_type_cd = 'C' 
SET invPayment.amount = invPayment.amount * -1
WHERE invPayment.amount < 0;
