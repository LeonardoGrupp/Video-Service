package com.example.VideoMicroservice_CopyAllToNew.services;

import com.example.VideoMicroservice_CopyAllToNew.dto.VideoDTO;
import com.example.VideoMicroservice_CopyAllToNew.entities.Genre;
import com.example.VideoMicroservice_CopyAllToNew.entities.Video;
import com.example.VideoMicroservice_CopyAllToNew.vo.Album;
import com.example.VideoMicroservice_CopyAllToNew.vo.Artist;

import java.util.List;

public interface VideoServiceInterface {
    List<Video> findAllVideo();
    List<Video> findVideoByArtist(String artistName);
    List<Video> findVideoByAlbum(String albumName);
    List<Video> findVideoByGenre(String genreName);
    Video findVideoByUrl(String url);
    Video findVideoById(long id);
    Video createVideo(VideoDTO videoDTO);
    List<Genre> getAllGenres(VideoDTO videoDTO);
    List<Album> getAllAlbums(VideoDTO videoDTO);
    List<Artist> getAllArtists(VideoDTO videoDTO);
    Video updateVideo(long id, VideoDTO newVideoInfo);
    String deleteVideo(long id);
    String playVideo(String url);
    String likeVideo(String url);
    String disLikeVideo(String url);
}
