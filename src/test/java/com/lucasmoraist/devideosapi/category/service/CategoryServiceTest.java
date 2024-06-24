package com.lucasmoraist.devideosapi.category.service;

import com.lucasmoraist.devideosapi.category.domain.Category;
import com.lucasmoraist.devideosapi.category.domain.ColorsEnum;
import com.lucasmoraist.devideosapi.category.dto.CreateOrUpdateCategoriesDTO;
import com.lucasmoraist.devideosapi.category.repository.CategoryRepository;
import com.lucasmoraist.devideosapi.exception.CategoryDefaultException;
import com.lucasmoraist.devideosapi.exception.ResourceNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("List All Categories Test")
    void case01() {
        Category category1 = new Category();
        Category category2 = new Category();
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryService.listAllCategories();

        assertEquals(2, categories.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("List Category By Id Test")
    void case02() {
        Category category = new Category();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category result = categoryService.listCategoryById(1L);

        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("List Category By Id Not Found Test")
    void case03() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> categoryService.listCategoryById(1L));
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Create Category Test")
    void case04() {
        CreateOrUpdateCategoriesDTO dto = new CreateOrUpdateCategoriesDTO("title", ColorsEnum.CINZA);

        Category category = new Category();
        category.setTitle(dto.title());
        category.setColor(dto.color());

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.createCategory(dto);

        assertEquals(category, result);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    @DisplayName("Update Category Test")
    void case05() {
        CreateOrUpdateCategoriesDTO dto = new CreateOrUpdateCategoriesDTO("title", ColorsEnum.CINZA);

        Category category = new Category();
        category.setTitle("old title");
        category.setColor(ColorsEnum.AZUL);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.updateCategory(1L, dto);

        assertEquals(dto.title(), result.getTitle());
        assertEquals(dto.color(), result.getColor());
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    @DisplayName("Delete Category Test")
    void case06() {
        Category category = new Category();
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));

        String result = categoryService.deleteCategory(2L);

        assertEquals("Excluído com sucesso!", result);
        verify(categoryRepository, times(1)).findById(2L);
        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    @DisplayName("Delete Category Default Exception Test")
    void case07() {
        assertThrows(CategoryDefaultException.class, () -> categoryService.deleteCategory(1L));
    }
}