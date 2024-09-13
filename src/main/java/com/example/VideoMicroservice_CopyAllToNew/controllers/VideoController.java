package com.example.VideoMicroservice_CopyAllToNew.controllers;

import com.example.VideoMicroservice_CopyAllToNew.entities.Video;
import com.example.VideoMicroservice_CopyAllToNew.dto.VideoDTO;
import com.example.VideoMicroservice_CopyAllToNew.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Video>> getAllVideo() {
        return ResponseEntity.ok(videoService.findAllVideo());
    }

    @GetMapping("/artist/{artist}")
    public ResponseEntity<List<Video>> getAllVideoForArtist(@PathVariable("artist") String artist) {
        return ResponseEntity.ok(videoService.findVideoByArtist(artist));
    }

    @GetMapping("/album/{album}")
    public ResponseEntity<List<Video>> getAllVideoForAlbum(@PathVariable("album") String album) {
        return ResponseEntity.ok(videoService.findVideoByAlbum(album));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Video>> getAllVideoForGenre(@PathVariable("genre") String genre) {
        return ResponseEntity.ok(videoService.findVideoByGenre(genre));
    }

    @GetMapping("/get/{url}")
    public ResponseEntity<Video> getVideoByUrl(@PathVariable("url") String url) {
        return ResponseEntity.ok(videoService.findVideoByUrl(url));
    }

    @PostMapping("/create")
    public ResponseEntity<Video> createVideo(@RequestBody VideoDTO videoDTO) {
        return ResponseEntity.ok(videoService.createVideo(videoDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable("id") long id, @RequestBody VideoDTO newVideoInfo) {
        return ResponseEntity.ok(videoService.updateVideo(id, newVideoInfo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable("id") long id) {
        return ResponseEntity.ok(videoService.deleteVideo(id));
    }

    @GetMapping("/play/{url}")
    public ResponseEntity<String> playVideo(@PathVariable("url") String url) {
        return ResponseEntity.ok(videoService.playVideo(url));
    }

    @GetMapping("/like/{url}")
    public ResponseEntity<String> likeVideo(@PathVariable("url") String url) {
        return ResponseEntity.ok(videoService.likeVideo(url));
    }

    @GetMapping("/dislike/{url}")
    public ResponseEntity<String> disLikeVideo(@PathVariable("url") String url) {
        return ResponseEntity.ok(videoService.disLikeVideo(url));
    }

    @GetMapping("/exists/{url}")
    public ResponseEntity<Boolean> videoExist(@PathVariable("url") String url) {
        return ResponseEntity.ok(videoService.checkIfVideoExistByUrl(url));
    }
}
