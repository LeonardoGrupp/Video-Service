package com.example.VideoMicroservice_CopyAllToNew.repositories;

import com.example.VideoMicroservice_CopyAllToNew.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
        Optional<Album> findByNameIgnoreCase(String name);
        boolean existsByNameIgnoreCase(String name);
}
