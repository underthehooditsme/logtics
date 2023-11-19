package com.subhamthirani.logingestorqueryinterface.service;

//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class QueryService {
//
// private final RestHighLevelClient elasticsearchClient;
//
// @Autowired
// public QueryService(RestHighLevelClient elasticsearchClient) {
//     this.elasticsearchClient = elasticsearchClient;
// }
//
// public String searchLogs(String query) {
//     // Implement logic to search logs in Elasticsearch based on the query
//     try {
//         SearchRequest searchRequest = new SearchRequest("logs");
//         searchRequest.source().query(QueryBuilders.queryStringQuery(query));
//
//         SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
//
//         // Process search response and return results
//
//     } catch (IOException e) {
//         // Handle exception
//         e.printStackTrace();
//     }
//
//     return "Search results not available.";
// }
//}


import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class QueryService {

    private final RestHighLevelClient elasticsearchClient;

    @Autowired
    public QueryService(RestHighLevelClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public String searchLogs(String query) {
        try {
            SearchRequest searchRequest = new SearchRequest("logs");
            searchRequest.source().query(QueryBuilders.queryStringQuery(query));

            // Highlighting to get fragments containing the matched query
//            HighlightBuilder highlightBuilder = new HighlightBuilder();
//            highlightBuilder.field(new Field("message"));
//
//            searchRequest.source().highlighter(highlightBuilder);

            SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);

            return processSearchResponse(searchResponse);

        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }

        return "Search results not available.";
    }

    private String processSearchResponse(SearchResponse searchResponse) {
        StringBuilder resultBuilder = new StringBuilder();

        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] hits = searchHits.getHits();

        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            String level = sourceAsMap.get("level").toString();
            String message = sourceAsMap.get("message").toString();
            String timestamp = sourceAsMap.get("timestamp").toString();
            String resourceId = sourceAsMap.get("resourceId").toString();

            resultBuilder.append("Level: ").append(level).append("\n");
            resultBuilder.append("Message: ").append(message).append("\n");
            resultBuilder.append("Timestamp: ").append(timestamp).append("\n");
            resultBuilder.append("Resource ID: ").append(resourceId).append("\n");
            resultBuilder.append("\n");

//            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
//            HighlightField messageHighlight = highlightFields.get("message");
//            if (messageHighlight != null) {
//                Text[] fragments = messageHighlight.fragments();
//                for (Text fragment : fragments) {
//                    resultBuilder.append("Highlighted: ").append(fragment.string()).append("\n");
//                }
//                resultBuilder.append("\n");
//            }
        }

        return resultBuilder.toString();
    }
}


