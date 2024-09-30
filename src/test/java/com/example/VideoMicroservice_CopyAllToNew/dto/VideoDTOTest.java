package com.example.VideoMicroservice_CopyAllToNew.dto;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VideoDTOTest {

    @Test
    void getTypeShouldReturnType() {
        VideoDTO videodto = new VideoDTO("DTO1", "Url1", "2000-01-01");
        assertEquals("video", videodto.getType());
    }

    @Test
    void setTypeShouldChangeTypeToPod() {
        VideoDTO videodto = new VideoDTO("DTO1", "Url1", "2000-01-01");
        videodto.setType("pod");
        assertEquals("pod", videodto.getType());
    }

    @Test
    void getTitleShouldReturnTitle() {
        VideoDTO videodto = new VideoDTO("DTO1", "Url1", "2000-01-01");
        assertEquals("DTO1", videodto.getTitle());
    }

    @Test
    void setTitleShouldChangeTitleToDTO2() {
        VideoDTO videodto = new VideoDTO("DTO1", "Url1", "2000-01-01");
        videodto.setTitle("DTO2");
        assertEquals("DTO2", videodto.getTitle());
    }

    @Test
    void getUrlShouldReturnUrl() {
        VideoDTO videodto = new VideoDTO("DTO1", "Url1", "2000-01-01");
        assertEquals("Url1", videodto.getUrl());
    }

    @Test
    void setUrlShouldChangeUrlToUrl2() {
        VideoDTO videodto = new VideoDTO("DTO1", "Url1", "2000-01-01");
        videodto.setUrl("Url2");
        assertEquals("Url2", videodto.getUrl());
    }

    @Test
    void getReleaseDateShouldReturnReleaseDate() {
        VideoDTO videodto = new VideoDTO("DTO1", "Url1", "2000-01-01");
        assertEquals("2000-01-01", videodto.getReleaseDate());
    }

    @Test
    void setReleaseDateShouldChangeReleaseDate() {
        VideoDTO videodto = new VideoDTO("DTO1", "Url1", "2000-01-01");
        videodto.setReleaseDate("2222-01-01");
        assertEquals("2222-01-01", videodto.getReleaseDate());
    }

    @Test
    void getGenresInputsShouldReturnEmptyGenreList() {
        VideoDTO videodto = new VideoDTO("Video1", "URL1", "2024-12-24");
        assertTrue(videodto.getGenreInputs().isEmpty());
    }

    @Test
    void setGenresInputsShouldUpdateGenreList() {
        VideoDTO videodto = new VideoDTO("Video1", "URL1", "2024-12-24");
        List<String> genres = new ArrayList<>();
        genres.add("Rock");
        genres.add("Pop");
        videodto.setGenreInputs(genres);
        assertEquals("Pop", videodto.getGenreInputs().get(1));
    }

    @Test
    void getAlbumInputsShouldReturnEmptyAlbumList() {
        VideoDTO videodto = new VideoDTO("Video1", "URL1", "2024-12-24");
        assertTrue(videodto.getAlbumInputs().isEmpty());
    }

    @Test
    void setAlbumInputsShouldUpdateAlbumList() {
        VideoDTO videodto = new VideoDTO("Video1", "URL1", "2024-12-24");
        List<String> albums = new ArrayList<>();
        albums.add("Rock in Rio");
        albums.add("Alchemy");
        videodto.setAlbumInputs(albums);
        assertEquals("Alchemy", videodto.getAlbumInputs().get(1));
    }

    @Test
    void getArtistInputsShouldReturnEmptyArtistList() {
        VideoDTO videodto = new VideoDTO("Video1", "URL1", "2024-12-24");
        assertTrue(videodto.getArtistInputs().isEmpty());
    }

    @Test
    void setArtistInputsShouldUpdateArtistList() {
        VideoDTO videodto = new VideoDTO("Video1", "URL1", "2024-12-24");
        List<String> artists = new ArrayList<>();
        artists.add("Iron Maiden");
        artists.add("Dire Straits");
        videodto.setArtistInputs(artists);
        assertEquals("Dire Straits", videodto.getArtistInputs().get(1));
    }

    @Test
    void ConstructorShouldReturnEmptyAndNullResults() {
        VideoDTO videodto = new VideoDTO();
        assertNull(videodto.getType());
        assertNull(videodto.getTitle());
        assertNull(videodto.getUrl());
        assertNull(videodto.getReleaseDate());
        assertTrue(videodto.getGenreInputs().isEmpty());
        assertTrue(videodto.getAlbumInputs().isEmpty());
        assertTrue(videodto.getArtistInputs().isEmpty());
    }

    @Test
    void ConstructorShouldReturnRightValuesCombinedWithEmptyValues() {
        VideoDTO videodto = new VideoDTO("Video1", "URL1", "2024-12-24");
        assertEquals("Video1", videodto.getTitle());
        assertEquals("URL1", videodto.getUrl());
        assertEquals("2024-12-24", videodto.getReleaseDate());
        assertEquals("video", videodto.getType());
        assertTrue(videodto.getGenreInputs().isEmpty());
        assertTrue(videodto.getAlbumInputs().isEmpty());
        assertTrue(videodto.getArtistInputs().isEmpty());
    }

    @Test
    void ConstructorShouldReturnRightValues() {
        List<String> genres = Arrays.asList("Heavy Metal", "Rock");
        List<String> albums = Arrays.asList("Rock in Rio", "Alchemy");
        List<String> artists = Arrays.asList("Iron Maiden", "Dire Straits");
        VideoDTO videodto = new VideoDTO("Video1", "URL1", "2024-12-24", genres, albums, artists);

        assertNotNull(videodto);
        assertEquals("video", videodto.getType());  // Kontrollera att typfältet är korrekt satt
        assertEquals("Video1", videodto.getTitle());
        assertEquals("URL1", videodto.getUrl());
        assertEquals("2024-12-24", videodto.getReleaseDate());
        assertEquals(Arrays.asList("Heavy Metal", "Rock"), videodto.getGenreInputs());
        assertEquals(Arrays.asList("Rock in Rio", "Alchemy"), videodto.getAlbumInputs());
        assertEquals(Arrays.asList("Iron Maiden", "Dire Straits"), videodto.getArtistInputs());
    }
}