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
        videoRepository.save(new Video("The Greatest", "url2000", "2016"));
        Video video = new Video("Kickstart My Heart", "url2001", "1990");
        videoRepository.save(video);
    }

    @Test
    void findVideoByUrlShouldReturnVideoWithTitleTheGreatest() {
        Video video = videoRepository.findVideoByUrl("url2000");
        assertEquals("The Greatest", video.getTitle());
    }

    @Test
    void findVideoByUrlShouldReturnVideoWithIdTwo() {
        Video video = videoRepository.findVideoByUrl("url2001");
        assertEquals(2, video.getId());
    }

    @Test
    void findVideoByUrlShouldReturnNullWhenUrlDoesNotExist() {
        Video video = videoRepository.findVideoByUrl("url9999");
        assertNull(video);
    }
}
