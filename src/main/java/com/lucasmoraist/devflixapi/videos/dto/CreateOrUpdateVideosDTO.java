package com.lucasmoraist.devflixapi.videos.dto;

import com.lucasmoraist.devflixapi.category.domain.Category;

public record CreateOrUpdateVideosDTO(String title, String description, String url, Long idCategory) {
}
