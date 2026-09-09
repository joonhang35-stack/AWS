/** Reset AWS on latest version 3.0.383
* To prevent foreign key check running script
**/
SET FOREIGN_KEY_CHECKS = 0;

/** Company Data **/
delete from employee where id>4;
ALTER TABLE employee AUTO_INCREMENT = 5;
update company set name='', code='', reg_no='', id_acct_sales=null, id_acct_purchase_trade=null, id_acct_purchase_sundry=null,
id_acct_gst=null, id_acct_non_claimable_gst=null, id_acct_rounding=null, myinvois_client_id=null, myinvois_client_secret_1=null,
myinvois_client_secret_2=null;
update company_address set addr_1='', addr_2='';
update company_name set name='', short_name='';
truncate company_contact;
truncate company_tax_log;

DELETE FROM company WHERE id>1;
ALTER TABLE company AUTO_INCREMENT = 2;
DELETE FROM company_address WHERE id_company>1;
DELETE FROM company_contact WHERE id_company>1;
DELETE FROM company_name WHERE id_company>1;
DELETE FROM company_tax WHERE id_company>1;
DELETE FROM customer WHERE id_company>1;
DELETE FROM employee WHERE id_company>1;

delete from sec_user where id>4;
ALTER TABLE sec_user AUTO_INCREMENT = 5;
DELETE FROM sec_user_role where LENGTH(U_USER)>15;

DELETE FROM online_booking_config WHERE id > 3;
ALTER TABLE online_booking_config AUTO_INCREMENT = 4;
UPDATE online_booking_config set value='' WHERE code='customer_id';
UPDATE online_booking_config set value='' WHERE code='sales_id';

DELETE FROM sys_num_conf WHERE id_company > 1;
UPDATE sys_num_conf SET next_no = 1 WHERE id_company = 1;

truncate inv_pmnt_cashbook_link;
truncate listing_table_view;
truncate listing_table_view_columns;
truncate ticketing;
truncate tmp_msg;
truncate tour_booking_charge_item_history;
truncate tour_image;
truncate tour_itinery;
truncate app_setting_log;
truncate audit_summary;

/** Customer Details **/
truncate customer;
truncate customer_history;
truncate online_booking_customer;
truncate customer_remarks;
truncate customer_profile_update;

truncate person;
truncate person_history;
truncate person_attachment;
truncate person_address;
truncate person_address_history;
truncate person_class;
truncate person_complication;
truncate person_contact;
truncate person_contact_history;
truncate person_email;
truncate person_email_history;
truncate person_identity;
truncate person_identity_history;
truncate person_identity_detail;
truncate person_identity_detail_history;
truncate person_lang;
truncate person_lang_history;
truncate person_meal;
truncate person_meal_history;

truncate corporate;
truncate corporate_address;
truncate corporate_address_history;
truncate corporate_contact;
truncate corporate_contact_history;
truncate corporate_pic;

/**  Invoice Details **/
truncate invoice;
truncate invoice_attachment;
truncate invoice_email_payment;
truncate invoice_eo_link;
truncate invoice_history;
truncate invoice_item;
truncate invoice_item_history;
truncate invoice_pax;
truncate invoice_pax_history;
truncate invoice_pmnt;
truncate invoice_pmnt_attachment;
truncate invoice_pmnt_history;
truncate invoice_reference;
truncate invoice_pax_ref_no;
truncate inv_eo_item;

# Supplier
truncate supplier;
truncate supplier_remarks;
truncate supplier_pic;

/**  Exchange Order **/
truncate ex_order;
truncate ex_order_log;
truncate ex_order_airline;
truncate ex_order_attachment;
truncate ex_order_bill;
truncate ex_order_bill_log;
truncate ex_order_bill_pmnt;
truncate ex_order_hotel;
truncate ex_order_inv;
truncate ex_order_item;
truncate ex_order_pax;

/**  Booking Details **/
truncate tour_booking;
truncate tour_booking_history;
truncate tour_booking_charge_item;
truncate tour_booking_charge_item_history;
truncate tour_booking_item;
truncate tour_booking_pax;
truncate tour_booking_sales_comm;
truncate tour_booking_sales_comm_history;
truncate tour_booking_survey;
truncate tour_booking_survey_history;

/**  Tour Packages **/
truncate tour_pkg;
truncate tour_pkg_attribute;
truncate tour_pkg_attribute_log;
truncate tour_pkg_commision;
truncate tour_pkg_country;
truncate tour_pkg_daily_itinerary;
truncate tour_pkg_daily_itinerary_history;
truncate tour_pkg_daily_itinerary_item;
truncate tour_pkg_daily_itinerary_item_history;
truncate tour_pkg_history;
truncate tour_pkg_itinery;
truncate tour_pkg_tag;

/**  Tour Theme **/
truncate tour_theme;
truncate tour_theme_country;

/**  Tour Departure **/
truncate tour_dep;
/** truncate tour_dep_account_code_config; **/
truncate tour_dep_attribute;
truncate tour_dep_discount;
truncate tour_dep_history;
truncate tour_dep_item;
truncate tour_dep_remarks;
truncate tour_dep_season;

/**  Tour Category **/
truncate tour_cat;

/**  Rooming List **/
truncate rooming_list;
truncate rooming_list_land_operator;
truncate rooming_list_land_operator_cont;

/** Airlines **/
truncate airline_schedule;
truncate airline_schedule_charge;
truncate airline_schedule_item;
truncate airline_schedule_item_charge;

/** Accounting Tables **/
truncate account;
truncate account_bal;
/** account_cat; **/
truncate account_depr;
truncate account_sub_cat;
truncate account_trans;
truncate account_trans_purc_bill_log;
truncate bank;
truncate bank_address;
truncate bank_contact;
truncate bank_recon;
truncate cash_book;
truncate cash_book_eo_bill;
truncate journal;

truncate financial_period;
truncate financial_period_lock;
truncate gst_summary;
truncate gst_summary_log;
truncate gst_summary_tax;
truncate gst_summary_tax_log;

truncate hotel;
truncate hotel_address;
truncate hotel_charges;
truncate hotel_contact;
truncate hotel_item;
truncate hotel_item_charge;
truncate hotel_reservation;
truncate hotel_room;

truncate ipay_config;

truncate online_booking_customer;
truncate online_booking_passenger;
truncate online_booking_payment;
truncate pos_upload_sales;
truncate pos_upload_sales_log;
truncate pos_upload_sales_trans;
truncate pos_upload_sales_trans_log;

truncate sales_comm_config;
truncate sales_comm_config_detail;
truncate sales_comm_config_detail_history;
truncate sales_comm_config_history;

truncate upd_acct_trans_dest_cust_tracking;
truncate visible_listing_config;

truncate e_invoice_document;
truncate e_invoice_consolidate;
truncate e_invoice_submission;
truncate e_invoice_consolidate_num_conf;
truncate e_invoice_access_token;

truncate tour_cruise_cabin;
truncate tour_cruise_cabin_discount;
truncate cruise;
truncate cruise_cabin;
truncate cruise_schedule;
truncate cruise_schedule_charge;
truncate cruise_schedule_item;
truncate cruise_schedule_item_charge;

SET FOREIGN_KEY_CHECKS = 1;


/* Checking all table row count */
SELECT
  TABLE_NAME, SUM(TABLE_ROWS)
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'ubingodb'
GROUP BY TABLE_NAME;



















