package com.lucasmoraist.devflixapi.videos.service;

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

    public List<Videos> listAll(){
        return this.videosRepository.findAll();
    }

    public Videos listById(Long id){
        return this.videosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video Not Found"));
    }

    public Videos createVideo(CreateOrUpdateVideosDTO dto){
        Videos newVideos = Videos.builder()
                .title(dto.title())
                .description(dto.description())
                .url(dto.url())
                .build();
        this.videosRepository.save(newVideos);
        return newVideos;
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
        Videos video = this.videosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video Not Found"));
        this.videosRepository.delete(video);
        return "Excluído com sucesso!";
    }

}
