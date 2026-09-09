/* =========================================================
   JOHOR
   ========================================================= */
UPDATE corporate_address
SET state = 'Johor'
WHERE UPPER(TRIM(state)) IN (
    'JOHOR DARUL TAKZIM',
    'JOHOR DARUL TA’ZIM',
    'JOHOR.',
    'JOHOR',
    'JOHOR BAHRU',
    'JOHOR BHARU',
    'BATU PAHAT'
);

/* =========================================================
   KEDAH
   ========================================================= */
UPDATE corporate_address
SET state = 'Kedah'
WHERE UPPER(TRIM(state)) IN (
    'KEDAH',
    'KEDAH DARULAMAN',
    'KEDAH DARUL AMAN',
    'LANGKAWI',
    'LANGKAWI, KEDAH DARUL AMAN'
);

/* =========================================================
   KELANTAN
   ========================================================= */
UPDATE corporate_address
SET state = 'Kelantan'
WHERE UPPER(TRIM(state)) IN (
    'KELANTAN'
);

/* =========================================================
   MELAKA
   ========================================================= */
UPDATE corporate_address
SET state = 'Melaka'
WHERE UPPER(TRIM(state)) IN (
    'MELAKA',
    'MELAKA.',
    'MALACCA'
);

/* =========================================================
   NEGERI SEMBILAN
   ========================================================= */
UPDATE corporate_address
SET state = 'Negeri Sembilan'
WHERE UPPER(TRIM(state)) IN (
    'N.S.D.K',
    'SEREMBAN',
    'NEGERI SEMBILAN.',
    'NEGERI SEMBILAN DARUL KHUSUS',
    'NSPK',
    'N. SEMBILAN',
	'NSDK',
    'NEGeri SEREMBAN',
    '71000'
);

/* =========================================================
   PAHANG
   ========================================================= */
UPDATE corporate_address
SET state = 'Pahang'
WHERE UPPER(TRIM(state)) IN (
    'KUANTAN',
    'PAHANG DARUL MAKMUR'
);

/* =========================================================
   PENANG
   ========================================================= */
UPDATE corporate_address
SET state = 'Penang'
WHERE UPPER(TRIM(state)) IN (
    'PULAU PINANG',
    'PENANG.',
    'BUTTERWORTH'
);

/* =========================================================
   PERAK
   ========================================================= */
UPDATE corporate_address
SET state = 'Perak'
WHERE UPPER(TRIM(state)) IN (
    'IPOH',
    'PERAK',
    'PERAK.',
    'PERAK DARUL RIDZUAN'
);

/* =========================================================
   PERLIS
   ========================================================= */
UPDATE corporate_address
SET state = 'Perlis'
WHERE UPPER(TRIM(state)) IN (
    'PERLIS'
);

/* =========================================================
   SABAH
   ========================================================= */
UPDATE corporate_address
SET state = 'Sabah'
WHERE UPPER(TRIM(state)) IN (
    'SABAH',
    'KOTA KINABALU'
);

/* =========================================================
   SARAWAK
   ========================================================= */
UPDATE corporate_address
SET state = 'Sarawak'
WHERE UPPER(TRIM(state)) IN (
    'KUCHING',
    'BINTULU',
    'SAWAK',
    'SARAWAK.',
    'SIBU',
    'SAWARAK'
);

/* =========================================================
   SELANGOR
   ========================================================= */
UPDATE corporate_address
SET state = 'Selangor'
WHERE UPPER(TRIM(state)) IN (
    'SELANGOT DARUL EHSAN',
    'SELANGOR DARUL EHSAN,',
    'SELANGOR DARUL ESHAN',
    'SELANGROR',
    'SELANGOR.',
    'SELANGOR D.E',
    'KAJANG',
    'BELAKONG',
    'SEL',
    'SELANGOR',
    'SEPANG, SELANGOR',
    'SELANJOR',
    'RAWANG',
    'SELANGOR DARUL ENSAN',
    'SLGR',
    'PJ',
    'SLELANGOR',
    '47400',
	'S.D.EHSAN',
	'SELANGON',
    'PETALING JAYA',
    'KUALA LANGAT',
    'KLANG',
    'PUCHONG',
    'CHERAS SELANGOR',
    'BALAKONG',
    'SHAH ALAM',
    'SEPANG',
    'SELNAGOR',
    'SELANGOR DARUL EHSAN.',
    '40150',
    'SUBANNG JAYA',
    'SELANGOR D.E.',
    'SELANGOR DARUL EHSAN',
    'DARUL EHSAN',
    'SUBANG JAYA',
    'SELANGOT',
    'SELANGLOR',
    'SEALNGOR',
    'SELABGOR DARUL EHSAN'
);

/* =========================================================
   TERENGGANU
   ========================================================= */
UPDATE corporate_address
SET state = 'Terengganu'
WHERE UPPER(TRIM(state)) IN (
    'TERENGGANU',
    'KUALA TERENGGANU'
);

/* =========================================================
   W.P. KUALA LUMPUR
   ========================================================= */
UPDATE corporate_address
SET state = 'W.P. Kuala Lumpur'
WHERE UPPER(TRIM(state)) IN (
    'KL',
    'K.L.',
    'KUALA LUMPUR,',
    'KUALA LUMPEUR',
    '56100',
    'WILAYAH PERSEKUTUAN KUALA LUMPUR',
    'W.P. KUALA LUMPUR',
    'KUALA LUMOUR',
    'K.L',
    'WPKL',
    'KUL',
    'KUALA LUMPUR.',
    'WP KUALA LUMPUR',
    'W. PERSEKUTUAN, KUALA LUMPUR',
    'W.PERSEKUTUAN (KL)',
    'SRI PETALING',
    '58200',
    'KAULA LUMPUR',
    '50200',
    'KUALA',
    '50450',
    'KUALA LUMPUJR',
    'KUALA LUMPUR',
    'KUALA LUMPUR',
    'W.P KUALA LUMPUR'
);

/* =========================================================
   W.P. LABUAN
   ========================================================= */
UPDATE corporate_address
SET state = 'W.P. Labuan'
WHERE UPPER(TRIM(state)) IN (
    'W.P. LABUAN',
    'LABUAN'
);

/* =========================================================
   W.P. PUTRAJAYA
   ========================================================= */
UPDATE corporate_address
SET state = 'W.P. Putrajaya'
WHERE UPPER(TRIM(state)) IN (
	'PUTRAJAYA',
    'W.P. PUTRAJAYA'
);
