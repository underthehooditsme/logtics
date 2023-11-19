package com.subhamthirani.logingestorqueryinterface.service;


import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LogIngestorService {

    private final RestHighLevelClient elasticsearchClient;

    @Autowired
    public LogIngestorService(RestHighLevelClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public void ingestLog(String logData) {
        try {
            IndexRequest indexRequest = new IndexRequest("logs");
            indexRequest.source(logData, XContentType.JSON);

            IndexResponse indexResponse = elasticsearchClient.index(indexRequest, RequestOptions.DEFAULT);

            String documentId = indexResponse.getId();
            System.out.println("Log ingested with documentId: " + documentId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean createIndexIfNeeded() {
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest("logs");
            boolean exists = elasticsearchClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

            if (!exists) {
                CreateIndexRequest request = new CreateIndexRequest("logs");
                request.mapping("{" +
                        "  \"properties\": {" +
                        "    \"level\": {\"type\": \"keyword\"}," +
                        "    \"message\": {\"type\": \"text\"}," +
                        "    \"resourceId\": {\"type\": \"keyword\"}," +
                        "    \"timestamp\": {\"type\": \"date\"}," +
                        "    \"traceId\": {\"type\": \"keyword\"}," +
                        "    \"spanId\": {\"type\": \"keyword\"}," +
                        "    \"commit\": {\"type\": \"keyword\"}," +
                        "    \"metadata\": {" +
                        "      \"properties\": {" +
                        "        \"parentResourceId\": {\"type\": \"keyword\"}" +
                        "      }" +
                        "    }" +
                        "  }" +
                        "}", XContentType.JSON);

                CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(request, RequestOptions.DEFAULT);

                return createIndexResponse.isAcknowledged();
            }

            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
