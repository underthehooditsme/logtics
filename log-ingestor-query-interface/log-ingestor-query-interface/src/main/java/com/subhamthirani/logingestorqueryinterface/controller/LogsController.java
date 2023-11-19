package com.subhamthirani.logingestorqueryinterface.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhamthirani.logingestorqueryinterface.service.LogIngestorService;

@RestController
@RequestMapping("/")
public class LogsController {

    @Autowired
    private LogIngestorService logIngestorService;

    @PostMapping
    public String ingestLog(@RequestBody String logData) {
        logIngestorService.ingestLog(logData);
        return "Log ingested successfully!";
    }
}
