package com.oromil.sberbank.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Post {
    @Element(name = "title", required = false)
    private String title;
    @Element(name = "description", required = false)
    private String description;
    @Element(name = "pubDate", required = false)
    private String date;
    @Element(name = "creator", required = false)
    private String creator;

    @ElementList(entry = "category", inline = true)
    private List<String> tags;

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

    public String getCreator() {
        return creator;
    }
}
