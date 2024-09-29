package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.entities.Artist;
import com.example.VideoMicroservice_CopyAllToNew.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService implements ArtistServiceInterface {

    private final ArtistRepository artistRepository;
    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // READ - Get All Artists
    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    // READ - Get Artist By Name
    @Override
    public Artist getArtistByName(String name) {
        Optional<Artist> optionalArtist = artistRepository.findByNameIgnoreCase(name);
        return optionalArtist.orElse(null);
    }

    // READ - Check If Artist Exits
    @Override
    public boolean artistExists(String name) {
        return artistRepository.existsByNameIgnoreCase(name);
    }

    // CREATE - Create A New Artist
    @Override
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }
}
