package com.lucasmoraist.devflixapi.category.repository;

import com.lucasmoraist.devflixapi.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
