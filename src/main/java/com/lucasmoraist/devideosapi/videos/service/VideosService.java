package com.lucasmoraist.devideosapi.videos.service;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.service.CategoryService;
import com.lucasmoraist.devideosapi.exception.ResourceNotFound;
import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.dto.CreateOrUpdateVideosDTO;
import com.lucasmoraist.devideosapi.videos.repository.IVideosRepository;
import com.lucasmoraist.devideosapi.videos.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VideosService implements IVideosRepository {

    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Videos> findAllVideos() {
        return this.videosRepository.findAll();
    }

    @Override
    public Videos findVideoById(Long id) {
        return this.videosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Video Not Found"));
    }

    @Override
    public Page<List<Videos>> findVideoByIdCategory(Long idCategory, Pageable pageable) {
        Category category = this.categoryService.listCategoryById(idCategory);
        if (category == null) throw new ResourceNotFound("Category Not Found");
        return this.videosRepository.findVideoByCategory(category.getId(), pageable);
    }

    @Override
    public Page<List<Videos>> findVideosByTitle(String search, Pageable pageable) {
        Page<List<Videos>> videos = this.videosRepository.findVideosByTitle(search, pageable);
        if (videos.isEmpty()) throw new ResourceNotFound("Video Not Found");
        return videos;
    }

    @Override
    public List<Videos> findVideosFree() {
        return this.videosRepository.findVideosFree();
    }

    @Override
    public Videos saveVideos(CreateOrUpdateVideosDTO dto) {

        Videos newVideos = this.instanceVideos(dto);

        this.videosRepository.save(newVideos);

        return newVideos;
    }

    @Override
    public Videos updateVideo(Long id, CreateOrUpdateVideosDTO dto) {
        Videos video = this.findVideoById(id);

        video.setTitle(dto.title());
        video.setDescription(dto.description());
        video.setUrl(dto.url());

        this.videosRepository.save(video);
        return video;
    }

    @Override
    public String deleteVideos(Long id) {
        Videos video = this.findVideoById(id);
        this.videosRepository.delete(video);
        return "Excluído com sucesso!";
    }

    private Category getCategory(Long idCategory) {
        return this.categoryService.listCategoryById(
                Objects.requireNonNullElse(idCategory, 1L));
    }

    private Videos instanceVideos(CreateOrUpdateVideosDTO dto) {
        if (dto.title() == null || dto.title().isEmpty() || dto.description() == null || dto.description().isEmpty() || dto.url() == null || dto.url().isEmpty()) {
            throw new IllegalArgumentException("Invalid data");
        }

        return Videos.builder()
                .title(dto.title())
                .description(dto.description())
                .url(dto.url())
                .category(this.getCategory(dto.idCategory()))
                .build();
    }
}
