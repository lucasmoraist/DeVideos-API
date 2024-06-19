package com.lucasmoraist.devideosapi.category.controller;

import com.lucasmoraist.devideosapi.category.service.CategoryService;
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
@RequestMapping("/api/category")
@Slf4j
@Tag(name = "Delete Category")
public class DeleteCategoryController {

    @Autowired
    private CategoryService service;

    @Operation(
            summary = "Delete Category",
            description = "This method will receive the Category ID and delete the category"
    )
    @Parameter(
            name = "id",
            description = "Should receive the Category ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Should return a phrase saying 'Excluído com sucesso!'"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("Deleting category with ID: {}", id);
        return ResponseEntity.ok().body(this.service.deleteCategory(id));
    }

}
