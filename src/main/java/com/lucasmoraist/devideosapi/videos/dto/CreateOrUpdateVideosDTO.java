package com.lucasmoraist.devideosapi.videos.dto;

public record CreateOrUpdateVideosDTO(String title, String description, String url, Long idCategory) {
}
