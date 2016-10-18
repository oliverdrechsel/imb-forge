//rule for task bam2bw from catalog NGS, version 1
//desc: Create scaled bigwig tracks from a bam file
bam2bw = {
	doc title: "bam2bw",
		desc:  "Convert BAM file to bigWig",
		constraints: "none.",
		bpipe_version: "tested with bpipe 0.9.8.7",
		author: "Sergi Sayols"

	output.dir=TRACKS
	
	transform(".bam") to ("_scaled.bw") {
		exec """
			export TOOL_DEPENDENCIES=$TOOL_DEPENDENCIES &&
			source ${TOOL_BEDTOOLS}/env.sh &&
			source ${TOOL_SAMTOOLS}/env.sh &&
			source ${TOOL_KENTUTILS}/env.sh &&

			if [ -n "\$LSB_JOBID" ]; then
				export TMPDIR=/jobdir/\${LSB_JOBID};
            		else
                		export TMPDIR=$TMP;
			fi &&
			
			if [ ! -d ${TMPDIR} ]; then
				mkdir -p ${TMPDIR};
			fi &&
			
			echo 'VERSION INFO'  1>&2 &&
                        echo \$(genomeCoverageBed -h 2>&1 | grep 'Version') 1>&2
                        bedGraphToBigWig    1>&2 &&
                        echo '/VERSION INFO' 1>&2 &&

			CHRSIZES=${TMP}/\$(basename ${input.prefix}).bam2bw.chrsizes &&
			samtools idxstats ${input} | cut -f1-2 > \${CHRSIZES} &&
			TOTAL_MAPPED=\$( samtools flagstat $input | head -n1| cut -f1 -d" ") &&
			SCALE=\$(echo "1000000/\$TOTAL_MAPPED" | bc -l) &&
			genomeCoverageBed -bg -split -scale \${SCALE} -ibam ${input} -g \${CHRSIZES} > ${output.prefix}.bedgraph &&
			bedGraphToBigWig ${output.prefix}.bedgraph \${CHRSIZES} $output &&
			rm \${CHRSIZES} ${output.prefix}.bedgraph
		""","bam2bw"
	}
}

