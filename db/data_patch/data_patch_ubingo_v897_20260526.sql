UPDATE corporate_address
SET state = CASE

    /* =====================================================
       KUALA LUMPUR
       ===================================================== */
    WHEN UPPER(TRIM(state)) IN (
        'WILAYAH PERSEKUTUAN KUALA LUMPUR',
        'KUALA LUMPUR'
    ) THEN 'W.P. Kuala Lumpur'

    /* =====================================================
       SELANGOR
       ===================================================== */
    WHEN UPPER(TRIM(state)) IN (
        'PUCHONG',
        'KELANA JAYA',
        'PETALING JAYA'
    ) THEN 'Selangor'

    ELSE state

END