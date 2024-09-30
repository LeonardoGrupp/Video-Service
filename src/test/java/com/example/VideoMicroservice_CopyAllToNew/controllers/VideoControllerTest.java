package com.example.VideoMicroservice_CopyAllToNew.controllers;

import com.example.VideoMicroservice_CopyAllToNew.dto.VideoDTO;
import com.example.VideoMicroservice_CopyAllToNew.entities.Album;
import com.example.VideoMicroservice_CopyAllToNew.entities.Artist;
import com.example.VideoMicroservice_CopyAllToNew.entities.Genre;
import com.example.VideoMicroservice_CopyAllToNew.services.VideoService;
import com.example.VideoMicroservice_CopyAllToNew.entities.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VideoControllerTest {

    private VideoController videoController;
    private VideoService videoServiceMock;

    @BeforeEach
    void setUp() {
        videoServiceMock = mock(VideoService.class);
        videoController = new VideoController(videoServiceMock);
    }

    @Test
    void getAllVideoShouldReturnList() {
        List<Video> allVideo = Arrays.asList(
                new Video("title1", "url1", "releasedate1"),
                new Video("title2", "url2", "release2"),
                new Video("title3", "url3", "release3"),
                new Video("title4", "url4", "release4")
        );

        when(videoServiceMock.findAllVideos()).thenReturn(allVideo);

        ResponseEntity<List<Video>> response = videoController.getAllVideos();

        assertEquals(allVideo, response.getBody(), "ERROR: Lists was not identical");
        assertEquals(allVideo.size(), Objects.requireNonNull(response.getBody()).size(), "ERROR: Sizes was not identical");

        verify(videoServiceMock).findAllVideos();
    }

    @Test
    void getAllVideoForArtistShouldReturnList() {
        List<Album> albumList = List.of(new Album("The Marshall Mathers LP"));
        List<Artist> artistList = List.of(new Artist("Eminem"));
        List<Genre> genreList = List.of(new Genre("Hip-Hop"));
        List<Video> artistVideo = Arrays.asList(
                new Video("title1", "url1", "releasedate1", genreList, albumList, artistList),
                new Video("title2", "url2", "releasedate2", genreList, albumList, artistList),
                new Video("title3", "url3", "releasedate3", genreList, albumList, artistList),
                new Video("title4", "url4", "releasedate4", genreList, albumList, artistList)
        );

        when(videoServiceMock.findVideosByArtist("Eminem")).thenReturn(artistVideo);

        ResponseEntity<List<Video>> response = videoController.getAllVideosForArtist("Eminem");

        assertEquals(artistVideo, response.getBody(), "ERROR: Lists was not identical");
        assertEquals(artistVideo.get(0).getArtists().get(0), response.getBody().get(0).getArtists().get(0), "ERROR: Artists was not identical");
        assertEquals(artistVideo.size(), response.getBody().size(), "ERROR: Sizes was not identical");

        verify(videoServiceMock).findVideosByArtist("Eminem");
    }

    @Test
    void getAllVideoForAlbumShouldReturnList() {
        List<Album> albumList = List.of(new Album("The Marshall Mathers LP"));
        List<Artist> artistList = List.of(new Artist("Eminem"));
        List<Genre> genreList = List.of(new Genre("Hip-Hop"));
        List<Video> albumVideo = Arrays.asList(
                new Video("title1", "url1", "releasedate1", genreList, albumList, artistList),
                new Video("title2", "url2", "releasedate2", genreList, albumList, artistList),
                new Video("title3", "url3", "releasedate3", genreList, albumList, artistList),
                new Video("title4", "url4", "releasedate4", genreList, albumList, artistList)
        );

        when(videoServiceMock.findVideosByAlbum("The Marshall Mathers LP")).thenReturn(albumVideo);

        ResponseEntity<List<Video>> response = videoController.getAllVideosForAlbum("The Marshall Mathers LP");

        assertEquals(albumVideo, response.getBody(), "ERROR: Lists was not identical");
        assertEquals(albumVideo.size(), Objects.requireNonNull(response.getBody()).size(), "ERROR: Sizes was not identical");
        assertEquals("The Marshall Mathers LP", response.getBody().get(0).getAlbums().get(0).getName(), "ERROR: Albums was not identical");

        verify(videoServiceMock).findVideosByAlbum("The Marshall Mathers LP");
    }

    @Test
    void getAllVideoForGenreShouldReturnList() {
        List<Album> albumList = List.of(new Album("The Marshall Mathers LP"));
        List<Artist> artistList = List.of(new Artist("Eminem"));
        List<Genre> genreList = List.of(new Genre("Hip-Hop"));
        List<Video> genreVideo = Arrays.asList(
                new Video("title1", "url1", "releasedate1", genreList, albumList, artistList),
                new Video("title2", "url2", "releasedate2", genreList, albumList, artistList),
                new Video("title3", "url3", "releasedate3", genreList, albumList, artistList),
                new Video("title4", "url4", "releasedate4", genreList, albumList, artistList)
        );

        when(videoServiceMock.findVideosByGenre("Hip-Hop")).thenReturn(genreVideo);

        ResponseEntity<List<Video>> response = videoController.getAllVideosForGenre("Hip-Hop");

        assertEquals(genreVideo, response.getBody(), "ERROR: Lists was not identical");
        assertEquals(genreVideo.size(), Objects.requireNonNull(response.getBody()).size(), "ERROR: Sizes was not identical");
        assertEquals("Hip-Hop", response.getBody().get(0).getGenres().get(0).getGenre(), "ERROR: Genres was not identical");

        verify(videoServiceMock).findVideosByGenre("Hip-Hop");
    }

    @Test
    void getVideoByUrlShouldReturnVideo() {
        Video video = new Video("title", "url", "releaseDate");

        when(videoServiceMock.findVideoByUrl("url")).thenReturn(video);

        ResponseEntity<Video> response = videoController.getVideoByUrl("url");

        assertEquals("title", Objects.requireNonNull(response.getBody()).getTitle(), "ERROR: Video Titles was not identical");

        verify(videoServiceMock).findVideoByUrl("url");
    }

    @Test
    void createVideoShouldReturnVideo() {
        List<String> albumInputs = List.of("The Slim Shady LP");
        List<String> artistInputs = List.of("Eminem");
        List<String> genreInputs = List.of("Hip-Hop");
        VideoDTO videoDTO = new VideoDTO("The Real Slim Shady", "url1", "2000-02-22", genreInputs, albumInputs, artistInputs);

        List<Album> albumList = List.of(new Album("The Slim Shady LP"));
        List<Artist> artistList = List.of(new Artist("Eminem"));
        List<Genre> genreList = List.of(new Genre("Hip-Hop"));
        Video videoToBeCreated = new Video(videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getReleaseDate(), genreList, albumList, artistList);

        when(videoServiceMock.createVideo(videoDTO)).thenReturn(videoToBeCreated);

        ResponseEntity<Video> response = videoController.createVideo(videoDTO);

        assertEquals("The Real Slim Shady", Objects.requireNonNull(response.getBody()).getTitle(), "ERROR: Video was not identical");

        verify(videoServiceMock).createVideo(videoDTO);
    }

    @Test
    void createVideoNoTypeShouldReturnException() {
        List<String> albumInputs = List.of("The Slim Shady LP");
        List<String> artistInputs = List.of("Eminem");
        List<String> genreInputs = List.of("Hip-Hop");
        VideoDTO videoDTO = new VideoDTO("title", "url1", "2022-02-02", genreInputs, albumInputs, artistInputs);

        when(videoServiceMock.createVideo(videoDTO)).thenThrow(new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Type was not provided"));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.createVideo(videoDTO);
        }, "ERROR: Exception not thrown");

        assertEquals("ERROR: Video Type was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).createVideo(videoDTO);
    }

    @Test
    void createVideoNoTitleShouldReturnException() {
        List<String> albumInputs = List.of("The Slim Shady LP");
        List<String> artistInputs = List.of("Eminem");
        List<String> genreInputs = List.of("Hip-Hop");
        VideoDTO videoDTO = new VideoDTO("", "url1", "2022-02-02", genreInputs, albumInputs, artistInputs);

        when(videoServiceMock.createVideo(videoDTO)).thenThrow(new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Title was not provided"));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.createVideo(videoDTO);
        }, "ERROR: Exception not thrown");

        assertEquals("ERROR: Video Title was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).createVideo(videoDTO);
    }

    @Test
    void createVideoNoUrlShouldReturnException() {
        List<String> albumInputs = List.of("The Slim Shady LP");
        List<String> artistInputs = List.of("Eminem");
        List<String> genreInputs = List.of("Hip-Hop");
        VideoDTO videoDTO = new VideoDTO("The Real Slim Shady", "", "2022-02-02", genreInputs, albumInputs, artistInputs);

        when(videoServiceMock.createVideo(videoDTO)).thenThrow(new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video URL was not provided"));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.createVideo(videoDTO);
        }, "ERROR: Exception not thrown");

        assertEquals("ERROR: Video URL was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).createVideo(videoDTO);
    }

    @Test
    void createVideoNoReleaseDateShouldReturnException() {
        List<String> albumInputs = List.of("The Slim Shady LP");
        List<String> artistInputs = List.of("Eminem");
        List<String> genreInputs = List.of("Hip-Hop");
        VideoDTO videoDTO = new VideoDTO("The Real Slim Shady", "url", "", genreInputs, albumInputs, artistInputs);

        when(videoServiceMock.createVideo(videoDTO)).thenThrow(new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Release date was not provided"));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.createVideo(videoDTO);
        }, "ERROR: Exception not thrown");

        assertEquals("ERROR: Video Release date was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).createVideo(videoDTO);
    }

    @Test
    void createVideoNoGenreShouldReturnException() {
        List<String> albumInputs = List.of("The Slim Shady LP");
        List<String> artistInputs = List.of("Eminem");
        List<String> genreInputs = List.of("Hip-Hop");
        VideoDTO videoDTO = new VideoDTO("The Real Slim Shady", "url1", "2022-02-02", genreInputs, albumInputs, artistInputs);

        when(videoServiceMock.createVideo(videoDTO)).thenThrow(new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Genre was not provided"));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.createVideo(videoDTO);
        }, "ERROR: Exception not thrown");

        assertEquals("ERROR: Video Genre was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).createVideo(videoDTO);
    }

    @Test
    void createVideoNoAlbumInputsShouldReturnException() {
        List<String> albumInputs = List.of("");
        List<String> artistInputs = List.of("Eminem");
        List<String> genreInputs = List.of("Hip-Hop");
        VideoDTO videoDTO = new VideoDTO("The Real Slim Shady", "url1", "2022-02-02", genreInputs, albumInputs, artistInputs);

        when(videoServiceMock.createVideo(videoDTO)).thenThrow(new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Album was not provided"));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.createVideo(videoDTO);
        }, "ERROR: Exception not thrown");

        assertEquals("ERROR: Video Album was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).createVideo(videoDTO);
    }

    @Test
    void createVideoNoArtistShouldReturnException() {
        List<String> albumInputs = List.of("The Slim Shady LP");
        List<String> artistInputs = List.of("");
        List<String> genreInputs = List.of("Hip-Hop");
        VideoDTO videoDTO = new VideoDTO("The Real Slim Shady", "", "2022-02-02", genreInputs, albumInputs, artistInputs);

        when(videoServiceMock.createVideo(videoDTO)).thenThrow(new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Artist was not provided"));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.createVideo(videoDTO);
        }, "ERROR: Exception not thrown");

        assertEquals("ERROR: Video Artist was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).createVideo(videoDTO);
    }

    @Test
    void updateVideoShouldReturnVideo() {
        List<Album> albumList = List.of(new Album("album1"));
        List<Artist> artistList = List.of(new Artist("artist1"));
        List<Genre> genreList = List.of(new Genre("Hip-Hop"));
        long videoId = 1;
        Video existingVideo = new Video("title1", "url1", "2022-02-02", genreList, albumList, artistList);
        existingVideo.setId(videoId);

        List<String> albumInputs = List.of("other album");
        List<String> artistInputs = List.of("other artist");
        List<String> genreInputs = List.of("other genre");
        VideoDTO newInfo = new VideoDTO("new title", "url2", "2024-09-02", genreInputs, albumInputs, artistInputs);

        List<Album> newVideoAlbumList = List.of(new Album("other album"));
        List<Artist> newVideoArtistList = List.of(new Artist("other artist"));
        List<Genre> newVideoGenreList = List.of(new Genre("other genre"));
        Video newVideoInfo = new Video("new title", "url2", "2024-09-02", newVideoGenreList, newVideoAlbumList, newVideoArtistList);

        when(videoServiceMock.updateVideo(videoId, newInfo)).thenReturn(newVideoInfo);

        ResponseEntity<Video> response = videoController.updateVideo(videoId, newInfo);

        assertEquals("new title", Objects.requireNonNull(response.getBody()).getTitle(), "ERROR: Titles was not identical");

        verify(videoServiceMock).updateVideo(videoId, newInfo);
    }

    @Test
    void updateVideoInvalidIdShouldReturnException() {
        List<String> albumList = List.of("new album");
        List<String> artistList = List.of("new artist");
        List<String> genreList = List.of("new genre");

        long videoId = 1;
        VideoDTO newInfo = new VideoDTO("new title", "url1", "2022-02-02", genreList, albumList, artistList);

        when(videoServiceMock.updateVideo(videoId, newInfo)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Could not find video with id: " + videoId));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.updateVideo(videoId, newInfo);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Could not find video with id: " + videoId, response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).updateVideo(videoId, newInfo);
    }

    @Test
    void deleteVideoShouldReturnString() {
        long videoId = 1;
        Video videoToDelete = new Video("title", "url1", "1991-01-19");
        videoToDelete.setId(videoId);

        when(videoServiceMock.deleteVideo(videoId)).thenReturn("Video successfully deleted");

        ResponseEntity<String> response = videoController.deleteVideo(videoId);

        assertEquals("Video successfully deleted", response.getBody(), "ERROR: Strings was not identical");

        verify(videoServiceMock).deleteVideo(videoId);
    }

    @Test
    void deleteVideoShouldReturnException() {
        long videoId = 1;

        when(videoServiceMock.deleteVideo(videoId)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Could not find video with id: " + videoId));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.deleteVideo(videoId);
        }, "ERROR: Exceptions was not thrown");

        assertEquals("ERROR: Could not find video with id: " + videoId, response.getReason(), "ERROR: Exceptions was not identical");

        verify(videoServiceMock).deleteVideo(videoId);
    }

    @Test
    void playVideoShouldReturnString() {
        String url = "url";
        Video video = new Video("The Real Slim Shady", url, "2000-01-01");

        when(videoServiceMock.playVideo(url)).thenReturn("Playing " + video.getType() + ": " + video.getTitle());

        String result = videoServiceMock.playVideo(url);

        assertEquals("Playing video: The Real Slim Shady", result, "ERROR: Strings was not identical");

        verify(videoServiceMock).playVideo(url);
    }

    @Test
    void playVideoShouldReturnException() {
        String url = "url";

        when(videoServiceMock.playVideo(url)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Video with URL not found"));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.playVideo(url);
        }, "ERROR: Exceptions was not thrown");

        assertEquals("ERROR: Video with URL not found", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).playVideo(url);
    }

    @Test
    void likeVideoShouldReturnString() {
        Video videoToLike = new Video("title", "url", "release");
        videoToLike.setLikes(0);

        when(videoServiceMock.likeVideo(videoToLike.getUrl())).thenReturn("Liked Video: " + videoToLike.getTitle());

        String result = videoServiceMock.likeVideo(videoToLike.getUrl());

        assertEquals("Liked Video: " + videoToLike.getTitle(), result, "ERROR: Strings was not identical");

        verify(videoServiceMock).likeVideo(videoToLike.getUrl());
    }

    @Test
    void likeVideoNotFoundShouldReturnException() {
        String url = "url";

        when(videoServiceMock.likeVideo(url)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Video with URL not found"));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.likeVideo(url);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Video with URL not found", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).likeVideo(url);
    }

    @Test
    void disLikeVideoShouldReturnString() {
        Video videoToDisLike = new Video("title", "url", "release");
        videoToDisLike.setLikes(0);

        when(videoServiceMock.dislikeVideo(videoToDisLike.getUrl())).thenReturn("Disliked Video: " + videoToDisLike.getTitle());

        String result = videoServiceMock.dislikeVideo(videoToDisLike.getUrl());

        assertEquals("Disliked Video: " + videoToDisLike.getTitle(), result, "ERROR: Strings was not identical");

        verify(videoServiceMock).dislikeVideo(videoToDisLike.getUrl());
    }

    @Test
    void disLikeVideoShouldReturnException() {
        String url = "url";

        when(videoServiceMock.dislikeVideo(url)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Video with URL not found"));

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoController.dislikeVideo(url);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Video with URL not found", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoServiceMock).dislikeVideo(url);
    }

    @Test
    void videoExistShouldReturnTrue() {
        String url = "url";

        when(videoServiceMock.checkIfVideoExistByUrl(url)).thenReturn(true);

        ResponseEntity<Boolean> response = videoController.videoExist(url);

        assertEquals(Boolean.TRUE, response.getBody(), "ERROR: Response was false");

        verify(videoServiceMock).checkIfVideoExistByUrl(url);
    }

    @Test
    void musicExistShouldReturnFalse() {
        String url = "url";

        when(videoServiceMock.checkIfVideoExistByUrl(url)).thenReturn(false);

        ResponseEntity<Boolean> response = videoController.videoExist(url);

        assertNotEquals(Boolean.TRUE, response.getBody(), "ERROR: Response was true");

        verify(videoServiceMock).checkIfVideoExistByUrl(url);
    }
}
