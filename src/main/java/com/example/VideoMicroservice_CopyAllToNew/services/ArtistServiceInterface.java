package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.entities.Artist;

import java.util.List;

public interface ArtistServiceInterface {
    Artist createArtist(Artist artist);
    Artist getArtistById(Long id);
    List<Artist> getAllArtists();
    Artist updateArtist(Long id, Artist artist);
    public boolean validateAlbumIds(List<Long> albumIds);
    Boolean checkIfArtistExistByName(String artistName);
    void deleteArtist(Long id);
}
