package com.subhamthirani.logingestorqueryinterface.controller;

import com.subhamthirani.logingestorqueryinterface.service.LogIngestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    private final LogIngestorService logIngestorService;

    @Autowired
    public IndexController(LogIngestorService logIngestorService) {
        this.logIngestorService = logIngestorService;
    }

    @GetMapping("/create")
    public ResponseEntity<String> createIndex() {
        boolean created = logIngestorService.createIndexIfNeeded();
        if (created) {
            return ResponseEntity.ok("Index created successfully");
        } else {
            return ResponseEntity.ok("Index already exists");
        }
    }
}
