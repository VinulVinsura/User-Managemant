package com.example.usermanagement.controller;

import com.example.usermanagement.dto.MovieDto;
import com.example.usermanagement.dto.ResponseDto;
import com.example.usermanagement.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/movie")
@RequiredArgsConstructor
@CrossOrigin("*")
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

    @PostMapping("/add-movie")
    public ResponseEntity<ResponseDto> addMovie(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.addMovie(movieDto));
    }

    @DeleteMapping("/delete/{imdb}")
    public ResponseEntity<ResponseDto> deleteMovie(@PathVariable String imdb){
        return ResponseEntity.ok(movieService.deleteMovie(imdb));
    }
}
