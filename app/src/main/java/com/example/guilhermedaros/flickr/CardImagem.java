package com.example.guilhermedaros.flickr;

import java.io.Serializable;

/**
 * Created by guilherme.daros on 06/01/2017.
 */

public class CardImagem implements Serializable{
    String title;
    String src;
    String published;
    String author;
    String tags;

    public CardImagem(String title, String src, String published, String author, String tags) {
        this.title = title;
        this.src = src;
        this.published = published;
        this.author = author;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

}

