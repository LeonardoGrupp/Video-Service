package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.entities.Album;
import java.util.List;

public interface AlbumServiceInterface {

    List<Album> getAllAlbums();
    Album getAlbumByName(String name);
    boolean albumExists(String name);
    Album createAlbum(Album album);
}
