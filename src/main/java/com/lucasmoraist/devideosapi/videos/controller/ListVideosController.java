package com.lucasmoraist.devideosapi.videos.controller;

import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.service.VideosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        return ResponseEntity.ok().body(this.service.findAllVideos());
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
        return ResponseEntity.ok().body(this.service.findVideoById(id));
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
    public ResponseEntity<Page<List<Videos>>> listVideosByTitle(@RequestParam(name = "search") String search, @PageableDefault(size = 5) Pageable pageable) {
        log.info("Listing video by title: {}", search);
        return ResponseEntity.ok().body(this.service.findVideosByTitle(search, pageable));
    }

    @GetMapping("/free")
    public ResponseEntity<List<Videos>> listVideosFree(){
        return ResponseEntity.ok().body(this.service.findVideosFree());
    }

}
