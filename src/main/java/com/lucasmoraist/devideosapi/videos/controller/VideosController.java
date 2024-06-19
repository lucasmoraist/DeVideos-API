package com.lucasmoraist.devideosapi.videos.controller;

import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.dto.CreateOrUpdateVideosDTO;
import com.lucasmoraist.devideosapi.videos.service.VideosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@Slf4j
public class VideosController {

    @Autowired
    private VideosService service;

    @GetMapping("list")
    public ResponseEntity<List<Videos>> listAll() {
        log.info("Listing all videos");
        return ResponseEntity.ok().body(this.service.listAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Videos> listById(@PathVariable Long id) {
        log.info("Listing category with id: {}", id);
        return ResponseEntity.ok().body(this.service.listById(id));
    }

    @GetMapping
    public ResponseEntity<List<Videos>> listVideosByTitle(@RequestParam(name = "search") String search) {
        log.info("Listing video by title: {}", search);
        return ResponseEntity.ok().body(this.service.listVideosByTitle(search));
    }

    @PostMapping
    public ResponseEntity<Videos> create(@RequestBody CreateOrUpdateVideosDTO dto) {
        Videos videos = this.service.createVideo(dto);
        log.info("Creating video: {}", videos);
        return ResponseEntity.ok().body(videos);
    }

    @PutMapping("{id}")
    public ResponseEntity<Videos> update(@PathVariable Long id, @RequestBody CreateOrUpdateVideosDTO dto) {
        log.info("Updating video with ID: {}", id);
        return ResponseEntity.ok(this.service.updateVideo(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("Deleting video with ID: {}", id);
        return ResponseEntity.ok(this.service.deleteVideos(id));
    }

}
