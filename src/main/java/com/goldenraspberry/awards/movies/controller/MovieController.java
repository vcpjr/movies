package com.goldenraspberry.awards.movies.controller;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.model.dto.PrizeDTO;
import com.goldenraspberry.awards.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "minMaxWinners")
    public PrizeDTO getMinAndMaxPrizeProducers(){
        return movieService.getMinAndMaxPrizeProducers();
    }

    @GetMapping
    public List<Movie> getMovies(){
        return movieService.getMovies();
    }

    @PostMapping
    public Movie insert(@RequestBody Movie movie){
        return movieService.insert(movie);
    }

    @PutMapping
    public void update(@RequestBody Movie movie){
        movieService.update(movie);
    }

    @DeleteMapping(path = "/{movieId}")
    public void delete(@PathVariable("movieId") Integer id){
        movieService.delete(id);
    }

    @GetMapping(path = "/{movieId}")
    public Movie findMovieByID(@PathVariable("movieId") Integer id){
        return movieService.findMovieById(id);
    }
}