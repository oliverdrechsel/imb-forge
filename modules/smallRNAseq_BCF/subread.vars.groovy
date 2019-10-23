SubreadCount_vars=[
    outdir  : RESULTS + "/subread-count",
    stranded: ESSENTIAL_STRANDED,   //whether the data is from a strand-specific assay (illumina SR: always reverse)
    ignore_duplicates : true,       //set the parm to ignore duplicates
    count_multimapping: true,       //set the parm to ignore duplicates
    genesgtf: ESSENTIAL_GENESGTF,
    threads : Integer.toString(ESSENTIAL_THREADS),
    extra   : ""                            // extra parms to sent to the tool
]
