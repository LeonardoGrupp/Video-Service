package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.entities.Album;
import com.example.VideoMicroservice_CopyAllToNew.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements AlbumServiceInterface {

    private final AlbumRepository albumRepository;
    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    // READ - Get All Albums
    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    // READ - Get An Album By Name
    @Override
    public Album getAlbumByName(String name) {
        Optional<Album> optionalAlbum = albumRepository.findByNameIgnoreCase(name);
        return optionalAlbum.orElse(null);
    }

    // READ - Check If Album Exits
    @Override
    public boolean albumExists(String name) {
        return albumRepository.existsByNameIgnoreCase(name);
    }

    // CREATE - Create A New Album
    @Override
    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }
}
