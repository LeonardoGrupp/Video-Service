package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.entities.Album;
import com.example.VideoMicroservice_CopyAllToNew.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Album getAlbumByName(String name) {
        Optional<Album> optionalAlbum = albumRepository.findByNameIgnoreCase(name);

        return optionalAlbum.orElse(null);
    }

    public boolean albumExist(String name) {
        return albumRepository.existsByNameIgnoreCase(name);
    }

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }
}
