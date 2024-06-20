package com.lucasmoraist.devideosapi.category.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lucasmoraist.devideosapi.videos.domain.Videos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name = "t_categories")
@Table(name = "t_categories")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String title;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ColorsEnum color;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Videos> videos;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", color=" + color +
                ", videos=" + videos +
                '}';
    }
}
