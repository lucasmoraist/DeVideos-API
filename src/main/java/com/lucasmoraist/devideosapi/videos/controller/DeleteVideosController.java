package com.lucasmoraist.devideosapi.videos.controller;

import com.lucasmoraist.devideosapi.videos.service.VideosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videos")
@Slf4j
@Tag(name = "Delete Video")
public class DeleteVideosController {

    @Autowired
    private VideosService service;

    @Operation(
            summary = "Delete Video",
            description = "This method will receive the Video ID and delete the video"
    )
    @Parameter(
            name = "id",
            description = "Should receive the Video ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Should return a phrase saying 'Excluído com sucesso!'"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("Deleting video with ID: {}", id);
        return ResponseEntity.ok(this.service.deleteVideos(id));
    }

}
