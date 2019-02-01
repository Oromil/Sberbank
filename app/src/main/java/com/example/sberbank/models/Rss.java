package com.example.sberbank.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss")
public class Rss {

    @Element(name = "channel")
    private Channel channel;

    public List<Post> getPosts() {
        return channel.items;
    }
}
