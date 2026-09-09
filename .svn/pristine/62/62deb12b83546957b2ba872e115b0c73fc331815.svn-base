/**
 * 2026-08-27
 * Kent
 * When dump db from production need to run this script
 */

/* UAT */
UPDATE company set myinvois_client_id = '', myinvois_client_secret_1 = '', myinvois_client_secret_2 = '';
UPDATE company set myinvois_client_id = 'db1c77c1-360c-4a26-94b3-639c5ca60f15',
myinvois_client_secret_1 = '4bf2db75-ea29-4121-afb9-df2d908f8985',
myinvois_client_secret_2 = '0a1a687b-0b8e-43f5-92ca-5c17eb322a7b'
WHERE id = 1;
/* LOCAL */
UPDATE company set myinvois_client_id = '', myinvois_client_secret_1 = '', myinvois_client_secret_2 = '';

/* For All*/
UPDATE app_setting SET value = 'N' WHERE module = 'MAINT' AND code IN ('AUTO_SUBMIT_E_INV_SCHEDULER', 'AUTO_DAILY_REFRESH_E_INV_SCHEDULER', 'AUTO_REFRESH_E_INV_SUBMISSION_SCHEDULER');
UPDATE app_setting SET value = 'N' WHERE  module = 'MAINT' AND code = 'REVERSE_SALES_JOURNAL_SCHEDULER';
UPDATE app_setting SET value = 'Y' WHERE  module = 'MAINT' AND code = 'POS_CONFIG_UPLOAD_AS_TEST';
UPDATE app_setting SET value = 'N' WHERE  module = 'MAINT' AND code = 'FEEDBACK_EMAIL_SCHEDULER';