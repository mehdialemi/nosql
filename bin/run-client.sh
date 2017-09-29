#!/usr/bin/env bash

java -cp ./nosql-0.1.jar ir.infra.clients.RandomEmsInfoWriter 10.255.51.30 cassandra/add/emsInfo2 100000 60
