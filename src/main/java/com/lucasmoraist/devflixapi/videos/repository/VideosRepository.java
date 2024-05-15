package com.lucasmoraist.devflixapi.videos.repository;

import com.lucasmoraist.devflixapi.category.domain.Category;
import com.lucasmoraist.devflixapi.videos.domain.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VideosRepository extends JpaRepository<Videos, Long> {

    @Query(value = "SELECT * FROM t_videos WHERE category_id = :idCategory", nativeQuery = true)
    Optional<List<Videos>> findVideoByCategory(Long idCategory);

}
