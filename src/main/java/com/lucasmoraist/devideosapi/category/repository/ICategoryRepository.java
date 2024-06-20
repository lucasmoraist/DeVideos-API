package com.lucasmoraist.devideosapi.category.repository;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.dto.CreateOrUpdateCategoriesDTO;

import java.util.List;

public interface ICategoryRepository {

    List<Category> listAllCategories();
    Category listCategoryById(Long id);
    Category createCategory(CreateOrUpdateCategoriesDTO dto);
    Category updateCategory(Long id, CreateOrUpdateCategoriesDTO dto);
    String deleteCategory(Long id);

}
