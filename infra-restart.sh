#!/bin/sh

echo 'Tearing down Kafka...'
docker-compose -f ./kafka/docker-compose.yml down

echo 'Tearing down Mongo DB...'
docker-compose -f ./mongodb/docker-compose.yml down

echo 'Setting up Kafka...'
docker-compose -f ./kafka/docker-compose.yml up -d

echo 'Setting up Mongo DB...'
docker-compose -f ./mongodb/docker-compose.yml up -d

