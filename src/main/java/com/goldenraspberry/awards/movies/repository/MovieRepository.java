package com.goldenraspberry.awards.movies.repository;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.model.dto.WinnerDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT m FROM Movie m, Producer p WHERE p.name in (?1)")
    Optional<Movie> findMovieByProducer(Object[] names);

    Optional<Movie> findMoviesByWinnerEquals(Boolean winner);

    @Query(value = "select t.name as producer, t.max_year as followingWin, t.min_year as previousWin, t.diferenza as interval " +
            "   from( " +
            "       select p.name as name, max(m.release_year) as max_year, min(m.release_year) as min_year, " +
            "       (max(m.release_year) - min(m.release_year)) as diferenza," +
            "        RANK() OVER ( " +
            "        ORDER BY (max(m.release_year) - min(m.release_year)) desc " +
            "    ) ordem " +
            "   from movie_producer mp " +
            "join movie m on mp.id_movie = m.id " +
            "join producer p on mp.id_producer = p.id " +
            "where m.winner = true " +
            "group by p.name " +
            "having (max(m.release_year) - min(m.release_year)) > 0 " +
            "order by diferenza desc " +
            ") as t " +
            "where t.ordem = 1", nativeQuery = true)
    List<Tuple> getMaxWinners();

    @Query(value = "select t.name as producer, t.max_year as followingWin, t.min_year as previousWin, t.diferenza as interval " +
            "   from( " +
            "       select p.name as name, max(m.release_year) as max_year, min(m.release_year) as min_year, " +
            "       (max(m.release_year) - min(m.release_year)) as diferenza," +
            "        RANK() OVER ( " +
            "        ORDER BY (max(m.release_year) - min(m.release_year)) asc " +
            "    ) ordem " +
            "   from movie_producer mp " +
            "join movie m on mp.id_movie = m.id " +
            "join producer p on mp.id_producer = p.id " +
            "where m.winner = true " +
            "group by p.name " +
            "having (max(m.release_year) - min(m.release_year)) > 0 " +
            "order by diferenza desc " +
            ") as t " +
            "where t.ordem = 1", nativeQuery = true)
    List<Tuple> getMinWinners();
}
