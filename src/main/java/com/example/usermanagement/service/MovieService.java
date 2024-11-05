package com.example.usermanagement.service;

import com.example.usermanagement.dto.MovieDto;
import com.example.usermanagement.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    ResponseDto getAllMovies();
    ResponseDto getMovieByImdb(String imdb);
    ResponseDto addMovie(MovieDto movieDto);
}
