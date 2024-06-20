package com.lucasmoraist.devideosapi.videos.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lucasmoraist.devideosapi.category.domain.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "t_videos")
@Table(name = "t_videos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Videos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 90)
    private String title;
    @Column(nullable = false, length = 200)
    private String description;
    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Category category;

    @Override
    public String toString() {
        return "Videos{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", category=" + category +
                '}';
    }
}
