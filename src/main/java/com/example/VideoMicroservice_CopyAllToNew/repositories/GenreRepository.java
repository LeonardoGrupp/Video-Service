package com.example.VideoMicroservice_CopyAllToNew.repositories;

import com.example.VideoMicroservice_CopyAllToNew.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findGenreByGenre(String genre);
}
