#!/bin/bash

#dts=`expr $(($(date +%s%N)/1000000)) - 15552000000`
dts=$(($(date +%s%N)/1000000))
echo "DELETE FROM traffic.emsinfo USING TIMESTAMP $dts WHERE emsinfoid IN () ; exit" | cqlsh

