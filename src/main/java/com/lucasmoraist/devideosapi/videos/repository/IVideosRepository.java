package com.lucasmoraist.devideosapi.videos.repository;

import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.dto.CreateOrUpdateVideosDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVideosRepository {
    List<Videos> findAllVideos();
    Videos findVideoById(Long id);
    Page<List<Videos>> findVideoByIdCategory(Long idCategory, Pageable pageable);
    Page<List<Videos>> findVideosByTitle(String search, Pageable pageable);
    List<Videos> findVideosFree();
    Videos saveVideos(CreateOrUpdateVideosDTO dto);
    Videos updateVideo(Long id, CreateOrUpdateVideosDTO dto);
    String deleteVideos(Long id);
}
