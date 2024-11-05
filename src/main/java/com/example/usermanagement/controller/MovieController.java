package com.example.usermanagement.controller;

import com.example.usermanagement.dto.ResponseDto;
import com.example.usermanagement.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/get-all-movie")
    public ResponseEntity<ResponseDto> getAllMovies(){
       return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/get-movie/{imdb}")
    public ResponseEntity<ResponseDto> getMovieByImdb(@PathVariable String imdb){
        return ResponseEntity.ok(movieService.getMovieByImdb(imdb));

    }
}
