package com.goldenraspberry.awards.movies.controller;

import com.goldenraspberry.awards.movies.model.Producer;
import com.goldenraspberry.awards.movies.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producers")
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public List<Producer> getAllProducers(){
        return producerService.getAllProducers();
    }

    @PostMapping
    public void insert(@RequestBody Producer producer){
        producerService.insert(producer);
    }

    @PutMapping
    public void update(@RequestBody Producer producer){
        producerService.update(producer);
    }

    @DeleteMapping(path = "{producerId}")
    public void delete(@PathVariable("producerId") Integer id){
        producerService.delete(id);
    }
}