/*
package com.example.VideoMicroservice_CopyAllToNew.repositories;

import com.example.VideoMicroservice_CopyAllToNew.entities.Video;
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
class VideoRepositoryTest {

    @Autowired
    private VideoRepository videoRepository;

    @BeforeEach
    void setUp() {
        videoRepository.save(new Video("The Way I Am", "url99", "2002-02-22");
        Video rnb1 = new Video("Cheer (Drink to That", "url3", "2009-07-14");
        Video rnb2 = new Video("Diamonds", "url4", "2010-06-02");

    }

    @Test
    void findMusicByUrlShouldReturnMusic() {
        Video video = videoRepository.findVideoByUrl("url99");

        assertEquals(video, video1, "ERROR: Video was not identical");
    }
}*/
