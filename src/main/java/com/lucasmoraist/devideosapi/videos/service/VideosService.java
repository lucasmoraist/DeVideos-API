package com.lucasmoraist.devideosapi.videos.service;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.service.CategoryService;
import com.lucasmoraist.devideosapi.exception.ResourceNotFound;
import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.dto.CreateOrUpdateVideosDTO;
import com.lucasmoraist.devideosapi.videos.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideosService {

    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Videos> listAll() {
        return this.videosRepository.findAll();
    }

    public Videos listById(Long id) {
        return this.findVideoById(id);
    }

    public List<Videos> listVideosByIdCategory(Long idCategory) {
        Category id = this.categoryService.listCategoryById(idCategory);

        return this.videosRepository.findVideoByCategory(id.getId())
                .orElseThrow(() -> new ResourceNotFound("Videos Not Found"));
    }

    public List<Videos> listVideosByTitle(String search) {
        return this.videosRepository.findVideosByTitle(search)
                .orElseThrow(() -> new ResourceNotFound("Video Not Found"));
    }

    public Videos createVideo(CreateOrUpdateVideosDTO dto) {
        Category category;
        Videos newVideos = Videos.builder()
                .title(dto.title())
                .description(dto.description())
                .url(dto.url())
                .build();

        if (dto.idCategory() == null) {
            category = this.categoryService.listCategoryById(1L);
        } else {
            category = this.categoryService.listCategoryById(dto.idCategory());
        }
        newVideos.setCategory(category);

        this.videosRepository.save(newVideos);

        return newVideos;
    }

    public Videos updateVideo(Long id, CreateOrUpdateVideosDTO dto) {
        Videos video = this.findVideoById(id);

        video.setTitle(dto.title());
        video.setDescription(dto.description());
        video.setUrl(dto.url());

        this.videosRepository.save(video);
        return video;
    }

    public String deleteVideos(Long id) {
        Videos video = this.findVideoById(id);
        this.videosRepository.delete(video);
        return "Excluído com sucesso!";
    }

    private Videos findVideoById(Long id) {
        return this.videosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Video Not Found"));
    }

}
