package com.example.sberbank.models;

import com.example.sberbank.models.Channel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss")
public class Rss {

    @Element(name = "channel")
    Channel channel;

    public List<Post> getPosts() {
        return channel.items;
    }
}
