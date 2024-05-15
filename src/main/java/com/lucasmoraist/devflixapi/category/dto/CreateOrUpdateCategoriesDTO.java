package com.lucasmoraist.devflixapi.category.dto;

import com.lucasmoraist.devflixapi.category.domain.ColorsEnum;

public record CreateOrUpdateCategoriesDTO(String title, ColorsEnum color) {
}
