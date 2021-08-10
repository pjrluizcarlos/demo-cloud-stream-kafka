#!/bin/sh

echo 'Setting up Kafka...'
docker-compose -f ./kafka/docker-compose.yml up -d

echo 'Setting up Mongo DB...'
docker-compose -f ./mongodb/docker-compose.yml up -d

