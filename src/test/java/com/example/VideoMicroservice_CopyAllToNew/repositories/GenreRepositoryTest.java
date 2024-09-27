package com.example.VideoMicroservice_CopyAllToNew.repositories;

import com.example.VideoMicroservice_CopyAllToNew.entities.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")                                                             // kör konfigurationen från application-test.properties
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)       // Gör en rollback efter varje test så att inget förändras i H2-databasen
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        genreRepository.save(new Genre("Rock"));     // Alt 1
        Genre genre = new Genre("Pop");              // Alt 2
        genreRepository.save(genre);
    }

    @Test
    void findGenreByGenreShouldReturnTrueWhenPopExits() {
        Genre response = genreRepository.findGenreByGenre("Pop");
        assertEquals(response.getGenre(), "Pop");
    }

    @Test
    void findGenreByGenreShouldReturnIdTwoForPop() {
        Genre response = genreRepository.findGenreByGenre("Pop");
        assertEquals(response.getId(), 2);
    }

    @Test
    void findGenreByGenreShouldReturnNullWhenGenreNotFound() {
        Genre response = genreRepository.findGenreByGenre("Jazz");
        assertNull(response);
    }
}