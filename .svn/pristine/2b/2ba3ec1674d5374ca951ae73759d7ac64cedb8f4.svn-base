# 2.0.1
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.1', dt_upd = NOW();
update invoice_pmnt set pmnt_type_cd = 'cheq_rtn' where ref_no like 'CHQ RTN%';
update invoice_pmnt set pmnt_type_cd = 'rfd' where ref_no like 'RFD%';

-- SET @codeNum:=13920;
-- update customer set code = (@codeNum:=@codeNum+1) where id in (select id from (select max(id) AS 'id', code, count(code) as 'countCode' from customer where id_company = 1 group by code) a where countCode > 1 order by id);
-- update sys_num_conf set next_no = (@codeNum:=@codeNum+1) where id_company = 1 and code = 'cust';

# 2.0.2
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.2', dt_upd = NOW();
ALTER TABLE `ex_order_bill` CHANGE COLUMN `cn_no` `cn_no` VARCHAR(100) NULL DEFAULT NULL  ;

# 2.0.3
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.3', dt_upd = NOW();
update ex_order_bill b, ex_order o set b.id_eo = o.id where b.id_company = 1 and o.id_company = 1 and o.code = b.id_eo and b.id_eo is not null;

# 2.0.4
# KS 20130927 - create new VIEW which store debit_sum
# 

