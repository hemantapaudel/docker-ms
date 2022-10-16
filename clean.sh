#!/bin/sh
echo "docker ps -a -q"
docker ps -a -q
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

docker images -a -q
docker rmi -f $(docker images -a -q)