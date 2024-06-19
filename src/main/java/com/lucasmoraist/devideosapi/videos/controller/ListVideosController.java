package com.lucasmoraist.devideosapi.videos.controller;

import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.service.VideosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@Slf4j
@Tag(name = "List Videos")
public class ListVideosController {

    @Autowired
    private VideosService service;

    @Operation(
            summary = "List Videos",
            description = "Return a list of all videos"
    )
    @ApiResponse(
            responseCode = "200",
            description = " Should list of all videos and your respective categories"
    )
    @GetMapping("list")
    public ResponseEntity<List<Videos>> listAll() {
        log.info("Listing all videos");
        return ResponseEntity.ok().body(this.service.listAll());
    }

    @Operation(
            summary = "List a video by ID",
            description = "Should return specific video when ID is informed"
    )
    @Parameter(
            name = "id",
            description = "Should receive ID of the Video"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Return specific Video"
    )
    @GetMapping("{id}")
    public ResponseEntity<Videos> listById(@PathVariable Long id) {
        log.info("Listing category with id: {}", id);
        return ResponseEntity.ok().body(this.service.listById(id));
    }

    @Operation(
            summary = "List a video by ID",
            description = "Should return specific video when title is informed"
    )
    @Parameter(
            name = "search",
            description = "Should return specific video when title is informed"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Return specific Video"
    )
    @GetMapping
    public ResponseEntity<List<Videos>> listVideosByTitle(@RequestParam(name = "search") String search) {
        log.info("Listing video by title: {}", search);
        return ResponseEntity.ok().body(this.service.listVideosByTitle(search));
    }

}
