package com.goldenraspberry.awards.movies.service;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.model.dto.PrizeDTO;
import com.goldenraspberry.awards.movies.model.dto.WinnerDTO;
import com.goldenraspberry.awards.movies.model.util.CsvMovieReader;
import com.goldenraspberry.awards.movies.repository.MovieRepository;
import com.goldenraspberry.awards.movies.repository.ProducerRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService{

    private static final Logger LOGGER = Logger.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private CsvMovieReader csvMovieReader;

    public Movie findMovieById(Integer id){
        return movieRepository.findById(id).get();
    }

    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public Movie insert(Movie movie) {
        //TODO incluir validação
//        Optional<Movie> movieByProducer = movieRepository.findMovieByProducer(movie.getProducers().toArray());
//
//        //Teste de validação
//        if(movieByProducer.isPresent()){
//            throw new IllegalStateException("Produtor já utilizado");
//        }

        return this.movieRepository.save(movie);
    }

    public void update(Movie movie) {
        this.movieRepository.save(movie);
    }

    public void delete(Integer id) {

        if(!this.movieRepository.existsById(id)) {
            throw new IllegalStateException(String.format("Movie with ID %d not found", id));
        }

        this.movieRepository.deleteById(id);
    }

    public void importCsv(InputStream is){
        List<Movie> movies = this.csvMovieReader.readCsv(is);
        this.movieRepository.saveAll(movies);
        LOGGER.infof("%d movies saved on database", movies.size());
    }

    public PrizeDTO getMinAndMaxPrizeProducers() {

        PrizeDTO prizeDTO = new PrizeDTO();

        List<WinnerDTO> maxWinners = this.movieRepository.getMaxWinners( Pageable.ofSize(2) );
        prizeDTO.setMax(maxWinners);

        List<WinnerDTO> minWinners = this.movieRepository.getMinWinners( Pageable.ofSize(2) );
        prizeDTO.setMin(minWinners);

        return prizeDTO;
    }
}
