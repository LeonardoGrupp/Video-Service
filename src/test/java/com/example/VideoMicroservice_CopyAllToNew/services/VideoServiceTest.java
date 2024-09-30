package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.dto.VideoDTO;
import com.example.VideoMicroservice_CopyAllToNew.entities.Album;
import com.example.VideoMicroservice_CopyAllToNew.entities.Artist;
import com.example.VideoMicroservice_CopyAllToNew.entities.Genre;
import com.example.VideoMicroservice_CopyAllToNew.repositories.VideoRepository;
import com.example.VideoMicroservice_CopyAllToNew.entities.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class VideoServiceTest {
    private VideoRepository videoRepositoryMock;
    private AlbumService albumService;
    private ArtistService artistService;
    private GenreService genreService;
    private VideoService videoService;

    @BeforeEach
    void setUp() {
        videoRepositoryMock = mock(VideoRepository.class);
        albumService = mock(AlbumService.class);
        artistService = mock(ArtistService.class);
        genreService = mock(GenreService.class);
        videoService = new VideoService(videoRepositoryMock, genreService, albumService, artistService);
    }

    @Test
    void findAllVideosShouldReturnList() {
        List<Video> allVideoList = Arrays.asList(
                new Video("The Wicker Man", "url1001", "2002-03-25"),
                new Video("Ghost Of the Navigator", "url1002", "2002-03-25"),
                new Video("Brave New World", "url1003", "2002-03-25"),
                new Video("Wrathchild", "url1004", "2002-03-25"),
                new Video("2 Minutes To Midnight", "url1005", "2002-03-25")
        );

        when(videoRepositoryMock.findAll()).thenReturn(allVideoList);

        List<Video> response = videoService.findAllVideos();

        assertEquals(allVideoList, response, "ERROR: Video lists was not identical");
        assertEquals(5, response.size(), "ERROR: Sizes was not identical");
        assertEquals("Brave New World", response.get(2).getTitle(), "ERROR: Titles was not identical");

        verify(videoRepositoryMock).findAll();
    }

    @Test
    void findVideosByArtistShouldReturnList() {
        Artist artist = new Artist("Dire Straits");
        Album album = new Album("Alchemy");
        Genre genre = new Genre("Rock");
        List<Video> videoList = Arrays.asList(
                new Video("Once Upon A Time in the West", "'url1019", "1984-03-16", List.of(genre), List.of(album), List.of(artist)),
                new Video("Romeo And Juliet", "'url1020", "1984-03-16", List.of(genre), List.of(album), List.of(artist)),
                new Video("Expresso Love", "'url1021", "1984-03-16", List.of(genre), List.of(album), List.of(artist))
        );

        when(videoRepositoryMock.findAll()).thenReturn(videoList);

        List<Video> response = videoService.findVideosByArtist("Dire Straits");

        assertEquals(videoList, response, "ERROR: Video lists was not identical");
        assertEquals(3, response.size(), "ERROR: Sizes was not identical");
        assertEquals("Romeo And Juliet", response.get(1).getTitle(), "ERROR: Artists was not identical");

        verify(videoRepositoryMock).findAll();
    }

    @Test
    void findVideosByAlbumShouldReturnList() {
        Artist avicii = new Artist("Lady Gaga");
        Album stories = new Album("The Chromatica Ball");
        Genre genre = new Genre("Pop");
        List<Video> albumVideoList = Arrays.asList(
                new Video("Bad Romance", "url1028", "2022-07-21", List.of(genre), List.of(stories), List.of(avicii)),
                new Video("Just Dance", "url1029", "2022-07-21", List.of(genre), List.of(stories), List.of(avicii))
        );

        when(videoRepositoryMock.findAll()).thenReturn(albumVideoList);

        List<Video> response = videoService.findVideosByAlbum("The Chromatica Ball");

        assertEquals(albumVideoList, response, "ERROR: Video lists was not identical");
        assertEquals(2, response.size(), "ERROR: Sizes was not identical");
        assertEquals(stories, response.get(0).getAlbums().get(0), "ERROR: Albums was not identical");

        verify(videoRepositoryMock).findAll();
    }

    @Test
    void findVideosByGenreShouldReturnList() {
        Artist avicii = new Artist("Ramones");
        Album stories = new Album("Live At the Roxy");
        Genre genre = new Genre("Punk");
        List<Video> genreVideoList = Arrays.asList(
                new Video("Loud Mouth", "url1049", "1976-08-12", List.of(genre), List.of(stories), List.of(avicii)),
                new Video("Beat On the Brat", "url1050", "1976-08-12", List.of(genre), List.of(stories), List.of(avicii))
        );

        when(videoRepositoryMock.findAll()).thenReturn(genreVideoList);

        List<Video> response = videoService.findVideosByGenre("Punk");

        assertEquals(genreVideoList, response, "ERROR: Video lists was not identical");
        assertEquals(2, response.size(), "ERROR: Sizes was not identical");
        assertEquals(genre, response.get(0).getGenres().get(0), "ERROR: Genres was not identical");

        verify(videoRepositoryMock).findAll();
    }

    @Test
    void findVideoByUrlShouldReturnVideo() {
        Video video = new Video("titel", "url", "releasedate");

        when(videoRepositoryMock.findVideoByUrl("url")).thenReturn(video);

        Video response = videoService.findVideoByUrl("url");

        assertEquals(video, response, "ERROR: Video was not identical");

        verify(videoRepositoryMock).findVideoByUrl("url");
    }

    @Test
    void findVideoByIdShouldReturnVideo() {
        Video video = new Video("Clint Eastwood", "url1", "2001-03-05");
        video.setId(1);

        long videoId = 1;

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.of(video));

        Video response = videoService.findVideoById(videoId);

        assertEquals(videoId, response.getId(), "ERROR: IDs was not identical");
        assertEquals("Clint Eastwood", response.getTitle(), "ERROR: Titles was not identical");

        verify(videoRepositoryMock).findById(videoId);
    }

    @Test
    void findVideoByIdShouldReturnException() {
        long videoId = 1;

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.empty());

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.findVideoById(videoId);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Could not find video with id: " + videoId, response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoRepositoryMock).findById(videoId);
    }

    @Test
    void createVideoShouldReturnVideo() {
        List<String> albumInputs = Arrays.asList("Album1", "Album2");
        List<String> artistInputs = Arrays.asList("Artist1", "Artist2");
        List<String> genreInputs = Arrays.asList("Pop", "RNB");
        VideoDTO videoDTO = new VideoDTO("VideoTitle", "url1", "2024-09-02", genreInputs, albumInputs, artistInputs);

        List<Album> albums = Arrays.asList(new Album("Album1"), new Album("Album2"));
        List<Artist> artists = Arrays.asList(new Artist("Artist1"), new Artist("Artist2"));
        List<Genre> genres = Arrays.asList(new Genre("Pop"), new Genre("RNB"));
        Video video = new Video("VideoTitle", "url1", "2024-09-02", genres, albums, artists);

        Video savedVideo = videoRepositoryMock.save(video);

        when(videoRepositoryMock.save(video)).thenReturn(video);

        Video response = videoService.createVideo(videoDTO);

        assertEquals(savedVideo, response, "ERROR: Responses was not the same");

        verify(videoRepositoryMock).save(video);
    }


    @Test
    void createVideoNoTitleShouldReturnException() {
        List<String> albumStringList = List.of("Good Girl Gone Bad");
        List<String> artistStringList = Arrays.asList("Rihanna", "Jay-Z");
        List<String> genreStringList = Arrays.asList("Pop", "RNB");
        VideoDTO videoDTO = new VideoDTO("", "url", "release", albumStringList, genreStringList, artistStringList);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.createVideo(videoDTO);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Video Title was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoRepositoryMock, never()).save(any(Video.class));
    }

    @Test
    void createVideoNoUrlShouldReturnException() {
        VideoDTO videoDTO = new VideoDTO("title", "", "release");
        List<String> albumStringList = List.of("Good Girl Gone Bad");
        List<String> artistStringList = Arrays.asList("Rihanna", "Jay-Z");
        List<String> genreStringList = Arrays.asList("Pop", "RNB");
        videoDTO.setAlbumInputs(albumStringList);
        videoDTO.setArtistInputs(artistStringList);
        videoDTO.setGenreInputs(genreStringList);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.createVideo(videoDTO);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Video URL was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoRepositoryMock, never()).save(any(Video.class));
    }

    @Test
    void createVideoNoReleaseDateShouldReturnException() {
        VideoDTO videoDTO = new VideoDTO("title", "url", "");
        List<String> albumStringList = List.of("Good Girl Gone Bad");
        List<String> artistStringList = Arrays.asList("Rihanna", "Jay-Z");
        List<String> genreStringList = Arrays.asList("Pop", "RNB");
        videoDTO.setAlbumInputs(albumStringList);
        videoDTO.setArtistInputs(artistStringList);
        videoDTO.setGenreInputs(genreStringList);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.createVideo(videoDTO);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Video Release date was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoRepositoryMock, never()).save(any(Video.class));
    }

    @Test
    void createMusicNoGenreShouldReturnException() {
        VideoDTO videoDTO = new VideoDTO("title", "url", "release");
        List<String> albumStringList = List.of("Good Girl Gone Bad");
        List<String> artistStringList = Arrays.asList("Rihanna", "Jay-Z");
        videoDTO.setAlbumInputs(albumStringList);
        videoDTO.setArtistInputs(artistStringList);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.createVideo(videoDTO);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Video Genre was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoRepositoryMock, never()).save(any(Video.class));
    }

    @Test
    void createVideoNoAlbumShouldReturnException() {
        VideoDTO videoDTO = new VideoDTO("title", "url", "release");
        List<String> artistStringList = Arrays.asList("Rihanna", "Jay-Z");
        List<String> genreStringList = Arrays.asList("Pop", "RNB");
        videoDTO.setArtistInputs(artistStringList);
        videoDTO.setGenreInputs(genreStringList);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.createVideo(videoDTO);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Video Album was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoRepositoryMock, never()).save(any(Video.class));
    }

    @Test
    void createVideoNoArtistShouldReturnException() {
        VideoDTO videoDTO = new VideoDTO("title", "url", "release");
        List<String> albumStringList = List.of("Good Girl Gone Bad");
        List<String> genreStringList = Arrays.asList("Pop", "RNB");
        videoDTO.setAlbumInputs(albumStringList);
        videoDTO.setGenreInputs(genreStringList);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.createVideo(videoDTO);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Video Artist was not provided", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "ERROR: Status Codes was not identical");

        verify(videoRepositoryMock, never()).save(any(Video.class));
    }

    @Test
    void getAllGenresShouldReturnList() {
        List<String> genreStrings = Arrays.asList("Pop", "RNB");
        List<String> albumStrings = List.of("Album");
        List<String> artistStrings = List.of("Artist");

        VideoDTO videoDTO = new VideoDTO("titel", "url", "release", genreStrings, albumStrings, artistStrings);

        List<Genre> genres = Arrays.asList(new Genre("Pop"), new Genre("RNB"));

        when(genreService.findAllGenres()).thenReturn(genres);

        List<Genre> responseList = videoService.getAllGenres(videoDTO);

        assertEquals(genres, responseList, "ERROR: Lists was not identical");

        verify(genreService).findAllGenres();
    }

    @Test
    void getAllAlbumsShouldReturnList() {
        List<String> genreStrings = List.of("Pop");
        List<String> albumStrings = Arrays.asList("Good Girl Gone Bad", "Come Clarity");
        List<String> artistStrings = List.of("Artist");

        VideoDTO videoDTO = new VideoDTO("titel", "url", "release", genreStrings, albumStrings, artistStrings);

        List<Album> albums = Arrays.asList(new Album("Good Girl Gone Bad"), new Album("Come Clarity"));

        when(albumService.getAllAlbums()).thenReturn(albums);

        List<Album> responseList = videoService.getAllAlbums(videoDTO);

        assertEquals(albums, responseList, "ERROR: Lists was not identical");

        verify(albumService).getAllAlbums();
    }

    @Test
    void getAllArtistsShouldReturnList() {
        List<String> genreStrings = List.of("Pop");
        List<String> albumStrings = List.of("Good Girl Gone Bad");
        List<String> artistStrings = Arrays.asList("Rihanna", "Jay-Z");

        VideoDTO videoDTO = new VideoDTO("titel", "url", "release", genreStrings, albumStrings, artistStrings);

        List<Artist> artists = Arrays.asList(new Artist("Rihanna"), new Artist("Jay-Z"));

        when(artistService.getAllArtists()).thenReturn(artists);

        List<Artist> responseList = videoService.getAllArtists(videoDTO);

        assertEquals(artists, responseList, "ERROR: Lists was not identical");

        verify(artistService).getAllArtists();
    }

    @Test
    void updateMusicShouldReturnMusic() {
        long videoId = 1;
        Video existingVideo = new Video("Umbrella", "url1", "2002-02-02");
        existingVideo.setId(videoId);

        VideoDTO newInfo = new VideoDTO("Waiting For Love", "url2", "2011-11-02");

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.of(existingVideo));
        when(videoRepositoryMock.save(existingVideo)).thenReturn(existingVideo);

        Video response = videoService.updateVideo(videoId, newInfo);

        assertEquals("Waiting For Love", response.getTitle(), "ERROR: Titles was not identical");
        assertEquals("2011-11-02", response.getReleaseDate(), "ERROR: ReleaseDates was not identical");

        verify(videoRepositoryMock).findById(videoId);
        verify(videoRepositoryMock).save(existingVideo);
    }

    @Test
    void updateVideoInvalidIdShouldReturnException() {
        long videoId = 1;
        VideoDTO newInfo = new VideoDTO("title", "url1", "2022-02-02");

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.empty());

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.updateVideo(videoId, newInfo);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Could not find video with id: " + videoId, response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "ERROR: Status Codes not identical");

        verify(videoRepositoryMock).findById(videoId);
    }

    @Test
    void updateVideoOnlyTypeShouldReturnVideo() {
        long videoId = 1;
        Video existingVideo = new Video("Umbrella", "url1", "2002-02-02");
        existingVideo.setId(videoId);

        VideoDTO newInfo = new VideoDTO("", "", "");

        Video updatedMusic = new Video("Umbrella", "url1", "2002-02-02");

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.of(existingVideo));
        when(videoRepositoryMock.save(existingVideo)).thenReturn(updatedMusic);

        Video response = videoService.updateVideo(videoId, newInfo);

        assertEquals("video", response.getType(), "ERROR: Types was not identical");
        assertEquals("url1", response.getUrl(), "ERROR: URLs was not identical");

        verify(videoRepositoryMock).findById(videoId);
        verify(videoRepositoryMock).save(existingVideo);
    }

    @Test
    void updateVideoOnlyTitleShouldReturnVideo() {
        long videoId = 1;
        Video existingVideo = new Video("Umbrella", "url1", "2002-02-02");
        existingVideo.setId(videoId);

        VideoDTO newInfo = new VideoDTO("Cheers", "", "");

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.of(existingVideo));
        when(videoRepositoryMock.save(existingVideo)).thenReturn(existingVideo);

        Video response = videoService.updateVideo(videoId, newInfo);

        assertEquals("Cheers", response.getTitle(), "ERROR: Titles was not identical");
        assertEquals("url1", response.getUrl(), "ERROR: URLs was not identical");

        verify(videoRepositoryMock).findById(videoId);
        verify(videoRepositoryMock).save(existingVideo);
    }

    @Test
    void updateVideoOnlyReleaseDateShouldReturnVideo() {
        long videoId = 1;
        Video existingVideo = new Video("Umbrella", "url1", "2002-02-02");
        existingVideo.setId(videoId);

        VideoDTO newInfo = new VideoDTO("", "", "2024-09-06");

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.of(existingVideo));
        when(videoRepositoryMock.save(existingVideo)).thenReturn(existingVideo);

        Video response = videoService.updateVideo(videoId, newInfo);

        assertEquals("2024-09-06", response.getReleaseDate(), "ERROR: Release dates was not identical");
        assertEquals("url1", response.getUrl(), "ERROR: URLs was not identical");

        verify(videoRepositoryMock).findById(videoId);
        verify(videoRepositoryMock).save(existingVideo);
    }

    @Test
    void updateVideoOnlyAlbumInputsShouldReturnVideo() {
        List<Album> albumList = List.of(new Album("album"));
        List<Artist> artistList = List.of(new Artist("artist"));
        List<Genre> genreList = List.of(new Genre("Pop"));
        long videoId = 1;
        Video existingVideo = new Video("Umbrella", "url1", "2002-02-02", genreList, albumList, artistList);
        existingVideo.setId(videoId);

        List<String> albumStringInputs = List.of("new album");
        List<String> artistStringInputs = Collections.emptyList();
        List<String> genreStringInputs = List.of("new genre");

        VideoDTO newInfo = new VideoDTO("", "", "2024-09-06", genreStringInputs, albumStringInputs, artistStringInputs);

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.of(existingVideo));
        when(videoRepositoryMock.save(existingVideo)).thenReturn(existingVideo);

        Video response = videoService.updateVideo(videoId, newInfo);

        assertEquals("2024-09-06", response.getReleaseDate(), "ERROR: Release dates was not identical");
        assertEquals("url1", response.getUrl(), "ERROR: URLs was not identical");

        verify(videoRepositoryMock).findById(videoId);
        verify(videoRepositoryMock).save(existingVideo);
    }
    @Test
    void updateVideoOnlyGenreShouldReturnVideo() {
        List<Album> albumList = List.of(new Album("album"));
        List<Artist> artistList = List.of(new Artist("artist"));
        List<Genre> genreList = List.of(new Genre("Pop"));
        long videoId = 1;
        Video existingVideo = new Video("Umbrella", "url1", "2002-02-02", genreList, albumList, artistList);
        existingVideo.setId(videoId);

        List<String> albumStringInputs = Collections.emptyList();
        List<String> artistStringInputs = Collections.emptyList();
        List<String> genreStringInputs = Arrays.asList("Pop", "RNB");
        VideoDTO newInfo = new VideoDTO("", "", "", genreStringInputs, albumStringInputs, artistStringInputs);

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.of(existingVideo));
        when(videoRepositoryMock.save(existingVideo)).thenReturn(existingVideo);

        Video response = videoService.updateVideo(videoId, newInfo);

        assertEquals("Umbrella", response.getTitle(), "ERROR: Titles was not identical");
        assertEquals("artist", response.getArtists().get(0).getName(), "ERROR: Genres was not identical");

        verify(videoRepositoryMock).findById(videoId);
        verify(videoRepositoryMock).save(existingVideo);
    }

    @Test
    void updateVideoOnlyArtistInputsShouldReturnVideo() {
        List<Album> albumList = List.of(new Album("album"));
        List<Artist> artistList = List.of(new Artist("artist"));
        List<Genre> genreList = List.of(new Genre("genre"));
        long videoId = 1;
        Video existingVideo = new Video("Umbrella", "url1", "2002-02-02", genreList, albumList, artistList);
        existingVideo.setId(videoId);

        List<String> albumStringInputs = Collections.emptyList();
        List<String> genreStringInputs = Collections.emptyList();
        List<String> artistStringInputs = List.of("new artist");

        VideoDTO newInfo = new VideoDTO("", "", "2024-09-06", genreStringInputs, albumStringInputs, artistStringInputs);

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.of(existingVideo));
        when(videoRepositoryMock.save(existingVideo)).thenReturn(existingVideo);

        Video response = videoService.updateVideo(videoId, newInfo);

        assertEquals("2024-09-06", response.getReleaseDate(), "ERROR: Release dates was not identical");
        assertEquals("genre", response.getGenres().get(0).getGenre(), "ERROR: Genres was not identical");

        verify(videoRepositoryMock).findById(videoId);
        verify(videoRepositoryMock).save(existingVideo);
    }

    @Test
    void deleteVideoShouldReturnString() {
        long videoId = 1;
        Video videoToBeDeleted = new Video("Umbrella", "url1", "2001-11-11");
        videoToBeDeleted.setId(videoId);

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.of(videoToBeDeleted));

        String response = videoService.deleteVideo(videoId);

        assertEquals("Video successfully deleted", response, "ERROR: Strings was not identical");

        verify(videoRepositoryMock).findById(videoId);
        verify(videoRepositoryMock).delete(videoToBeDeleted);
    }

    @Test
    void playVideoShouldReturnString() {
        String url = "url";
        Video video = new Video("The Real Slim Shady", url, "release date");
        List<Genre> genreList = Arrays.asList(new Genre("Rock"), new Genre("Pop"));
        video.setGenres(genreList);

        when(videoRepositoryMock.findVideoByUrl(url)).thenReturn(video);

        String result = videoService.playVideo(url);

        assertEquals("Playing " + video.getType() + ": " + video.getTitle(), result, "ERROR: Strings was not identical");

        verify(videoRepositoryMock).findVideoByUrl(url);
    }

    @Test
    void playVideoShouldReturnException() {
        String nonExistingURL = "url";

        when(videoRepositoryMock.findVideoByUrl(nonExistingURL)).thenReturn(null);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.playVideo(nonExistingURL);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: Video with URL not found", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "ERROR: Status Codes was not identical");
    }

    @Test
    void likeVideoShouldReturnString() {
        String url = "url";
        Video videoToLike = new Video("The Real Slim Shady", url, "releasedate");
        videoToLike.setLikes(0);
        List<Genre> genreLists = Arrays.asList(new Genre("Rock"), new Genre("Pop"));
        videoToLike.setGenres(genreLists);

        when(videoRepositoryMock.findVideoByUrl(url)).thenReturn(videoToLike);

        String result = videoService.likeVideo(url);

        assertEquals(1, videoToLike.getLikes(), "ERROR: Total likes was not identical");
        assertEquals("Liked video: " + videoToLike.getTitle(), result, "ERROR: Strings was not identical");
    }

    @Test
    void likeVideoIdNotFoundShouldReturnException() {
        String nonExistingURL = "url";

        when(videoRepositoryMock.findVideoByUrl(nonExistingURL)).thenReturn(null);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.likeVideo(nonExistingURL);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: video with URL not found", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "ERROR: Status Codes was not identical");
    }

    @Test
    void dislikeVideoShouldReturnString() {
        String url = "url";
        Video videoToDislike = new Video("The Real Slim Shady", url, "releasedate");
        videoToDislike.setDisLikes(0);

        when(videoRepositoryMock.findVideoByUrl(url)).thenReturn(videoToDislike);

        String result = videoService.dislikeVideo(url);

        assertEquals(1, videoToDislike.getDisLikes(), "ERROR: Total likes was not identical");
        assertEquals("Disliked " + videoToDislike.getType() + ": " + videoToDislike.getTitle(), result, "ERROR: Strings was not identical");
    }

    @Test
    void dislikeVideoIdNotFoundShouldReturnException() {
        String nonExistingURL = "url";

        when(videoRepositoryMock.findVideoByUrl(nonExistingURL)).thenReturn(null);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.dislikeVideo(nonExistingURL);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: video with URL not found", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "ERROR: Status Codes was not identical");
    }

    @Test
    void checkIfVideoExistByUrlShouldReturnTrue() {
        String url = "url";
        Video video = new Video("title", "url", "release");

        when(videoRepositoryMock.findVideoByUrl(url)).thenReturn(video);

        Boolean response = videoService.checkIfVideoExistByUrl(url);

        assertTrue(response, "ERROR: Response was false");

        verify(videoRepositoryMock).findVideoByUrl(url);
    }

    @Test
    void checkIfVideoExistByUrlShouldReturnFalse() {
        String url = "url";

        when(videoRepositoryMock.findVideoByUrl(url)).thenReturn(null);

        Boolean response = videoService.checkIfVideoExistByUrl(url);

        assertFalse(response, "ERROR: Response was true");

        verify(videoRepositoryMock).findVideoByUrl(url);
    }

}
