package com.lucasmoraist.devideosapi.category.controller;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.dto.CreateOrUpdateCategoriesDTO;
import com.lucasmoraist.devideosapi.category.service.CategoryService;
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
@RequestMapping("/api/category")
@Slf4j
@Tag(name = "Create Category")
public class CreateCategoryController {

    @Autowired
    private CategoryService service;

    @Operation(
            summary = "Create new category",
            description = "Should category a new instance of the 'Category' object and insert it into the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Should return information from the created category"
    )
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CreateOrUpdateCategoriesDTO dto) {
        Category category = this.service.createCategory(dto);
        log.info("Creating category: {}", category);
        return ResponseEntity.ok().body(category);
    }

}
