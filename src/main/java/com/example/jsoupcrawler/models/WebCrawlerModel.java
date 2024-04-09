package com.example.jsoupcrawler.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class WebCrawlerModel {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String url;


    @Getter
    private String content;

    @Getter
    private String metaData;

    @Getter
    private Integer statusCode;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }
}
