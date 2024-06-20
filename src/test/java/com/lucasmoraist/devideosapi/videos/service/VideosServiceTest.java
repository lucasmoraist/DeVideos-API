package com.lucasmoraist.devideosapi.videos.service;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.domain.ColorsEnum;
import com.lucasmoraist.devideosapi.category.dto.CreateOrUpdateCategoriesDTO;
import com.lucasmoraist.devideosapi.category.service.CategoryService;
import com.lucasmoraist.devideosapi.exception.ResourceNotFound;
import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.dto.CreateOrUpdateVideosDTO;
import com.lucasmoraist.devideosapi.videos.repository.VideosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class VideosServiceTest {

    @InjectMocks
    private VideosService videosService;

    @Mock
    private VideosRepository videosRepository;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a list of videos")
    void case01() {
        Videos video = new Videos();
        when(videosRepository.findAll()).thenReturn(Collections.singletonList(video));

        assertEquals(Collections.singletonList(video), videosService.findAllVideos());
        verify(videosRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Must return a video by id")
    void case02() {
        Videos video = new Videos();
        when(videosRepository.findById(anyLong())).thenReturn(Optional.of(video));

        assertEquals(video, videosService.findVideoById(1L));
        verify(videosRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Must save a video")
    void case03() {
        Category category = new Category();
        when(categoryService.listCategoryById(anyLong())).thenReturn(category);

        Videos video = Videos.builder()
                .title("title")
                .description("description")
                .url("url")
                .category(category)
                .build();
        when(videosRepository.save(any(Videos.class))).thenReturn(video);

        CreateOrUpdateVideosDTO dto = new CreateOrUpdateVideosDTO("title", "description", "url", 1L);
        Videos result = videosService.saveVideos(dto);

        assertEquals(video, result);
        verify(videosRepository, times(1)).save(any(Videos.class));
    }

    @Test
    @DisplayName("Should update a video")
    void case04() {
        Videos video = new Videos();
        when(videosRepository.findById(anyLong())).thenReturn(Optional.of(video));
        when(videosRepository.save(any(Videos.class))).thenReturn(video);

        CreateOrUpdateVideosDTO dto = new CreateOrUpdateVideosDTO("title", "description", "url", 1L);
        Videos result = videosService.updateVideo(1L, dto);

        assertEquals(video, result);
        verify(videosRepository, times(1)).save(any(Videos.class));
    }

    @Test
    @DisplayName("Should delete a video")
    void case05() {
        Videos video = new Videos();
        when(videosRepository.findById(anyLong())).thenReturn(Optional.of(video));

        String result = videosService.deleteVideos(1L);

        assertEquals("Excluído com sucesso!", result);
        verify(videosRepository, times(1)).delete(any(Videos.class));
    }

    @Test
    @DisplayName("Should throw ResourceNotFound when video not found by ID")
    void case06() {
        when(videosRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> videosService.findVideoById(1L));
    }

    @Test
    @DisplayName("Should throw ResourceNotFound when video not found by category ID")
    void case07() {
        when(categoryService.listCategoryById(anyLong())).thenReturn(null);

        assertThrows(ResourceNotFound.class, () -> videosService.findVideoByIdCategory(1L, Pageable.unpaged()));
    }

    @Test
    @DisplayName("Should throw ResourceNotFound when video not found by title")
    void case08() {
        when(videosRepository.findVideosByTitle(anyString(), any(Pageable.class))).thenReturn(Page.empty());

        assertThrows(ResourceNotFound.class, () -> videosService.findVideosByTitle("non-existing-title", Pageable.unpaged()));
    }

//    @Test
//    @DisplayName("Should throw IllegalArgumentException when saving a video with invalid data")
//    void case09() {
//        CreateOrUpdateCategoriesDTO dtoCategory = new CreateOrUpdateCategoriesDTO("teste", ColorsEnum.CINZA);
//        categoryService.createCategory(dtoCategory);
//        CreateOrUpdateVideosDTO dto = new CreateOrUpdateVideosDTO(null, null, null, 1L);
//
//        assertThrows(IllegalArgumentException.class, () -> videosService.saveVideos(dto));
//    }

    @Test
    @DisplayName("Should throw ResourceNotFound when updating a non-existing video")
    void case10() {
        when(videosRepository.findById(anyLong())).thenReturn(Optional.empty());

        CreateOrUpdateVideosDTO dto = new CreateOrUpdateVideosDTO("title", "description", "url", 1L);
        assertThrows(ResourceNotFound.class, () -> videosService.updateVideo(1L, dto));
    }

    @Test
    @DisplayName("Should throw ResourceNotFound when deleting a non-existing video")
    void case11() {
        when(videosRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> videosService.deleteVideos(1L));
    }
}