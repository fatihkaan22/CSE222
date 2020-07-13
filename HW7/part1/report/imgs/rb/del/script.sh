#!/bin/bash
count=0

for f in *.dot; do
	dot $f | gvpr -c -ftree.gv | neato -n -Tpng -o $f.png
	# dot $f | gvpr -c -ftree.gv | neato -n -Tpng -o "$count.png"
	# let "count++"
done
