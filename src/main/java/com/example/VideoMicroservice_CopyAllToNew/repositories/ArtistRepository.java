package com.example.VideoMicroservice_CopyAllToNew.repositories;

import com.example.VideoMicroservice_CopyAllToNew.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
