package com.example.VideoMicroservice_CopyAllToNew.entities;

import jakarta.persistence.*;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Attributes
    private String name;

    // Constructors
    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
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
