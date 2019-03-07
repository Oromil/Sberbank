package com.example.sberbank.models;

import org.simpleframework.xml.Element;

public class Post {
    @Element(name = "title", required = false)
    String title;
    @Element(name = "description", required = false)
    String description;
    @Element(name = "pubDate", required = false)
    String date;
    @Element(name = "dc:creator", required = false)
    String creator;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
