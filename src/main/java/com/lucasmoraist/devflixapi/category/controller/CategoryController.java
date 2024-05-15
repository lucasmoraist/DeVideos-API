package com.lucasmoraist.devflixapi.category.controller;

import com.lucasmoraist.devflixapi.category.domain.Category;
import com.lucasmoraist.devflixapi.category.dto.CreateOrUpdateCategoriesDTO;
import com.lucasmoraist.devflixapi.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> listAll(){
        return ResponseEntity.ok(this.service.listAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> listById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.listById(id));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CreateOrUpdateCategoriesDTO dto){
        return ResponseEntity.ok(this.service.createCategory(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CreateOrUpdateCategoriesDTO dto) throws Exception{
        return ResponseEntity.ok(this.service.updateCategory(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.service.deleteCategory(id));
    }

}
