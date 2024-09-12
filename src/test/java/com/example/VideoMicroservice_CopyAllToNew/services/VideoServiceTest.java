package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.dto.VideoDTO;
import com.example.VideoMicroservice_CopyAllToNew.entities.Genre;
import com.example.VideoMicroservice_CopyAllToNew.repositories.VideoRepository;
import com.example.VideoMicroservice_CopyAllToNew.entities.Video;
import com.example.VideoMicroservice_CopyAllToNew.vo.Album;
import com.example.VideoMicroservice_CopyAllToNew.vo.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class VideoServiceTest {
    private VideoRepository videoRepositoryMock;
    private VideoService videoService;
    private RestTemplate restTemplate;
    private GenreService genreService;

    @BeforeEach
    void setUp() {
        videoRepositoryMock = mock(VideoRepository.class);
        restTemplate = mock(RestTemplate.class);
        genreService = mock(GenreService.class);
        videoService = new VideoService(videoRepositoryMock, restTemplate, genreService);
    }

    @Test
    void findAllVideoShouldReturnList() {
        List<Video> allVideoList = Arrays.asList(
                new Video("The Way I Am", "url1", "1999-01-01"), // The Marshall Mathers LP
                new Video("Slim Shady", "url2", "1998-02-03"), // The Slim Shady LP
                new Video("Eminem låt 3", "url3", "1997-07-07"), // The Marshall Mathers LP
                new Video("Diamonds", "url4", "2005-06-05"), // Loud
                new Video("Waiting For Love", "url5", "2011-11-02"), // Stories
                new Video("Talk To Myself", "url6", "2013-12-31"), // Stories
                new Video("Hey Brother", "url7", "2010-02-05") // True
        );

        when(videoRepositoryMock.findAll()).thenReturn(allVideoList);

        List<Video> response = videoService.findAllVideo();

        assertEquals(allVideoList, response, "ERROR: Video lists was not identical");
        assertEquals(7, response.size(), "ERROR: Sizes was not identical");
        assertEquals("The Way I Am", response.get(0).getTitle(), "ERROR: Titles was not identical");

        verify(videoRepositoryMock).findAll();
    }

    @Test
    void findVideoByArtistShouldReturnList() {
        Artist eminem = new Artist("Eminem");
        Album album = new Album("Eminem album");
        Genre genre = new Genre("Hip-Hop");
        List<Video> eminemVideoList = Arrays.asList(
                new Video("The Way I Am", "url1", "1999-01-01", Arrays.asList(genre), Arrays.asList(album), Arrays.asList(eminem)),
                new Video("Slim Shady", "url2", "1998-02-03", Arrays.asList(genre), Arrays.asList(album), Arrays.asList(eminem)),
                new Video("Eminem låt 3", "url3", "1997-07-07", Arrays.asList(genre), Arrays.asList(album), Arrays.asList(eminem))
        );

        when(videoRepositoryMock.findAll()).thenReturn(eminemVideoList);

        List<Video> response = videoService.findVideoByArtist("Eminem");

        assertEquals(eminemVideoList, response, "ERROR: Video lists was not identical");
        assertEquals(3, response.size(), "ERROR: Sizes was not identical");
        assertEquals("Slim Shady", response.get(1).getTitle(), "ERROR: Artists was not identical");

        verify(videoRepositoryMock).findAll();
    }

    @Test
    void findVideoByAlbumShouldReturnList() {
        Artist avicii = new Artist("Avicii");
        Album stories = new Album("Stories");
        Genre genre = new Genre("EDM");
        List<Video> albumVideoList = Arrays.asList(
                new Video("Waiting For Love", "url1", "2011-11-02", Arrays.asList(genre), Arrays.asList(stories), Arrays.asList(avicii)),
                new Video("Talk To Myself", "url2", "2013-12-31", Arrays.asList(genre), Arrays.asList(stories), Arrays.asList(avicii))
        );

        when(videoRepositoryMock.findAll()).thenReturn(albumVideoList);

        List<Video> response = videoService.findVideoByAlbum("Stories");

        assertEquals(albumVideoList, response, "ERROR: Video lists was not identical");
        assertEquals(2, response.size(), "ERROR: Sizes was not identical");
        assertEquals(stories, response.get(0).getAlbums().get(0), "ERROR: Albums was not identical");

        verify(videoRepositoryMock).findAll();
    }

    @Test
    void findVideoByGenreShouldReturnList() {
        Artist avicii = new Artist("Avicii");
        Album stories = new Album("Stories");
        Genre genre = new Genre("EDM");
        List<Video> genreVideoList = Arrays.asList(
                new Video("Waiting For Love", "url1", "2011-11-02", Arrays.asList(genre), Arrays.asList(stories), Arrays.asList(avicii)),
                new Video("Talk To Myself", "url2", "2013-12-31", Arrays.asList(genre), Arrays.asList(stories), Arrays.asList(avicii))
        );

        when(videoRepositoryMock.findAll()).thenReturn(genreVideoList);

        List<Video> response = videoService.findVideoByGenre("EDM");

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
        // Arrange: Prepare mock data
        List<String> albumInputs = Arrays.asList("Album1", "Album2");
        List<String> artistInputs = Arrays.asList("Artist1", "Artist2");
        List<String> genreInputs = Arrays.asList("Pop", "RNB");
        VideoDTO videoDTO = new VideoDTO("SongTitle", "url1", "2024-09-02", genreInputs, albumInputs, artistInputs);

        // Mock album existence check (returns true)
        ResponseEntity<Boolean> albumExistsResponse = new ResponseEntity<>(true, HttpStatus.OK);
        doReturn(albumExistsResponse).when(restTemplate).getForEntity(contains("exists"), eq(Boolean.class));

        // Mock artist existence check (returns true)
        ResponseEntity<Boolean> artistExistsResponse = new ResponseEntity<>(true, HttpStatus.OK);
        doReturn(artistExistsResponse).when(restTemplate).getForEntity(contains("exists"), eq(Boolean.class));

        // Mock the list of albums returned by the album-service
        Album[] albums = { new Album("Album1"), new Album("Album2") };
        ResponseEntity<Album[]> albumListResponse = new ResponseEntity<>(albums, HttpStatus.OK);
        doReturn(albumListResponse).when(restTemplate).getForEntity(contains("getAllAlbums"), eq(Album[].class));

        // Mock the list of artists returned by the artist-service
        Artist[] artists = { new Artist("Artist1"), new Artist("Artist2") };
        ResponseEntity<Artist[]> artistListResponse = new ResponseEntity<>(artists, HttpStatus.OK);
        doReturn(artistListResponse).when(restTemplate).getForEntity(contains("getAllArtists"), eq(Artist[].class));

        // Mock the list of genres returned by the genreService
        List<Genre> genres = Arrays.asList(new Genre("Pop"), new Genre("RNB"));
        when(genreService.findAllGenres()).thenReturn(genres);


        // Mock the repository save
        Video savedVideo = new Video("SongTitle", "url1", "2024-09-02", genres, Arrays.asList(albums), Arrays.asList(artists));
        when(videoRepositoryMock.save(any(Video.class))).thenReturn(savedVideo);

        // Act: Call the service method
        Video result = videoService.createVideo(videoDTO);

        // Assert: Verify that the result matches the expected saved video
        assertEquals(videoDTO.getTitle(), result.getTitle(), "ERROR: Titles was not identical");
        assertEquals(videoDTO.getReleaseDate(), result.getReleaseDate(), "ERROR: Release dates was not identical");
        assertEquals(videoDTO.getGenreInputs().get(0), result.getGenres().get(0).getGenre(), "ERROR: Genres was not identical");
        assertEquals(2, result.getAlbums().size(), "ERROR: Album list sizes was not identical");
        assertEquals(2, result.getArtists().size(), "ERROR: Artist list sizes was not identical");

        // Verify the spy was used and calls were made
        verify(restTemplate, times(4)).getForEntity(contains("exists"), eq(Boolean.class));
        verify(restTemplate, times(1)).getForEntity(contains("getAllAlbums"), eq(Album[].class));
        verify(restTemplate, times(1)).getForEntity(contains("getAllArtists"), eq(Artist[].class));
        verify(genreService).findAllGenres();
    }

    @Test
    void createVideoArtistAndAlbumDontExistShouldReturnVideo() {
        List<String> albumInputs = Arrays.asList("Album1", "Album2");
        List<String> artistInputs = Arrays.asList("Artist1", "Artist2");
        List<String> genreInputs = Arrays.asList("Pop", "RNB");
        VideoDTO videoDTO = new VideoDTO("SongTitle", "url1", "2024-09-02", genreInputs, albumInputs, artistInputs);

        // Mock album existence check (returns true)
        ResponseEntity<Boolean> albumExistsResponse = new ResponseEntity<>(true, HttpStatus.OK);
        doReturn(albumExistsResponse).when(restTemplate).getForEntity(contains("exists"), eq(Boolean.class));

        // Mock artist existence check (returns false for both artists)
        ResponseEntity<Boolean> artistExistsResponse = new ResponseEntity<>(false, HttpStatus.OK);
        doReturn(artistExistsResponse).when(restTemplate).getForEntity(contains("exists"), eq(Boolean.class));

        // Mock the list of albums returned by the album-service
        ResponseEntity<Album> createAlbumResponse1 = new ResponseEntity<>(new Album("Album1"), HttpStatus.OK);
        ResponseEntity<Album> createAlbumResponse2 = new ResponseEntity<>(new Album("Album2"), HttpStatus.OK);
        doReturn(createAlbumResponse1).when(restTemplate).postForEntity(contains("createAlbum"), any(Album.class), eq(Album.class));
        doReturn(createAlbumResponse2).when(restTemplate).postForEntity(contains("createAlbum"), any(Album.class), eq(Album.class));


        // Mock artist creation when artist does not exist
        ResponseEntity<Artist> createArtistResponse1 = new ResponseEntity<>(new Artist("Artist1"), HttpStatus.OK);
        ResponseEntity<Artist> createArtistResponse2 = new ResponseEntity<>(new Artist("Artist2"), HttpStatus.OK);
        doReturn(createArtistResponse1).when(restTemplate).postForEntity(contains("createArtist"), any(Artist.class), eq(Artist.class));
        doReturn(createArtistResponse2).when(restTemplate).postForEntity(contains("createArtist"), any(Artist.class), eq(Artist.class));

        // Mock the list of genres returned by the genreService
        List<Genre> genres = Arrays.asList(new Genre("Pop"), new Genre("RNB"));
        when(genreService.findAllGenres()).thenReturn(genres);

        // Mock the repository save
        Video savedVideo = new Video("SongTitle", "url1", "2024-09-02", genres, Arrays.asList(new Album("Album1"), new Album("Album2")), Arrays.asList(new Artist("Artist1"), new Artist("Artist2")));
        when(videoRepositoryMock.save(any(Video.class))).thenReturn(savedVideo);

        // Act: Call the service method
        Video result = videoService.createVideo(videoDTO);

        // Assert: Verify that the result matches the expected saved video
        assertEquals(videoDTO.getTitle(), result.getTitle(), "ERROR: Titles was not identical");
        assertEquals(videoDTO.getReleaseDate(), result.getReleaseDate(), "ERROR: Release dates was not identical");
        assertEquals(2, result.getAlbums().size(), "ERROR: Album list sizes was not identical");
        assertEquals(2, result.getArtists().size(), "ERROR: Artist list sizes was not identical");

        // Verify that the artist existence check was made and the creation calls were triggered
        verify(restTemplate, times(4)).getForEntity(contains("exists"), eq(Boolean.class));
        verify(restTemplate, times(2)).postForEntity(contains("createAlbum"), any(Album.class), eq(Album.class));
        verify(restTemplate, times(2)).postForEntity(contains("createArtist"), any(Artist.class), eq(Artist.class));
        verify(genreService).findAllGenres();
    }

    @Test
    void createVideoNoTitleShouldReturnException() {
        List<String> albumStringList = Arrays.asList("Good Girl Gone Bad");
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
        List<String> albumStringList = Arrays.asList("Good Girl Gone Bad");
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
        List<String> albumStringList = Arrays.asList("Good Girl Gone Bad");
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
    void createVideoNoGenreShouldReturnException() {
        VideoDTO videoDTO = new VideoDTO("title", "url", "release");
        List<String> albumStringList = Arrays.asList("Good Girl Gone Bad");
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
        List<String> albumStringList = Arrays.asList("Good Girl Gone Bad");
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
    void updateVideoShouldReturnVideo() {
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

        Video updatedVideo = new Video("Umbrella", "url1", "2002-02-02");

        when(videoRepositoryMock.findById(videoId)).thenReturn(Optional.of(existingVideo));
        when(videoRepositoryMock.save(existingVideo)).thenReturn(updatedVideo);

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
        List<Album> albumList = Arrays.asList(new Album("album"));
        List<Artist> artistList = Arrays.asList(new Artist("artist"));
        List<Genre> genreList = Arrays.asList(new Genre("Pop"));
        long videoId = 1;
        Video existingVideo = new Video("Umbrella", "url1", "2002-02-02", genreList, albumList, artistList);
        existingVideo.setId(videoId);

        List<String> albumStringInputs = Arrays.asList("new album");
        List<String> artistStringInputs = Collections.emptyList();
        List<String> genreStringInputs = Arrays.asList("new genre");

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
        List<Album> albumList = Arrays.asList(new Album("album"));
        List<Artist> artistList = Arrays.asList(new Artist("artist"));
        List<Genre> genreList = Arrays.asList(new Genre("Pop"));
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
        List<Album> albumList = Arrays.asList(new Album("album"));
        List<Artist> artistList = Arrays.asList(new Artist("artist"));
        List<Genre> genreList = Arrays.asList(new Genre("genre"));
        long videoId = 1;
        Video existingVideo = new Video("Umbrella", "url1", "2002-02-02", genreList, albumList, artistList);
        existingVideo.setId(videoId);

        List<String> albumStringInputs = Collections.emptyList();
        List<String> genreStringInputs = Collections.emptyList();
        List<String> artistStringInputs = Arrays.asList("new artist");

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
        Video video = new Video("The Real Slim Shady", url, "releasdate");
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
    void disLikeVideoShouldReturnString() {
        String url = "url";
        Video videoToDisLike = new Video("The Real Slim Shady", url, "releasedate");
        videoToDisLike.setDisLikes(0);

        when(videoRepositoryMock.findVideoByUrl(url)).thenReturn(videoToDisLike);

        String result = videoService.disLikeVideo(url);

        assertEquals(1, videoToDisLike.getDisLikes(), "ERROR: Total likes was not identical");
        assertEquals("Disliked " + videoToDisLike.getType() + ": " + videoToDisLike.getTitle(), result, "ERROR: Strings was not identical");
    }

    @Test
    void disLikeVideoIdNotFoundShouldReturnException() {
        String nonExistingURL = "url";

        when(videoRepositoryMock.findVideoByUrl(nonExistingURL)).thenReturn(null);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            videoService.disLikeVideo(nonExistingURL);
        }, "ERROR: Exception was not thrown");

        assertEquals("ERROR: video with URL not found", response.getReason(), "ERROR: Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "ERROR: Status Codes was not identical");
    }
}