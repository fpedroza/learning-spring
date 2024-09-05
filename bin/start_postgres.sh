#!/usr/bin/env bash

# -e: This option causes the script to exit immediately if a command returns a non-zero exit status. This can help to identify errors early in the execution of the script.
# -u: This option causes the script to exit immediately if a variable is used without being assigned a value. This can help to prevent unexpected behavior due to uninitialized variables.
# -o pipefail: This option causes the script to exit immediately if any command in a pipeline returns a non-zero exit status. This can help to identify errors in complex pipelines.
set -euo pipefail
which psql > /dev/null || (echoerr "Please ensure that postgres client is in your PATH" && exit 1)

mkdir -p $HOME/docker/volumes/postgres
rm -rf $HOME/docker/volumes/postgres/data

docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql postgres
sleep 3
export PGPASSWORD=postgres
psql -U postgres -d dev -h localhost -f schema.sql
psql -U postgres -d dev -h localhost -f data.sql
