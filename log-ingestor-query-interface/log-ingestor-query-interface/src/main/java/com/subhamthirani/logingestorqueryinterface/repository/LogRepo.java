package com.subhamthirani.logingestorqueryinterface.repository;

import com.subhamthirani.logingestorqueryinterface.entity.Log;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepo extends ElasticsearchRepository<Log, String> {
    // Custom query methods if needed
}






