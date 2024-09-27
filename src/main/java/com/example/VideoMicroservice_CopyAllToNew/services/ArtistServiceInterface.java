package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.entities.Artist;
import java.util.List;

public interface ArtistServiceInterface {

    List<Artist> getAllArtists();
    Artist getArtistByName(String name);
    boolean artistExists(String name);
    Artist createArtist(Artist artist);
}
