package com.lucasmoraist.devideosapi.category.controller;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.dto.CreateOrUpdateCategoriesDTO;
import com.lucasmoraist.devideosapi.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@Slf4j
@Tag(name = "Update Category")
public class UpdateCategoryController {

    @Autowired
    private CategoryService service;

    @Operation(
            summary = "Update Category",
            description = "Will update category using the provided ID"
    )
    @Parameter(
            name = "id",
            description = "Should receive the Category ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Should return category updated"
    )
    @PutMapping("{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CreateOrUpdateCategoriesDTO dto) {
        log.info("Updating category with ID: {}", id);
        return ResponseEntity.ok().body(this.service.updateCategory(id, dto));
    }

}
