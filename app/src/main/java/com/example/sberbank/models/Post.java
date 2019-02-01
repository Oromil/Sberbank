package com.example.sberbank.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Post {
    @Element(name = "title", required = false)
    String title;
    @Element(name = "description", required = false)
    String description;
    @Element(name = "pubDate", required = false)
    String date;
    @Element(name = "creator", required = false)
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
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = new Date(date);
        return df.format(date1);
    }

    public String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date date1 = new Date(date);
        return df.format(date1);
    }

    public List<String> getTags() {
        return tags;
    }

    public String getTagsAsString() {
        String tagsString = "";
        for (int i = 0; i < tags.size(); i++) {
            tagsString = tagsString.concat(tags.get(i));
            if (i != tags.size() - 1) {
                tagsString = tagsString.concat(", ");
            }
        }
        return tagsString;
    }

    public String getLink() {
        return link;
    }

    public String getCreator() {
        return creator;
    }
}
