# Logtics(Log Ingestor and Query Interface)

## Overview

This project is a log ingestor system built with Spring Boot and Elasticsearch for efficiently handling vast volumes of log data. It provides a simple query interface for searching logs using full-text search or specific field filters.

## Running the Project

### Prerequisites

- Docker
- Java 16 or later

### Steps

1.To start the elastic search
run the command inside the folder containing docker-compose.yml

docker-compose up -d


2. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/log-ingestor.git



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

docker run -p 9200:9200 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.10.0


##
for windows first download the curl and then set the path
curl.exe -k -u elastic:password https://localhost:9200/_aliases



https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/getting-started-java.html