package com.subhamthirani.logingestorqueryinterface.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.subhamthirani.logingestorqueryinterface.service.QueryService;

@Controller
@RequestMapping("/search")
public class SearchController {

 @Autowired
 private QueryService queryService;

 @GetMapping
 public String showSearchPage() {
     return "search";
 }

 @GetMapping("/results")
 public String searchLogs(@RequestParam String query, Model model) {
     String searchResults = queryService.searchLogs(query);
     model.addAttribute("searchResults", searchResults);
     return "search";
 }
 
 
}

