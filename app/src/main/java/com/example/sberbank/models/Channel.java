package com.example.sberbank.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel")
class Channel {
    @ElementList(entry = "item", inline = true)
    List<Post> items;
}
