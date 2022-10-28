package com.goldenraspberry.awards.movies.repository;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {

    public Producer findByName(String name);
}
