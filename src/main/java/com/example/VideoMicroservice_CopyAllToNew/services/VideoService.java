package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.dto.VideoDTO;
import com.example.VideoMicroservice_CopyAllToNew.entities.Album;
import com.example.VideoMicroservice_CopyAllToNew.entities.Artist;
import com.example.VideoMicroservice_CopyAllToNew.entities.Genre;
import com.example.VideoMicroservice_CopyAllToNew.entities.Video;
import com.example.VideoMicroservice_CopyAllToNew.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService implements VideoServiceInterface {
    private VideoRepository videoRepository;
    private GenreService genreService;
    private AlbumService albumService;
    private ArtistService artistService;

    @Autowired
    public VideoService(VideoRepository videoRepository, GenreService genreService, AlbumService albumService, ArtistService artistService) {
        this.videoRepository = videoRepository;
        this.genreService = genreService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @Override
    public List<Video> findAllVideo() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> findVideoByArtist(String artistName) {
        List<Video> videoByArtist = new ArrayList<>();

        for (Video video : videoRepository.findAll()) {
            for (Artist artist : video.getArtists()) {
                if (artistName.toLowerCase().equals(artist.getName().toLowerCase())) {
                    videoByArtist.add(video);
                }
            }
        }

        return videoByArtist;
    }

    @Override
    public List<Video> findVideoByAlbum(String albumName) {
        List<Video> videoByAlbum = new ArrayList<>();

        for (Video video : videoRepository.findAll()) {
            for (Album album : video.getAlbums()) {
                if (albumName.toLowerCase().equals(album.getName().toLowerCase())) {
                    videoByAlbum.add(video);
                }
            }
        }

        return videoByAlbum;
    }

    @Override
    public List<Video> findVideoByGenre(String genreName) {
        List<Video> videoByGenre = new ArrayList<>();

        for (Video video : videoRepository.findAll()) {
            for (Genre genre : video.getGenres()) {
                if (genreName.toLowerCase().equals(genre.getGenre().toLowerCase())) {
                    videoByGenre.add(video);
                }
            }
        }

        return videoByGenre;
    }

    @Override
    public Video findVideoByUrl(String url) {
        return videoRepository.findVideoByUrl(url);
    }

    @Override
    public Video findVideoById(long id) {
        Optional<Video> optionalVideo = videoRepository.findById(id);

        if (optionalVideo.isPresent()) {
            return optionalVideo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Could not find video with id: " + id);
        }
    }

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

        // Check to see if album exist
        for (String albumName : videoDTO.getAlbumInputs()) {

            boolean albumExist = albumService.albumExist(albumName);

            if (!albumExist) {
                albumService.createAlbum(new Album(albumName));
            }

        }

        // Check to see if artist exist
        for (String artistName : videoDTO.getArtistInputs()) {

            boolean artistExist = artistService.artistExist(artistName);

            if (!artistExist) {
                artistService.createArtist(new Artist(artistName));
            }
        }


        return videoRepository.save(new Video(videoDTO.getTitle(), videoDTO.getUrl(), videoDTO.getReleaseDate(), getAllGenres(videoDTO), getAllAlbums(videoDTO), getAllArtists(videoDTO)));
    }

    @Override
    public List<Genre> getAllGenres(VideoDTO videoDTO) {
        List<Genre> genreList = genreService.findAllGenres();

        List<Genre> genres = new ArrayList<>();

        if (genres != null) {
            for (Genre genre : genreList) {
                for (String genreName : videoDTO.getGenreInputs()) {
                    if (genreName.toLowerCase().equals(genre.getGenre().toLowerCase())) {
                        genres.add(genre);
                    }
                }
            }
        }

        return genres;
    }

    @Override
    public List<Album> getAllAlbums(VideoDTO videoDTO) {
        List<Album> allAlbums = albumService.getAllAlbums();

        List<Album> albumList = new ArrayList<>();

        if (allAlbums != null) {
            for (Album album : allAlbums) {
                for (String albumName : videoDTO.getAlbumInputs()) {
                    if (albumName.toLowerCase().equals(album.getName().toLowerCase())) {
                        albumList.add(album);
                    }
                }
            }
        }

        return albumList;
    }

    @Override
    public List<Artist> getAllArtists(VideoDTO videoDTO) {
        List<Artist> allArtists = artistService.getAllArtists();

        List<Artist> artistList = new ArrayList<>();
        if (allArtists != null) {
            for (Artist artist : allArtists) {
                for (String artistName : videoDTO.getArtistInputs()) {
                    if (artistName.toLowerCase().equals(artist.getName().toLowerCase())) {
                        artistList.add(artist);
                    }
                }
            }
        }

        return artistList;
    }

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

    @Override
    public String deleteVideo(long id) {
        Video videoToDelete = findVideoById(id);

        videoRepository.delete(videoToDelete);

        return "Video successfully deleted";
    }

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

    @Override
    public String disLikeVideo(String url) {
        Video videoToDisLike = videoRepository.findVideoByUrl(url);

        if (videoToDisLike == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: video with URL not found");
        }

        videoToDisLike.addDisLike();

        return "Disliked " + videoToDisLike.getType() + ": " + videoToDisLike.getTitle();
    }

    @Override
    public Boolean checkIfVideoExistByUrl(String url) {
        Video video = videoRepository.findVideoByUrl(url);

        return video != null;
    }
}