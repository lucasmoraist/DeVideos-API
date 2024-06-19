package com.lucasmoraist.devideosapi.category.service;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.dto.CreateOrUpdateCategoriesDTO;
import com.lucasmoraist.devideosapi.category.repository.CategoryRepository;
import com.lucasmoraist.devideosapi.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAll() {
        return this.categoryRepository.findAll();
    }

    public Category listCategoryById(Long id) {
        return this.findCategoryById(id);
    }

    public Category createCategory(CreateOrUpdateCategoriesDTO dto) {
        Category newCategory = Category.builder()
                .title(dto.title())
                .color(dto.color())
                .build();
        this.categoryRepository.save(newCategory);
        return newCategory;
    }

    public Category updateCategory(Long id, CreateOrUpdateCategoriesDTO dto) {

        Category category = this.findCategoryById(id);

        category.setTitle(dto.title());
        category.setColor(dto.color());

        this.categoryRepository.save(category);
        return category;
    }

    public String deleteCategory(Long id) {
        if (id == 1L) {
            return "Não é possível excluir essa categoria!";
        } else {
            Category category = this.findCategoryById(id);
            this.categoryRepository.delete(category);
            return "Excluído com sucesso!";
        }
    }

    private Category findCategoryById(Long id){
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Category Not Found"));
    }
}
