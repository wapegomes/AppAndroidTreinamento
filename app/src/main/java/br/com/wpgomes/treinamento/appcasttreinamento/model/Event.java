package br.com.wpgomes.treinamento.appcasttreinamento.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wgomes on 17/06/16.
 */

public class Event implements Serializable {

    public String id;
    public String title;
    public String description;
    public String resourceUri;
    public List<MarvelUrl> urls;
    public MarvelImage thumbnail;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public List<MarvelUrl> getUrls() {
        return urls;
    }

    public void setUrls(List<MarvelUrl> urls) {
        this.urls = urls;
    }

    public MarvelImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MarvelImage thumbnail) {
        this.thumbnail = thumbnail;
    }
}
