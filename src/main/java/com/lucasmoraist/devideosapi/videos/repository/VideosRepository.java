package com.lucasmoraist.devideosapi.videos.repository;

import com.lucasmoraist.devideosapi.videos.domain.Videos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface VideosRepository extends JpaRepository<Videos, Long> {

    @Query(value = "SELECT * FROM t_videos WHERE category_id = :idCategory", nativeQuery = true)
    Page<List<Videos>> findVideoByCategory(Long idCategory, Pageable pageable);

    @Query(value = "SELECT * FROM t_videos WHERE title LIKE %:search%", nativeQuery = true)
    Page<List<Videos>> findVideosByTitle(@PathVariable String search, Pageable pageable);

    @Query(value = "SELECT * FROM t_videos ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Videos> findVideosFree();
}
