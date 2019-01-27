package com.example.sberbank.models;

import com.example.sberbank.models.Channel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss")
public class Rss{

    @Element(name = "channel")
    public Channel channel;
}
