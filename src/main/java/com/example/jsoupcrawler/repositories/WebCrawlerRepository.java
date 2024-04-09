package com.example.jsoupcrawler.repositories;

import com.example.jsoupcrawler.models.WebCrawlerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebCrawlerRepository extends JpaRepository<WebCrawlerModel, Long> {
    boolean existsByUrl(String url);
}
