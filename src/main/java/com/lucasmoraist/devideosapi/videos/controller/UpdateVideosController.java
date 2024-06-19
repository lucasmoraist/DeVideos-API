package com.lucasmoraist.devideosapi.videos.controller;

import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.dto.CreateOrUpdateVideosDTO;
import com.lucasmoraist.devideosapi.videos.service.VideosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videos")
@Slf4j
@Tag(name = "Update Video")
public class UpdateVideosController {

    @Autowired
    private VideosService service;

    @Operation(
            summary = "Update Video",
            description = "Will update video using the provided ID"
    )
    @Parameter(
            name = "id",
            description = "Should receive the Video ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Should return video updated"
    )
    @PutMapping("{id}")
    public ResponseEntity<Videos> update(@PathVariable Long id, @RequestBody CreateOrUpdateVideosDTO dto) {
        log.info("Updating video with ID: {}", id);
        return ResponseEntity.ok(this.service.updateVideo(id, dto));
    }

}
