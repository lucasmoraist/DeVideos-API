package com.lucasmoraist.devideosapi.category.controller;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.dto.CreateOrUpdateCategoriesDTO;
import com.lucasmoraist.devideosapi.category.service.CategoryService;
import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.service.VideosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private VideosService videosService;

    @GetMapping("/{idCategory}/videos")
    public ResponseEntity<List<Videos>> findVideosByCategory(@PathVariable Long idCategory) {
        List<Videos> videos = this.videosService.listVideosByIdCategory(idCategory);
        log.info("Listing videos by category: {}", videos);
        return ResponseEntity.ok().body(videos);
    }

    @GetMapping
    public ResponseEntity<List<Category>> listAll() {
        log.info("Listing all categories");
        return ResponseEntity.ok().body(this.service.listAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> listById(@PathVariable Long id) {
        log.info("Listing category with id: {}", id);
        return ResponseEntity.ok().body(this.service.listCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CreateOrUpdateCategoriesDTO dto) {
        Category category = this.service.createCategory(dto);
        log.info("Creating category: {}", category);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CreateOrUpdateCategoriesDTO dto) {
        log.info("Updating category with ID: {}", id);
        return ResponseEntity.ok().body(this.service.updateCategory(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("Deleting category with ID: {}", id);
        return ResponseEntity.ok().body(this.service.deleteCategory(id));
    }

}
