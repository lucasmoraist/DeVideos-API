package com.lucasmoraist.devideosapi.category.service;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.dto.CreateOrUpdateCategoriesDTO;
import com.lucasmoraist.devideosapi.category.repository.CategoryRepository;
import com.lucasmoraist.devideosapi.category.repository.ICategoryRepository;
import com.lucasmoraist.devideosapi.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryRepository {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> listAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category listCategoryById(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Category Not Found"));
    }

    @Override
    public Category createCategory(CreateOrUpdateCategoriesDTO dto) {
        Category newCategory = Category.builder()
                .title(dto.title())
                .color(dto.color())
                .build();
        this.categoryRepository.save(newCategory);
        return newCategory;
    }

    @Override
    public Category updateCategory(Long id, CreateOrUpdateCategoriesDTO dto) {
        Category category = this.listCategoryById(id);

        category.setTitle(dto.title());
        category.setColor(dto.color());

        this.categoryRepository.save(category);
        return category;
    }

    @Override
    public String deleteCategory(Long id) {
        if (id == 1L) {
            return "Não é possível excluir essa categoria!";
        } else {
            Category category = this.listCategoryById(id);
            this.categoryRepository.delete(category);
            return "Excluído com sucesso!";
        }
    }
}
