package com.goldenraspberry.awards.movies.model.util;

import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.model.Producer;
import com.goldenraspberry.awards.movies.repository.MovieRepository;
import com.goldenraspberry.awards.movies.repository.ProducerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.util.List;

@Service
public class CsvMovieReader {

	private final String YES = "yes";
	private final MovieRepository movieRepository;
	private final ProducerRepository producerRepository;

	public CsvMovieReader(MovieRepository movieRepository, ProducerRepository producerRepository) {
		this.movieRepository = movieRepository;
		this.producerRepository = producerRepository;
	}

	public void importar(InputStream fis) {
		try (InputStreamReader isr = new InputStreamReader(fis);
			  BufferedReader br = new BufferedReader(isr)){
			//Pula a primeira linha
			br.readLine();

			while(br.ready()){
				String linha = br.readLine();

				if(!linha.trim().isEmpty()){
					Movie movie = Movie.fromCSV(linha);

					//TODO verificar antes se o produtor já existe?

					//Isso era usado antes do salvamento em cascata
//					List<Producer> producers = movie.getProducers();
//					for (Producer producer: producers) {
//						if(producerRepository.findByName(producer.getName()) == null){
//							producerRepository.save(producer);
//						}
//					}

					this.movieRepository.save(movie);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}