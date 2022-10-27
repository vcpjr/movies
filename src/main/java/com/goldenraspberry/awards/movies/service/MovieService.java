package com.goldenraspberry.awards.movies.service;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService{

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public void insert(Movie movie) {
        Optional<Movie> movieByProducer = movieRepository.findMovieByProducer(movie.getProducer());

        //Teste de validação
        if(movieByProducer.isPresent()){
            throw new IllegalStateException("Produtor já utilizado");
        }

        this.movieRepository.save(movie);
    }

    public void update(Movie movie) {
        //TODO não existe update/merge?
        this.movieRepository.save(movie);
    }

    public void delete(Integer id) {

        if(!this.movieRepository.existsById(id)) {
            throw new IllegalStateException("Id de filme informado (" + id + ") " + "não existe");
        }

        this.movieRepository.deleteById(id);
    }
}
