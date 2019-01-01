package com.example.mahmoudbahaa.etilaaftask.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MahmoudBahaa on 30/12/2018.
 */

public class Story implements Serializable {

    private int id;
    private String title;
    private String url;
    private String urlToImage;
    private Date publishedAt;
    private String content;


    public Story() {
    }

    public Story(int id, String title, String url, String urlToImage, Date publishedAt, String content) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date createdAt) {
        this.publishedAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
