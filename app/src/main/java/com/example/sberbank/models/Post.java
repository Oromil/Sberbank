package com.example.sberbank.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class Post {
    @Element(name = "title", required = false)
    String title;
    @Element(name = "description", required = false)
    String description;
    @Element(name = "pubDate", required = false)
    String date;
    @Element(name = "dc:creator", required = false)
    String creator;
    @Element(name = "guid")
    String link;

    @ElementList(entry = "category", inline = true)
    List<String>tags;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getLink() {
        return link;
    }

    public String getCreator() {
        return creator;
    }
}
