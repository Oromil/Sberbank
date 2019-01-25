package com.example.sberbank.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel")
public class Channel {
    @Element(name = "title", required = false)
    public String title;

    @Element(name = "link", required = false)
    public String link;
    @Element(name = "language", required = false)
    public String language;
    @Element(name = "pubDate", required = false)
    public String date;

    @ElementList(entry = "item", inline = true)
    public List<Post> items;
}
