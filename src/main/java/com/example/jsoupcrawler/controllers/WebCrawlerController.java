package com.example.jsoupcrawler.controllers;

import com.example.jsoupcrawler.crawler.WebCrawler;
import com.example.jsoupcrawler.services.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crawl")
public class WebCrawlerController {
    @Autowired
    private WebCrawlerService webCrawlerService;

    @GetMapping
    public String startCrawling(){
        WebCrawler webCrawler = new WebCrawler(webCrawlerService);
        webCrawler.getPageLinks("https://www.allrecipes.com/");
        return "Crawling started. Check console for progress.";
    }
}
