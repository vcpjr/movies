package com.goldenraspberry.awards.movies.service;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.model.Producer;
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
public class ProducerService {

    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public List<Producer> getAllProducers(){
        return producerRepository.findAll();
    }

    public void insert(Producer producer) {
        Optional<Producer> producerByName = producerRepository.findByName(producer.getName());

        if(producerByName.isPresent()){
            throw new IllegalStateException("Produtor já existe");
        }

        this.producerRepository.save(producer);
    }

    public void update(Producer producer) {
        this.producerRepository.save(producer);
    }

    public void delete(Integer id) {
        if(!this.producerRepository.existsById(id)) {
            throw new IllegalStateException("Id de produtor informado (" + id + ") " + "não existe");
        }

        //TODO verificar se produtor está em algum filme

        this.producerRepository.deleteById(id);
    }
}
