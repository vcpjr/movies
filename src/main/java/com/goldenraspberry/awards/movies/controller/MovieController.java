package com.goldenraspberry.awards.movies.controller;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getMovies(){
        return movieService.getMovies();
    }

    @PostMapping
    public void insert(@RequestBody Movie movie){
        movieService.insert(movie);
    }

    @PutMapping
    public void update(@RequestBody Movie movie){
        movieService.update(movie);
    }

    @DeleteMapping(path = "{movieId}")
    public void delete(@PathVariable("movieId") Integer id){
        movieService.delete(id);
    }
}