package com.goldenraspberry.awards.movies.repository;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.model.dto.WinnerDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT m FROM Movie m, Producer p WHERE p.name in (?1)")
    Optional<Movie> findMovieByProducer(Object[] names);

    //@Query("SELECT m FROM Movie m WHERE m.winner = true")
    Optional<Movie> findMoviesByWinnerEquals(Boolean winner);


    @Query("select new com.goldenraspberry.awards.movies.model.dto.WinnerDTO( " +
            "p.name, (max(m.releaseYear) - min(m.releaseYear)), min(m.releaseYear), max(m.releaseYear) " +
            ")" +
            "from Movie m join m.producers p " +
            "where m.winner = true " +
            "group by p.name " +
            "having (max(m.releaseYear) - min(m.releaseYear)) > 0 " +
            "order by (max(m.releaseYear) - min(m.releaseYear)) desc, p.name ")
    List<WinnerDTO> getMaxWinners(Pageable pageable);

    @Query("select new com.goldenraspberry.awards.movies.model.dto.WinnerDTO( " +
            "p.name, (max(m.releaseYear) - min(m.releaseYear)), min(m.releaseYear), max(m.releaseYear) " +
            ")" +
            "from Movie m join m.producers p " +
            "where m.winner = true " +
            "group by p.name " +
            "having (max(m.releaseYear) - min(m.releaseYear)) > 0 " +
            "order by (max(m.releaseYear) - min(m.releaseYear)) asc, p.name ")
    List<WinnerDTO> getMinWinners(Pageable pageable);

}
