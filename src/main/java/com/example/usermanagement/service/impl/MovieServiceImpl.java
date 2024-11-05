package com.example.usermanagement.service.impl;

import com.example.usermanagement.Exception.AuthenticationException;
import com.example.usermanagement.Exception.BadRequestException;
import com.example.usermanagement.dto.MovieDto;
import com.example.usermanagement.dto.ResponseDto;
import com.example.usermanagement.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

}
