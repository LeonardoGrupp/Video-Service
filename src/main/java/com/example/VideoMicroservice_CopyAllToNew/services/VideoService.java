package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.dto.VideoDTO;
import com.example.VideoMicroservice_CopyAllToNew.entities.Album;
import com.example.VideoMicroservice_CopyAllToNew.entities.Artist;
import com.example.VideoMicroservice_CopyAllToNew.entities.Genre;
import com.example.VideoMicroservice_CopyAllToNew.entities.Video;
import com.example.VideoMicroservice_CopyAllToNew.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService implements VideoServiceInterface {
    private final VideoRepository videoRepository;
    private final GenreService genreService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    @Autowired
    public VideoService(VideoRepository videoRepository, GenreService genreService, AlbumService albumService, ArtistService artistService) {
        this.videoRepository = videoRepository;
        this.genreService = genreService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    // READ - Get All Videos
    @Override
    public List<Video> findAllVideos() {
        return videoRepository.findAll();
    }

    // READ - Get Video By Artist
    @Override
    public List<Video> findVideosByArtist(String artistName) {
        List<Video> videosByArtist = new ArrayList<>();

        for (Video video : videoRepository.findAll()) {
            for (Artist artist : video.getArtists()) {
                if (artistName.equalsIgnoreCase(artist.getName())) {
                    videosByArtist.add(video);
                }
            }
        }
        return videosByArtist;
    }

    // READ - Get Videos By Album
    @Override
    public List<Video> findVideosByAlbum(String albumName) {
        List<Video> videoByAlbum = new ArrayList<>();

        for (Video video : videoRepository.findAll()) {
            for (Album album : video.getAlbums()) {
                if (albumName.equalsIgnoreCase(album.getName())) {
                    videoByAlbum.add(video);
                }
            }
        }
        return videoByAlbum;
    }

    // READ - Get Videos By Genre
    @Override
    public List<Video> findVideosByGenre(String genreName) {
        List<Video> videoByGenre = new ArrayList<>();

        for (Video video : videoRepository.findAll()) {
            for (Genre genre : video.getGenres()) {
                if (genreName.equalsIgnoreCase(genre.getGenre())) {
                    videoByGenre.add(video);
                }
            }
        }
        return videoByGenre;
    }

    // READ - Get Video By URL
    @Override
    public Video findVideoByUrl(String url) {
        return videoRepository.findVideoByUrl(url);
    }

    // READ - Get Video By ID
    @Override
    public Video findVideoById(long id) {
        Optional<Video> optionalVideo = videoRepository.findById(id);

        if (optionalVideo.isPresent()) {
            return optionalVideo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Could not find video with id: " + id);
        }
    }

    // CREATE - Create A New Video
    @Override
    public Video createVideo(VideoDTO videoDTO) {
        if (videoDTO.getTitle().isEmpty() || videoDTO.getTitle() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Title was not provided");
        }
        if (videoDTO.getUrl().isEmpty() || videoDTO.getUrl() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video URL was not provided");
        }
        if (videoDTO.getReleaseDate().isEmpty() || videoDTO.getReleaseDate() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Release date was not provided");
        }
        if (videoDTO.getGenreInputs().isEmpty() || videoDTO.getGenreInputs() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Genre was not provided");
        }
        if (videoDTO.getAlbumInputs().isEmpty() || videoDTO.getAlbumInputs() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Album was not provided");
        }
        if (videoDTO.getArtistInputs().isEmpty() || videoDTO.getArtistInputs() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Video Artist was not provided");
        }

        videoDTO.setType("video");

        for (String genreName : videoDTO.getGenreInputs()) {
            boolean genreExist = genreService.genreExists(genreName);

            if (!genreExist) {
                genreService.create(new Genre(genreName));
            }
        }

        // READ - Check If Album Exists
        for (String albumName : videoDTO.getAlbumInputs()) {
            boolean albumExist = albumService.albumExists(albumName);

            if (!albumExist) {
                albumService.createAlbum(new Album(albumName));
            }
        }


        // READ - Check If Artist Exists
        for (String artistName : videoDTO.getArtistInputs()) {

            boolean artistExist = artistService.artistExists(artistName);

            if (!artistExist) {
                artistService.createArtist(new Artist(artistName));
            }
        }

        return videoRepository.save(new Video(videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getReleaseDate(), getAllGenres(videoDTO), getAllAlbums(videoDTO), getAllArtists(videoDTO)));
    }

    // READ - Get All Genres
    @Override
    public List<Genre> getAllGenres(VideoDTO videoDTO) {
        List<Genre> genreList = genreService.findAllGenres();

        List<Genre> genres = new ArrayList<>();

        if (genres != null) {
            for (Genre genre : genreList) {
                for (String genreName : videoDTO.getGenreInputs()) {
                    if (genreName.equalsIgnoreCase(genre.getGenre())) {
                        genres.add(genre);
                    }
                }
            }
        }

        return genres;
    }

    // READ - Get All Albums
    @Override
    public List<Album> getAllAlbums(VideoDTO videoDTO) {

        List<Album> allAlbums = albumService.getAllAlbums();
        List<Album> albumList = new ArrayList<>();

        if (allAlbums != null) {
            for (Album album : allAlbums) {
                for (String albumName : videoDTO.getAlbumInputs()) {
                    if (albumName.equalsIgnoreCase(album.getName())) {
                        albumList.add(album);
                    }
                }
            }
        }

        return albumList;
    }

    // READ - Get All Artists
    @Override
    public List<Artist> getAllArtists(VideoDTO videoDTO) {

        List<Artist> allArtists = artistService.getAllArtists();
        List<Artist> artistList = new ArrayList<>();

        if (allArtists != null) {
            for (Artist artist : allArtists) {
                for (String artistName : videoDTO.getArtistInputs()) {
                    if (artistName.equalsIgnoreCase(artist.getName())) {
                        artistList.add(artist);
                    }
                }
            }
        }

        return artistList;
    }

    // UPDATE - Update Video By ID
    @Override
    public Video updateVideo(long id, VideoDTO newVideoInfo) {
        Video existingVideo = findVideoById(id);

        if (newVideoInfo.getTitle() != null && !newVideoInfo.getTitle().isEmpty()) {
            existingVideo.setTitle(newVideoInfo.getTitle());
        }
        if (newVideoInfo.getReleaseDate() != null && !newVideoInfo.getReleaseDate().isEmpty()) {
            existingVideo.setReleaseDate(newVideoInfo.getReleaseDate());
        }
        if (newVideoInfo.getAlbumInputs() != null && !newVideoInfo.getAlbumInputs().isEmpty()) {

            List<Album> albums = getAllAlbums(newVideoInfo);

            existingVideo.setAlbums(albums);
        }
        if (newVideoInfo.getGenreInputs() != null && !newVideoInfo.getGenreInputs().isEmpty()) {

            List<Genre> genres = getAllGenres(newVideoInfo);

            existingVideo.setGenres(genres);
        }
        if (newVideoInfo.getArtistInputs() != null && !newVideoInfo.getArtistInputs().isEmpty()) {

            List<Artist> artists = getAllArtists(newVideoInfo);

            existingVideo.setArtists(artists);
        }

        return videoRepository.save(existingVideo);
    }

    // DELETE - Delete Video By ID
    @Override
    public String deleteVideo(long id) {
        Video videoToDelete = findVideoById(id);

        videoRepository.delete(videoToDelete);

        return "Video successfully deleted";
    }

    // UPDATE - Play Video
    @Override
    public String playVideo(String url) {
        Video videoToPlay = videoRepository.findVideoByUrl(url);

        if (videoToPlay == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Video with URL not found");
        }

        videoToPlay.countPlay();

        List<Genre> genres = videoToPlay.getGenres();
        for (Genre genre : genres) {
            genre.countPlay();
        }

        videoRepository.save(videoToPlay);

        return "Playing " + videoToPlay.getType() + ": " + videoToPlay.getTitle();
    }

    // UPDATE - Like A Video
    @Override
    public String likeVideo(String url) {
        Video videoToLike = videoRepository.findVideoByUrl(url);

        if (videoToLike == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: video with URL not found");
        }

        videoToLike.addLike();

        List<Genre> genres = videoToLike.getGenres();
        for (Genre genre : genres) {
            genre.addLike();
        }

        return "Liked video: " + videoToLike.getTitle();
    }

    // UPDATE - Dislike A Video
    @Override
    public String dislikeVideo(String url) {
        Video videoToDisLike = videoRepository.findVideoByUrl(url);

        if (videoToDisLike == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: video with URL not found");
        }

        videoToDisLike.addDisLike();

        return "Disliked " + videoToDisLike.getType() + ": " + videoToDisLike.getTitle();
    }

    // READ - Check If Video Exists By URL
    @Override
    public Boolean checkIfVideoExistByUrl(String url) {
        Video video = videoRepository.findVideoByUrl(url);

        return video != null;
    }
}