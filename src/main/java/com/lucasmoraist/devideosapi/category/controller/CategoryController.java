package com.lucasmoraist.devideosapi.category.controller;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.dto.CreateOrUpdateCategoriesDTO;
import com.lucasmoraist.devideosapi.category.service.CategoryService;
import com.lucasmoraist.devideosapi.videos.domain.Videos;
import com.lucasmoraist.devideosapi.videos.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private VideosService videosService;

    @GetMapping("{idCategory}/videos")
    public ResponseEntity<List<Videos>> findVideosByCategory(@PathVariable Long idCategory){
        return ResponseEntity.ok(this.videosService.listByIdCategory(idCategory));
    }

    @GetMapping
    public ResponseEntity<List<Category>> listAll(){
        return ResponseEntity.ok(this.service.listAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> listById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.listById(id));
    }

    @PostMapping("create")
    public ResponseEntity<Category> create(@RequestBody CreateOrUpdateCategoriesDTO dto){
        return ResponseEntity.ok(this.service.createCategory(dto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CreateOrUpdateCategoriesDTO dto) throws Exception{
        return ResponseEntity.ok(this.service.updateCategory(id, dto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.service.deleteCategory(id));
    }

}
