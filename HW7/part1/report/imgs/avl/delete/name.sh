#!/bin/bash

i=0
for f in {1..100}.dot.png; do
	if [[ -f "$f" ]]; then
		mv $f $i.png
		let "i++"
	fi
done

