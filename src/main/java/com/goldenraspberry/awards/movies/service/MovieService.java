package com.goldenraspberry.awards.movies.service;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.model.dto.PrizeDTO;
import com.goldenraspberry.awards.movies.model.util.CsvMovieReader;
import com.goldenraspberry.awards.movies.repository.MovieRepository;
import com.goldenraspberry.awards.movies.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService{

    private final MovieRepository movieRepository;
    private final ProducerRepository producerRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ProducerRepository producerRepository) {
        this.movieRepository = movieRepository;
        this.producerRepository = producerRepository;
    }

    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public void insert(Movie movie) {
        //TODO incluir validação
//        Optional<Movie> movieByProducer = movieRepository.findMovieByProducer(movie.getProducers().toArray());
//
//        //Teste de validação
//        if(movieByProducer.isPresent()){
//            throw new IllegalStateException("Produtor já utilizado");
//        }

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

    public void upload(MultipartFile file) throws IOException {
        CsvMovieReader importador = new CsvMovieReader(movieRepository, producerRepository);
        importador.importar(file.getInputStream());
    }

    public PrizeDTO getMinAndMaxPrizeProducers() {

        PrizeDTO prizeDTO = new PrizeDTO();
        Optional<Movie> movies = this.movieRepository.findMoviesByWinnerEquals(true);


        //Como iterar sobre isso?

        //min: produtor com menor intervalo entre prêmios
//        WinnerDTO min = new WinnerDTO();
//        min.setProducer("");
//        min.setPreviousWin();
//        min.getFollowingWin();
//        min.setInterval();
//        prizeDTO.setMin(min);

        //max: produtor com menor intervalo entre prêmios

        return prizeDTO;
    }
}
