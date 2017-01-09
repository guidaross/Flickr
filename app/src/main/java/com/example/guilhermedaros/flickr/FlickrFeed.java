package com.example.guilhermedaros.flickr;

import java.io.Serializable;
import java.util.List;

public class FlickrFeed implements Serializable {
    String title;
    String link;
    String description;
    String modified;
    String generator;
    List<ItemFlickrFeed> items;

    public FlickrFeed() {
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public List<ItemFlickrFeed> getItems() {
        return items;
    }

    public void setItems(List<ItemFlickrFeed> items) {
        this.items = items;
    }
}