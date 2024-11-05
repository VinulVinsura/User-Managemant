package com.example.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private Long id;
    private String imdb;
    private String title;
    private String description;
    private double rating;
    private String category;
    private int year;
    private String imageUrl;
}
