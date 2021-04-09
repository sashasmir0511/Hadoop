#!/bin/bash
if [[ $# -eq 0 ]] ; then
    echo 'You should specify output file!'
    exit 1
fi

rm -rf input
mkdir input

for i in {1..50}
	do
		RESULT="$((RANDOM % 300)) $((RANDOM % 200)) 40028 $((RANDOM * RANDOM * RANDOM / 1000))"
		echo $RESULT >> input/$1.1
	done
