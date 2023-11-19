# logtics


To start the elastic search
first add the password for ELASTIC_PASSWORD,KIBANA_PASSWORD in .env file
also add the version in .env file
then run the command

docker-compose up -d


To stop a Docker Compose setup, you can use the following command in your terminal:
docker-compose down

http://localhost:5601/

Creates 62 shards in 2 nodes(where 31 shards are primary)


Before running docker-compose down, make sure you are in the same directory as your docker-compose.yml file, as it operates on the current context.

curl -k -u elastic:subham1234 https://localhost:9200


