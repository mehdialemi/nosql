#!/usr/bin/env bash

java -cp target/nosql-0.1.jar ir.infra.clients.RandomEmsInfoWriter localhost cassandra/add/emsInfo 10 5 3600
