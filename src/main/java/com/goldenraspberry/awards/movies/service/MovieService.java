package com.goldenraspberry.awards.movies.service;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.model.Producer;
import com.goldenraspberry.awards.movies.model.dto.PrizeDTO;
import com.goldenraspberry.awards.movies.model.dto.WinnerDTO;
import com.goldenraspberry.awards.movies.model.util.CsvMovieReader;
import com.goldenraspberry.awards.movies.repository.MovieRepository;
import com.goldenraspberry.awards.movies.repository.ProducerRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

        //TODO ver com Hallan se tem forma mais otimizada de evitar os registros de produtor duplicados
        for (Movie movie: movies) {
            List<Producer> producers = movie.getProducers();
            for(int i = 0; i < producers.size(); i++) {
                Optional<Producer> savedProducer = producerRepository.findByName(producers.get(i).getName());

                if(savedProducer.isPresent()){
                    producers.set(i, savedProducer.get());
                }else{
                    this.producerRepository.save(producers.get(i));
                }
            }
            this.movieRepository.save(movie);
        }

        //this.movieRepository.saveAll(movies);
        LOGGER.infof("%d movies saved on database", movies.size());
    }

    public PrizeDTO getMinAndMaxPrizeProducers() {

        PrizeDTO prizeDTO = new PrizeDTO();

        List<WinnerDTO> maxWinners = WinnerDTO.fromTuple(this.movieRepository.getMaxWinners());
        prizeDTO.setMax(maxWinners);

        List<WinnerDTO> minWinners = WinnerDTO.fromTuple(this.movieRepository.getMinWinners());
        prizeDTO.setMin(minWinners);

        return prizeDTO;
    }
}
