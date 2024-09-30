package com.example.VideoMicroservice_CopyAllToNew.entities;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VideoTest {

    @Test
    void getIdShouldReturnZero() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        long result = video.getId();
        assertEquals(0, result);
    }

    @Test
    void setIdShouldSetIdToFour() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.setId(4);
        long result = video.getId();
        assertEquals(4, result);
    }

    @Test
    void getTypeShouldReturnVideo() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        String result = video.getType();
        assertEquals("video", result);
    }

    @Test
    void setTypeShouldSetTypeToPod() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.setType("pod");
        String result = video.getType();
        assertEquals("pod", result);
    }

    @Test
    void getTitleShouldReturnVideo1() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        String result = video.getTitle();
        assertEquals("Video1", result);
    }

    @Test
    void setTitleShouldSetTitleToVideo2() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.setTitle("Video2");
        String result = video.getTitle();
        assertEquals("Video2", result);
    }

    @Test
    void getUrlShouldReturnURL1() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        String result = video.getUrl();
        assertEquals("URL1", result);
    }

    @Test
    void setUrlShouldSetUrlToURL4() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.setUrl("URL4");
        String result = video.getUrl();
        assertEquals("URL4", result);
    }

    @Test
    void getReleaseDateShouldReturnReleaseDate() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        String result = video.getReleaseDate();
        assertEquals("2024-12-24", result);
    }

    @Test
    void setReleaseDateShouldUpdateReleaseDate() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.setReleaseDate("1978-01-01");
        String result = video.getReleaseDate();
        assertEquals("1978-01-01", result);
    }

    @Test
    void getPlayCounterShouldReturnZero() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        int result = video.getPlayCounter();
        assertEquals(0, result);
    }

    @Test
    void setPlayCounterShouldUpdatePlayCounterToFour() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.setPlayCounter(4);
        int result = video.getPlayCounter();
        assertEquals(4, result);
    }

    @Test
    void getLikesShouldReturnZero() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        int result = video.getLikes();
        assertEquals(0, result);
    }

    @Test
    void setLikesShouldUpdateLikesToFour() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.setLikes(4);
        int result = video.getLikes();
        assertEquals(4, result);
    }

    @Test
    void getDisLikesShouldReturnZero() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        int result = video.getDisLikes();
        assertEquals(0, result);
    }

    @Test
    void setDisLikesShouldUpdatePlayCounterToFour() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.setDisLikes(4);
        int result = video.getDisLikes();
        assertEquals(4, result);
    }

    @Test
    void getGenresShouldReturnEmptyGenreList() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        assertTrue(video.getGenres().isEmpty());
    }

    @Test
    void setGenresShouldUpdateGenreList() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("Rock"));
        genres.add(new Genre("Pop"));
        video.setGenres(genres);
        assertEquals("Pop", video.getGenres().get(1).getGenre());
    }

    @Test
    void getAlbumsShouldReturnEmptyAlbumList() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        assertTrue(video.getAlbums().isEmpty());
    }

    @Test
    void setAlbumsShouldUpdateAlbumList() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        List<Album> album = new ArrayList<>();
        album.add(new Album("Rock in Rio"));
        album.add(new Album("Alchemy"));
        video.setAlbums(album);
        assertEquals("Alchemy", video.getAlbums().get(1).getName());
    }

    @Test
    void getArtistsShouldReturnEmptyArtistList() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        assertTrue(video.getArtists().isEmpty());
    }

    @Test
    void setArtistsShouldUpdateArtistList() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        List<Artist> artist = new ArrayList<>();
        artist.add(new Artist("Iron Maiden"));
        artist.add(new Artist("Dire Straits"));
        video.setArtists(artist);
        assertEquals("Dire Straits", video.getArtists().get(1).getName());
    }

    @Test
    void countPlayShouldIncreasePlayCounterFromZeroToOne() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.countPlay();
        int result = video.getPlayCounter();
        assertEquals(1, result);
    }

    @Test
    void resetPlayShouldResetPlayCounterToZero() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.countPlay();
        video.resetPlayCounter();
        int result = video.getPlayCounter();
        assertEquals(0, result);
    }

    @Test
    void addLikeShouldIncreaseLikesWithOne() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.addLike();
        int result = video.getLikes();
        assertEquals(1, result);
    }

    @Test
    void resetLikesShouldResetLikesToZero() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.addLike();
        video.resetLikes();
        int result = video.getLikes();
        assertEquals(0, result);
    }

    @Test
    void addDisLikeShouldIncreaseDisLikesWithOne() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.addDisLike();
        int result = video.getDisLikes();
        assertEquals(1, result);
    }

    @Test
    void resetDisLikesShouldResetDisLikesToZero() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.addDisLike();
        video.resetDisLikes();
        int result = video.getDisLikes();
        assertEquals(0, result);
    }

    @Test
    void resetPlayCounterLikesAndDisLikesShouldResetPlayCounterLikesAndDisLikesToZero() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        video.countPlay();
        video.addLike();
        video.addDisLike();
        video.resetPlayCounterLikesAndDisLikes();
        assertEquals(0, video.getPlayCounter());
        assertEquals(0, video.getLikes());
        assertEquals(0, video.getDisLikes());
    }

    @Test
    void ConstructorShouldReturnRightValuesCombinedWithEmptyValues() {
        Video video = new Video("Video1", "URL1", "2024-12-24");
        assertEquals("Video1", video.getTitle());
        assertEquals("URL1", video.getUrl());
        assertEquals("2024-12-24", video.getReleaseDate());
        assertTrue(video.getGenres().isEmpty());
        assertTrue(video.getAlbums().isEmpty());
        assertTrue(video.getArtists().isEmpty());
    }

    @Test
    void ConstructorShouldReturnRightValues() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("Heavy Metal"));
        genres.add(new Genre("Rock"));
        List<Album> albums = new ArrayList<>();
        albums.add(new Album("Rock in Rio"));
        albums.add(new Album("Alchemy"));
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist("Iron Maiden"));
        artists.add(new Artist("Dire Straits"));

        Video video = new Video("Video1", "URL1", "2024-12-24", genres, albums, artists);

        assertNotNull(video);
        assertEquals("video", video.getType());  // Kontrollera att typfältet är korrekt satt
        assertEquals("Video1", video.getTitle());
        assertEquals("URL1", video.getUrl());
        assertEquals("2024-12-24", video.getReleaseDate());
        assertEquals("Rock", video.getGenres().get(1).getGenre());
        assertEquals("Rock in Rio", video.getAlbums().get(0).getName());
        assertEquals("Dire Straits", video.getArtists().get(1).getName());
    }

    @Test
    void ConstructorShouldReturnRightValueForId() {
        Video video = new Video();
        assertEquals(0, video.getId());
    }
}