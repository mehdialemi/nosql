#!/usr/bin/env bash

old_allowed_sec=$((2*31*86400))
echo "Delete invalid rows older than $old_allowed_sec seconds with allowed = true field"
java -cp target/nosql-0.1.jar ir.infra.cassandra.DeleteOldAllowed $HOSTNAME $old_allowed_sec 1
#java -cp target/nosql-0.1.jar ir.infra.cassandra.DeleteOldAllowed localhost $old_allowed_sec
