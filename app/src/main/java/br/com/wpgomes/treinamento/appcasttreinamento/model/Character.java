package br.com.wpgomes.treinamento.appcasttreinamento.model;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Character implements Serializable {

    @SerializedName("id")
    public String id;
    public String name;
    public String description;
    public String modified;
    public String resourceUri;
    public List<MarvelUrl> urls;
    public MarvelImage thumbnail;

    public String thumbnailUrl;
//    public MarvelResources<ComicResourceDto> comics;
//    public MarvelResources<StoryResourceDto> stories;
//    public MarvelResources<EventResourceDto> events;
//    public MarvelResources<SerieResourceDto> series;


    @Override
    public String toString() {
        return "Character{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", modified='" + modified + '\'' +
                ", resourceUri='" + resourceUri + '\'' +
                ", urls=" + urls +
                ", thumbnail=" + thumbnail +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
