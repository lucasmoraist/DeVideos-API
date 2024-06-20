package com.lucasmoraist.devideosapi.category.controller;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.service.CategoryService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Slf4j
@Tag(name = "List Category")
public class ListCategoryController {

    @Autowired
    private CategoryService service;
    @Autowired
    private VideosService videosService;

    @Operation(
            summary = "List Categories",
            description = "Return a list of all categories"
    )
    @ApiResponse(
            responseCode = "200",
            description = " Should list of all categories"
    )
    @GetMapping
    public ResponseEntity<List<Category>> listAll() {
        log.info("Listing all categories");
        return ResponseEntity.ok().body(this.service.listAllCategories());
    }

    @Operation(
            summary = "List video by category ID",
            description = "List video when category ID is provided"
    )
    @Parameter(
            name = "idCategory",
            description = "Should receive the category ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Should return video in category"
    )
    @GetMapping("/{idCategory}/videos")
    public ResponseEntity<Page<List<Videos>>> findVideosByCategory(@PathVariable Long idCategory, @PageableDefault(size = 5) Pageable pageable) {
        Page<List<Videos>> videos = this.videosService.findVideoByIdCategory(idCategory, pageable);
        log.info("Listing videos by category: {}", videos);
        return ResponseEntity.ok().body(videos);
    }

    @Operation(
            summary = "List a category by ID",
            description = "Should return specific video when ID is informed"
    )
    @Parameter(
            name = "id",
            description = "Should receive ID of the Category"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Return specific Category"
    )
    @GetMapping("{id}")
    public ResponseEntity<Category> listById(@PathVariable Long id) {
        log.info("Listing category with id: {}", id);
        return ResponseEntity.ok().body(this.service.listCategoryById(id));
    }

}
