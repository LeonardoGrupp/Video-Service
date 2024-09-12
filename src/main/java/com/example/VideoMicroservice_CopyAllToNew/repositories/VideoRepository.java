package com.example.VideoMicroservice_CopyAllToNew.repositories;

import com.example.VideoMicroservice_CopyAllToNew.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Video findVideoByUrl(String url);
}
