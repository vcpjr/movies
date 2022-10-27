package com.goldenraspberry.awards.movies.config;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MovieConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository repository){
        return args -> {
            Movie blackAdam = new Movie(
                    1,
                    2022,
                    "Black Adam",
                    "WB",
                    "Dwayne Johnson",
                    true
            );

            Movie shazam = new Movie(
                    1,
                    2020,
                    "Shazam",
                    "WB",
                    "Zachary Levi",
                    false
            );

            repository.saveAll(List.of(blackAdam, shazam));
        };
    }
}
