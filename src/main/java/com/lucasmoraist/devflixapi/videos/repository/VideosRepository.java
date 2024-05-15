package com.lucasmoraist.devflixapi.videos.repository;

import com.lucasmoraist.devflixapi.videos.domain.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideosRepository extends JpaRepository<Videos, Long> {
}
