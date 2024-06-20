package com.lucasmoraist.devideosapi.videos.repository;

import com.lucasmoraist.devideosapi.videos.domain.Videos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class VideosRepositoryTest {

    @Autowired
    private VideosRepository videoRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    @DisplayName("Test findVideoByCategory with valid id - Success")
    void testFindVideoByCategoryWithInvalidId() {
        Long invalidCategoryId = 9999L;
        Pageable pageable = PageRequest.of(0, 5);

        Page<List<Videos>> videosPage = videoRepository.findVideoByCategory(invalidCategoryId, pageable);

        assertThat(videosPage).isNotNull();
        assertThat(videosPage.getContent()).isEmpty();
    }

    @Test
    @DisplayName("Test findVideosByTitle with invalid search - Success")
    void testFindVideosByTitleWithInvalidSearch() {
        String invalidSearch = "invalid";
        Pageable pageable = PageRequest.of(0, 5);

        Page<List<Videos>> videosPage = videoRepository.findVideosByTitle(invalidSearch, pageable);

        assertThat(videosPage).isNotNull();
        assertThat(videosPage.getContent()).isEmpty();
    }

    @Test
    @DisplayName("Test findVideosFree limit - Success")
    void testFindVideosFreeLimit() {

        for (int i = 0; i < 5; i++) {
            Videos video = Videos.builder()
                    .title("Video " + i)
                    .description("Description " + i)
                    .url("https://www.youtube.com/watch?v=" + i)
                    .build();
            entityManager.persist(video);
        }

        List<Videos> videos = videoRepository.findVideosFree();

        assertThat(videos).isNotNull();
        assertThat(videos).hasSize(5);
    }

}