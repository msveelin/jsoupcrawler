package com.example.jsoupcrawler.crawler;

import com.example.jsoupcrawler.models.WebCrawlerModel;
import com.example.jsoupcrawler.services.WebCrawlerService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class WebCrawler {

    @Autowired
    private WebCrawlerService webCrawlerService;

    private HashSet<String> urlLink;

    public WebCrawler(WebCrawlerService webCrawlerService) {
        this.webCrawlerService = webCrawlerService;
        urlLink = new HashSet<String>();
    }

    public void getPageLinks(String URL) {
        if (!urlLink.contains(URL)) {
            try {
                urlLink.add(URL);
                WebCrawlerModel webCrawlerModel = new WebCrawlerModel();
                webCrawlerModel.setUrl(URL);

                // Connect to the URL and get the response
                Connection.Response response = Jsoup.connect(URL).execute();

                // Extract the status code
                int statusCode = response.statusCode();
                webCrawlerModel.setStatusCode(statusCode);
                System.out.println("Status Code: " + statusCode);

                // Extract html from the link using Jsoup connect
                Document doc = Jsoup.connect(URL).get();
                // Extracting text
                webCrawlerModel.setContent(doc.text());

                // Extracting metadata
                Element head = doc.head();
                Elements metaElements = head.select("meta");
                webCrawlerModel.setMetaData(metaElements.attr("content"));

                webCrawlerService.saveUrl(webCrawlerModel);

                System.out.println(URL + ": with status " + statusCode + "was added successfully");

                Elements availableLinksOnPage = doc.select("a[href]");
                for (Element el : availableLinksOnPage) {
                    String extractedURL = el.attr("abs:href");
                    // Check if the extracted link is not already in the database
                    if (!webCrawlerService.existsByUrl(extractedURL)) {
                        getPageLinks(extractedURL);
                    } else {
//                        getPageLinks(extractedURL);
                        System.out.println(extractedURL + ": already exists in the database");
                    }
                }
                /// Check if the URL is already in the database
//                if (!webCrawlerService.existsByUrl(URL)) {
//
//                } else {
//                    System.out.println(URL + ": already exists in the database");
//                }

            } catch (Exception e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
}
