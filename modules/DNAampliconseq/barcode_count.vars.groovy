barcode_count_vars=[
    outdir: RESULTS + "/barcode_count",
    logdir: LOGS + "/barcode_count",
    verbose : true,
    method : "directional", // (default "directional") UMI grouping method, one of directional, unique, percentile, cluster or adjacency.
    edit_distance_threshold : 1, // (default 1) Edit distance threshold at which to join two UMIs when grouping UMIs.
    spliced_is_unique : false, // (default false) Treat a spliced read as different to an unspliced one
    barcode_separator : "_", // (default "_") barcode-separator between readname and barcodes
    param : "--per-cell",
    // For per-cell counting with umi_tools count_tab, the input txt-file must have 2 columns (tab separated) in the following format: read_id[SEP]UMI[SEP]CB gene.
    // However, umi_tools extract creates readnames (1st column) of the format read_id_CB_UMI. Therefore, UMI and CB have to be switched before counting.
    // This is done in barcode_count.module.groovy before calling umi_tools count_tab. The sample name is used in place of a gene name.
    extra : ""
]
