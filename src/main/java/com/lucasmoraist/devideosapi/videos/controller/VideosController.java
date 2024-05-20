package com.lucasmoraist.devideosapi.videos.controller;

import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.dto.CreateOrUpdateVideosDTO;
import com.lucasmoraist.devideosapi.videos.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideosService service;

    @GetMapping("list")
    public ResponseEntity<List<Videos>> listAll(){
        return ResponseEntity.ok(this.service.listAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Videos> listById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.listById(id));
    }

    @GetMapping
    public ResponseEntity<List<Videos>> listVideosByTitle(@RequestParam(name = "search") String search){
        return ResponseEntity.ok(this.service.listVideosByTitle(search));
    }

    @PostMapping("create")
    public ResponseEntity<Videos> create(@RequestBody CreateOrUpdateVideosDTO dto){
        return ResponseEntity.ok(this.service.createVideo(dto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Videos> update(@PathVariable Long id, @RequestBody CreateOrUpdateVideosDTO dto) throws Exception{
        return ResponseEntity.ok(this.service.updateVideo(id, dto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.service.deleteVideos(id));
    }

}
