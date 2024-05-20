package com.lucasmoraist.devideosapi.category.dto;

import com.lucasmoraist.devideosapi.category.domain.ColorsEnum;

public record CreateOrUpdateCategoriesDTO(String title, ColorsEnum color) {
}
