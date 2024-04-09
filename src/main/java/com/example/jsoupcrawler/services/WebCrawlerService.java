package com.example.jsoupcrawler.services;

import com.example.jsoupcrawler.models.WebCrawlerModel;
import com.example.jsoupcrawler.repositories.WebCrawlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebCrawlerService {

    @Autowired
    private WebCrawlerRepository webCrawlerRepository;

    public boolean existsByUrl(String url) {
        // Check if a record with the given URL exists in the database
        return webCrawlerRepository.existsByUrl(url);
    }

    public WebCrawlerModel saveUrl(WebCrawlerModel webCrawlerModel){
        return webCrawlerRepository.save(webCrawlerModel);
    }
}
