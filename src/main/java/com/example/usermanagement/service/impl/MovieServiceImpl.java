package com.example.usermanagement.service.impl;

import com.example.usermanagement.Exception.AuthenticationException;
import com.example.usermanagement.Exception.BadRequestException;
import com.example.usermanagement.dto.MovieDto;
import com.example.usermanagement.dto.ResponseDto;
import com.example.usermanagement.service.MovieService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {
    private final RestTemplate restTemplate;
    @Override
    public ResponseDto getAllMovies() {
        try {
            String url="http://localhost:8080/api/v1/movies";
            ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
            if (response.getBody()==null){
                return new ResponseDto("02","No Movies Found",null);
            }
            return new ResponseDto("00","Success",response.getBody());

        }catch (Exception ex){
            throw new BadRequestException();
        }

    }

    @Override
    public ResponseDto getMovieByImdb(String imdb) {
        try {
            String url="http://localhost:8080/api/v1/movies/"+imdb;
            ResponseEntity<MovieDto> response = restTemplate.getForEntity(url, MovieDto.class);
            if (response.getBody()==null){
                return new ResponseDto("02","No Such Movie Found",null);
            }
            return new ResponseDto("00","Success",response.getBody());


        }catch (Exception ex){
            throw new BadRequestException();
        }

    }

    @Override
    public ResponseDto addMovie(MovieDto movieDto) {
        try {
            String url="http://localhost:8080/api/v1/movies";
            HttpEntity<MovieDto> movieDtoHttpEntity=new HttpEntity<>(movieDto);
            ResponseEntity<MovieDto> response = restTemplate.postForEntity(url, movieDtoHttpEntity, MovieDto.class);
            if (response.getBody()==null){
                return new ResponseDto("04","Movie Already Exists",null);
            }
            return new ResponseDto("00","Success",null);
        }catch (BadRequestException ex){
            throw new BadRequestException();
        }
    }

    @Override
    public ResponseDto deleteMovie(String imdb) {
        try {

            String url="http://localhost:8080/api/v1/movies/{imdb}";
            ResponseEntity<ResponseDto> response = restTemplate.exchange(url, HttpMethod.DELETE, null, ResponseDto.class, imdb);
            return response.getBody();
        }catch (Exception ex){
            log.error(imdb);
           throw new BadRequestException();
        }
    }

}
