package com.goldenraspberry.awards.movies.repository;

import com.goldenraspberry.awards.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT m FROM Movie m WHERE m.producer = ?1")
    Optional<Movie> findMovieByProducer(String producer);
}
