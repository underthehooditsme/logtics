package com.subhamthirani.logingestorqueryinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.subhamthirani.logingestorqueryinterface.repository.")
@Slf4j
public class LogIngestorQueryInterfaceApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(LogIngestorQueryInterfaceApplication.class, args);
		log.info("Started the log-ingestion-system");
	}

}
 