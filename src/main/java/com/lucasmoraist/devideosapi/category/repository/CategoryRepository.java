package com.lucasmoraist.devideosapi.category.repository;

import com.lucasmoraist.devideosapi.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
