package com.lucasmoraist.devflixapi.videos.service;

import com.lucasmoraist.devflixapi.category.repository.CategoryRepository;
import com.lucasmoraist.devflixapi.videos.domain.Videos;
import com.lucasmoraist.devflixapi.videos.dto.CreateOrUpdateVideosDTO;
import com.lucasmoraist.devflixapi.videos.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideosService {

    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Videos> listAll(){
        return this.videosRepository.findAll();
    }

    public Videos listById(Long id){
        return this.videosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video Not Found"));
    }

    public List<Videos> listByIdCategory(Long idCategory){
        var id = this.categoryRepository.findById(idCategory)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));

        return this.videosRepository.findVideoByCategory(id.getId())
                .orElseThrow(() -> new RuntimeException("Videos Not Found"));
    }

    public List<Videos> listVideosByTitle(String search){
        return this.videosRepository.findVideosByTitle(search)
                .orElseThrow(() -> new RuntimeException("Video Not Found"));
    }

    public Videos createVideo(CreateOrUpdateVideosDTO dto){

        if(dto.idCategory() == null){
            var category = this.categoryRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Category Not Found"));

            Videos newVideos = Videos.builder()
                    .title(dto.title())
                    .description(dto.description())
                    .url(dto.url())
                    .category(category)
                    .build();

            this.videosRepository.save(newVideos);
            return newVideos;
        }else{
            var category = this.categoryRepository.findById(dto.idCategory())
                    .orElseThrow(() -> new RuntimeException("Category Not Found"));

            Videos newVideos = Videos.builder()
                    .title(dto.title())
                    .description(dto.description())
                    .url(dto.url())
                    .category(category)
                    .build();

            this.videosRepository.save(newVideos);
            return newVideos;
        }
    }
    
    public Videos updateVideo(Long id, CreateOrUpdateVideosDTO dto) throws Exception{
        Optional<Videos> optionalVideos = this.videosRepository.findById(id);
        
        if(optionalVideos.isEmpty()){
            throw new Exception("Video Not Found");
        }

        var video = optionalVideos.get();
        video.setTitle(dto.title());
        video.setDescription(dto.description());
        video.setUrl(dto.url());
        
        this.videosRepository.save(video);
        return video;
    }

    public String deleteVideos(Long id){
        if(id == 1L){
            return "Não é possível excluir essa categoria";
        }else{
            Videos video = this.videosRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Video Not Found"));
            this.videosRepository.delete(video);
            return "Excluído com sucesso!";
        }
    }

}
