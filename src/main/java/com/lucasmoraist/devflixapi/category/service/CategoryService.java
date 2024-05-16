package com.lucasmoraist.devflixapi.category.service;

import com.lucasmoraist.devflixapi.category.domain.Category;
import com.lucasmoraist.devflixapi.category.dto.CreateOrUpdateCategoriesDTO;
import com.lucasmoraist.devflixapi.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAll(){
        return this.categoryRepository.findAll();
    }

    public Category listById(Long id){
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));
    }

    public Category createCategory(CreateOrUpdateCategoriesDTO dto){
        Category newCategory = Category.builder()
                .title(dto.title())
                .color(dto.color())
                .build();
        this.categoryRepository.save(newCategory);
        return newCategory;
    }

    public Category updateCategory(Long id, CreateOrUpdateCategoriesDTO dto) throws Exception{
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new Exception("Category Not Found");
        }

        var category = optionalCategory.get();
        category.setTitle(dto.title());
        category.setColor(dto.color());

        this.categoryRepository.save(category);
        return category;
    }

    public String deleteCategory(Long id){
        if(id == 1L){
            return "Não é possível excluir essa categoria!";
        }else{
            Category category = this.categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category Not Found"));
            this.categoryRepository.delete(category);
            return "Excluído com sucesso!";
        }
    }

}