DROP VIEW IF EXISTS `vw_cash_book_sum`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_cash_book_sum`
AS SELECT
   `csh`.`group_no` AS `group_no`,sum(`csh`.`debit`) AS `debit_sum`
FROM `cash_book` `csh` where (`csh`.`group_no` <> '') group by `csh`.`group_no`;

# 2.0.5
# Kent
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.5', dt_upd = NOW();

ALTER TABLE `invoice_history` ADD COLUMN `travel_warrant_chk` TINYINT(1) NULL DEFAULT 0  AFTER `upd_by` ;
ALTER TABLE `invoice_item_history` ADD COLUMN `id_airline` bigint(20) DEFAULT NULL AFTER `description`;
ALTER TABLE `invoice_item_history` ADD COLUMN `net_price` float(10,2) NULL DEFAULT '0.00' AFTER `unit_price`;
ALTER TABLE `invoice_pax_history` ADD COLUMN `act_room_indicator` varchar(45) DEFAULT NULL AFTER `act_room_pairing_no`;
ALTER TABLE `invoice_pax_history` ADD COLUMN `act_room_remarks` text AFTER `act_room_indicator`;
ALTER TABLE `invoice_pax_history` ADD COLUMN `act_room_remarks_f` tinyint(1) DEFAULT '0' AFTER `act_room_remarks`;
ALTER TABLE `invoice_pmnt_history` ADD COLUMN `id_inv_pmnt` varchar(3) DEFAULT '0' AFTER `dt_upd`;
ALTER TABLE `invoice_pmnt_history` ADD COLUMN `id_cashbook` bigint(20) DEFAULT NULL AFTER `id_inv_pmnt`;

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
  `upd_by`,
  `travel_warrant_chk`)
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
  `upd_by`,
  `travel_warrant_chk`
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
  `id_airline`,
  `net_price`,
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
  `id_airline`,
  `net_price`,
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
  `act_room_pairing_no`,
  `act_room_indicator`,
  `act_room_remarks`,
  `act_room_remarks_f`)
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
  `act_room_pairing_no`,
  `act_room_indicator`,
  `act_room_remarks`,
  `act_room_remarks_f`
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
  `upd_by`,
  `id_inv_pmnt`,
  `id_cashbook`)
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
  `upd_by`,
  `id_inv_pmnt`,
  `id_cashbook`
  FROM invoice_pmnt
  WHERE id_inv = p_id);
  SET v_inv_pmnt_id := LAST_INSERT_ID();
select v_inv_id,v_inv_item_id, v_inv_pax_id, v_inv_pmnt_id, LAST_INSERT_ID();
END $$

DELIMITER ;


INSERT INTO `com_ref_data` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`)
VALUES ('U_AUDIT_TRL_CAT_INV', '*', 'AUDIT_TRL_CAT', 'INV', 'Invoice', 'Invoice', 1, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM');
INSERT INTO `com_ref_data` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`)
VALUES ('U_AUDIT_TRL_CAT_TOUR_DEP', '*', 'AUDIT_TRL_CAT', 'TOUR_DEP', 'Tour Departure', 'Tour Departure', 2, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

# 2.0.6
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.6', dt_upd = NOW();
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_task_scheduler`(IN flag INTEGER)
BEGIN
	IF flag = 1 THEN
		UPDATE tour_dep d, tour_pkg p SET d.status_cd = 'IA', d.dt_upd = NOW(), d.upd_by = 'SYSTEM' WHERE d.id_tour_pkg = p.id AND p.type_cd IN ('TOUR', 'MICE', 'FNE') AND DATE(d.dt_dep) <= DATE(NOW()) AND d.status_cd <> 'IA';
	ELSEIF flag = 2 THEN
		UPDATE tour_pkg SET status_cd = 'IA', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE type_cd = 'FNE' AND DATE(dt_travel_end) <= DATE(NOW()) AND status_cd <> 'IA';
	ELSEIF flag = 3 THEN
		UPDATE tour_pkg p, tour_dep d SET p.status_cd = 'IA', p.dt_upd = NOW(), p.upd_by = 'SYSTEM' WHERE p.id = d.id_tour_pkg AND p.type_cd = 'MICE' AND DATE(d.dt_dep) <= DATE(NOW()) AND p.status_cd <> 'IA';
	ELSEIF flag = 4 THEN
		UPDATE tour_booking SET pmnt_status_cd = 'KIVEXP', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE pmnt_status_cd = 'KIV' AND DATE(dt_exp) < DATE(NOW());
	ELSEIF flag = 5 THEN
		UPDATE invoice SET status_cd = 'VD', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE id_tour_booking IN (SELECT id FROM tour_booking WHERE pmnt_status_cd = 'KIVEXP' AND DATE(dt_upd) = DATE(NOW()));
	ELSEIF flag = 6 THEN
		UPDATE account_trans a, invoice i SET a.status_cd = 'T', a.dt_upd = NOW(), a.upd_by = 'SYSTEM' WHERE a.status_cd = 'A' AND a.sys_cd = 'invc' AND a.sys_no = i.code AND i.status_cd = 'VD' AND i.upd_by = 'SYSTEM';
	ELSEIF flag = 7 THEN
		UPDATE tour_dep SET tour_status_cd = 'A', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE id in (SELECT id FROM (SELECT * FROM (SELECT d.id, d.tour_status_cd, d.status_cd, d.seat_allotment, (d.reserved_seat + d.tour_mgr_pax + (SELECT CASE WHEN SUM(c.quantity) IS NULL THEN 0 ELSE SUM(c.quantity) END FROM tour_booking_charge_item c LEFT JOIN (SELECT id, id_tour_dep, pmnt_status_cd FROM tour_booking WHERE status_cd NOT IN ('CC', 'VD')) b ON b.pmnt_status_cd <> 'KIVEXP' WHERE b.id_tour_dep = d.id AND c.id_tour_booking = b.id AND c.code LIKE 'FT%' AND c.code <> 'FT_INFT')) AS 'quantity' FROM tour_dep d WHERE d.status_cd = 'AC' AND d.tour_status_cd = 'F') s WHERE seat_allotment > 0 AND seat_allotment > quantity) a);
	END IF;
END
DELIMITER ;

# 2.0.7
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.7', dt_upd = NOW();
ALTER TABLE `ex_order_airline` CHANGE COLUMN `pnr` `pnr` VARCHAR(50) NULL DEFAULT NULL;

# 2.0.8
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.8', dt_upd = NOW();
DROP procedure IF EXISTS `prc_task_scheduler`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_task_scheduler`(IN flag INTEGER)
BEGIN
	IF flag = 1 THEN
		UPDATE tour_dep d, tour_pkg p SET d.status_cd = 'IA', d.dt_upd = NOW(), d.upd_by = 'SYSTEM' WHERE d.id_tour_pkg = p.id AND p.type_cd IN ('TOUR', 'MICE', 'FNE') AND DATE(d.dt_dep) <= DATE(NOW()) AND d.status_cd <> 'IA';
	ELSEIF flag = 2 THEN
		UPDATE tour_pkg SET status_cd = 'IA', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE type_cd = 'FNE' AND DATE(dt_travel_end) <= DATE(NOW()) AND status_cd <> 'IA';
	ELSEIF flag = 3 THEN
		UPDATE tour_pkg p, tour_dep d SET p.status_cd = 'IA', p.dt_upd = NOW(), p.upd_by = 'SYSTEM' WHERE p.id = d.id_tour_pkg AND p.type_cd = 'MICE' AND DATE(d.dt_dep) <= DATE(NOW()) AND p.status_cd <> 'IA';
	ELSEIF flag = 4 THEN
		UPDATE tour_booking SET pmnt_status_cd = 'KIVEXP', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE pmnt_status_cd = 'KIV' AND status_cd <> 'VD' AND DATE(dt_exp) < DATE(NOW());
	ELSEIF flag = 5 THEN
		UPDATE invoice SET status_cd = 'VD', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE id_tour_booking IN (SELECT id FROM tour_booking WHERE pmnt_status_cd = 'KIVEXP' AND DATE(dt_upd) = DATE(NOW()));
	ELSEIF flag = 6 THEN
		UPDATE account_trans a, invoice i SET a.status_cd = 'T', a.dt_upd = NOW(), a.upd_by = 'SYSTEM' WHERE a.status_cd = 'A' AND a.sys_cd = 'invc' AND a.sys_no = i.code AND i.status_cd = 'VD' AND i.upd_by = 'SYSTEM';
	ELSEIF flag = 7 THEN
		UPDATE tour_dep SET tour_status_cd = 'A', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE id in (SELECT id FROM (SELECT * FROM (SELECT d.id, d.tour_status_cd, d.status_cd, d.seat_allotment, (d.reserved_seat + d.tour_mgr_pax + (SELECT CASE WHEN SUM(c.quantity) IS NULL THEN 0 ELSE SUM(c.quantity) END FROM tour_booking_charge_item c LEFT JOIN (SELECT id, id_tour_dep, pmnt_status_cd FROM tour_booking WHERE status_cd NOT IN ('CC', 'VD')) b ON b.pmnt_status_cd <> 'KIVEXP' WHERE b.id_tour_dep = d.id AND c.id_tour_booking = b.id AND c.code LIKE 'FT%' AND c.code <> 'FT_INFT')) AS 'quantity' FROM tour_dep d WHERE d.status_cd = 'AC' AND d.tour_status_cd = 'F') s WHERE seat_allotment > 0 AND seat_allotment > quantity) a);
	END IF;
END$$
DELIMITER ;

# 2.0.9
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.9', dt_upd = NOW();
ALTER TABLE `invoice_pmnt` CHANGE COLUMN `ref_no` `ref_no` VARCHAR(100) NULL DEFAULT NULL COMMENT 'cheque no./bank'  ;
ALTER TABLE `invoice_pmnt_history` CHANGE COLUMN `ref_no` `ref_no` VARCHAR(100) NULL DEFAULT NULL COMMENT 'cheque no./bank'  ;

# 2.0.10
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.10', dt_upd = NOW();
ALTER TABLE `ex_order_bill` CHANGE COLUMN `payment_term` `payment_term` VARCHAR(50) NULL DEFAULT NULL  ;

update supplier set pmnt_type_cd = 'CHEQUE' where pmnt_type_cd = '0';
update supplier set pmnt_type_cd = 'cr_30' where pmnt_type_cd = '30';
update supplier set pmnt_type_cd = 'cr_60' where pmnt_type_cd = '60';
update supplier set pmnt_type_cd = 'cr_90' where pmnt_type_cd = '90';

update ex_order set pmnt_type_cd = 'CHEQUE' where pmnt_type_cd = '0';
update ex_order set pmnt_type_cd = 'cr_30' where pmnt_type_cd = '30';
update ex_order set pmnt_type_cd = 'cr_60' where pmnt_type_cd = '60';
update ex_order set pmnt_type_cd = 'cr_90' where pmnt_type_cd = '90';

update ex_order_bill set payment_term = 'CHEQUE' where payment_term = '0.00';
update ex_order_bill set payment_term = 'cr_30' where payment_term = '30.00';
update ex_order_bill set payment_term = 'cr_60' where payment_term = '60.00';
update ex_order_bill set payment_term = 'cr_90' where payment_term = '90.00';

# 2.0.11
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.11', dt_upd = NOW();
update cash_book cb
left join (
	select id, id_company, code from customer
) c on c.code = cb.id_customer and c.id_company = (select b.id_company from bank b where b.id = cb.id_bank)
set cb.id_customer = c.id
where date(cb.dt_created) >= '2013-09-07';

# 2.0.12
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.12', dt_upd = NOW();
ALTER TABLE `bank_recon` ADD COLUMN `cashbook_bal` DOUBLE(10,2) NOT NULL DEFAULT 0  AFTER `statement_bal` , CHANGE COLUMN `credit` `statement_cr` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `debit` `statement_dr` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  , CHANGE COLUMN `balance` `statement_bal` DOUBLE(10,2) NOT NULL DEFAULT '0.00'  ;

# 2.0.13
# HS
#
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.13', dt_upd = NOW();
ALTER TABLE `bank_recon` ADD COLUMN `cashbook_cr` DOUBLE(10,2) NOT NULL DEFAULT 0  AFTER `statement_bal` , ADD COLUMN `cashbook_dr` DOUBLE(10,2) NOT NULL DEFAULT 0  AFTER `cashbook_cr` ;
ALTER TABLE `cash_book` ADD COLUMN `dt_clear` DATETIME NULL  AFTER `is_mark` , ADD COLUMN `dt_mark` DATETIME NULL  AFTER `dt_clear` ;

# 2.0.14
# HS
# Invoice changes
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.14', dt_upd = NOW();
ALTER TABLE `invoice_item` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `amount` , CHANGE COLUMN `dt_upd` `dt_upd` DATETIME NOT NULL  AFTER `status_cd` ;
ALTER TABLE `invoice_pmnt` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `remarks` , CHANGE COLUMN `id_cashbook` `id_cashbook` BIGINT(20) NULL DEFAULT NULL  AFTER `id_bank` , CHANGE COLUMN `id_inv_pmnt` `id_inv_pmnt` VARCHAR(3) NULL DEFAULT '0'  AFTER `id_cashbook` ;
ALTER TABLE `invoice_item_history` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `amount` , CHANGE COLUMN `dt_upd` `dt_upd` DATETIME NOT NULL  AFTER `status_cd` ;
ALTER TABLE `invoice_pmnt_history` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `remarks` , CHANGE COLUMN `id_cashbook` `id_cashbook` BIGINT(20) NULL DEFAULT NULL  AFTER `id_bank` , CHANGE COLUMN `id_inv_pmnt` `id_inv_pmnt` VARCHAR(3) NULL DEFAULT '0'  AFTER `id_cashbook` ;
ALTER TABLE `invoice_pax` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `act_room_remarks_f` ;
ALTER TABLE `invoice_pax_history` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `act_room_remarks_f` ;

DELIMITER $$
DROP PROCEDURE IF EXISTS `prc_invoice` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_invoice`(IN p_id bigint(20), IN p_action varchar(50), IN p_reason text)
BEGIN

  DECLARE v_inv_id bigint(20);
  DECLARE v_inv_item_id bigint(20);
  DECLARE v_inv_pax_id bigint(20);
  DECLARE v_inv_pmnt_id bigint(20);

  /***** Invoice History *****/

  INSERT INTO invoice_history (`id_hist`, `id_company`, `id_customer`, `id_acct`, `id_tour_booking`, `dt_inv`, `code`, `doc_type_cd`, `type_cd`, `attn_to`, `cn_inv_no`,
  	`pmnt_type_cd`, `id_saler`, `id_tour_dep`, `dt_departure`, `id_issuer`, `id_eo_ref`, `cat_cd`, `order_cd`, `delivery_cd`, `inv_due`, `gds_booking_ref`,
  	`amount`, `balance`, `reason`, `action_cd`, `status_cd`, `is_inv_paid`, `subj_line`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `travel_warrant_chk`)
  (SELECT
  	`id`, `id_company`, `id_customer`, `id_acct`, `id_tour_booking`, `dt_inv`, `code`, `doc_type_cd`, `type_cd`, `attn_to`, `cn_inv_no`, `pmnt_type_cd`, `id_saler`,
  	`id_tour_dep`, `dt_departure`, `id_issuer`, `id_eo_ref`, `cat_cd`, `order_cd`, `delivery_cd`, `inv_due`, `gds_booking_ref`, `amount`, `balance`, p_reason, p_action,
  	`status_cd`, `is_inv_paid`, `subj_line`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `travel_warrant_chk`
  FROM invoice
  WHERE id = p_id);
  SET v_inv_id := LAST_INSERT_ID();

  /***** Invoice Item History *****/

  INSERT INTO invoice_item_history (`id_ref`, `id_hist`, `id_inv`, `id_acct`, `id_inv_eo_item`, `description`, `id_airline`, `net_price`, `quantity`, `unit_price`, `amount`,
  	`status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
  (SELECT
  	v_inv_id, `id`, `id_inv`, `id_acct`, `id_inv_eo_item`, `description`, `id_airline`, `net_price`, `quantity`, `unit_price`, `amount`, `status_cd`, `dt_created`, `created_by`,
  	`dt_upd`, `upd_by`
  FROM invoice_item
  WHERE id_inv = p_id);
  SET v_inv_item_id := LAST_INSERT_ID();

  /***** Invoice Pax History *****/

  INSERT INTO invoice_pax_history (`id_ref`, `id_hist`, `id_inv`, `id_cust`, `room_type_cd`, `room_pairing_no`, `travel_ins_type`, `travel_ins_policy`, `ticket_no`, `special_request`,
  	`lang_cd`, `act_room_type_cd`, `act_room_pairing_no`, `act_room_indicator`, `act_room_remarks`, `act_room_remarks_f`, `status_cd`)
  (SELECT
  	v_inv_id, `id`, `id_inv`, `id_cust`, `room_type_cd`, `room_pairing_no`, `travel_ins_type`, `travel_ins_policy`, `ticket_no`, `special_request`, `lang_cd`, `act_room_type_cd`,
  	`act_room_pairing_no`, `act_room_indicator`, `act_room_remarks`, `act_room_remarks_f`, `status_cd`
  FROM invoice_pax
  WHERE id_inv = p_id);
  SET v_inv_pax_id := LAST_INSERT_ID();

  /***** Invoice Payment History *****/

  INSERT INTO invoice_pmnt_history (`id_ref`, `id_hist`, `id_inv`, `id_issuer`, `id_bank`, `dt_pmnt`, `code`, `pmnt_type_cd`, `ref_no`, `received_from`, `amount`,
  	`remarks`, `status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `id_inv_pmnt`, `id_cashbook`)
  (SELECT
  	v_inv_id, `id`, `id_inv`, `id_issuer`, `id_bank`, `dt_pmnt`, `code`, `pmnt_type_cd`, `ref_no`, `received_from`, `amount`, `remarks`, `status_cd`, `dt_created`,
  	`created_by`, `dt_upd`, `upd_by`, `id_inv_pmnt`, `id_cashbook`
  FROM invoice_pmnt
  WHERE id_inv = p_id);
  SET v_inv_pmnt_id := LAST_INSERT_ID();
select v_inv_id,v_inv_item_id, v_inv_pax_id, v_inv_pmnt_id, LAST_INSERT_ID();
END $$
DELIMITER ;

ALTER TABLE `person_contact` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `number` ;
ALTER TABLE `person_identity` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `number` ;
ALTER TABLE `customer_remarks` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `time_stamp` ;
ALTER TABLE `corporate_contact` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `number` ;
ALTER TABLE `corporate_contact` ADD COLUMN `contact_person` VARCHAR(100) NULL  AFTER `number` ;

/***** Change sales menu list *****/
UPDATE `sec_func` SET `LEVEL_NO`=2, `SEQ_NO`=4, `U_PARENT_FUNC`='U_SALES' WHERE `UUID`='U_SALES_BOOKING_LIST';
UPDATE `sec_func` SET `LEVEL_NO`=2, `SEQ_NO`=3, `U_PARENT_FUNC`='U_SALES' WHERE `UUID`='U_SALES_BOOKING_RESERVE';
UPDATE `sec_func` SET `SEQ_NO`=5, `STATUS_CD`='T' WHERE `ID`='87';

################################################################################################################################
# PHASE 1 - update_script.sql
# 2.0.14.1
# 2.0.14.2
# 2.0.14.3
# 2.0.14.4
# 2.0.14.5
# 2.0.14.6
# 2.0.14.7
# 2.0.14.8
################################################################################################################################

# 2.0.15
# HS
# Exchange orders changes
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.15', dt_upd = NOW();
/***** Add status code for ex order tables *****/
ALTER TABLE `ex_order_item` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `fore_cur_amt` ;
ALTER TABLE `ex_order_pax` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `payable` ;
ALTER TABLE `ex_order_airline` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `pnr` ;
ALTER TABLE `ex_order_hotel` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `hotel_usage` ;
/*ALTER TABLE `ex_order_inv` ADD COLUMN `status_cd` VARCHAR(50) NOT NULL DEFAULT 'A'  AFTER `id_inv` ;*/

/* Move to phase 1 - 2.0.14.7
ALTER TABLE `ex_order` ADD COLUMN `dt_tour_dep` DATETIME NULL  AFTER `dt_due` ;
update ex_order eo, tour_dep td set eo.dt_tour_dep = td.dt_dep where eo.id_tour = td.id;
*/

/** supplier - add account column to supplier **/
ALTER TABLE `supplier` ADD COLUMN `id_acct` BIGINT NULL  AFTER `id_company` ;
/*update supplier set type_cd = (case when type_cd = 'TC' then 'TRADE CREDITOR' else 'SUNDRY CREDITOR' end);*/
update supplier s, account a set s.id_acct = a.id where s.id_company = a.id_company and trim(a.description) = (case when s.type_cd = 'TC' then 'TRADE CREDITOR' else 'SUNDRY CREDITOR' end);


/***** drop tables id *****/
/***** temporarily commented by steven at 19 March 2014 *****/
/* 
 * ALTER TABLE `person_lang` DROP COLUMN `id` , DROP PRIMARY KEY ;
 * ALTER TABLE `person_meal` DROP COLUMN `id` , DROP PRIMARY KEY ;
 * ALTER TABLE `ex_order_inv` DROP COLUMN `id` , DROP PRIMARY KEY ;
*/


# 2.0.16
# KS
# Add new Menu list : Monthly Ticketing Sales Report

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.16', dt_upd = NOW();

-- add Monthly Ticketing Sales Report function
/*
INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, 
	IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
VALUES('U_SALES_REPORT', 'SALES_REPORT', 'Report', 'L', '/app/sales/report', '', 
	'1', 2, 6, 'U_SALES', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP)
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;
*/

INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, 
	IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
VALUES('U_SALES_REPORT_MONTHLYTICKETING', 'SALES_REPORT_MONTHLYTICKETING', 'Monthly Ticketing Sales', 'L', '/app/sales/report/monthlyTicketing', '', 
	'1', 3, 1, 'U_SALES_REPORT', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP)
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;

# 2.0.17
# Ravi
# Add new Menu list : Debtors Statement  Report

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.17', dt_upd = NOW();

INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('U_SALES_REPORT_DEBTOR_STMT', '*', 'SALES_REPORT_DEBTOR_STMT', 'Debtors Statement', 'L', 
'/app/sales/report/DebtorsStatement', '1', '3', '3', 'U_SALES_REPORT', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;

# 2.0.18
# Ravi
# Added new category , items  to lookup 

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.18', dt_upd = NOW();


INSERT INTO `lookup_cat` (`code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES ('deq_prd', 'Delinquency Period', 'Delinquency Period', NOW(), 'SYSTEM',NOW(), 'SYSTEM');

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES ('deq_prd', 'current', 'Current', 'Current', '0', '1', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES ('deq_prd', '30_days', '30 days', '30 days', '0', '2', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES ('deq_prd', '60_days', '60 days', '60 days', '0', '3', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES ('deq_prd', '90_days', '90 days', '90 days', '0', '4', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) 
VALUES ('deq_prd', '120_days', '120 days', '120 days', '0', '5', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES ('deq_prd', 'over_120_days', 'Over 120 days', 'Over 120 days', '0', '6', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- update exchange order payment terms based on supplier payment terms
/*
update ex_order eo
left join (
	select pmnt_type_cd, id from supplier
) s on s.id = eo.id_supplier
set eo.pmnt_type_cd = s.pmnt_type_cd
where eo.pmnt_type_cd = '0';
*/

# 2.0.19
# HS
# New tour remarks template

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.19', dt_upd = NOW();

INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, 
	IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
VALUES('U_MAINT_TMP_MSG', 'MAINT_TMP_MSG', 'Template Message', 'L', '/app/maintenance/tmpMsg', '', 
	'1', 2, 8, 'U_MAINTENANCE', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP)
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;

DROP TABLE IF EXISTS tmp_msg;

CREATE  TABLE `tmp_msg` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `cat_cd` VARCHAR(50) NOT NULL DEFAULT 'TOUR' ,
  `code` VARCHAR(50) NOT NULL COMMENT 'Template code which assign by user.' ,
  `description` VARCHAR(100) NULL ,
  `remarks` TEXT NOT NULL ,
  `status_cd` VARCHAR(1) DEFAULT 'A' ,
  `dt_created` DATETIME NOT NULL ,
  `created_by` VARCHAR(50) NOT NULL ,
  `dt_upd` DATETIME NOT NULL ,
  `upd_by` VARCHAR(50) NOT NULL ,
  `version` INT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) );

INSERT INTO `com_ref_data` (`UUID`, `APP_ID`, `CAT_CD`, `REF_CD`, `REF_VALUE`, `REF_DESC`, `SEQ_NO`, `STATUS_CD`, `IS_DEFAULT`, `IS_DISPLAY`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`)
VALUES ('U_TMP_MSG_TOUR', '*', 'TMP_MSG_TOUR', 'TOUR', 'Tour', 'Tour template message', 1, 'A', 'N', 'Y', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

UPDATE `sec_func` SET `LEVEL_NO`=2 WHERE `ID`='80';
UPDATE `sec_func` SET `LEVEL_NO`=2 WHERE `ID`='89';
UPDATE `sec_func` SET `LEVEL_NO`=2 WHERE `ID`='88';
UPDATE `sec_func` SET `LEVEL_NO`=2 WHERE `ID`='78';
UPDATE `sec_func` SET `LEVEL_NO`=2 WHERE `ID`='83';
UPDATE `sec_func` SET `LEVEL_NO`=2 WHERE `ID`='79';

# 2.0.20
# KS

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.20', dt_upd = NOW();


DROP TABLE IF EXISTS company_tax;
CREATE TABLE IF NOT EXISTS company_tax (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_company` BIGINT(20) NOT NULL,
  `type` ENUM('GST', 'TAX') NOT NULL COMMENT 'GST, TAX',
  `tax_number` VARCHAR(20) NULL,
  `valid_from` DATETIME NULL,
  `valid_to` DATETIME NULL,
  `gst_filling` DATETIME NULL,
  `refund_carry_forward` ENUM('Y', 'N') NOT NULL,
  `period` VARCHAR(1) NULL,
  `currency` VARCHAR(4) NULL,
  `status_cd` VARCHAR(1) NOT NULL,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NOT NULL,
  `dt_update` DATETIME NOT NULL,
  `updated_by` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `company_tax_FK_COMPANY_idx` (`id_company` ASC),
  CONSTRAINT `company_tax_FK_COMPANY`
    FOREIGN KEY (`id_company`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DROP TABLE IF EXISTS company_tax_log;
CREATE TABLE IF NOT EXISTS company_tax_log AS SELECT * FROM company_tax;

DELIMITER $$
CREATE TRIGGER `company_tax_AFTER_INSERT` 
AFTER INSERT ON `company_tax` 
FOR EACH ROW
BEGIN
	INSERT INTO company_tax_log (id, id_company, `type`, tax_number, valid_from, 
		valid_to, gst_filling, refund_carry_forward, period, currency, status_cd, dt_created, 
        created_by, dt_update, updated_by) 
	VALUES (NEW.id, NEW.id_company, NEW.`type`, NEW.tax_number, NEW.valid_from, 
		NEW.valid_to, NEW.gst_filling, NEW.refund_carry_forward, NEW.period, NEW.currency, NEW.status_cd, NEW.dt_created, NEW.created_by, NEW.dt_update, NEW.updated_by);
END;
$$

DELIMITER $$
CREATE TRIGGER `company_tax_AFTER_UPDATE` 
AFTER UPDATE ON `company_tax` 
FOR EACH ROW
BEGIN
	INSERT INTO company_tax_log (id, id_company, `type`, tax_number, valid_from, 
		valid_to, gst_filling, refund_carry_forward, period, currency, status_cd, dt_created, 
        created_by, dt_update, updated_by) 
	VALUES (NEW.id, NEW.id_company, NEW.`type`, NEW.tax_number, NEW.valid_from, 
		NEW.valid_to, NEW.gst_filling, NEW.refund_carry_forward, NEW.period, NEW.currency, NEW.status_cd, NEW.dt_created, NEW.created_by, NEW.dt_update, NEW.updated_by);
END;
$$

DELIMITER ;

# 2.0.21
# KS
# GST TaxCode table

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.21', dt_upd = NOW();


DROP TABLE IF EXISTS tax_code;
CREATE TABLE IF NOT EXISTS tax_code (
  `id` INT NOT NULL AUTO_INCREMENT,
  `UUID` VARCHAR(15) NOT NULL,
  `tax_type` ENUM('GST','TAX') NOT NULL,
  `sub_type` VARCHAR(3) NULL,
  `code` VARCHAR(10) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `rate` FLOAT(11,2) NOT NULL DEFAULT 0.00,
  `status_cd` VARCHAR(1) NOT NULL,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NOT NULL,
  `dt_update` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS tax_code_log;
CREATE TABLE IF NOT EXISTS tax_code_log AS SELECT * FROM tax_code;

DELIMITER $$
CREATE TRIGGER `tax_code_AFTER_INSERT` 
AFTER INSERT ON `tax_code` 
FOR EACH ROW
BEGIN
	INSERT INTO tax_code_log (id, UUID, tax_type, sub_type, `code`, description, rate, 
		status_cd, dt_created, created_by, dt_update, updated_by) 
	VALUES (NEW.id, NEW.UUID, NEW.tax_type, NEW.sub_type, NEW.`code`, NEW.description, NEW.rate,  
        NEW.status_cd, NEW.dt_created, NEW.created_by, NEW.dt_update, NEW.updated_by);
END;
$$

DELIMITER $$
CREATE TRIGGER `tax_code_AFTER_UPDATE` 
AFTER UPDATE ON `tax_code` 
FOR EACH ROW
BEGIN
	INSERT INTO tax_code_log (id, UUID, tax_type, sub_type, `code`, description, rate, 
		status_cd, dt_created, created_by, dt_update, updated_by) 
	VALUES (NEW.id, NEW.UUID, NEW.tax_type, NEW.sub_type, NEW.`code`, NEW.description, NEW.rate,  
        NEW.status_cd, NEW.dt_created, NEW.created_by, NEW.dt_update, NEW.updated_by);
END;
$$

DELIMITER ;


INSERT INTO tax_code (UUID, tax_type, sub_type, `code`, rate, description, status_cd, dt_created, created_by, dt_update, updated_by) 
VALUES 
	('TAX150000001', 'GST', 'IN', 'TX', 		6, 'Purchases with GST incurred at 6% and directly attributable to taxable supplies.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000002', 'GST', 'IN', 'IM', 		6, 'Import of goods with GST incurred.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000003', 'GST', 'IN', 'IS', 		0, 'Imports under special scheme with no GST incurred (e.g. Approved Trader Scheme, ATMS Scheme).', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000004', 'GST', 'IN', 'BL', 		6, 'Purchases with GST incurred but not claimable (Disallowance of Input Tax) (e.g. medical expenses for staff).', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000005', 'GST', 'IN', 'NR', 		0, 'Purchase from non GST-registered supplier with no GST incurred.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000006', 'GST', 'IN', 'ZP', 		0, 'Purchase from GST-registered supplier with no GST incurred. (e.g. supplier provides transportation of goods that qualify as international services).', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000007', 'GST', 'IN', 'EP', 		0, 'Purchases exempted from GST. E.g. purchase of residential property or financial services.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000008', 'GST', 'IN', 'OP', 		0, 'Purchase transactions which is out of the scope of GST legislation (e.g. purchase of goods overseas).', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000009', 'GST', 'IN', 'TX-E43', 	6, 'Purchase with GST incurred directly attributable to incidental exempt supplies.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000010', 'GST', 'IN', 'TX-N43', 	6, 'Purchase with GST incurred directly attributable to non-incidental exempt supplies.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000011', 'GST', 'IN', 'TX-RE', 		6, 'Purchase with GST incurred that is not directly attributable to taxable or exempt supplies.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000012', 'GST', 'IN', 'GP', 		0, 'Purchase transactions which disregarded under GST legislation (e.g. purchase within GST group registration).', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000013', 'GST', 'IN', 'AJP', 		6, 'Any adjustment made to Input Tax e.g.: Bad Debt Relief & other input tax adjustment.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000015', 'GST', 'OUT', 'SR', 		6, 'Standard-rated supplies with GST Charged', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000016', 'GST', 'OUT', 'ZRL', 		0, 'Local supply of goods or services which are subject to zero rated supplies', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000017', 'GST', 'OUT', 'ZRE', 		0, 'Exportation of goods or services which are subject to zero rated supplies', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000018', 'GST', 'OUT', 'ES43', 		0, 'Incidental Exempt supplies', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000019', 'GST', 'OUT', 'DS', 		6, 'Deemed supplies (e.g. transfer or disposal of business assets without consideration)', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000020', 'GST', 'OUT', 'OS', 		0, 'Out-of-scope supplies', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000021', 'GST', 'OUT', 'ES', 		0, 'Exempt supplies under GST.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000022', 'GST', 'OUT', 'RS', 		0, 'Relief supply under GST', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000023', 'GST', 'OUT', 'GS', 		0, 'Disregarded supplies', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000024', 'GST', 'OUT', 'AJS', 		6, 'Any adjustment made to Output Tax (e.g. Longer period adjustment, Bad Debt recover, outstanding invoice > 6 months and other output tax adjustments)', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, 
	IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
VALUES
	('U_GST', 'GST', 'GST', 'L', '#', '', 
		'1', 1, 15, NULL, 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP),
	('U_GST_CODE', 'GST_CODE', 'Tax Code', 'L', '/app/gst/code', '', 
		'1', 2, 1, 'U_GST', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP),
	('U_GST_RETURN', 'GST_RETURN', 'GST Return', 'L', '/app/gst/taxreturn', '', 
		'1', 2, 2, 'U_GST', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

UPDATE sec_func
SET SEQ_NO = 98
WHERE UUID='U_ACCT_REPORT';

UPDATE sec_func
SET SEQ_NO = SEQ_NO + 1
WHERE UUID='U_MAINTENANCE';

UPDATE sec_func
SET SEQ_NO = SEQ_NO + 1
WHERE UUID='U_HISTORY';

INSERT INTO global_config (`code`,`description`,`value`, `seq`, `created_by`,`dt_upd`,`upd_by`) 
VALUES 
	('GST_GAF_VER','GST GAF File Version','GAFv1.0.0', 1, 'Super User', NOW(),'Super User'),
	('GST_GAF_GRACE_PERIOD','GST Submit Close Period', 30, 1, 'Super User', NOW(),'Super User');



# 2.0.22
# KS
# GST Summary table

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.22', dt_upd = NOW();

DROP TABLE IF EXISTS gst_summary;
CREATE TABLE IF NOT EXISTS gst_summary (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_company` VARCHAR(15) NOT NULL,
  `date_from` DATE NOT NULL,
  `date_to` DATE NOT NULL,
  `opening_balance` FLOAT(11,2) NOT NULL DEFAULT 0.00,
  `output_tax` FLOAT(11,2) NOT NULL DEFAULT 0.00,
  `input_tax` FLOAT(11,2) NOT NULL DEFAULT 0.00,
  `amount_clamable` FLOAT(11,2) NOT NULL DEFAULT 0.00,
  `amount_payable` FLOAT(11,2) NOT NULL DEFAULT 0.00,
  `closing_balance` FLOAT(11,2) NOT NULL DEFAULT 0.00,
  `refund_carry_forward` ENUM('Y', 'N') NOT NULL,
  `processed` ENUM('P', 'L', 'S') NOT NULL,
  `status_cd` VARCHAR(1) NOT NULL,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NOT NULL,
  `dt_update` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS gst_summary_log;
CREATE TABLE IF NOT EXISTS gst_summary_log AS SELECT * FROM gst_summary;

DELIMITER $$
CREATE TRIGGER `gst_summary_AFTER_INSERT` 
AFTER INSERT ON `gst_summary` 
FOR EACH ROW
BEGIN
	INSERT INTO gst_summary_log (id, id_company, date_from, date_to, opening_balance, 
		output_tax, input_tax, amount_clamable, amount_payable, 
        closing_balance, refund_carry_forward, processed, 
        status_cd, dt_created, created_by, dt_update, updated_by) 
	VALUES (NEW.id, NEW.id_company, NEW.date_from, NEW.date_to, NEW.opening_balance, 
		NEW.output_tax, NEW.input_tax, NEW.amount_clamable, NEW.amount_payable, 
        NEW.closing_balance, NEW.refund_carry_forward, NEW.processed,
        NEW.status_cd, NEW.dt_created, NEW.created_by, NEW.dt_update, NEW.updated_by);
END;
$$

DELIMITER $$
CREATE TRIGGER `gst_summary_AFTER_UPDATE` 
AFTER UPDATE ON `gst_summary` 
FOR EACH ROW
BEGIN
	INSERT INTO gst_summary_log (id, id_company, date_from, date_to, opening_balance, 
		output_tax, input_tax, amount_clamable, amount_payable, 
        closing_balance, refund_carry_forward, processed, 
        status_cd, dt_created, created_by, dt_update, updated_by) 
	VALUES (NEW.id, NEW.id_company, NEW.date_from, NEW.date_to, NEW.opening_balance, 
		NEW.output_tax, NEW.input_tax, NEW.amount_clamable, NEW.amount_payable, 
        NEW.closing_balance, NEW.refund_carry_forward, NEW.processed,
        NEW.status_cd, NEW.dt_created, NEW.created_by, NEW.dt_update, NEW.updated_by);
END;
$$

DELIMITER ;

/*	gst_summary_tax	*/
DROP TABLE IF EXISTS gst_summary_tax;
CREATE TABLE IF NOT EXISTS gst_summary_tax (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_gst_summary` INT NOT NULL,
  `tax_code` VARCHAR(10) NOT NULL,
  `amount` FLOAT(11,2) NOT NULL DEFAULT 0.00,
  `rate` FLOAT(11,2) NOT NULL DEFAULT 0.00,
  `tax_amount` FLOAT(11,2) NOT NULL DEFAULT 0.00,
  `status_cd` VARCHAR(1) NOT NULL,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NOT NULL,
  `dt_update` DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS gst_summary_tax_log;
CREATE TABLE IF NOT EXISTS gst_summary_tax_log AS SELECT * FROM gst_summary_tax;

DELIMITER $$
CREATE TRIGGER `gst_summary_tax_AFTER_INSERT` 
AFTER INSERT ON `gst_summary_tax` 
FOR EACH ROW
BEGIN
	INSERT INTO gst_summary_tax_log (id, id_gst_summary, tax_code, 
		amount, rate, tax_amount, 
		status_cd, dt_created, created_by, dt_update, updated_by) 
	VALUES (NEW.id, NEW.id_gst_summary, NEW.tax_code, 
		NEW.amount, NEW.rate, NEW.tax_amount, 
        NEW.status_cd, NEW.dt_created, NEW.created_by, NEW.dt_update, NEW.updated_by);
END;
$$

DELIMITER $$
CREATE TRIGGER `gst_summary_tax_AFTER_UPDATE` 
AFTER UPDATE ON `gst_summary_tax` 
FOR EACH ROW
BEGIN
	INSERT INTO gst_summary_tax_log (id, id_gst_summary, tax_code, 
		amount, rate, tax_amount, 
		status_cd, dt_created, created_by, dt_update, updated_by) 
	VALUES (NEW.id, NEW.id_gst_summary, NEW.tax_code, 
		NEW.amount, NEW.rate, NEW.tax_amount, 
        NEW.status_cd, NEW.dt_created, NEW.created_by, NEW.dt_update, NEW.updated_by);
END;
$$

DELIMITER ;

ALTER TABLE `invoice_item` 
	ADD COLUMN `tax_code` VARCHAR(10) NULL,
	ADD COLUMN `tax_rate` FLOAT(11,2) NULL,
	ADD COLUMN `tax_amount` FLOAT(11,2) NOT NULL DEFAULT 0.00;

ALTER TABLE `account_trans` 
	ADD COLUMN `tax_code` VARCHAR(10) NULL,
	ADD COLUMN `tax_rate` FLOAT(11,2) NULL,
	ADD COLUMN `tax_amount` FLOAT(11,2) NOT NULL DEFAULT 0.00;

ALTER TABLE `account` 
	ADD COLUMN `tax_code` VARCHAR(10) NULL;

/*	END 2.0.22	*/

# 2.0.23
# HS
# Account code and invoice item GST implementation

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.23', dt_upd = NOW();

alter table tour_dep_item add column tax_cd varchar(10) default null after type_cd, add column tax_rate float(11,2) default null after tax_cd;
alter table inv_eo_item add column tax_cd varchar(10) default null after amount, add column tax_rate float(11,2) default null after tax_cd;

/*	END 2.0.23	*/


# 2.0.24
# Kent
# Financial Period status change the column type to integer
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.24', dt_upd = NOW();

ALTER TABLE `financial_period` MODIFY COLUMN `status` INT(1) NOT NULL COMMENT '-1 = 2nd Level Close, 0 = close, 1= open';

/*	END 2.0.24	*/

# 2.0.25
# KS
# Company Table add default account for GST and rounding

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.25', dt_upd = NOW();

ALTER TABLE company 
ADD COLUMN id_acct_gst BIGINT DEFAULT NULL AFTER id_acct_purchase_sundry, 
ADD COLUMN id_acct_rounding BIGINT DEFAULT NULL AFTER id_acct_gst;

/*	END 2.0.25	*/

# 2.0.26
# Kent
# Change table invoice_item tax amount to Double
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.26', dt_upd = NOW();

ALTER TABLE `invoice_item` MODIFY COLUMN `tax_amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00';
ALTER TABLE `account_trans` MODIFY COLUMN `tax_amount` DOUBLE(10,2) NOT NULL DEFAULT '0.00';

/*	END DEBUG 2.0.26	*/

# 2.0.27
# HS
# Add column id_acct_current_pl and id_acct_accumulated_pl into company table

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.27', dt_upd = NOW();

alter table tour_dep modify column dt_dep date;
alter table tour_dep_history modify column dt_dep date;
alter table ticketing modify column dt_book_start date, modify column dt_book_end date, modify column dt_travel_start date, modify column dt_travel_end date;
alter table person_identity_detail modify column dt_issued date, modify column dt_expired date;
alter table journal modify column dt_journal date;
alter table invoice_pmnt_history modify column dt_pmnt date;
alter table invoice_pmnt modify column dt_pmnt date;
alter table invoice_history modify column dt_inv date;
alter table invoice modify column dt_inv date;
alter table ex_order_hotel modify column dt_chk_in date, modify column dt_chk_out date;
alter table ex_order_bill_pmnt modify column dt_pmnt date;
alter table ex_order_bill modify column dt_bill date, modify column dt_due date, modify column dt_inv date;
alter table ex_order_airline modify column dt_flight date;
alter table ex_order modify column dt_eo date;
alter table cash_book modify column dt_trans date;
alter table bank_recon modify column dt_start date, modify column dt_end date;
alter table account_trans modify column dt_trans date;
alter table account_bal modify column dt_begin_bal date, modify column dt_current_bal date, modify column dt_close_bal date;

alter table company add column id_acct_accumulated_pl bigint default null after id_acct_rounding, add column id_acct_current_pl bigint default null after id_acct_accumulated_pl;

update company set id_acct_accumulated_pl = 330, id_acct_current_pl = 331 where id = 1;
update company set id_acct_accumulated_pl = 500, id_acct_current_pl = 501 where id = 19;
update company set id_acct_accumulated_pl = 509, id_acct_current_pl = 510 where id = 21;

/*	END 2.0.27	*/


# 2.0.28
# KS
# Add default gst account in account table

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.28', dt_upd = NOW();
DELETE FROM account WHERE `code` = '3020';

/*	Company 1 */
INSERT INTO account(id_company, id_acct_cat, id_acct_sub_cat, `code`, sub_code, 
					description, sub_description, tax_code, status_cd, dt_created, created_by, dt_upd, upd_by)
VALUES 
	(1, 3, 36, 	'3020', '', 'Rounding Adjustment', '', NULL, 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

/*	Company 19 */
INSERT INTO account(id_company, id_acct_cat, id_acct_sub_cat, `code`, sub_code, 
					description, sub_description, tax_code, status_cd, dt_created, created_by, dt_upd, upd_by)
SELECT 19, id_acct_cat, 60, `code`, sub_code, description, sub_description, tax_code, status_cd, 
		dt_created, created_by, dt_upd, upd_by FROM account WHERE id_company=1 AND `code` = '3020';
            
/*	Company 22 */
INSERT INTO account(id_company, id_acct_cat, id_acct_sub_cat, `code`, sub_code, 
					description, sub_description, tax_code, status_cd, dt_created, created_by, dt_upd, upd_by)
SELECT 21, id_acct_cat, 63, `code`, sub_code, description, sub_description, tax_code, status_cd, 
		dt_created, created_by, dt_upd, upd_by FROM account WHERE id_company=1 AND `code` = '3020';
            
/*	Company 24 */
INSERT INTO account(id_company, id_acct_cat, id_acct_sub_cat, `code`, sub_code, 
					description, sub_description, tax_code, status_cd, dt_created, created_by, dt_upd, upd_by)
SELECT 24, id_acct_cat, 52, `code`, sub_code, description, sub_description, tax_code, status_cd, 
		dt_created, created_by, dt_upd, upd_by FROM account WHERE id_company=1 AND `code` = '3020';

/* Account Beginning Balance */
INSERT INTO `account_bal` (`id_acct`, `dt_begin_bal`, `debit_begin_bal`, `credit_begin_bal`, `dt_current_bal`, `debit_current_bal`, `credit_current_bal`, `dt_close_bal`, `debit_close_bal`, `credit_close_bal`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
SELECT id,'2014-08-01',0.00,0.00,'2014-08-01',0.00,0.00,'2014-08-01',0.00,0.00,SYSDATE(),'SYSTEM',SYSDATE(),'SYSTEM'
FROM `account` WHERE id_company=1 AND `code` = '3020';


INSERT INTO `account_bal` (`id_acct`, `dt_begin_bal`, `debit_begin_bal`, `credit_begin_bal`, `dt_current_bal`, `debit_current_bal`, `credit_current_bal`, `dt_close_bal`, `debit_close_bal`, `credit_close_bal`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
SELECT id,'2014-09-01',0.00,0.00,'2014-09-01',0.00,0.00,'2014-09-01',0.00,0.00,SYSDATE(),'SYSTEM',SYSDATE(),'SYSTEM'
FROM `account` WHERE id_company=19 AND `code` = '3020';

/*	END 2.0.28	*/

# 2.0.29
# HS
# Add default gst account in account table

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.29', dt_upd = NOW();

/* Add type code for account balance for calculation purpose */
alter table account_bal add column type_cd enum('I', 'C') not null default 'I' comment 'I = insert while create by using web / C = close period generated' after credit_close_bal;
update financial_period set status = -1 where year = 2012;

/* Data update for financial calculation purpose
 * cause of account code created for previous financial period used
 */
update account_bal set dt_begin_bal = '2013-08-01', dt_current_bal = '2013-08-01', dt_close_bal = '2013-08-01' where id_acct >= 659 and id_acct <= 671 and dt_begin_bal = '2014-08-01';

/* Add type code for acount code */
alter table account add column type_cd enum('N', 'G') not null default 'N' comment 'N = normal account code / G = GST account code' after remarks;
update account set type_cd = 'G' where code = '3020';


# 2.0.30
# KS
# Add default ROUNDING account for company

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.30', dt_upd = NOW();

UPDATE company
SET id_acct_rounding = (SELECT id FROM account WHERE id_company=1 AND code = '3020' AND status_cd = 'A')
WHERE id = 1;

UPDATE company
SET id_acct_rounding = (SELECT id FROM account WHERE id_company=19 AND code = '3020' AND status_cd = 'A')
WHERE id = 19;

UPDATE company
SET id_acct_rounding = (SELECT id FROM account WHERE id_company=21 AND code = '3020' AND status_cd = 'A')
WHERE id = 21;

UPDATE company
SET id_acct_rounding = (SELECT id FROM account WHERE id_company=24 AND code = '3020' AND status_cd = 'A')
WHERE id = 24;

/*	END 2.0.30	*/

# 2.0.31
# HS
# update ref data description

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.31', dt_upd = NOW();
update com_ref_data set ref_value = 'TRAVEL INSURANCE', ref_desc = 'TRAVEL INSURANCE' where uuid = 'U_AIRLINE_ITM_CD_TRVL_INS';

/*	END 2.0.31	*/

# 2.0.32
# HS
# add index to account_trans table

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.32', dt_upd = NOW();
ALTER TABLE `account_trans` ADD INDEX `idx_sys_cd` (`sys_cd`), ADD INDEX `idx_sys_no` (`sys_no`);

/*	END 2.0.32	*/

# 2.0.33
# HS
# add void invoice while booking voided in scheduler

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.33', dt_upd = NOW();

DROP procedure IF EXISTS `prc_task_scheduler`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_task_scheduler`(IN flag INTEGER)
BEGIN
	IF flag = 1 THEN
		UPDATE tour_dep d, tour_pkg p SET d.status_cd = 'IA', d.dt_upd = NOW(), d.upd_by = 'SYSTEM' WHERE d.id_tour_pkg = p.id AND p.type_cd IN ('TOUR', 'MICE', 'FNE') AND DATE(d.dt_dep) <= DATE(NOW()) AND d.status_cd <> 'IA';
	ELSEIF flag = 2 THEN
		UPDATE tour_pkg SET status_cd = 'IA', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE type_cd = 'FNE' AND DATE(dt_travel_end) <= DATE(NOW()) AND status_cd <> 'IA';
	ELSEIF flag = 3 THEN
		UPDATE tour_pkg p, tour_dep d SET p.status_cd = 'IA', p.dt_upd = NOW(), p.upd_by = 'SYSTEM' WHERE p.id = d.id_tour_pkg AND p.type_cd = 'MICE' AND DATE(d.dt_dep) <= DATE(NOW()) AND p.status_cd <> 'IA';
	ELSEIF flag = 4 THEN
		UPDATE tour_booking SET pmnt_status_cd = 'KIVEXP', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE pmnt_status_cd = 'KIV' AND status_cd <> 'VD' AND DATE(dt_exp) < DATE(NOW());
	ELSEIF flag = 5 THEN
		UPDATE invoice SET status_cd = 'VD', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE id_tour_booking IN (SELECT id FROM tour_booking WHERE pmnt_status_cd = 'KIVEXP' AND DATE(dt_upd) = DATE(NOW()));
	ELSEIF flag = 6 THEN
		UPDATE account_trans a, invoice i SET a.status_cd = 'T', a.dt_upd = NOW(), a.upd_by = 'SYSTEM' WHERE a.status_cd = 'A' AND a.sys_cd = 'invc' AND a.sys_no = i.code AND i.status_cd = 'VD' AND i.upd_by = 'SYSTEM';
	ELSEIF flag = 7 THEN
		UPDATE tour_dep SET tour_status_cd = 'A', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE id in (SELECT id FROM (SELECT * FROM (SELECT d.id, d.tour_status_cd, d.status_cd, d.seat_allotment, (d.reserved_seat + d.tour_mgr_pax + (SELECT CASE WHEN SUM(c.quantity) IS NULL THEN 0 ELSE SUM(c.quantity) END FROM tour_booking_charge_item c LEFT JOIN (SELECT id, id_tour_dep, pmnt_status_cd FROM tour_booking WHERE status_cd NOT IN ('CC', 'VD')) b ON b.pmnt_status_cd <> 'KIVEXP' WHERE b.id_tour_dep = d.id AND c.id_tour_booking = b.id AND c.code LIKE 'FT%' AND c.code <> 'FT_INFT')) AS 'quantity' FROM tour_dep d WHERE d.status_cd = 'AC' AND d.tour_status_cd = 'F') s WHERE seat_allotment > 0 AND seat_allotment > quantity) a);
	ELSEIF flag = 8 THEN
		UPDATE account_trans a LEFT JOIN (SELECT i.id_company, i.code FROM invoice i, tour_booking b WHERE i.id_tour_booking = b.id AND b.status_cd = 'VD' AND i.status_cd = 'IN') ib ON ib.code = a.sys_no AND ib.id_company = a.id_company SET a.status_cd = 'T', a.dt_upd = now(), a.upd_by = 'SYSTEM' WHERE a.sys_cd = 'invc' AND a.status_cd = 'A' AND ib.id_company IS NOT NULL;
	ELSEIF flag = 9 THEN
		UPDATE invoice i, tour_booking b SET i.status_cd = 'VD', i.dt_upd = NOW(), i.upd_by = 'SYSTEM' WHERE i.id_tour_booking = b.id AND b.status_cd = 'VD' AND i.status_cd = 'IN';
	END IF;
END$$
DELIMITER ;

# 2.0.34
# Kent
# Added New role GST Free Text

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.34', dt_upd = NOW();

INSERT INTO `SEC_ROLE` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `REMARKS`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('ROLE_GST_FT','*','GST_FT','GST Free Text',1,'A','',CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP,'SYSTEM',1);


# 2.0.35
# HS
# Added New role GST Free Text

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.35', dt_upd = NOW();

insert into sec_user_role (u_user, u_role) select uuid, 'ROLE_GST_FT' as 'role' from sec_user where login_id in ('yee', 'bernietan', 'chiasyn', 'liching') and status_cd = 'A';

# 2.0.36
# KS
# Added default access for IT and account manager

UPDATE `db_tracking` SET version = '2.0.36', dt_upd = NOW();

INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, 
	IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
VALUES
	('U_GST_03', 'GST_03', 'GST-03 PAGE', 'T', '/app/gst/gst-03.xhtml', '', 
		'1', 3, 0, NULL, 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO sec_role_func(U_ROLE, U_FUNC, IS_DEFAULT)
SELECT UUID, 'U_GST_03', '0' FROM sec_role WHERE ROLE_CD IN ('it', 'acct', 'acct_exec');

# 2.0.37
# Kent
# Added New Link for GST Report

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.37', dt_upd = NOW();

INSERT INTO sec_func(UUID, FUNC_CD, FUNC_NAME, TYPE_CD, URI_ENTRY, URI_PROCESS, 
	IS_SECURE, LEVEL_NO, SEQ_NO, U_PARENT_FUNC, STATUS_CD, REMARKS, CREATED_BY, UPD_BY, DT_UPD)
VALUES
	('U_GST_REPORT', 'GST_REPORT', 'GST Report', 'L', '/app/gst/gstreport', '', 
		'1', 2, 3, 'U_GST', 'A', '', 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP);

# 2.0.38
# KS
# Change the gst Summary table from FLOAT to DOUBLE

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.38', dt_upd = NOW();

ALTER TABLE  gst_summary_tax
CHANGE COLUMN `amount` `amount` DOUBLE(13,2) NOT NULL DEFAULT '0.00' ,
CHANGE COLUMN `tax_amount` `tax_amount` DOUBLE(13,2) NOT NULL DEFAULT '0.00' ;

ALTER TABLE  gst_summary_tax_log
CHANGE COLUMN `amount` `amount` DOUBLE(13,2) NOT NULL DEFAULT '0.00' ,
CHANGE COLUMN `tax_amount` `tax_amount` DOUBLE(13,2) NOT NULL DEFAULT '0.00' ;


# 2.0.39
# Kent
# Added New role for Invoice Items GST Free Text

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.39', dt_upd = NOW();

INSERT INTO `SEC_ROLE` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `REMARKS`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('ROLE_INV_ITEMS_GST_FT','*','INV_ITEMS_GST_FT','Invoice Items GST Free Text',1,'A','',CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP,'SYSTEM',1);


# 2.0.40
# HS
# prcess task scheduler debug
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.40', dt_upd = NOW();

DROP procedure IF EXISTS `prc_task_scheduler`;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_task_scheduler`(IN flag INTEGER)
BEGIN
	IF flag = 1 THEN
		UPDATE tour_dep d, tour_pkg p SET d.status_cd = 'IA', d.dt_upd = NOW(), d.upd_by = 'SYSTEM' WHERE d.id_tour_pkg = p.id AND p.type_cd IN ('TOUR', 'MICE', 'FNE') AND DATE(d.dt_dep) <= DATE(NOW()) AND d.status_cd <> 'IA';
	ELSEIF flag = 2 THEN
		UPDATE tour_pkg SET status_cd = 'IA', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE type_cd = 'FNE' AND DATE(dt_travel_end) <= DATE(NOW()) AND status_cd <> 'IA';
	ELSEIF flag = 3 THEN
		UPDATE tour_pkg p, tour_dep d SET p.status_cd = 'IA', p.dt_upd = NOW(), p.upd_by = 'SYSTEM' WHERE p.id = d.id_tour_pkg AND p.type_cd = 'MICE' AND DATE(d.dt_dep) <= DATE(NOW()) AND p.status_cd <> 'IA';
	ELSEIF flag = 4 THEN
		UPDATE tour_booking SET pmnt_status_cd = 'KIVEXP', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE pmnt_status_cd = 'KIV' AND status_cd <> 'VD' AND DATE(dt_exp) < DATE(NOW());
	ELSEIF flag = 5 THEN
		UPDATE invoice SET status_cd = 'VD', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE id_tour_booking IN (SELECT id FROM tour_booking WHERE pmnt_status_cd = 'KIVEXP' AND DATE(dt_upd) = DATE(NOW()));
	ELSEIF flag = 6 THEN
		UPDATE account_trans a, invoice i SET a.status_cd = 'T', a.dt_upd = NOW(), a.upd_by = 'SYSTEM' WHERE i.doc_type_cd = 'I' AND a.id_company = i.id_company AND a.status_cd = 'A' AND a.sys_cd = 'invc' AND a.sys_no = i.code AND i.status_cd = 'VD' AND i.upd_by = 'SYSTEM';
	ELSEIF flag = 7 THEN
		UPDATE tour_dep SET tour_status_cd = 'A', dt_upd = NOW(), upd_by = 'SYSTEM' WHERE id in (SELECT id FROM (SELECT * FROM (SELECT d.id, d.tour_status_cd, d.status_cd, d.seat_allotment, (d.reserved_seat + d.tour_mgr_pax + (SELECT CASE WHEN SUM(c.quantity) IS NULL THEN 0 ELSE SUM(c.quantity) END FROM tour_booking_charge_item c LEFT JOIN (SELECT id, id_tour_dep, pmnt_status_cd FROM tour_booking WHERE status_cd NOT IN ('CC', 'VD')) b ON b.pmnt_status_cd <> 'KIVEXP' WHERE b.id_tour_dep = d.id AND c.id_tour_booking = b.id AND c.code LIKE 'FT%' AND c.code <> 'FT_INFT')) AS 'quantity' FROM tour_dep d WHERE d.status_cd = 'AC' AND d.tour_status_cd = 'F') s WHERE seat_allotment > 0 AND seat_allotment > quantity) a);
	ELSEIF flag = 8 THEN
		UPDATE account_trans a LEFT JOIN (SELECT i.id_company, i.code FROM invoice i, tour_booking b WHERE i.doc_type_cd = 'I' AND i.id_tour_booking = b.id AND b.status_cd = 'VD' AND i.status_cd = 'IN') ib ON ib.code = a.sys_no AND ib.id_company = a.id_company SET a.status_cd = 'T', a.dt_upd = now(), a.upd_by = 'SYSTEM' WHERE a.sys_cd = 'invc' AND a.status_cd = 'A' AND ib.id_company IS NOT NULL;
	ELSEIF flag = 9 THEN
		UPDATE invoice i, tour_booking b SET i.status_cd = 'VD', i.dt_upd = NOW(), i.upd_by = 'SYSTEM' WHERE i.doc_type_cd = 'I' AND i.id_tour_booking = b.id AND b.status_cd = 'VD' AND i.status_cd = 'IN';
	END IF;
END$$
DELIMITER ;

# 2.0.41
# Kent
# Add new column(acct_mgr_use) in table inv_eo_item
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.41', dt_upd = NOW();

ALTER TABLE `inv_eo_item` ADD COLUMN `acct_mgr_use` TINYINT(1) DEFAULT 0 COMMENT '0 = Normal, 1 = Acct Mgr Viewable' AFTER `amount`;


# 2.0.42
# HS
# Add account transaction purc bill log
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.42', dt_upd = NOW();

CREATE TABLE `account_trans_purc_bill_log` (
  `id` bigint(20) NOT NULL DEFAULT 0,
  `id_company` bigint(20) NOT NULL,
  `id_acct` bigint(20) NOT NULL,
  `id_ref` bigint(20) DEFAULT NULL,
  `dt_trans` date DEFAULT NULL,
  `sys_cd` varchar(50) NOT NULL,
  `sys_prefix` varchar(10) DEFAULT NULL,
  `sys_no` varchar(20) NOT NULL COMMENT 'Invoice no / bill payment no / etc.',
  `ref_no` varchar(30) NOT NULL COMMENT 'cheque no / etc.',
  `source` text NOT NULL,
  `destination` text NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `debit` double(10,2) NOT NULL DEFAULT '0.00',
  `credit` double(10,2) NOT NULL,
  `type_cd` varchar(10) DEFAULT NULL,
  `status_cd` varchar(50) NOT NULL DEFAULT 'A' COMMENT 'A = Active / I = Inactive',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  `tax_code` varchar(10) DEFAULT NULL,
  `tax_rate` float(11,2) DEFAULT NULL,
  `tax_amount` double(10,2) NOT NULL DEFAULT '0.00',
  `action` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Account transactions purchase bill log';

DROP TRIGGER IF EXISTS account_trans_purc_bill_AFTER_INSERT $$
CREATE TRIGGER `account_trans_purc_bill_AFTER_INSERT` 
AFTER INSERT ON `account_trans` 
FOR EACH ROW
BEGIN
	IF NEW.sys_cd = 'purc_bill' THEN
		INSERT INTO account_trans_purc_bill_log (id, id_company, id_acct, id_ref, dt_trans, sys_cd, sys_prefix, sys_no, ref_no, 
		  	source, destination, code, description, debit, credit, type_cd, status_cd, dt_created, created_by, dt_upd, upd_by, 
		  	tax_code, tax_rate, tax_amount, action) 
		VALUES (NEW.id, NEW.id_company, NEW.id_acct, NEW.id_ref, NEW.dt_trans, NEW.sys_cd, NEW.sys_prefix, NEW.sys_no, NEW.ref_no, 
		  	NEW.source, NEW.destination, NEW.code, NEW.description, NEW.debit, NEW.credit, NEW.type_cd, NEW.status_cd, NEW.dt_created, 
		  	NEW.created_by, NEW.dt_upd, NEW.upd_by, NEW.tax_code, NEW.tax_rate, NEW.tax_amount, 'A');
	END IF;
END;

DROP TRIGGER IF EXISTS account_trans_purc_bill_AFTER_UPDATE $$
CREATE TRIGGER `account_trans_purc_bill_AFTER_UPDATE` 
AFTER UPDATE ON `account_trans` 
FOR EACH ROW
BEGIN
	IF NEW.sys_cd = 'purc_bill' THEN
		INSERT INTO account_trans_purc_bill_log (id, id_company, id_acct, id_ref, dt_trans, sys_cd, sys_prefix, sys_no, ref_no, 
		  	source, destination, code, description, debit, credit, type_cd, status_cd, dt_created, created_by, dt_upd, upd_by, 
		  	tax_code, tax_rate, tax_amount, action) 
		VALUES (NEW.id, NEW.id_company, NEW.id_acct, NEW.id_ref, NEW.dt_trans, NEW.sys_cd, NEW.sys_prefix, NEW.sys_no, NEW.ref_no, 
		  	NEW.source, NEW.destination, NEW.code, NEW.description, NEW.debit, NEW.credit, NEW.type_cd, NEW.status_cd, NEW.dt_created, 
		  	NEW.created_by, NEW.dt_upd, NEW.upd_by, NEW.tax_code, NEW.tax_rate, NEW.tax_amount, 'U');
	END IF;
END;

CREATE TABLE `cash_book_eo_bill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_cash_book` bigint(20) NOT NULL,
  `id_eo_bill` bigint(20) NOT NULL,
  `amount` double(10,2) NOT NULL,
  `amount_paid` double(10,2) NOT NULL,
  `status_cd` varchar(10) NOT NULL DEFAULT 'A',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


# 2.0.43
# Kent
# Add new column(attention) in table person_contact and corporate_contact
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.43', dt_upd = NOW();

ALTER TABLE `person_contact` ADD COLUMN `attention` VARCHAR(100) DEFAULT NULL COMMENT 'Attention To' AFTER `number`;
ALTER TABLE `corporate_contact` ADD COLUMN `attention` VARCHAR(100) DEFAULT NULL COMMENT 'Attention To' AFTER `number`;


# 2.0.44
# Kent
# Add new column(seq_no, claimable) in table tax_code
# Update table tax_code with column(seq_no, claimable)
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.44', dt_upd = NOW();

ALTER TABLE `tax_code` ADD COLUMN `claimable` tinyint(1) DEFAULT '1' COMMENT '0: Non-Claimable, 1: Claimable: 3: None' AFTER `status_cd`;
ALTER TABLE `tax_code` ADD COLUMN `seq_no` SMALLINT(2) NOT NULL DEFAULT 0 AFTER `status_cd`;

UPDATE tax_code SET seq_no = 1, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'TX';
UPDATE tax_code SET seq_no = 2, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'EP';
UPDATE tax_code SET seq_no = 3, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'IS';
UPDATE tax_code SET seq_no = 4, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'GP';
UPDATE tax_code SET seq_no = 5, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'IM';
UPDATE tax_code SET seq_no = 6, claimable = 0 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'BL';
UPDATE tax_code SET seq_no = 7, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'NR';
UPDATE tax_code SET seq_no = 8, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'ZP';
UPDATE tax_code SET seq_no = 9, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'OP';
UPDATE tax_code SET seq_no = 10, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'TX-E43';
UPDATE tax_code SET seq_no = 11, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'TX-N43';
UPDATE tax_code SET seq_no = 12, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'TX-RE';
UPDATE tax_code SET seq_no = 13, claimable = 1 WHERE tax_type = 'GST' AND sub_type = 'IN' AND status_cd = 'A' AND code = 'AJP';

# 2.0.45
# Kent
# Insert new Debtors Outstanding link
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.45', dt_upd = NOW();

INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('U_SALES_REPORT_DEBTOR_OUTSTANDING', '*', 'SALES_REPORT_DEBTOR_OUTSTANDING', 'Debtors Outstanding', 'L', 
'/app/sales/report/DebtorsOutstanding', '1', '3', '3', 'U_SALES_REPORT', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;

update sec_func set seq_no = 4 where uuid = 'U_SALES_REPORT_DEBTOR_STMT';

# 2.0.46
# Kent
# Added New role GST Free Text

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.46', dt_upd = NOW();

INSERT INTO `SEC_ROLE` (`UUID`, `APP_ID`, `ROLE_CD`, `ROLE_NAME`, `ORDER_SEQ_NO`, `STATUS_CD`, `REMARKS`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('ROLE_INV_DT_CHG_FT','*','INV_DT_CHG_FT','Invoice Date Changeable First Time',1,'A','',CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP,'SYSTEM',1);

# 2.0.47
# HS
# RT#36 Extra charge function enhance to tick option
# add is_misc column table to airline_schedule_charge

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.47', dt_upd = NOW();

alter table airline_schedule_charge add column is_misc tinyint(1) default 0 after amount;

# 2.0.48
# HS
# Fine tune slowness query in AccountTransDAOImpl.updateAcctTransDestinationCust()
# Add indexes to table

UPDATE `db_tracking` SET version = '2.0.48', dt_upd = NOW();

ALTER TABLE `cash_book` MODIFY remarks varchar(1500) DEFAULT NULL;
ALTER TABLE `cash_book` ADD INDEX `idx_sys_cd` (`sys_cd`), ADD INDEX `idx_sys_no` (`sys_no`);
ALTER TABLE `account_trans` ADD INDEX `idx_dt_trans` (`dt_trans`);

# 2.0.49
# Kent
# Add new store procedure to handle update customer name to related tables
# SP with time tracking table log

UPDATE `db_tracking` SET version = '2.0.49', dt_upd = NOW();

DROP TABLE IF EXISTS `upd_acct_trans_dest_cust_tracking`;
CREATE TABLE `upd_acct_trans_dest_cust_tracking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_company` bigint(20) NOT NULL,
  `timespan` bigint(20) NOT NULL,
  `dt_fin_period` date NOT NULL,
  `inv_count` bigint(20) NOT NULL,
  `cn_count` bigint(20) NOT NULL,
  `rfnd_cb_count` bigint(20) NOT NULL,
  `rfnd_trans_count` bigint(20) NOT NULL,
  `bd_cb_count` bigint(20) NOT NULL,
  `bd_trans_count` bigint(20) NOT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT='Update Customer Destination Procedures Tracking';

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_upd_acct_trans_dest_cust`(IN p_id_company bigint(20))
BEGIN
	DECLARE v_fin_date date;
	DECLARE v_fin_period_count bigint(20);
	DECLARE v_inv_count bigint(20);
	DECLARE v_cn_count bigint(20);
	DECLARE v_rfnd_cb_count bigint(20);
	DECLARE v_rfnd_trans_count bigint(20);
	DECLARE v_bd_cb_count bigint(20);
	DECLARE v_bd_trans_count bigint(20);

	SELECT variable_value INTO @t1
	FROM INFORMATION_SCHEMA.GLOBAL_STATUS
	WHERE variable_name='uptime';
	
	select dt_end into v_fin_date from financial_period where id_company = p_id_company and status = -1 order by dt_start desc limit 1;
	
	/*** Invoice ***/
	update account_trans a,
		(select iv.code,
			concat('Cust-',
			case
				when cm.pc_type_cd = 'C' then cm.corporate_name
				else concat(l.description, ' ', ps.last_name, ' ', ps.first_name)
			end, ' ', cm.code) as 'destination'
		from invoice iv, customer cm, person ps left join (select code, description from lookup_item where lookup_cat_cd = 'salutatn') l on ps.salutation_cd = l.code
		where
			iv.id_company = p_id_company and cm.id_company = p_id_company and
			iv.doc_type_cd = 'I' and iv.status_cd != 'VD' and
			iv.id_customer = cm.id and cm.id_pc = ps.id) b
	set a.destination = b.destination
	where
		a.id_company = p_id_company and a.status_cd = 'A' and a.sys_cd = 'invc' and a.sys_no = b.code
		and date(a.dt_trans) > date(v_fin_date) and a.destination != b.destination;
	SET v_inv_count = ROW_COUNT();
	
	/*** Credit Note ***/
	update account_trans a,
		(select iv.code,
			concat('Cust-',
			case
				when cm.pc_type_cd = 'C' then cm.corporate_name
				else concat(l.description, ' ', ps.last_name, ' ', ps.first_name)
			end, ' ', cm.code) as 'destination'
		from invoice iv, customer cm, person ps left join (select code, description from lookup_item where lookup_cat_cd = 'salutatn') l on ps.salutation_cd = l.code
		where
			iv.id_company = p_id_company and cm.id_company = p_id_company and
			iv.doc_type_cd = 'C' and iv.status_cd != 'VD' and
			iv.id_customer=cm.id and cm.id_pc=ps.id) b
	set a.destination = b.destination
	where
		a.id_company = p_id_company and a.status_cd = 'A' and a.sys_cd = 'crdt_note' and a.sys_no = b.code
		and date(a.dt_trans) > date(v_fin_date) and a.destination != b.destination;
	SET v_cn_count = ROW_COUNT();
	
	/*** Refund ***/
	update cash_book cb, bank b, customer cm, person ps left join (select code, description from lookup_item where lookup_cat_cd = 'salutatn') l on ps.salutation_cd = l.code
	set cb.payee = (case when cm.pc_type_cd = 'C' then cm.corporate_name else concat(l.description, ' ', ps.last_name, ' ', ps.first_name) end)
	where
		b.id_company = p_id_company and cm.id_company = p_id_company and b.status_cd = 'A' and cb.status_cd = 'A' and cb.sys_cd = 'rfnd' and
		cb.id_bank = b.id and
		cb.id_customer = cm.id and cm.id_pc = ps.id
		and cb.payee != (case when cm.pc_type_cd = 'C' then cm.corporate_name else concat(l.description, ' ', ps.last_name, ' ', ps.first_name) end)
		and date(cb.dt_trans) > date(v_fin_date);
	SET v_rfnd_cb_count = ROW_COUNT();
	
	update account_trans a join
	(select cb.sys_no,
		concat(cb.remarks,' To-', case when cm.pc_type_cd = 'C' then cm.corporate_name else concat(l.description, ' ', ps.last_name, ' ', ps.first_name) end) as 'destination'
	from cash_book cb, bank b, customer cm, person ps left join (select code, description from lookup_item where lookup_cat_cd = 'salutatn') l on ps.salutation_cd = l.code
	where b.id_company = p_id_company and cm.id_company = p_id_company and
		b.status_cd = 'A' and cb.status_cd = 'A' and
		cb.sys_cd = 'rfnd' and cb.id_bank = b.id and
		cb.id_customer=cm.id and cm.id_pc=ps.id) c on a.sys_no = c.sys_no
	set a.destination = c.destination
	where
		a.id_company = p_id_company and a.status_cd = 'A' and a.sys_cd = 'rfnd'
		and date(a.dt_trans) > date(v_fin_date) and a.destination != c.destination;
	SET v_rfnd_trans_count = ROW_COUNT();
	
	/*** Bank Deposit ***/
	update cash_book cb, bank b, customer cm, person ps left join (select code, description from lookup_item where lookup_cat_cd = 'salutatn') l on ps.salutation_cd = l.code
	set cb.payee = (case when cm.pc_type_cd = 'C' then cm.corporate_name else concat(l.description, ' ', ps.last_name, ' ', ps.first_name) end)
	where
		b.id_company = p_id_company and cm.id_company = p_id_company and b.status_cd = 'A' and cb.status_cd = 'A' and cb.sys_cd = 'bank_deps' and
		cb.id_bank = b.id and
		cb.id_customer = cm.id and cm.id_pc = ps.id
		and cb.payee != (case
			when cm.pc_type_cd = 'C' then cm.corporate_name
			else concat(l.description, ' ', ps.last_name, ' ', ps.first_name)
		end) and date(cb.dt_trans) > date(v_fin_date);
	SET v_bd_cb_count = ROW_COUNT();
	
	update account_trans a join
	(select cb.sys_no,
		concat(cb.remarks,' Cust-',
		case
			when cm.pc_type_cd = 'C' then cm.corporate_name
			else concat(l.description, ' ', ps.last_name, ' ', ps.first_name)
		end, ' ', cm.code) as 'destination'
	from cash_book cb, bank b, customer cm, person ps left join (select code, description from lookup_item where lookup_cat_cd = 'salutatn') l on ps.salutation_cd = l.code
	where b.id_company = p_id_company and cm.id_company = p_id_company and
		b.status_cd = 'A' and cb.status_cd = 'A' and
		cb.sys_cd = 'bank_deps' and cb.id_bank = b.id and
		cb.id_customer = cm.id and cm.id_pc = ps.id) c on a.sys_no = c.sys_no
	set a.destination = c.destination
	where
		a.id_company = p_id_company and a.status_cd = 'A' and a.sys_cd = 'bank_deps'
		and date(a.dt_trans) > date(v_fin_date) and a.destination != c.destination;
	SET v_bd_trans_count = ROW_COUNT();
	
	SELECT variable_value INTO @t2
	FROM INFORMATION_SCHEMA.GLOBAL_STATUS
	WHERE variable_name='uptime';
	SET @RunningTimeSec = @t2 - @t1;
	
	INSERT INTO upd_acct_trans_dest_cust_tracking(id_company, timespan, dt_fin_period, inv_count, cn_count, rfnd_cb_count, rfnd_trans_count, bd_cb_count, bd_trans_count, dt_created, created_by)
	VALUES (p_id_company, @RunningTimeSec, v_fin_date, v_inv_count, v_cn_count, v_rfnd_cb_count, v_rfnd_trans_count, v_bd_cb_count, v_bd_trans_count, now(), 'SYSTEM');
END;;
DELIMITER ;

# 2.0.50
# HS
# Performance tune for Bills Payment, Bank Payment, Bank Deposit, Bank Adjustment, Bank Refund
# Add column tax_code_group to keep tax code missing status

UPDATE `db_tracking` SET version = '2.0.50', dt_upd = NOW();

DROP INDEX idx_sys_cd ON cash_book;
DROP INDEX idx_sys_no ON cash_book;
DROP INDEX dt_trans ON account_trans;

/*** add column tax_code_group to ex_order_bill ***/
alter table ex_order_bill add column tax_code_group enum('YES', 'NO') default 'NO' after ex_reason;
/*** update bill tax_code_group ***/
update ex_order_bill b
left join (select id_company, sys_no, group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) as tax_code_group from account_trans 
	where sys_cd = 'purc_bill' and status_cd = 'A' and id_ref is not null 
	group by id_company, sys_cd, sys_no) a on a.id_company = b.id_company and a.sys_no = b.code 
set b.tax_code_group = IF((a.tax_code_group LIKE '%NONE%') = 1,'NO','YES')
where b.id_company = 1;

update ex_order_bill b
left join (select id_company, sys_no, group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) as tax_code_group from account_trans 
	where sys_cd = 'purc_bill' and status_cd = 'A' and id_ref is not null 
	group by id_company, sys_cd, sys_no) a on a.id_company = b.id_company and a.sys_no = b.code 
set b.tax_code_group = IF((a.tax_code_group LIKE '%NONE%') = 1,'NO','YES')
where b.id_company = 19;

update ex_order_bill b
left join (select id_company, sys_no, group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) as tax_code_group from account_trans 
	where sys_cd = 'purc_bill' and status_cd = 'A' and id_ref is not null 
	group by id_company, sys_cd, sys_no) a on a.id_company = b.id_company and a.sys_no = b.code 
set b.tax_code_group = IF((a.tax_code_group LIKE '%NONE%') = 1,'NO','YES')
where b.id_company = 21;

/*** add column tax_code_group to cash_book ***/
alter table cash_book add column tax_code_group enum('YES', 'NO') default 'YES' after group_no;
/*** update cash_book tax_code_group - bank_deps ***/
update cash_book c
left join (select name,id,id_company from bank) b on b.id=c.id_bank
left join (
select sys_no, if((group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) like '%NONE%') = 1,'NO','YES') as tax_code_group from account_trans 
where id_company = 1 and sys_cd = 'bank_deps' and status_cd = 'A' and case when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' end
group by id_company, sys_cd, sys_no
) ac on ac.sys_no = c.sys_no
set c.tax_code_group = ac.tax_code_group
WHERE c.sys_cd = 'bank_deps'  and c.status_cd = 'A' and b.id_company= 1;

update cash_book c
left join (select name,id,id_company from bank) b on b.id=c.id_bank
left join (
select sys_no, if((group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) like '%NONE%') = 1,'NO','YES') as tax_code_group from account_trans 
where id_company = 19 and sys_cd = 'bank_deps' and status_cd = 'A' and case when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' end
group by id_company, sys_cd, sys_no
) ac on ac.sys_no = c.sys_no
set c.tax_code_group = ac.tax_code_group
WHERE c.sys_cd = 'bank_deps'  and c.status_cd = 'A' and b.id_company= 19;

update cash_book c
left join (select name,id,id_company from bank) b on b.id=c.id_bank
left join (
select sys_no, if((group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) like '%NONE%') = 1,'NO','YES') as tax_code_group from account_trans 
where id_company = 21 and sys_cd = 'bank_deps' and status_cd = 'A' and case when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' end
group by id_company, sys_cd, sys_no
) ac on ac.sys_no = c.sys_no
set c.tax_code_group = ac.tax_code_group
WHERE c.sys_cd = 'bank_deps'  and c.status_cd = 'A' and b.id_company= 21;

/*** update cash_book tax_code_group - bank_pmnt ***/
update cash_book c
LEFT JOIN bank b ON b.id = c.id_bank
LEFT JOIN (
select sys_no, group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) as tax_code_group from account_trans
where id_company = 1 and sys_cd = 'bank_pmnt' and status_cd = 'A' and 
case when sys_cd = 'bank_adjm' then type_cd = 'gnrl_deps' or type_cd = 'gnrl_pymt' when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' when sys_cd = 'bank_pmnt' then type_cd = 'gnrl_pymt' end 
group by id_company, sys_cd, sys_no) a on a.sys_no = c.sys_no
set c.tax_code_group = IF((a.tax_code_group LIKE '%NONE%') = 1,'NO','YES')
WHERE c.sys_cd = 'bank_pmnt' AND c.status_cd = 'A' AND b.id_company = 1;

update cash_book c
LEFT JOIN bank b ON b.id = c.id_bank
LEFT JOIN (
select sys_no, group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) as tax_code_group from account_trans
where id_company = 19 and sys_cd = 'bank_pmnt' and status_cd = 'A' and 
case when sys_cd = 'bank_adjm' then type_cd = 'gnrl_deps' or type_cd = 'gnrl_pymt' when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' when sys_cd = 'bank_pmnt' then type_cd = 'gnrl_pymt' end 
group by id_company, sys_cd, sys_no) a on a.sys_no = c.sys_no
set c.tax_code_group = IF((a.tax_code_group LIKE '%NONE%') = 1,'NO','YES')
WHERE c.sys_cd = 'bank_pmnt' AND c.status_cd = 'A' AND b.id_company = 19;

update cash_book c
LEFT JOIN bank b ON b.id = c.id_bank
LEFT JOIN (
select sys_no, group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) as tax_code_group from account_trans
where id_company = 21 and sys_cd = 'bank_pmnt' and status_cd = 'A' and 
case when sys_cd = 'bank_adjm' then type_cd = 'gnrl_deps' or type_cd = 'gnrl_pymt' when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' when sys_cd = 'bank_pmnt' then type_cd = 'gnrl_pymt' end 
group by id_company, sys_cd, sys_no) a on a.sys_no = c.sys_no
set c.tax_code_group = IF((a.tax_code_group LIKE '%NONE%') = 1,'NO','YES')
WHERE c.sys_cd = 'bank_pmnt' AND c.status_cd = 'A' AND b.id_company = 21;

/*** update cash_book tax_code_group - bank_adjm ***/
update cash_book c
LEFT JOIN bank b ON b.id = c.id_bank
LEFT JOIN (
select sys_cd, sys_no, group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) as tax_code_group from account_trans
where id_company = 1 and sys_cd = 'bank_adjm' and status_cd = 'A' and 
case when sys_cd = 'bank_adjm' then type_cd = 'gnrl_deps' or type_cd = 'gnrl_pymt' when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' when sys_cd = 'bank_pmnt' then type_cd = 'gnrl_pymt' end 
group by id_company, sys_cd, sys_no) a on a.sys_cd = c.sys_cd and a.sys_no = c.sys_no
set c.tax_code_group = IF((a.tax_code_group LIKE '%NONE%') = 1,'NO','YES')
WHERE c.sys_cd = 'bank_adjm' AND c.status_cd = 'A' AND b.id_company = 1;

update cash_book c
LEFT JOIN bank b ON b.id = c.id_bank
LEFT JOIN (
select sys_no, group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) as tax_code_group from account_trans
where id_company = 19 and sys_cd = 'bank_adjm' and status_cd = 'A' and 
case when sys_cd = 'bank_adjm' then type_cd = 'gnrl_deps' or type_cd = 'gnrl_pymt' when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' when sys_cd = 'bank_pmnt' then type_cd = 'gnrl_pymt' end 
group by id_company, sys_cd, sys_no) a on a.sys_no = c.sys_no
set c.tax_code_group = IF((a.tax_code_group LIKE '%NONE%') = 1,'NO','YES')
WHERE c.sys_cd = 'bank_adjm' AND c.status_cd = 'A' AND b.id_company = 19;

update cash_book c
LEFT JOIN bank b ON b.id = c.id_bank
LEFT JOIN (
select sys_no, group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) as tax_code_group from account_trans
where id_company = 21 and sys_cd = 'bank_adjm' and status_cd = 'A' and 
case when sys_cd = 'bank_adjm' then type_cd = 'gnrl_deps' or type_cd = 'gnrl_pymt' when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' when sys_cd = 'bank_pmnt' then type_cd = 'gnrl_pymt' end 
group by id_company, sys_cd, sys_no) a on a.sys_no = c.sys_no
set c.tax_code_group = IF((a.tax_code_group LIKE '%NONE%') = 1,'NO','YES')
WHERE c.sys_cd = 'bank_adjm' AND c.status_cd = 'A' AND b.id_company = 21;

/*** update cash_book tax_code_group - rfnd ***/
update cash_book set tax_code_group = 'NO' where sys_cd = 'rfnd';

# 2.0.51
# HS
# add column invoices to cash_book for deposit attach invoices purpose

UPDATE `db_tracking` SET version = '2.0.51', dt_upd = NOW();

/*** add column invoices to cash_book for deposit attach invoices purpose ***/
alter table cash_book add column invoices varchar(300) default NULL after tax_code_group;


# 2.0.52
# Kent
# Insert new staff sales summary link

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.52', dt_upd = NOW();

INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('U_SALES_REPORT_STAFF_SALES_SMMRY_INV', '*', 'SALES_REPORT_STAFF_SALES_SMMRY_INV', 'Staff Sales Summary - Invoice', 'L', 
'/app/sales/report/staffsalessmmryinv', '1', '3', '5', 'U_SALES_REPORT', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;

INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('U_SALES_REPORT_STAFF_SALES_SMMRY_BOOKING', '*', 'SALES_REPORT_STAFF_SALES_SMMRY_BOOKING', 'Staff Sales Summary - Booking', 'L', 
'/app/sales/report/staffsalessmmrybooking', '1', '3', '6', 'U_SALES_REPORT', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;

INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('U_SALES_REPORT_REGION_SALES_SMMRY_INV', '*', 'SALES_REPORT_REGION_SALES_SMMRY_INV', 'Region Sales Summary - Invoice', 'L', 
'/app/sales/report/regionsalessmmryinv', '1', '3', '7', 'U_SALES_REPORT', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;

INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('U_SALES_REPORT_REGION_SALES_SMMRY_BOOKING', '*', 'SALES_REPORT_REGION_SALES_SMMRY_BOOKING', 'Region Sales Summary - Booking', 'L', 
'/app/sales/report/regionsalessmmrybooking', '1', '3', '8', 'U_SALES_REPORT', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;

INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('U_SALES_REPORT_SALES_SMMRY_BY_MONTHS', '*', 'SALES_REPORT__SALES_SMMRY_BY_MONTHS', 'Sales Summary By Months', 'L', 
'/app/sales/report/salessmmrybymonths', '1', '3', '9', 'U_SALES_REPORT', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;

# 2.0.53
# Kent
# Added new table bridge link for inv and cashbook

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.53', dt_upd = NOW();

DROP TABLE IF EXISTS inv_pmnt_cashbook_link;
CREATE TABLE `inv_pmnt_cashbook_link` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_company` bigint(20) NOT NULL,
  `id_invoice` bigint(20) NOT NULL,
  `invoice_no` varchar(20) NOT NULL,
  `id_cash_book` bigint(20) NOT NULL,
  `id_inv_pmnt` bigint(20) NOT NULL,
  `status_cd` varchar(10) NOT NULL DEFAULT 'A',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `inv_pmnt_cashbook_link` ADD INDEX `idx_id_cash_book` (`id_cash_book`);
ALTER TABLE `inv_pmnt_cashbook_link` ADD INDEX `idx_id_inv_pmnt` (`id_inv_pmnt`);


insert into inv_pmnt_cashbook_link (id_company, id_invoice, invoice_no, id_cash_book, id_inv_pmnt, status_cd, dt_created, created_by, dt_upd, upd_by)
select i.id_company, i.id, i.code, ip.id_cashbook, ip.id, 'A', sysdate(), 'SYSTEM', sysdate(), 'SYSTEM'
from invoice i, invoice_pmnt ip
where (ip.id_cashbook is not null and ip.id_cashbook != '')
and i.id = ip.id_inv and ip.status_cd = 'A';


# 2.0.54
# Kent
# Amend invoice item quantity

UPDATE `db_tracking` SET version = '2.0.54', dt_upd = NOW();

ALTER TABLE `invoice_item` CHANGE COLUMN `quantity` `quantity` SMALLINT(4) NOT NULL DEFAULT 0;
ALTER TABLE `invoice_item_history` CHANGE COLUMN `quantity` `quantity` SMALLINT(4) NOT NULL DEFAULT 0;

# 2.0.55
# HS
# create table ex_order_bill_log

UPDATE `db_tracking` SET version = '2.0.55', dt_upd = NOW();

CREATE TABLE `ex_order_bill_log` (
  `id` bigint(20) NOT NULL DEFAULT 0,
  `id_company` bigint(20) NOT NULL DEFAULT '1',
  `id_eo` bigint(20) DEFAULT NULL,
  `id_supplier` bigint(20) NOT NULL,
  `id_acct` bigint(20) NOT NULL,
  `id_tour_dep` bigint(20) DEFAULT NULL,
  `dt_bill` date DEFAULT NULL,
  `dt_due` date DEFAULT NULL,
  `code` varchar(50) NOT NULL,
  `cn_no` varchar(100) DEFAULT NULL,
  `dt_inv` date DEFAULT NULL,
  `dt_dep` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_cd` varchar(10) DEFAULT NULL,
  `bill_amt` double(10,2) NOT NULL DEFAULT '0.00',
  `amt_paid` double(10,2) DEFAULT '0.00',
  `payment_term` varchar(50) DEFAULT NULL,
  `comments` text,
  `ex_reason` text,
  `tax_code_group` enum('YES','NO') DEFAULT 'NO',
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  `action` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Exchange order bill log';

DROP TRIGGER IF EXISTS ex_order_bill_AFTER_INSERT $$
DELIMITER //
CREATE TRIGGER `ex_order_bill_AFTER_INSERT` 
AFTER INSERT ON `ex_order_bill` 
FOR EACH ROW
BEGIN
	INSERT INTO ex_order_bill_log (id, id_company, id_eo, id_supplier, id_acct, id_tour_dep, dt_bill, dt_due, code, cn_no, dt_inv, 
		dt_dep, description, status_cd, bill_amt, amt_paid, payment_term, comments, ex_reason, tax_code_group, dt_created, created_by, 
		dt_upd, upd_by, action) 
	VALUES (NEW.id, NEW.id_company, NEW.id_eo, NEW.id_supplier, NEW.id_acct, NEW.id_tour_dep, NEW.dt_bill, NEW.dt_due, NEW.code, 
		NEW.cn_no, NEW.dt_inv, NEW.dt_dep, NEW.description, NEW.status_cd, NEW.bill_amt, NEW.amt_paid, NEW.payment_term, NEW.comments, 
		NEW.ex_reason, NEW.tax_code_group, NEW.dt_created, NEW.created_by, NEW.dt_upd, NEW.upd_by, 'A');
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS ex_order_bill_AFTER_UPDATE $$
DELIMITER //
CREATE TRIGGER `ex_order_bill_AFTER_UPDATE` 
AFTER UPDATE ON `ex_order_bill` 
FOR EACH ROW
BEGIN
	INSERT INTO ex_order_bill_log (id, id_company, id_eo, id_supplier, id_acct, id_tour_dep, dt_bill, dt_due, code, cn_no, dt_inv, 
		dt_dep, description, status_cd, bill_amt, amt_paid, payment_term, comments, ex_reason, tax_code_group, dt_created, created_by, 
		dt_upd, upd_by, action) 
	VALUES (NEW.id, NEW.id_company, NEW.id_eo, NEW.id_supplier, NEW.id_acct, NEW.id_tour_dep, NEW.dt_bill, NEW.dt_due, NEW.code, 
		NEW.cn_no, NEW.dt_inv, NEW.dt_dep, NEW.description, NEW.status_cd, NEW.bill_amt, NEW.amt_paid, NEW.payment_term, NEW.comments, 
		NEW.ex_reason, NEW.tax_code_group, NEW.dt_created, NEW.created_by, NEW.dt_upd, NEW.upd_by, 'U');
END; //
DELIMITER ;

# 2.0.56
# Kent
# Added menu link for acounting report

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.56', dt_upd = NOW();


INSERT INTO `sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `URI_PROCESS`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES
	('U_ACCT_RPT', '*', 'ACCT_RPT', 'Account Report', 'T', '#', '', '1', 1, 15, NULL, 'A', '', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM', 1);
	
	
UPDATE sec_func SET SEQ_NO = 16 WHERE UUID = 'U_GST';
UPDATE sec_func SET SEQ_NO = 17 WHERE UUID = 'U_MAINTENANCE';
UPDATE sec_func SET SEQ_NO = 18 WHERE UUID = 'U_HISTORY';

INSERT INTO `sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `URI_PROCESS`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES
	('U_ACCT_RPT_DEBTOR', '*', 'ACCT_RPT_DEBTOR', 'Debtor Report', 'L', '/app/acctreport/debtor', '', '1', 2, 1, 'U_ACCT_RPT', 'A', '', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM', 1);
INSERT INTO `sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `URI_PROCESS`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES
	('U_ACCT_RPT_CREDITOR', '*', 'ACCT_RPT_CREDITOR', 'Creditor Report', 'L', '/app/acctreport/creditor', '', '1', 2, 2, 'U_ACCT_RPT', 'A', '', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM', 1);

# 2.0.57
# HS
# add seq to table tour_theme and tour_pkg

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.57', dt_upd = NOW();

alter table tour_theme add column seq smallint(5) default 999 after img_path;
alter table tour_pkg add column seq smallint(5) default 999 after reserved_3;
alter table tour_pkg_history add column seq smallint(5) default 999 after reserved_3;

# 2.0.58
# HS
# new gst code

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.58', dt_upd = NOW();

INSERT INTO tax_code (UUID, tax_type, sub_type, `code`, rate, description, status_cd, dt_created, created_by, dt_update, updated_by) 
VALUES 
	('TAX150000025', 'GST', 'IN', 'TX-FRS', 2, 'Purchase under Flat Rate Scheme.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000026', 'GST', 'IN', 'TX-NC', 6, 'GST incurredand choose not to claim the input tax.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000027', 'GST', 'IN', 'TX-ER', 6, 'Input tax allowed on the acquisition of goods or services by local authority or statutory body.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000028', 'GST', 'IN', 'IM-CG', 6, 'Import of goods with GST incurred for a capital goods acquisition.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000029', 'GST', 'IN', 'IM-RE', 6, 'Import of goods with GST incurred that is not directly attributable to taxable or exempt supplies (Residual input tax).', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000030', 'GST', 'IN', 'NP', 0, 'Matters to be treated as neither a purchase of goods nor a purchase of services, and no GST incurred.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000031', 'GST', 'OUT', 'SR-MS', 6, 'Standard-rated supplies under Margin Scheme.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000032', 'GST', 'OUT', 'SR-JS', 0, 'Supplies under Approved Jeweller Scheme(AJS).', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000033', 'GST', 'OUT', 'OS-ER', 0, 'Out-of-scopesupplies for Enforcement and Regulatory functions.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000034', 'GST', 'OUT', 'OS-OV', 0, 'Out-of-scopesupplies between overseas country with other overseas country.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('TAX150000035', 'GST', 'OUT', 'NS', 0, 'Matters to be treated as neither a supply of goods nor a supply of services, and no GST chargeable.', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM');
	
#2.0.59
#Skyz	
# Add bank account report
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.59', dt_upd = NOW();

INSERT INTO `sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `URI_PROCESS`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES
	('U_ACCT_RPT_BANK', '*', 'ACCT_RPT_BANK', 'Bank Report', 'L', '/app/acctreport/bank', '', '1', 2, 3, 'U_ACCT_RPT', 'A', '', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM', 1)
ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;

# 2.0.60
# Kent	
# Add table cash_book with id_tour_dep
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.60', dt_upd = NOW();

ALTER TABLE `cash_book` ADD COLUMN `id_tour_dep` bigint(20) DEFAULT NULL AFTER `id_customer`;

# 2.0.61
# Kent	
# Change invoice remarks to ut8
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.61', dt_upd = NOW();

ALTER TABLE `invoice` MODIFY COLUMN `remarks` TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;
ALTER TABLE `invoice_history` MODIFY COLUMN `remarks` TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;


# 2.0.62
# Kent	
# Add table invoice_pmnt new column id_credit_note
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.62', dt_upd = NOW();

ALTER TABLE `invoice_pmnt` ADD COLUMN `id_credit_note` BIGINT(20) NULL DEFAULT NULL AFTER `id_inv_pmnt`;
ALTER TABLE `invoice_pmnt_history` ADD COLUMN `id_credit_note` BIGINT(20) NULL DEFAULT NULL AFTER `id_inv_pmnt`;

update invoice iv, invoice cn, invoice_pmnt p
set p.id_credit_note = cn.id
where iv.id_company = 1 and iv.doc_type_cd = 'I' and p.status_cd = 'A' and iv.id = p.id_inv and p.pmnt_type_cd = 'credit_note'
and cn.id_company = 1 and cn.doc_type_cd = 'C' and cn.status_cd not in ('CC', 'VD')
and iv.code = cn.cn_inv_no;

update invoice iv, invoice cn, invoice_pmnt p
set p.id_credit_note = cn.id
where iv.id_company = 19 and iv.doc_type_cd = 'I' and p.status_cd = 'A' and iv.id = p.id_inv and p.pmnt_type_cd = 'credit_note'
and cn.id_company = 19 and cn.doc_type_cd = 'C' and cn.status_cd not in ('CC', 'VD')
and iv.code = cn.cn_inv_no;

update invoice iv, invoice cn, invoice_pmnt p
set p.id_credit_note = cn.id
where iv.id_company = 21 and iv.doc_type_cd = 'I' and p.status_cd = 'A' and iv.id = p.id_inv and p.pmnt_type_cd = 'credit_note'
and cn.id_company = 21 and cn.doc_type_cd = 'C' and cn.status_cd not in ('CC', 'VD')
and iv.code = cn.cn_inv_no;

DELIMITER $$
DROP PROCEDURE IF EXISTS `prc_invoice` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_invoice`(IN p_id bigint(20), IN p_action varchar(50), IN p_reason text)
BEGIN

  DECLARE v_inv_id bigint(20);
  DECLARE v_inv_item_id bigint(20);
  DECLARE v_inv_pax_id bigint(20);
  DECLARE v_inv_pmnt_id bigint(20);

  INSERT INTO invoice_history (`id_hist`, `id_company`, `id_customer`, `id_acct`, `id_tour_booking`, `dt_inv`, `code`, `doc_type_cd`, `type_cd`, `attn_to`, `cn_inv_no`,
  	`pmnt_type_cd`, `id_saler`, `id_tour_dep`, `dt_departure`, `id_issuer`, `id_eo_ref`, `cat_cd`, `order_cd`, `delivery_cd`, `inv_due`, `gds_booking_ref`,
  	`amount`, `balance`, `reason`, `action_cd`, `status_cd`, `is_inv_paid`, `subj_line`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `travel_warrant_chk`)
  (SELECT
  	`id`, `id_company`, `id_customer`, `id_acct`, `id_tour_booking`, `dt_inv`, `code`, `doc_type_cd`, `type_cd`, `attn_to`, `cn_inv_no`, `pmnt_type_cd`, `id_saler`,
  	`id_tour_dep`, `dt_departure`, `id_issuer`, `id_eo_ref`, `cat_cd`, `order_cd`, `delivery_cd`, `inv_due`, `gds_booking_ref`, `amount`, `balance`, p_reason, p_action,
  	`status_cd`, `is_inv_paid`, `subj_line`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `travel_warrant_chk`
  FROM invoice
  WHERE id = p_id);
  SET v_inv_id := LAST_INSERT_ID();

  

  INSERT INTO invoice_item_history (`id_ref`, `id_hist`, `id_inv`, `id_acct`, `id_inv_eo_item`, `description`, `id_airline`, `net_price`, `quantity`, `unit_price`, `amount`,
  	`status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `tax_code`, `tax_rate`, `tax_amount`)
  (SELECT
  	v_inv_id, `id`, `id_inv`, `id_acct`, `id_inv_eo_item`, `description`, `id_airline`, `net_price`, `quantity`, `unit_price`, `amount`, `status_cd`, `dt_created`, `created_by`,
  	`dt_upd`, `upd_by`, `tax_code`, `tax_rate`, `tax_amount`
  FROM invoice_item
  WHERE id_inv = p_id);
  SET v_inv_item_id := LAST_INSERT_ID();

  

  INSERT INTO invoice_pax_history (`id_ref`, `id_hist`, `id_inv`, `id_cust`, `room_type_cd`, `room_pairing_no`, `travel_ins_type`, `travel_ins_policy`, `ticket_no`, `special_request`,
  	`lang_cd`, `act_room_type_cd`, `act_room_pairing_no`, `act_room_indicator`, `act_room_remarks`, `act_room_remarks_f`, `status_cd`)
  (SELECT
  	v_inv_id, `id`, `id_inv`, `id_cust`, `room_type_cd`, `room_pairing_no`, `travel_ins_type`, `travel_ins_policy`, `ticket_no`, `special_request`, `lang_cd`, `act_room_type_cd`,
  	`act_room_pairing_no`, `act_room_indicator`, `act_room_remarks`, `act_room_remarks_f`, `status_cd`
  FROM invoice_pax
  WHERE id_inv = p_id);
  SET v_inv_pax_id := LAST_INSERT_ID();

  

  INSERT INTO invoice_pmnt_history (`id_ref`, `id_hist`, `id_inv`, `id_issuer`, `id_bank`, `dt_pmnt`, `code`, `pmnt_type_cd`, `ref_no`, `received_from`, `amount`,
  	`remarks`, `status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `id_inv_pmnt`, `id_cashbook`, `id_credit_note`)
  (SELECT
  	v_inv_id, `id`, `id_inv`, `id_issuer`, `id_bank`, `dt_pmnt`, `code`, `pmnt_type_cd`, `ref_no`, `received_from`, `amount`, `remarks`, `status_cd`, `dt_created`,
  	`created_by`, `dt_upd`, `upd_by`, `id_inv_pmnt`, `id_cashbook`, `id_credit_note`
  FROM invoice_pmnt
  WHERE id_inv = p_id);
  SET v_inv_pmnt_id := LAST_INSERT_ID();
select v_inv_id,v_inv_item_id, v_inv_pax_id, v_inv_pmnt_id, LAST_INSERT_ID();
END $$
DELIMITER ;


# 2.0.63
# Kent	
# Add bank - master control link
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.63', dt_upd = NOW();

INSERT INTO `sec_func` (`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `URI_PROCESS`, `IS_SECURE`, `LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`, `DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
VALUES ('U_BANK_MASTER_CONTROL', '*', 'BANK_MASTER_CONTROL', 'Master Control', 'L', '/app/bank/masterControl', '', '1', 2, 8, 'U_BANK', 'A', '', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM', 1);


/*# 2.0.64
# Kent	
# Added new table tax_code_mapping_state
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.64', dt_upd = NOW();

CREATE TABLE `tax_code_mapping_state` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dt_mapping` DATE NOT NULL,
  `gst_field` VARCHAR(15) NOT NULL,
  `description` VARCHAR(500) DEFAULT NULL,
  `tax_code` VARCHAR(500) DEFAULT NULL,
  `status_cd` VARCHAR(1) NOT NULL,
  `dt_cal` DATE NOT NULL,
  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NOT NULL,
  `dt_update` DATETIME NOT NULL,
  `updated_by` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO tax_code_mapping_state
(`dt_mapping`,`gst_field`,`description`,`tax_code`,`status_cd`, `dt_cal`, `dt_created`,`created_by`,`dt_update`,`updated_by`)
VALUES
('2014-04-01', '5a', 'Total Value of Standard Rated Supply (excluding GST)', 'SR, SR-MS, SR-JS, DS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '5b', 'Total Output Tax', 'SR, SR-MS, SR-JS, DS, AJS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '6a', 'Total Value of Standard Rated Acquisition (excluding GST)', 'TX, TX-CG, TX-ES, TX-IES, TX-E43, TX-RE, TX-FRS, TX-ER, IM, IM-CG, IM-RE', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '6b', 'Total Input Tax', 'TX, TX-CG, TX-ES, TX-IES, TX-E43, TX-RE, TX-FRS, TX-ER, IM, IM-CG, IM-RE, AJP', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '7', 'GST Amount Payable', '', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '8', 'GST Amount Claimable', '', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '10', 'Total Value of zero rated supplies', 'ZRL, ZDA', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '11', 'Total Value of export supplies', 'ZRE', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '12', 'Total Value of exempt supplies', 'ES, IES, ES43', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '13', 'Total Value of supplies granted GST relief', 'RS, GS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '14', 'Total Value of goods imported under ATS', 'IS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '15', 'Total Value of GST suspended under field 14', 'IS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '16', 'Total Value of capital goods acquired (excluding GST)', 'IM-CG, TX-CG', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '17', 'Total Value of Bad Debt Relief (including GST)', 'AJP', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2014-04-01', '18', 'Total Value of Bad Debt Recovered (including GST)', 'AJS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '5a', 'Total Value of Standard Rated Supply (excluding GST)', 'SR, SR-MS, SR-JWS, DS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '5b', 'Total Output Tax', 'SR, SR-MS, SR-JWS, DS, AJS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '6a', 'Total Value of Standard Rated Acquisition (excluding GST)', 'TX, TX-CG, TX-ES, TX-IES, TX-RE, TX-FRS, IM', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '6b', 'Total Input Tax', 'TX, TX-CG, TX-ES, TX-IES, TX-RE, TX-FRS, IM, AJP', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '7', 'GST Amount Payable', '', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '8', 'GST Amount Claimable', '', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '10', 'Total Value of zero rated supplies', 'ZRL, NTX', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '11', 'Total Value of export supplies', 'ZDA, ZRE', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '12', 'Total Value of exempt supplies', 'ES, IES', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '13', 'Total Value of supplies granted GST relief', 'RS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '14', 'Total Value of goods imported under ATS', 'IS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '15', 'Total Value of GST suspended under field 14', 'IS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '16', 'Total Value of capital goods acquired (excluding GST)', 'TX-CG', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '17', 'Total Value of Bad Debt Relief (including GST)', 'AJP', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM'),
('2017-03-02', '18', 'Total Value of Bad Debt Recovered (including GST)', 'AJS', 'A', '2017-05-01', SYSDATE(), 'SYSTEM', SYSDATE(), 'SYSTEM');*/

# 2.0.65
# Kent	
# Added new table tax_code_mapping_state

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.65', dt_upd = NOW();

INSERT INTO com_sys_param(UUID, APP_ID, CAT_CD, PARAM_CD, PARAM_VALUE, PARAM_DESC, DT_CREATED, CREATED_BY, DT_UPD, UPD_BY, VERSION)
VALUES('U_GST_MAPPING_DATE', '*', 'GST', 'GST_MAPPING_DATE', '2017-05-01', 'GST Mapping Applied Date', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', 1);



# 2.0.65
# Kent
# Company Table add default account for non claimable GST

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.65', dt_upd = NOW();

ALTER TABLE company ADD COLUMN id_acct_non_claimable_gst BIGINT DEFAULT NULL AFTER id_acct_gst;

ALTER TABLE tax_code ADD COLUMN type_cd enum('S', 'N') NOT NULL DEFAULT 'S' COMMENT 'S = Suspense GST / N = Non Claimable GST' AFTER rate;
update tax_code set type_cd = 'N' where tax_type = 'GST' and sub_type = 'IN' and status_cd = 'A' and code = 'BL';
update tax_code set type_cd = 'N' where tax_type = 'GST' and sub_type = 'IN' and status_cd = 'A' and code = 'TX-NC';
update tax_code set type_cd = 'N' where tax_type = 'GST' and sub_type = 'IN' and status_cd = 'A' and code = 'TX-ES';



# 2.0.66
# HongYee
# supplier Table add default GstRegistrationNO & TypeCode
# lookup_item Table Insert Value(Asia, West, Sundry) for type_code
# based on type_cd & country name to update supplier[table] column type_code 

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.66', dt_upd = NOW();

ALTER TABLE corporate 
ADD COLUMN gst_reg_no VARCHAR(20) DEFAULT NULL AFTER reg_no;

ALTER TABLE supplier 
ADD COLUMN reg_no VARCHAR(20) DEFAULT NULL AFTER beneficiary_name, 
ADD COLUMN gst_reg_no VARCHAR(20) DEFAULT NULL AFTER reg_no, 
ADD COLUMN group_cd VARCHAR(50) DEFAULT NULL AFTER gst_reg_no;

INSERT INTO `lookup_cat` (`code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES ('supl_group_code', 'Group Supplier', 'Group Supplier', NOW(), 'SYSTEM',NOW(), 'SYSTEM');

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES ('supl_group_code', 'A', 'Asia', 'Asia', '0', '1', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES ('supl_group_code', 'W', 'West', 'West', '0', '2', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
VALUES ('supl_group_code', 'S', 'Sundry', 'Sundry', '0', '3', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- based on type_cd & country name to update supplier[table] column type_code
-- update column type_code to S if type_cd = 'SC'
UPDATE supplier SET group_cd = 'S' WHERE type_cd = 'SC';

-- update column type_code to W if type_cd = 'TC' & name = West Country Name
UPDATE supplier  s
LEFT JOIN person p on s.id_person = p.id
LEFT JOIN corporate c on c.id_person = p.id 
LEFT JOIN corporate_address ca ON c.id = ca.id_corporate
LEFT JOIN country co ON ca.id_country = co.id 
SET s.group_cd = 'W'
WHERE  s.type_cd = 'TC' 
and co.name IN ('Andorra', 'Argentina', 'Australia', 'Austria', 'ALAND ISLANDS', 'Belgium', 'Canada', 
				'Chile', 'Croatia', 'Czech Republic', 'Denmark', 'Estonia', 'Finland', 'France',
				'Germany', 'Greece', 'Hungary', 'Iceland', 'Ireland', 'Israel', 'Italy', 'Latvia',
				'Liechtenstein', 'Lithuania', 'Luxembourg', 'Malta', 'Monaco', 'Netherlands',
				'New Zealand', 'Norway', 'Poland', 'Portugal', 'San Marino', 'Slovakia', 'Slovenia', 'Spain',
				'Sweden', 'Switzerland', 'United Kingdom', 'United States', 'Vatican City', 'USA',
				'MAURITIUS', 'SOUTH AFRICA', 'ALBANIA', 'KENYA');

-- update column type_code to A if type_cd = 'TC' & name = Asia Country Name
UPDATE supplier s
LEFT JOIN person p ON s.id_person = p.id
LEFT JOIN corporate c ON c.id_person = p.id
LEFT JOIN corporate_address ca ON c.id = ca.id_corporate
LEFT JOIN country co ON ca.id_country = co.id 
SET  s.group_cd = 'A'
WHERE s.type_cd = 'TC'
AND co.name IN ('AZERBAIJAN' , 'JAPAN', 'QATAR', 'ARMENIA', 'JORDAN', 'SAUDI ARABIA',
		        'BAHRAIN', 'KAZAKHSTAN', 'SINGAPORE', 'BANGLADESH', 'KUWAIT', 'SOUTH KOREA', 'BHUTAN',
        		'KYRGYZSTAN', 'SRI LANKA', 'BRUNEI', 'LAOS', 'SYRIA', 'BURMA', 'LEBANON', 'TAIWAN',
        		'CAMBODIA', 'MALAYSIA', 'TAJIKISTAN', 'CHINA', 'MALDIVES', 'THAILAND', 'EAST TIMOR',
        		'MONGOLIA', 'TURKEY', 'INDIA', 'NEPAL', 'TURKMENISTAN', 'INDONESIA', 'NORTH KOREA',
       		 	'UNITED ARABIC EMIRATES', 'IRAN', 'OMAN', 'UZBEKISTAN', 'IRAQ', 'PAKISTAN', 'VIETNAM',
       			'ISRAEL', 'PHILIPPINES', 'YEMEN', 'HONG KONG','DUBAI');


# 2.0.67
# Kent
# supplier Table add default GstRegistrationNO & TypeCode
 
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.67', dt_upd = NOW();

-- Company 21, Account: 1050 TRADEMARK and 1107 PROV FOR DEPR - TRADE MARK
insert into account_depr(id_company, id_acct_cat, id_acct_sub_cat, id_acct_fa, id_acct_depr)
value (21, 1, 66, 704, 832);


# 2.0.68
# Kent
# Balance Sheet (Kanan) merge COMPUTER HARDWARE and PROV FOR DEPR - COMPUTER HARDWARE
 
-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.68', dt_upd = NOW();

-- Company 19, Account: 1008 COMPUTER HARDWARE and 1108 PROV FOR DEPR - COMPUTER HARDWARE
insert into account_depr(id_company, id_acct_cat, id_acct_sub_cat, id_acct_fa, id_acct_depr)
value (19, 1, 65, 812, 813);

# 2.0.69
# Kent	
# Added GST Bill Payment Applied Date (Switch Credit to Negative)

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.69', dt_upd = NOW();

INSERT INTO com_sys_param(UUID, APP_ID, CAT_CD, PARAM_CD, PARAM_VALUE, PARAM_DESC, DT_CREATED, CREATED_BY, DT_UPD, UPD_BY, VERSION)
VALUES('U_GST_BP_DATE', '*', 'GST', 'GST_BP_DATE', '2017-08-01', 'GST Bill Payment Applied Date', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', 1);

# 2.0.70
# Kent	
# Added GST tax code mapping

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.70', dt_upd = NOW();

INSERT INTO `tax_code_mapping_state` (`dt_mapping`, gst_field, description, tax_code, status_cd, dt_cal, dt_created, created_by, dt_update, updated_by)
VALUES
	('2018-02-05', '5a', 'Total Value of Standard Rated Supply (excluding GST)', 'SR, DS, SR-MS', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '5b', 'Total Output Tax', 'SR, DS, AJS, SR-MS', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '6a', 'Total Value of Standard Rated Acquisition (excluding GST)', 'TX, TX-CG, TX-ES, TX-IES, TX-RE, TX-FRS, IM, IM-CG', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '6b', 'Total Input Tax', 'TX, TX-CG, TX-ES, TX-IES, TX-RE, TX-FRS, IM, IM-CG, AJP', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '7', 'GST Amount Payable', '', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '8', 'GST Amount Claimable', '', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '10', 'Total Value of zero rated supplies', 'ZRL', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '11', 'Total Value of export supplies', 'ZDA, ZRE', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '12', 'Total Value of exempt supplies', 'ES, IES', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '13', 'Total Value of supplies granted GST relief', 'RS', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '14', 'Total Value of goods imported under ATS', 'IS', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '15', 'Total Value of Other Supplies', 'OS, GS, OS-TXM, NTX, SR-JWS', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '16', 'Total Value of capital goods acquired (excluding GST)', 'TX-CG', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '17', 'Total Value of Bad Debt Relief (including GST)', 'AJP', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '18', 'Total Value of Bad Debt Recovered (including GST)', 'AJS', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
	('2018-02-05', '19', 'Breakdown Value of Output Tax in accordance with the Major Industries Code', '', 'A', '2017-05-01', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

UPDATE `tax_code` 
SET `description`='Purchase with GST incurred directly attributable to exempt supplies, and only applicable for partially exempt trader/mixed supplier.' 
WHERE `code`='TX-ES';

UPDATE `tax_code` 
SET `description`='Purchase with GST incurred that is not directly attributable to taxable or exempt supplies, and only applicable for partially exempt trader/mixed supplier.' 
WHERE `code`='TX-RE';

UPDATE `tax_code` 
SET `description`='Imports of goods under Approved Trader Scheme (ATS) whereas the payment of GST chargeable is suspended on the goods imported.' 
WHERE `code`='IS';

UPDATE `tax_code` 
SET `description`='Purchase from GST-registered supplier with subject to GST other than standard rate. Example, zero-rate, relief, disregard and exempt supply.' 
WHERE `code`='ZP';

UPDATE `tax_code` 
SET `description`='Exportation of goods or services.' 
WHERE `code`='ZRE';


# 2.0.71
# Kent	
# Added table tour_dep_season to tour departure season

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.71', dt_upd = NOW();

CREATE TABLE `tour_dep_season` (
  `id_tour_dep` bigint(20) NOT NULL,
  `season` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `tour_pkg` ADD COLUMN `is_top_pkg` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes' AFTER `is_theme_tour`;
ALTER TABLE `tour_pkg_history` ADD COLUMN `is_top_pkg` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = No / 1 = Yes' AFTER `is_theme_tour`;


INSERT INTO `global_config` (`code`, `description`, `value`, `remarks`, `seq`, `created_by`, `dt_upd`, `upd_by`, `version`)
VALUES
	('PATH_TOUR','Tour Image','/upload/tour',NULL,8,'SYSTEM',NOW(),'SYSTEM',1);
	
CREATE TABLE `tour_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_tour_pkg` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `url` varchar(400) NOT NULL,
  `type_cd` varchar(50) NULL,
  `is_default` enum('Y','N') NOT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;


# 2.0.72
# Kent	
# Added table tour_theme_country

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.72', dt_upd = NOW();

CREATE TABLE `tour_theme_country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_tour_theme` bigint(20) NOT NULL,
  `id_country` bigint(20) NOT NULL,
  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) NOT NULL,
  `dt_upd` datetime NOT NULL,
  `upd_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;


# 2.0.73
# Kent	
# update tour_dep_item tax code to null for removed GST

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.73', dt_upd = NOW();

UPDATE tour_dep_item i, tour_dep d
SET i.tax_cd = null
WHERE i.id_tour_dep = d.id AND DATE(d.dt_dep) >= DATE('2018-09-01') AND d.status_cd = 'AC';

UPDATE tour_dep_item i, tour_dep_item_bk b
SET i.tax_cd = b.tax_cd
WHERE i.id = b.id;

UPDATE tour_dep_item i, tour_dep d,
(
	SELECT d.id_tour_pkg, i.code, GROUP_CONCAT(DISTINCT i.tax_cd SEPARATOR ', ') AS tax_cd2
	FROM tour_dep_item i, tour_dep d
	WHERE i.id_tour_dep = d.id AND i.tax_cd IS NOT NULL AND d.id_tour_pkg IN
		(SELECT d.id_tour_pkg FROM tour_dep_item i, tour_dep d
		WHERE i.id_tour_dep = d.id AND DATE(d.dt_created) >= DATE('2018-09-01') AND d.status_cd = 'AC'
		AND i.tax_cd is null GROUP BY d.id_tour_pkg)
	GROUP BY d.id_tour_pkg, i.code
) main
SET  i.tax_cd = main.tax_cd2
WHERE i.id_tour_dep = d.id AND DATE(d.dt_created) >= DATE('2018-09-01') AND d.status_cd = 'AC' AND i.tax_cd is null
AND d.id_tour_pkg = main.id_tour_pkg AND i.code = main.code;



# 2.0.74
# JJ	
# Add new page visible listing config

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.74', dt_upd = NOW();


DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2074 $$
CREATE PROCEDURE db_upd_2074()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM sec_func WHERE uuid = 'U_MAINT_VISIBLE_LISTING_CONFIG') THEN 
		INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
		`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
		VALUES ('U_MAINT_VISIBLE_LISTING_CONFIG', '*', 'MAINT_VISIBLE_LISTING_CONFIG', 'Visible Listing Config', 'L', 
		'/app/maintenance/visiblelistingconfig', '1', '2', '9', 'U_MAINTENANCE', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
		ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;
	END IF;
	
	CREATE TABLE `visible_listing_config` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `cat_cd` VARCHAR(50) NOT NULL,
	  `listing_type` VARCHAR(30) NOT NULL,
	  `id_role` VARCHAR(50) NULL,
	  `status_cd` VARCHAR(1) NOT NULL,
	  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  `created_by` varchar(50) NOT NULL,
	  `dt_upd` datetime NOT NULL,
	  `upd_by` varchar(50) DEFAULT NULL,
	  `version` INT(11) NOT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB;
    
END $$

CALL db_upd_2074() $$

DROP PROCEDURE IF EXISTS db_upd_2074 $$
DELIMITER ; 


# 2.0.75
# JJ	
# Add new col to store reference invoice & internal remarks

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.75', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2075 $$
CREATE PROCEDURE db_upd_2075()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice' AND COLUMN_NAME='id_inv_ref' ) THEN
		ALTER TABLE `invoice` 
		ADD COLUMN `id_inv_ref` BIGINT(20) NULL DEFAULT NULL AFTER `id_eo_ref`;
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice' AND COLUMN_NAME='inv_ref_code' ) THEN
		ALTER TABLE `invoice` 
		ADD COLUMN `inv_ref_code` VARCHAR(20) NULL DEFAULT NULL AFTER `id_inv_ref`;
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice_history' AND COLUMN_NAME='id_inv_ref' ) THEN
		ALTER TABLE `invoice_history` 
		ADD COLUMN `id_inv_ref` BIGINT(20) NULL DEFAULT NULL AFTER `id_eo_ref`;
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice_history' AND COLUMN_NAME='inv_ref_code' ) THEN
		ALTER TABLE `invoice_history` 
		ADD COLUMN `inv_ref_code` VARCHAR(20) NULL DEFAULT NULL AFTER `id_inv_ref`;
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice' AND COLUMN_NAME='internal_remarks' ) THEN
		ALTER TABLE `invoice` 
			ADD COLUMN `internal_remarks` TEXT NULL DEFAULT NULL AFTER `remarks`;
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice_history' AND COLUMN_NAME='internal_remarks' ) THEN
		ALTER TABLE `invoice_history` 
			ADD COLUMN `internal_remarks` TEXT NULL DEFAULT NULL AFTER `remarks`;
	END IF;
    
    
END $$

CALL db_upd_2075() $$

DROP PROCEDURE IF EXISTS db_upd_2075 $$
DELIMITER ;


# 2.0.76
# JJ	
# Add new col to store note items
# Add new lookup `note items`

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.76', dt_upd = NOW();


DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2076 $$
CREATE PROCEDURE db_upd_2076()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice' AND COLUMN_NAME='note_items' ) THEN
		ALTER TABLE `invoice` 
		ADD COLUMN `note_items` VARCHAR(250) NULL DEFAULT NULL AFTER `internal_remarks`;
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice_history' AND COLUMN_NAME='note_items' ) THEN
		ALTER TABLE `invoice_history` 
		ADD COLUMN `note_items` VARCHAR(250) NULL DEFAULT NULL AFTER `internal_remarks`;
	END IF;
    
        IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice' AND COLUMN_NAME='note_items_other' ) THEN
		ALTER TABLE `invoice` 
		ADD COLUMN `note_items_other` VARCHAR(250) NULL DEFAULT NULL AFTER `note_items`;
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice_history' AND COLUMN_NAME='note_items_other' ) THEN
		ALTER TABLE `invoice_history` 
		ADD COLUMN `note_items_other` VARCHAR(250) NULL DEFAULT NULL AFTER `note_items`;
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM `lookup_cat` WHERE `code` = 'note_items' ) THEN
		INSERT INTO `lookup_cat` (`code`, `description`, `remarks`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
		VALUES ('note_items', 'Note Items', 'Note Items', NOW(), 'SYSTEM',NOW(), 'SYSTEM');
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM `lookup_item` WHERE `code` = 'valid_passprt' ) THEN
		INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
		VALUES ('note_items', 'valid_passprt', 'Valid Passport', 'Valid Passport', '0', '1', NOW(), 'SYSTEM', NOW(), 'SYSTEM');
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM `lookup_item` WHERE `code` = 'expired_passprt' ) THEN
		INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
		VALUES ('note_items', 'expired_passprt', 'Expired Passport', 'Expired Passport', '0', '2', NOW(), 'SYSTEM', NOW(), 'SYSTEM');
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM `lookup_item` WHERE `code` = 'visa_copies' ) THEN
		INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
		VALUES ('note_items', 'visa_copies', 'Visa Copies', 'Visa Copies', '0', '3', NOW(), 'SYSTEM', NOW(), 'SYSTEM');
	END IF;
	
	IF NOT EXISTS (SELECT 1 FROM `lookup_item` WHERE `code` = 'visa_expired' ) THEN
		INSERT INTO `lookup_item` (`lookup_cat_cd`, `code`, `description`, `remarks`, `is_editable`, `seq_no`, `dt_created`, `created_by`, `dt_upd`, `upd_by`)
		VALUES ('note_items', 'visa_expired', 'Visa Expired', 'Visa Expired', '0', '4', NOW(), 'SYSTEM', NOW(), 'SYSTEM');
    END IF;
    
END $$

CALL db_upd_2076() $$

DROP PROCEDURE IF EXISTS db_upd_2076 $$
DELIMITER ; 

DELIMITER $$
DROP PROCEDURE IF EXISTS `prc_invoice` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_invoice`(IN p_id bigint(20), IN p_action varchar(50), IN p_reason text)
BEGIN

  DECLARE v_inv_id bigint(20);
  DECLARE v_inv_item_id bigint(20);
  DECLARE v_inv_pax_id bigint(20);
  DECLARE v_inv_pmnt_id bigint(20);

  INSERT INTO invoice_history (`id_hist`, `id_company`, `id_customer`, `id_acct`, `id_tour_booking`, `dt_inv`, `code`, `doc_type_cd`, `type_cd`, `attn_to`, `cn_inv_no`,
  	`pmnt_type_cd`, `id_saler`, `id_tour_dep`, `dt_departure`, `id_issuer`, `id_eo_ref`, `id_inv_ref`, `inv_ref_code`, `cat_cd`, `order_cd`, `delivery_cd`, `inv_due`, `gds_booking_ref`,
  	`amount`, `balance`, `reason`, `action_cd`, `status_cd`, `is_inv_paid`, `subj_line`, `remarks`, `internal_remarks`, `note_items`, `note_items_other`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `travel_warrant_chk`)
  (SELECT
  	`id`, `id_company`, `id_customer`, `id_acct`, `id_tour_booking`, `dt_inv`, `code`, `doc_type_cd`, `type_cd`, `attn_to`, `cn_inv_no`, `pmnt_type_cd`, `id_saler`,
  	`id_tour_dep`, `dt_departure`, `id_issuer`, `id_eo_ref`,  `id_inv_ref`, `inv_ref_code`, `cat_cd`, `order_cd`, `delivery_cd`, `inv_due`, `gds_booking_ref`, `amount`, `balance`, p_reason, p_action,
  	`status_cd`, `is_inv_paid`, `subj_line`, `remarks`,  `internal_remarks`, `note_items`, `note_items_other`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `travel_warrant_chk`
  FROM invoice
  WHERE id = p_id);
  SET v_inv_id := LAST_INSERT_ID();

  
  INSERT INTO invoice_item_history (`id_ref`, `id_hist`, `id_inv`, `id_acct`, `id_inv_eo_item`, `description`, `id_airline`, `net_price`, `quantity`, `unit_price`, `amount`,
  	`status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `tax_code`, `tax_rate`, `tax_amount`)
  (SELECT
  	v_inv_id, `id`, `id_inv`, `id_acct`, `id_inv_eo_item`, `description`, `id_airline`, `net_price`, `quantity`, `unit_price`, `amount`, `status_cd`, `dt_created`, `created_by`,
  	`dt_upd`, `upd_by`, `tax_code`, `tax_rate`, `tax_amount`
  FROM invoice_item
  WHERE id_inv = p_id);
  SET v_inv_item_id := LAST_INSERT_ID();
	
  
  INSERT INTO invoice_pax_history (`id_ref`, `id_hist`, `id_inv`, `id_cust`, `room_type_cd`, `room_pairing_no`, `travel_ins_type`, `travel_ins_policy`, `ticket_no`, `special_request`,
  	`lang_cd`, `act_room_type_cd`, `act_room_pairing_no`, `act_room_indicator`, `act_room_remarks`, `act_room_remarks_f`, `status_cd`)
  (SELECT
  	v_inv_id, `id`, `id_inv`, `id_cust`, `room_type_cd`, `room_pairing_no`, `travel_ins_type`, `travel_ins_policy`, `ticket_no`, `special_request`, `lang_cd`, `act_room_type_cd`,
  	`act_room_pairing_no`, `act_room_indicator`, `act_room_remarks`, `act_room_remarks_f`, `status_cd`
  FROM invoice_pax
  WHERE id_inv = p_id);
  SET v_inv_pax_id := LAST_INSERT_ID();

  
  INSERT INTO invoice_pmnt_history (`id_ref`, `id_hist`, `id_inv`, `id_issuer`, `id_bank`, `dt_pmnt`, `code`, `pmnt_type_cd`, `ref_no`, `received_from`, `amount`,
  	`remarks`, `status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `id_inv_pmnt`, `id_cashbook`, `id_credit_note`)
  (SELECT
  	v_inv_id, `id`, `id_inv`, `id_issuer`, `id_bank`, `dt_pmnt`, `code`, `pmnt_type_cd`, `ref_no`, `received_from`, `amount`, `remarks`, `status_cd`, `dt_created`,
  	`created_by`, `dt_upd`, `upd_by`, `id_inv_pmnt`, `id_cashbook`, `id_credit_note`
  FROM invoice_pmnt
  WHERE id_inv = p_id);
  SET v_inv_pmnt_id := LAST_INSERT_ID();
select v_inv_id,v_inv_item_id, v_inv_pax_id, v_inv_pmnt_id, LAST_INSERT_ID();
END $$
DELIMITER ;


# 2.0.77
# JJ	
# Add new col `lock_status`

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.77', dt_upd = NOW();


DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2077 $$
CREATE PROCEDURE db_upd_2077()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='tour_booking' AND COLUMN_NAME='lock_status' ) THEN
		ALTER TABLE `tour_booking` 
		ADD COLUMN `lock_status` VARCHAR(1) NOT NULL DEFAULT '0' AFTER `quantity`;
	END IF;
	
END $$

CALL db_upd_2077() $$

DROP PROCEDURE IF EXISTS db_upd_2077 $$
DELIMITER ; 

/**
 * 2.0.78
 * JJ
 * Add new col `file_path`, to store unique name of file name
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.78', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2078 $$
CREATE PROCEDURE db_upd_2078()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='person_identity_detail' AND COLUMN_NAME='file_path' ) THEN
		ALTER TABLE `person_identity_detail` 
			ADD COLUMN `file_path` VARCHAR(255) NULL DEFAULT NULL AFTER `scanned_path`;
	END IF;
	
	UPDATE person_identity_detail SET file_path = scanned_path;
	
END $$

CALL db_upd_2078() $$

DROP PROCEDURE IF EXISTS db_upd_2078 $$
DELIMITER ; 

/**
 * 2.0.79
 * JJ
 * Add new table `tour_pkg_itinery`
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.79', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2079 $$
CREATE PROCEDURE db_upd_2079()
BEGIN
	
	CREATE TABLE IF NOT EXISTS tour_pkg_itinery (
	  `id` INT NOT NULL AUTO_INCREMENT,
	  `id_tour_pkg` BIGINT(20) NOT NULL,
	  `name` VARCHAR(255) NOT NULL,
	  `path` VARCHAR(255) NOT NULL,
	  `lang_cd` VARCHAR(50) NOT NULL,
	  `type_cd` VARCHAR(50) NOT NULL,
	  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  `created_by` VARCHAR(50) NOT NULL,
	  `dt_update` DATETIME NOT NULL,
	  `updated_by` VARCHAR(50) NOT NULL,
	  PRIMARY KEY (`id`))
	ENGINE = InnoDB;
	
END $$

CALL db_upd_2079() $$

DROP PROCEDURE IF EXISTS db_upd_2079 $$
DELIMITER ; 

/**
 * 2.0.80
 * JJ
 * Add new table `sec_user_password_reset`
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.80', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2080 $$
CREATE PROCEDURE db_upd_2080()
BEGIN
	
	DROP TABLE IF EXISTS sec_user_password_reset;
	CREATE TABLE sec_user_password_reset (
	  `id` INT NOT NULL AUTO_INCREMENT,
	  `email_address` VARCHAR(60) NOT NULL,
	  `login_id` VARCHAR(20) DEFAULT NULL,
	  `encoded_password` VARCHAR(60) NOT NULL,
	  `status_cd` VARCHAR(1) NOT NULL,
	  `dt_update` DATETIME NOT NULL,
	   PRIMARY KEY (`id`)
	) ENGINE=InnoDB;
	
END $$

CALL db_upd_2080() $$

DROP PROCEDURE IF EXISTS db_upd_2080 $$

DELIMITER ;


/**
 * 2.0.81
 * JJ
 * Add new table `listing_table_view` to store user saved columns to view on listing
 * currently apply on invoice listing
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.81', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2081 $$
CREATE PROCEDURE db_upd_2081()
BEGIN
	
	CREATE TABLE IF NOT EXISTS `listing_table_view` (
	  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	  `id_user` BIGINT(20) NOT NULL,
	  `listing_type` VARCHAR(20) NOT NULL,
	  `status_cd` VARCHAR(1) NOT NULL,
	  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  `created_by` VARCHAR(50) NOT NULL,
	  `dt_update` DATETIME NOT NULL,
	  `updated_by` VARCHAR(50) NOT NULL,
	   PRIMARY KEY (`id`)
	) ENGINE=InnoDB;
	
	CREATE TABLE IF NOT EXISTS `listing_table_view_columns` (
	  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
      `id_listing_table` BIGINT(20) NOT NULL,
	  `columns_name` varchar(100) NOT NULL,
      `seq_no` INT(12) NOT NULL DEFAULT '0',
	  `visible` varchar(1) NOT NULL DEFAULT '0',
	  `status_cd` VARCHAR(1) NOT NULL,
	  `dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  `created_by` VARCHAR(50) NOT NULL,
	  `dt_update` DATETIME NOT NULL,
	  `updated_by` VARCHAR(50) NOT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB;
	
END $$

CALL db_upd_2081() $$

DROP PROCEDURE IF EXISTS db_upd_2081 $$

DELIMITER ;

/**
 * 2.0.82
 * JJ
 * Add new menu `counter sales report`
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.82', dt_upd = NOW();


DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2082 $$
CREATE PROCEDURE db_upd_2082()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM sec_func WHERE uuid = 'U_SALES_REPORT_COUNTER_SALES_REPORT') THEN 
		INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
		`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
		VALUES ('U_SALES_REPORT_COUNTER_SALES_REPORT', '*', 'SALES_REPORT_COUNTER_SALES_REPORT', 'Counter Sales Report', 'L', 
		'/app/sales/report/countersalesreport', '1', '3', '4', 'U_SALES_REPORT', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
		ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;
	END IF;
	
END $$

CALL db_upd_2082() $$

DROP PROCEDURE IF EXISTS db_upd_2082 $$
DELIMITER ; 


/**
 * 2.0.83
 * JJ
 * Update template message category code to use invoice category code
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.83', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2083 $$
CREATE PROCEDURE db_upd_2083()
BEGIN
	
    UPDATE tmp_msg SET cat_cd = 'to' WHERE cat_cd = 'TOUR' AND status_cd = 'A';
	
END $$

CALL db_upd_2083() $$

DROP PROCEDURE IF EXISTS db_upd_2083 $$
DELIMITER ;

/**
 * 2.0.84
 * JJ
 * Add new menu POS Upload Config
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.84', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2084 $$
CREATE PROCEDURE db_upd_2084()
BEGIN
    
    IF NOT EXISTS (SELECT 1 FROM sec_func WHERE uuid = 'U_MAINT_POS_UPLOAD_CONFIG') THEN 
		INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
		`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
		VALUES ('U_MAINT_POS_UPLOAD_CONFIG', '*', 'MAINT_POS_UPLOAD_CONFIG', 'POS Upload Config', 'L', 
		'/app/maintenance/posupload/config', '1', '2', '11', 'U_MAINTENANCE', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
		ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;
	END IF;
    
    CREATE TABLE IF NOT EXISTS `app_setting` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `module` VARCHAR(50) NOT NULL,
      `code` VARCHAR(60) NOT NULL,
      `description` VARCHAR(100) NULL,
      `value` TEXT NOT NULL,
	  `status_cd` VARCHAR(1) NOT NULL,
	  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  `created_by` varchar(50) NOT NULL,
	  `dt_upd` datetime NOT NULL,
	  `upd_by` varchar(50) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB;
    
    DROP TABLE IF EXISTS `app_setting_log`;
	CREATE TABLE IF NOT EXISTS `app_setting_log` AS SELECT *, status_cd AS ACTION FROM app_setting;
    
END $$

CALL db_upd_2084() $$

DROP PROCEDURE IF EXISTS db_upd_2084 $$
DELIMITER ; 

DELIMITER $$ 
DROP TRIGGER IF EXISTS app_setting_AFTER_INSERT$$ 
CREATE TRIGGER `app_setting_AFTER_INSERT` 
AFTER INSERT ON `app_setting` 
FOR EACH ROW 
	BEGIN 
		INSERT INTO app_setting_log (`id`, `module`, `code`, `description`, `value`, 
			`status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, ACTION) 
		VALUES (NEW.`id`, NEW.`module`, NEW.`code`, NEW.`description`, NEW.`value`, 
			NEW.`status_cd`, NEW.`dt_created`, NEW.`created_by`, NEW.`dt_upd`, NEW.`upd_by`, 'A'); 
	END 
$$ 

DELIMITER $$ 
DROP TRIGGER IF EXISTS app_setting_AFTER_UPDATE$$ 
CREATE TRIGGER `app_setting_AFTER_UPDATE` 
AFTER UPDATE ON `app_setting` 
FOR EACH ROW 
	BEGIN 
		INSERT INTO app_setting_log (`id`, `module`, `code`, `description`, `value`, 
			`status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, ACTION) 
		VALUES (NEW.`id`, NEW.`module`, NEW.`code`, NEW.`description`, NEW.`value`, 
			NEW.`status_cd`, NEW.`dt_created`, NEW.`created_by`, NEW.`dt_upd`, NEW.`upd_by`, CASE WHEN NEW.status_cd = 'D' THEN 'D' ELSE 'U' END); 
	END 
$$ 

DELIMITER ; 

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2084_1 $$
CREATE PROCEDURE db_upd_2084_1()
BEGIN
    
	IF NOT EXISTS (SELECT 1 FROM app_setting WHERE code = 'POS_CONFIG_UPLOAD_AS_TEST') THEN 
		INSERT INTO `app_setting` (`module`, `code`, `description`, `value`, `status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) 
		VALUES ('MAINT', 'POS_CONFIG_UPLOAD_AS_TEST', 'POS Config Upload As Test Data', 'Y', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM');
	END IF;
    
    IF NOT EXISTS (SELECT 1 FROM app_setting WHERE code = 'POS_CONFIG_SCHEDULER_TIME_UPLOAD') THEN 
		INSERT INTO `app_setting` (`module`, `code`, `description`, `value`, `status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) 
		VALUES ('MAINT', 'POS_CONFIG_SCHEDULER_TIME_UPLOAD', 'POS Config Scheduler Time Upload', '23:00', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM');
	END IF;
	
END $$

CALL db_upd_2084_1() $$

DROP PROCEDURE IF EXISTS db_upd_2084_1 $$
DELIMITER ;

/**
 * 2.0.85
 * JJ
 * Add new menu POS Upload Sales
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.85', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2085 $$
CREATE PROCEDURE db_upd_2085()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM sec_func WHERE uuid = 'U_MAINT_POS_UPLOAD_SALES_TRANS') THEN 
		INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
		`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
		VALUES ('U_MAINT_POS_UPLOAD_SALES_TRANS', '*', 'MAINT_POS_UPLOAD_SALES_TRANS', 'POS Upload - Sales Transaction', 'L', 
		'/app/maintenance/posupload/sales', '1', '2', '10', 'U_MAINTENANCE', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
		ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;
	END IF;
    
    IF NOT EXISTS (SELECT 1 FROM sec_func WHERE uuid = 'U_MAINT_POS_UPLOAD_SALES_HISTORY') THEN 
		INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
		`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
		VALUES ('U_MAINT_POS_UPLOAD_SALES_HISTORY', '*', 'MAINT_POS_UPLOAD_SALES_HISTORY', 'POS Upload History', 'L', 
		'/app/maintenance/posupload/history', '1', '2', '11', 'U_MAINTENANCE', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
		ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;
	END IF;
    
	CREATE TABLE IF NOT EXISTS `pos_upload_sales` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `total_amount` DOUBLE(10,2) NOT NULL,
      `upload_date` DATE NOT NULL,
      `upload_status` VARCHAR(20) NOT NULL,
      `is_test` INT(1) NOT NULL,
	  `status_cd` VARCHAR(1) NOT NULL,
	  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  `created_by` varchar(50) NOT NULL,
	  `dt_upd` datetime NOT NULL,
	  `upd_by` varchar(50) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB;
    
    DROP TABLE IF EXISTS `pos_upload_sales_log`;
	CREATE TABLE IF NOT EXISTS `pos_upload_sales_log` AS SELECT *, status_cd AS ACTION FROM pos_upload_sales;
    
    CREATE TABLE IF NOT EXISTS `pos_upload_sales_trans` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `id_pos_upload_sales`  bigint(20) NOT NULL,
      `id_invoice`  bigint(20) NOT NULL,
      `doc_type_cd` VARCHAR(50) NOT NULL,
	  `invoice_no` VARCHAR(20) NOT NULL,
	  `sub_total` DOUBLE(10,2) NOT NULL,
	  `discount_percent` DOUBLE(10,2) NULL,
	  `discount_amount` DOUBLE(10,2) NULL,
      `tax_rate` DOUBLE(10,2) NOT NULL,
      `tax_amount` DOUBLE(10,2) NOT NULL,
      `service_charge_percent` DOUBLE(10,2) NOT NULL,
      `service_charge_amount` DOUBLE(10,2) NOT NULL,
      `grand_total` DOUBLE(10,2) NOT NULL,
      `is_test` INT(1) NOT NULL,
      `is_void` INT(1) NOT NULL,
      `invoice_date` DATETIME NOT NULL,
      `status_cd` VARCHAR(1) NOT NULL,
	  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  `created_by` varchar(50) NOT NULL,
	  `dt_upd` datetime NOT NULL,
	  `upd_by` varchar(50) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB;
    
    DROP TABLE IF EXISTS `pos_upload_sales_trans_log`;
	CREATE TABLE IF NOT EXISTS `pos_upload_sales_trans_log` AS SELECT *, status_cd AS ACTION FROM pos_upload_sales_trans;
    
    UPDATE `sec_func` SET `SEQ_NO`='12' WHERE `UUID`='U_MAINT_POS_UPLOAD_CONFIG';

END $$

CALL db_upd_2085() $$

DROP PROCEDURE IF EXISTS db_upd_2085 $$
DELIMITER ; 


DELIMITER $$ 
DROP TRIGGER IF EXISTS pos_upload_sales_AFTER_INSERT$$ 
CREATE TRIGGER `pos_upload_sales_AFTER_INSERT` 
AFTER INSERT ON `pos_upload_sales` 
FOR EACH ROW 
	BEGIN 
		INSERT INTO pos_upload_sales_log (`id`, `total_amount`, `upload_date`, `upload_status`, `is_test`, 
			`status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, ACTION) 
		VALUES (NEW.`id`, NEW.`total_amount`, NEW.`upload_date`, NEW.`upload_status`, NEW.`is_test`, 
			NEW.`status_cd`, NEW.`dt_created`, NEW.`created_by`, NEW.`dt_upd`, NEW.`upd_by`, 'A'); 
	END 
$$ 

DELIMITER $$ 
DROP TRIGGER IF EXISTS pos_upload_sales_AFTER_UPDATE$$ 
CREATE TRIGGER `pos_upload_sales_AFTER_UPDATE` 
AFTER UPDATE ON `pos_upload_sales` 
FOR EACH ROW 
	BEGIN 
		INSERT INTO pos_upload_sales_log (`id`, `total_amount`, `upload_date`, `upload_status`, `is_test`, 
			`status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, ACTION) 
		VALUES (NEW.`id`, NEW.`total_amount`, NEW.`upload_date`, NEW.`upload_status`, NEW.`is_test`, 
			NEW.`status_cd`, NEW.`dt_created`, NEW.`created_by`, NEW.`dt_upd`, NEW.`upd_by`, CASE WHEN NEW.status_cd = 'D' THEN 'D' ELSE 'U' END); 
	END 
$$ 

DELIMITER ; 

DELIMITER $$ 
DROP TRIGGER IF EXISTS pos_upload_sales_trans_AFTER_INSERT$$ 
CREATE TRIGGER `pos_upload_sales_trans_AFTER_INSERT` 
AFTER INSERT ON `pos_upload_sales_trans` 
FOR EACH ROW 
	BEGIN 
		INSERT INTO pos_upload_sales_trans_log (`id`, `id_pos_upload_sales`, `id_invoice`, `doc_type_cd`, `invoice_no`, 
			`sub_total`, `discount_percent`, `discount_amount`, `tax_rate`, `tax_amount`, 
			`service_charge_percent`, `service_charge_amount`, `grand_total`, `is_test`, `is_void`, 
			`invoice_date`, `status_cd`, `dt_created`, `created_by`, `dt_upd`, 
			`upd_by`, ACTION) 
		VALUES (NEW.`id`, NEW.`id_pos_upload_sales`, NEW.`id_invoice`, NEW.`doc_type_cd`, NEW.`invoice_no`, 
			NEW.`sub_total`, NEW.`discount_percent`, NEW.`discount_amount`, NEW.`tax_rate`, NEW.`tax_amount`, 
			NEW.`service_charge_percent`, NEW.`service_charge_amount`, NEW.`grand_total`, NEW.`is_test`, NEW.`is_void`, 
			NEW.`invoice_date`, NEW.`status_cd`, NEW.`dt_created`, NEW.`created_by`, NEW.`dt_upd`, 
			NEW.`upd_by`, 'A'); 
	END 
$$ 

DELIMITER $$ 
DROP TRIGGER IF EXISTS pos_upload_sales_trans_AFTER_UPDATE$$ 
CREATE TRIGGER `pos_upload_sales_trans_AFTER_UPDATE` 
AFTER UPDATE ON `pos_upload_sales_trans` 
FOR EACH ROW 
	BEGIN 
		INSERT INTO pos_upload_sales_trans_log (`id`, `id_pos_upload_sales`, `id_invoice`, `doc_type_cd`, `invoice_no`, 
			`sub_total`, `discount_percent`, `discount_amount`, `tax_rate`, `tax_amount`, 
			`service_charge_percent`, `service_charge_amount`, `grand_total`, `is_test`, `is_void`, 
			`invoice_date`, `status_cd`, `dt_created`, `created_by`, `dt_upd`, 
			`upd_by`, ACTION) 
		VALUES (NEW.`id`, NEW.`id_pos_upload_sales`, NEW.`id_invoice`, NEW.`doc_type_cd`, NEW.`invoice_no`, 
			NEW.`sub_total`, NEW.`discount_percent`, NEW.`discount_amount`, NEW.`tax_rate`, NEW.`tax_amount`, 
			NEW.`service_charge_percent`, NEW.`service_charge_amount`, NEW.`grand_total`, NEW.`is_test`, NEW.`is_void`, 
			NEW.`invoice_date`, NEW.`status_cd`, NEW.`dt_created`, NEW.`created_by`, NEW.`dt_upd`, 
			NEW.`upd_by`, CASE WHEN NEW.status_cd = 'D' THEN 'D' ELSE 'U' END); 
	END 
$$ 

DELIMITER ;

/**
 * 2.0.86
 * JJ
 * Add new menu CN Report
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.86', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2086 $$
CREATE PROCEDURE db_upd_2086()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM sec_func WHERE uuid = 'U_SALES_REPORT_CN_REPORT') THEN 
		INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
		`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
		VALUES ('U_SALES_REPORT_CN_REPORT', '*', 'SALES_REPORT_CN_REPORT', 'C/N Report', 'L', 
		'/app/sales/report/cnreport', '1', '3', '5', 'U_SALES_REPORT', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
		ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;
	END IF;
	
END $$

CALL db_upd_2086() $$

DROP PROCEDURE IF EXISTS db_upd_2086 $$
DELIMITER ; 


/**
 * 2.0.87
 * JJ
 * Add new menu `invoice_reference`
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.87', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2087 $$
CREATE PROCEDURE db_upd_2087()
BEGIN
	
	CREATE TABLE IF NOT EXISTS `invoice_reference` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `id_invoice` bigint(20) NOT NULL,
      `id_inv_ref` bigint(20) NOT NULL,
      `inv_ref_code` VARCHAR(20) NOT NULL,
	  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  `created_by` varchar(50) NOT NULL,
	  `dt_upd` datetime NOT NULL,
	  `upd_by` varchar(50) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB;
	
	ALTER TABLE `invoice` 
	DROP COLUMN `inv_ref_code`,
	DROP COLUMN `id_inv_ref`;
	
	ALTER TABLE `invoice_history` 
	DROP COLUMN `inv_ref_code`,
	DROP COLUMN `id_inv_ref`;
   
END $$

CALL db_upd_2087() $$

DROP PROCEDURE IF EXISTS db_upd_2087 $$
DELIMITER ; 

DELIMITER $$
DROP PROCEDURE IF EXISTS `prc_invoice` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_invoice`(IN p_id bigint(20), IN p_action varchar(50), IN p_reason text)
BEGIN

  DECLARE v_inv_id bigint(20);
  DECLARE v_inv_item_id bigint(20);
  DECLARE v_inv_pax_id bigint(20);
  DECLARE v_inv_pmnt_id bigint(20);

  INSERT INTO invoice_history (`id_hist`, `id_company`, `id_customer`, `id_acct`, `id_tour_booking`, `dt_inv`, `code`, `doc_type_cd`, `type_cd`, `attn_to`, `cn_inv_no`,
	`pmnt_type_cd`, `id_saler`, `id_tour_dep`, `dt_departure`, `id_issuer`, `id_eo_ref`, `cat_cd`, `order_cd`, `delivery_cd`, `inv_due`, `gds_booking_ref`,
	`amount`, `balance`, `reason`, `action_cd`, `status_cd`, `is_inv_paid`, `subj_line`, `remarks`, `internal_remarks`, `note_items`, `note_items_other`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `travel_warrant_chk`)
  (SELECT
	`id`, `id_company`, `id_customer`, `id_acct`, `id_tour_booking`, `dt_inv`, `code`, `doc_type_cd`, `type_cd`, `attn_to`, `cn_inv_no`, `pmnt_type_cd`, `id_saler`,
	`id_tour_dep`, `dt_departure`, `id_issuer`, `id_eo_ref`, `cat_cd`, `order_cd`, `delivery_cd`, `inv_due`, `gds_booking_ref`, `amount`, `balance`, p_reason, p_action,
	`status_cd`, `is_inv_paid`, `subj_line`, `remarks`,  `internal_remarks`, `note_items`, `note_items_other`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `travel_warrant_chk`
  FROM invoice
  WHERE id = p_id);
  SET v_inv_id := LAST_INSERT_ID();

  
  INSERT INTO invoice_item_history (`id_ref`, `id_hist`, `id_inv`, `id_acct`, `id_inv_eo_item`, `description`, `id_airline`, `net_price`, `quantity`, `unit_price`, `amount`,
	`status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `tax_code`, `tax_rate`, `tax_amount`)
  (SELECT
	v_inv_id, `id`, `id_inv`, `id_acct`, `id_inv_eo_item`, `description`, `id_airline`, `net_price`, `quantity`, `unit_price`, `amount`, `status_cd`, `dt_created`, `created_by`,
	`dt_upd`, `upd_by`, `tax_code`, `tax_rate`, `tax_amount`
  FROM invoice_item
  WHERE id_inv = p_id);
  SET v_inv_item_id := LAST_INSERT_ID();
	
  
  INSERT INTO invoice_pax_history (`id_ref`, `id_hist`, `id_inv`, `id_cust`, `room_type_cd`, `room_pairing_no`, `travel_ins_type`, `travel_ins_policy`, `ticket_no`, `special_request`,
	`lang_cd`, `act_room_type_cd`, `act_room_pairing_no`, `act_room_indicator`, `act_room_remarks`, `act_room_remarks_f`, `status_cd`)
  (SELECT
	v_inv_id, `id`, `id_inv`, `id_cust`, `room_type_cd`, `room_pairing_no`, `travel_ins_type`, `travel_ins_policy`, `ticket_no`, `special_request`, `lang_cd`, `act_room_type_cd`,
	`act_room_pairing_no`, `act_room_indicator`, `act_room_remarks`, `act_room_remarks_f`, `status_cd`
  FROM invoice_pax
  WHERE id_inv = p_id);
  SET v_inv_pax_id := LAST_INSERT_ID();

  
  INSERT INTO invoice_pmnt_history (`id_ref`, `id_hist`, `id_inv`, `id_issuer`, `id_bank`, `dt_pmnt`, `code`, `pmnt_type_cd`, `ref_no`, `received_from`, `amount`,
	`remarks`, `status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `id_inv_pmnt`, `id_cashbook`, `id_credit_note`)
  (SELECT
	v_inv_id, `id`, `id_inv`, `id_issuer`, `id_bank`, `dt_pmnt`, `code`, `pmnt_type_cd`, `ref_no`, `received_from`, `amount`, `remarks`, `status_cd`, `dt_created`,
	`created_by`, `dt_upd`, `upd_by`, `id_inv_pmnt`, `id_cashbook`, `id_credit_note`
  FROM invoice_pmnt
  WHERE id_inv = p_id);
  SET v_inv_pmnt_id := LAST_INSERT_ID();
select v_inv_id,v_inv_item_id, v_inv_pax_id, v_inv_pmnt_id, LAST_INSERT_ID();
END $$
DELIMITER ;


/**
 * 2.0.88
 * JJ
 * Add new table `tour_dep_discount`
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.88', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2088 $$
CREATE PROCEDURE db_upd_2088()
BEGIN
	
	IF NOT EXISTS (SELECT 1 FROM app_setting WHERE code = 'TOUR_DEPARTURE_DISCOUNT_LIMIT') THEN 
		INSERT INTO `app_setting` (`module`, `code`, `description`, `value`, `status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) 
		VALUES ('SALES', 'TOUR_DEPARTURE_DISCOUNT_LIMIT', 'Tour Departure Discount Limit', '2', 'A', NOW(), 'SYSTEM', NOW(), 'SYSTEM');
	END IF;
	
	CREATE TABLE IF NOT EXISTS `tour_dep_discount` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `id_tour_dep` bigint(20) NOT NULL,
      `discount_amt` DOUBLE(10,2) NOT NULL DEFAULT '0.00',
      `discount_pax` SMALLINT(3) NOT NULL,
      `discount_pax_used` SMALLINT(3) NOT NULL DEFAULT '0.00',
	  `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  `created_by` varchar(50) NOT NULL,
	  `dt_upd` datetime NOT NULL,
	  `upd_by` varchar(50) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB;
	
	INSERT INTO `tour_dep_discount` 
	(`id_tour_dep`, `discount_amt`, `discount_pax`, `dt_created`, `created_by`, `dt_upd`, `upd_by`) 
	SELECT id, tfair_discount, discount_pax, NOW(), 'SYSTEM', NOW(), 'SYSTEM'
	FROM 
	(SELECT 
		d.id,
	    d.tfair_discount,
	    d.seat_allotment - d.reserved_seat - d.tour_mgr_pax - 
	    ((SELECT  CASE WHEN SUM(i.quantity) IS NULL THEN 0 ELSE SUM(i.quantity) END
	        FROM  tour_booking b, tour_booking_charge_item i
	        WHERE b.id_tour_dep = d.id AND i.id_tour_booking = b.id AND b.status_cd = 'CF'
	                AND b.order_type_cd LIKE 't_fair%' AND i.code IN ('FT_SGL' , 'FT_TWN', 'FT_CTW', 'FT_CWB', 'FT_CNB')) + 
	    (SELECT CASE WHEN SUM(i.quantity) IS NULL THEN 0 ELSE SUM(i.quantity) END
	        FROM tour_booking b, tour_booking_charge_item i
	        WHERE b.id_tour_dep = d.id AND i.id_tour_booking = b.id AND b.status_cd = 'CF'
	                AND b.order_type_cd LIKE 'agent%' AND i.code IN ('FT_SGL' , 'FT_TWN', 'FT_CTW', 'FT_CWB', 'FT_CNB')) +
	    (SELECT CASE WHEN SUM(i.quantity) IS NULL THEN 0 ELSE SUM(i.quantity) END
	        FROM tour_booking b, tour_booking_charge_item i
	        WHERE b.id_tour_dep = d.id AND i.id_tour_booking = b.id AND b.status_cd = 'CF'
	                AND b.order_type_cd LIKE 'in_house%' AND i.code IN ('FT_SGL' , 'FT_TWN', 'FT_CTW', 'FT_CWB', 'FT_CNB')) +
	    (SELECT CASE WHEN SUM(i.quantity) IS NULL THEN 0 ELSE SUM(i.quantity) END
	        FROM tour_booking b, tour_booking_charge_item i
	        WHERE b.id_tour_dep = d.id AND i.id_tour_booking = b.id AND b.status_cd = 'BK'
	                AND b.pmnt_status_cd = 'KIV' AND i.code IN ('FT_SGL' , 'FT_TWN', 'FT_CTW', 'FT_CWB', 'FT_CNB'))
	    ) AS discount_pax
	FROM
	    tour_dep d
	WHERE
	    d.status_cd = 'AC' AND d.tfair_discount > 0) a WHERE discount_pax > 0;
END $$

CALL db_upd_2088() $$

DROP PROCEDURE IF EXISTS db_upd_2088 $$
DELIMITER ; 


/**
 * 2.0.89
 * JJ
 * Move all POS menu park under POS
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.89', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2089 $$
CREATE PROCEDURE db_upd_2089()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM sec_func WHERE uuid = 'U_POS') THEN 
		INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
		`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
		VALUES ('U_POS', '*', 'POS', 'POS', 'T',  '#', '1', '1', '16', NULL, 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
		ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;
	END IF;
    
    UPDATE `sec_func` SET `U_PARENT_FUNC`='U_POS' WHERE `UUID`='U_MAINT_POS_UPLOAD_CONFIG';
	UPDATE `sec_func` SET `U_PARENT_FUNC`='U_POS' WHERE `UUID`='U_MAINT_POS_UPLOAD_SALES_TRANS';
	UPDATE `sec_func` SET `U_PARENT_FUNC`='U_POS' WHERE `UUID`='U_MAINT_POS_UPLOAD_SALES_HISTORY'; 
	
	UPDATE `sec_func` SET `SEQ_NO`='17' WHERE `UUID`='U_GST';
	UPDATE `sec_func` SET `SEQ_NO`='18' WHERE `UUID`='U_MAINTENANCE';
	UPDATE `sec_func` SET `SEQ_NO`='19' WHERE `UUID`='U_HISTORY';
	
END $$

CALL db_upd_2089() $$

DROP PROCEDURE IF EXISTS db_upd_2089 $$
DELIMITER ; 


/**
 * 2.0.90
 * JJ
 * Add new column to store user linkage with customer
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.90', dt_upd = NOW();


DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2090 $$
CREATE PROCEDURE db_upd_2090()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='employee' AND COLUMN_NAME='id_customer' ) THEN
		ALTER TABLE `employee` 
		ADD COLUMN `id_customer` BIGINT(20) NULL DEFAULT NULL AFTER `is_default_comp`;
	END IF;
    
    IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='employee' AND COLUMN_NAME='customer_name' ) THEN
		ALTER TABLE `employee` 
		ADD COLUMN `customer_name` VARCHAR(255) NULL DEFAULT NULL AFTER `id_customer`;
	END IF;
    
	IF NOT EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='employee' AND COLUMN_NAME='role_type' ) THEN
		ALTER TABLE `employee` 
		ADD COLUMN `role_type` VARCHAR(20) NOT NULL AFTER `customer_name`;
	END IF;
    
    UPDATE employee SET role_type = 'staff';
    
END $$

CALL db_upd_2090() $$

DROP PROCEDURE IF EXISTS db_upd_2090 $$
DELIMITER ; 


/**
 * 2.0.91
 * JJ
 * Add new menu for `Agent booking`
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.91', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2091 $$
CREATE PROCEDURE db_upd_2091()
BEGIN
	
    IF NOT EXISTS (SELECT 1 FROM sec_func WHERE uuid = 'U_SALES_AGENT_BOOKING') THEN 
		INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
		`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
		VALUES ('U_SALES_AGENT_BOOKING', '*', 'SALES_AGENT_BOOKING', 'Agent Booking', 'L', 
		'/app/sales/agentbooking/reserve', '1', '2', '6', 'U_SALES', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
		ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;
	END IF;
    
    IF NOT EXISTS (SELECT 1 FROM sec_func WHERE uuid = 'U_SALES_AGENT_BOOKING_LIST') THEN 
		INSERT INTO sec_func(`UUID`, `APP_ID`, `FUNC_CD`, `FUNC_NAME`, `TYPE_CD`, `URI_ENTRY`, `IS_SECURE`,
		`LEVEL_NO`, `SEQ_NO`, `U_PARENT_FUNC`, `STATUS_CD`, `REMARKS`,`DT_CREATED`, `CREATED_BY`, `DT_UPD`, `UPD_BY`, `VERSION`)
		VALUES ('U_SALES_AGENT_BOOKING_LIST', '*', 'SALES_AGENT_BOOKING_LIST', 'Agent Booking List', 'L', 
		'/app/sales/agentbooking/list', '1', '2', '7', 'U_SALES', 'A','', NOW(), 'SYSTEM', NOW(), 'SYSTEM', '1')
		ON DUPLICATE KEY UPDATE FUNC_CD=FUNC_CD;
	END IF;
    
    UPDATE `sec_func` SET `SEQ_NO`='90' WHERE `UUID`='U_SALES_REPORT';
	
END $$

CALL db_upd_2091() $$

DROP PROCEDURE IF EXISTS db_upd_2091 $$
DELIMITER ; 


/**
 * 2.0.92
 * JJ
 * Drop column note item other as it not using anymore
 */

-- update db_tracking version
UPDATE `db_tracking` SET version = '2.0.92', dt_upd = NOW();

DELIMITER $$
DROP PROCEDURE IF EXISTS db_upd_2092 $$
CREATE PROCEDURE db_upd_2092()
BEGIN

	IF EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice' AND COLUMN_NAME='note_items_other' ) THEN
		ALTER TABLE `invoice` 
			DROP COLUMN `note_items_other`;
	END IF;
    
	IF EXISTS (SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE()
			AND TABLE_NAME='invoice_history' AND COLUMN_NAME='note_items_other' ) THEN
		ALTER TABLE `invoice_history` 
			DROP COLUMN `note_items_other`;
	END IF;
	
END $$

CALL db_upd_2092() $$

DROP PROCEDURE IF EXISTS db_upd_2092 $$
DELIMITER ; 

DELIMITER $$
DROP PROCEDURE IF EXISTS `prc_invoice` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_invoice`(IN p_id bigint(20), IN p_action varchar(50), IN p_reason text)
BEGIN

  DECLARE v_inv_id bigint(20);
  DECLARE v_inv_item_id bigint(20);
  DECLARE v_inv_pax_id bigint(20);
  DECLARE v_inv_pmnt_id bigint(20);

  INSERT INTO invoice_history (`id_hist`, `id_company`, `id_customer`, `id_acct`, `id_tour_booking`, `dt_inv`, `code`, `doc_type_cd`, `type_cd`, `attn_to`, `cn_inv_no`,
	`pmnt_type_cd`, `id_saler`, `id_tour_dep`, `dt_departure`, `id_issuer`, `id_eo_ref`, `cat_cd`, `order_cd`, `delivery_cd`, `inv_due`, `gds_booking_ref`,
	`amount`, `balance`, `reason`, `action_cd`, `status_cd`, `is_inv_paid`, `subj_line`, `remarks`, `internal_remarks`, `note_items`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `travel_warrant_chk`)
  (SELECT
	`id`, `id_company`, `id_customer`, `id_acct`, `id_tour_booking`, `dt_inv`, `code`, `doc_type_cd`, `type_cd`, `attn_to`, `cn_inv_no`, `pmnt_type_cd`, `id_saler`,
	`id_tour_dep`, `dt_departure`, `id_issuer`, `id_eo_ref`, `cat_cd`, `order_cd`, `delivery_cd`, `inv_due`, `gds_booking_ref`, `amount`, `balance`, p_reason, p_action,
	`status_cd`, `is_inv_paid`, `subj_line`, `remarks`,  `internal_remarks`, `note_items`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `travel_warrant_chk`
  FROM invoice
  WHERE id = p_id);
  SET v_inv_id := LAST_INSERT_ID();

  
  INSERT INTO invoice_item_history (`id_ref`, `id_hist`, `id_inv`, `id_acct`, `id_inv_eo_item`, `description`, `id_airline`, `net_price`, `quantity`, `unit_price`, `amount`,
	`status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `tax_code`, `tax_rate`, `tax_amount`)
  (SELECT
	v_inv_id, `id`, `id_inv`, `id_acct`, `id_inv_eo_item`, `description`, `id_airline`, `net_price`, `quantity`, `unit_price`, `amount`, `status_cd`, `dt_created`, `created_by`,
	`dt_upd`, `upd_by`, `tax_code`, `tax_rate`, `tax_amount`
  FROM invoice_item
  WHERE id_inv = p_id);
  SET v_inv_item_id := LAST_INSERT_ID();
	
  
  INSERT INTO invoice_pax_history (`id_ref`, `id_hist`, `id_inv`, `id_cust`, `room_type_cd`, `room_pairing_no`, `travel_ins_type`, `travel_ins_policy`, `ticket_no`, `special_request`,
	`lang_cd`, `act_room_type_cd`, `act_room_pairing_no`, `act_room_indicator`, `act_room_remarks`, `act_room_remarks_f`, `status_cd`)
  (SELECT
	v_inv_id, `id`, `id_inv`, `id_cust`, `room_type_cd`, `room_pairing_no`, `travel_ins_type`, `travel_ins_policy`, `ticket_no`, `special_request`, `lang_cd`, `act_room_type_cd`,
	`act_room_pairing_no`, `act_room_indicator`, `act_room_remarks`, `act_room_remarks_f`, `status_cd`
  FROM invoice_pax
  WHERE id_inv = p_id);
  SET v_inv_pax_id := LAST_INSERT_ID();

  
  INSERT INTO invoice_pmnt_history (`id_ref`, `id_hist`, `id_inv`, `id_issuer`, `id_bank`, `dt_pmnt`, `code`, `pmnt_type_cd`, `ref_no`, `received_from`, `amount`,
	`remarks`, `status_cd`, `dt_created`, `created_by`, `dt_upd`, `upd_by`, `id_inv_pmnt`, `id_cashbook`, `id_credit_note`)
  (SELECT
	v_inv_id, `id`, `id_inv`, `id_issuer`, `id_bank`, `dt_pmnt`, `code`, `pmnt_type_cd`, `ref_no`, `received_from`, `amount`, `remarks`, `status_cd`, `dt_created`,
	`created_by`, `dt_upd`, `upd_by`, `id_inv_pmnt`, `id_cashbook`, `id_credit_note`
  FROM invoice_pmnt
  WHERE id_inv = p_id);
  SET v_inv_pmnt_id := LAST_INSERT_ID();
select v_inv_id,v_inv_item_id, v_inv_pax_id, v_inv_pmnt_id, LAST_INSERT_ID();
END $$
DELIMITER ;