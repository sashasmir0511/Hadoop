#!/bin/bash
if [[ $# -eq 0 ]] ; then
    echo 'You should specify output file!'
    exit 1
fi

rm -rf input
mkdir input

for i in {1..200000}
	do
		RESULT="$((RANDOM % 300)) $((RANDOM % 200)) 40028 $((RANDOM * RANDOM * RANDOM / 1000))"
		echo $RESULT >> input/$1.1
	done

for i in {1..200000}
	do
		RESULT="$((RANDOM % 300)) $((RANDOM % 200)) 40028 $((RANDOM * RANDOM * RANDOM / 1000))"
		echo $RESULT >> input/$1.2
	done

for i in {1..300000}
	do
		RESULT="$((RANDOM % 300)) $((RANDOM % 200)) 40028 $((RANDOM * RANDOM * RANDOM / 1000))"
		echo $RESULT >> input/$1.3
	done