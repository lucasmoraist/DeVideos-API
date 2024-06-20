package com.lucasmoraist.devideosapi.videos.controller;

import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.dto.CreateOrUpdateVideosDTO;
import com.lucasmoraist.devideosapi.videos.service.VideosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videos")
@Slf4j
@Tag(name = "Create Video")
public class CreateVideosController {

    @Autowired
    private VideosService service;

    @Operation(
            summary = "Create new video",
            description = "Should create a new instance of the 'Videos' object and insert it into the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Should return information from the created video"
    )
    @PostMapping
    public ResponseEntity<Videos> create(@RequestBody CreateOrUpdateVideosDTO dto) {
        Videos videos = this.service.saveVideos(dto);
        log.info("Creating video: {}", videos);
        return ResponseEntity.ok().body(videos);
    }

}
