package com.example.VideoMicroservice_CopyAllToNew.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Artist {

    private long id;
    private String name;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist(String name, String albumIdsString) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}