# Logtics(Log Ingestor and Query Interface)

## Overview

This project is a log ingestor system built with Spring Boot and Elasticsearch for efficiently handling vast volumes of log data. It provides a simple query interface for searching logs using full-text search or specific field filters.

## Running the Project

### Prerequisites

- Docker
- Java 16 or later

### Steps

1.Clone the repository
   
git clone https://github.com/yourusername/log-ingestor.git

2.To start the elastic search
run the command inside the folder containing docker-compose.yml

docker-compose up -d

To stop a Docker Compose setup, you can use the following command in your terminal:
docker-compose down

to access the kibana use the url
http://localhost:5601/

Wait for some time let the elasticsearch start,it takes some time, then only build the jar

3.To build the JAR and run the Spring Boot application, you can use the following commands. Open a terminal or command prompt in the directory where your build.gradle file is located, and then run the following commands:
(Remember to replace ./gradlew with gradlew.bat on Windows systems.)

a. Build the Jar

   ./gradlew build

b. Run the application

   ./gradlew bootRun

c. To run the JAR file separately (for example, on a server), you can use the following command:

    java -jar build/libs/your-project-name-0.0.1-SNAPSHOT.jar




4.Access the API:

The Spring Boot application's API is accessible at http://localhost:3000
Use the API endpoints for log ingestion and searching as per the defined specifications.


###API Endpoints

1) localhost:3000/index/create
    handled by Index Controller
    to create the index in elasticSearch if not present

2) localhost:3000/
    handles by Logscontroller
    to index the document in this case log

3) localhost:3000/search
    handled by SearchController
    to serve the search page

4) localhost:3000/results
    handled by SearchController
    to get the result

###System Design

logs--->SpringBootApplication---->ElasticSearch

Query------>|SpringBootApplication|---->ElasticSearch
results<----|                     |


Issues:
1. Bottleneck if write speed is less compare to read speed,exception can occur

    Solution:We can use Kafka to solve the issue,its batch processiong capability can also help cut down write operations

2.Currently the build is failing because of gradle jdk 17 version issues

3.UI part should be separate with the ingestion part,separting out the concern(Microservices),thus not putting load on the ingestion machine


