#!/usr/bin/env bash

docker-compose down
docker volume rm docker_spring-sandbox-jpa-mysql-dev
docker-compose up
