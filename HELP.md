




docker network create my-network-test --driver bridge

docker inspect my-network-test

docker run -d -p 8090:8080 --name server --network my-network-test hemantapaudel/docker-ms:1.13

docker run -d -p 8091:8080 --name client --network my-network-test hemantapaudel/docker-ms:1.13

docker ps

docker exec -it <id>  sh

apk add curl

apk add curl

curl http://server:8080
